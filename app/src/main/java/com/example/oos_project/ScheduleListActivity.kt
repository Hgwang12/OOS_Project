package com.example.oos_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oos_project.ui.theme.OOS_ProjectTheme

// 여행 일정 데이터를 담는 클래스
class TravelSchedule(val time: String, val title: String, val place: String) {
    // 시간 가져오기
    fun fetchTime(): String {
        return time
    }

    // 제목 가져오기
    fun fetchTitle(): String {
        return title
    }

    // 장소 가져오기
    fun fetchPlace(): String {
        return place
    }
}

class ScheduleListActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OOS_ProjectTheme {
                // 일정 데이터들 만들기
                val schedule1 = TravelSchedule("09:00", "호텔 체크인", "신주쿠 호텔 도착")
                val schedule2 = TravelSchedule("11:30", "센소지 사원 방문", "아사쿠사 관광")

                // 배경색 변수로 빼기
                val topBarColor = Color(0xFFE3F2FD)
                val backgroundColor = Color(0xFFEFF8FF)
                val cardColor = Color(0xFFF5F5F5)
                val accentColor = Color(0xFF2196F3)
                val timeCardColor = Color(0xFFE3F2FD)

                // Scaffold로 화면 구조 잡기
                Scaffold(
                        topBar = {
                            TopAppBar(
                                    colors =
                                            TopAppBarDefaults.topAppBarColors(
                                                    containerColor = topBarColor
                                            ),
                                    title = {
                                        Column {
                                            Text("도쿄 여행 일정", fontWeight = FontWeight.Bold)
                                            Text(
                                                    "Tokyo Travel Plan",
                                                    fontSize = 12.sp,
                                                    lineHeight = 12.sp
                                            )
                                        }
                                    },
                                    navigationIcon = {
                                        IconButton(
                                                onClick = {
                                                    val intent =
                                                            android.content.Intent(
                                                                    this@ScheduleListActivity,
                                                                    TestScreenActivity::class.java
                                                            )
                                                    startActivity(intent)
                                                    finish()
                                                }
                                        ) {
                                            Icon(
                                                    imageVector =
                                                            Icons.AutoMirrored.Filled.ArrowBack,
                                                    contentDescription = "뒤로가기"
                                            )
                                        }
                                    }
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = {}, containerColor = accentColor) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "추가")
                            }
                        }
                ) { paddingValues ->
                    Column(
                            modifier =
                                    Modifier.fillMaxSize()
                                            .background(backgroundColor)
                                            .padding(paddingValues)
                                            .padding(horizontal = 16.dp)
                    ) {
                        // 첫번째 일정 카드
                        Card(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = cardColor),
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                        ) {
                            Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(
                                        modifier = Modifier.padding(end = 16.dp),
                                        colors =
                                                CardDefaults.cardColors(
                                                        containerColor = timeCardColor
                                                )
                                ) {
                                    Row(
                                            modifier = Modifier.padding(8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                                imageVector = Icons.Filled.Info,
                                                contentDescription = "시계",
                                                tint = accentColor,
                                                modifier = Modifier.padding(end = 8.dp)
                                        )
                                        // 객체에서 시간 가져오기
                                        Text(schedule1.fetchTime())
                                    }
                                }
                                Column {
                                    // 객체에서 제목이랑 장소 가져오기
                                    Text(schedule1.fetchTitle())
                                    Text(schedule1.fetchPlace(), fontSize = 12.sp)
                                }
                            }
                        }

                        // 두번째 일정 카드
                        Card(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = cardColor),
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                        ) {
                            Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(
                                        modifier = Modifier.padding(end = 16.dp),
                                        colors =
                                                CardDefaults.cardColors(
                                                        containerColor = timeCardColor
                                                )
                                ) {
                                    Row(
                                            modifier = Modifier.padding(8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                                imageVector = Icons.Filled.Info,
                                                contentDescription = "시계",
                                                tint = accentColor,
                                                modifier = Modifier.padding(end = 8.dp)
                                        )
                                        Text(schedule2.fetchTime())
                                    }
                                }
                                Column {
                                    Text(schedule2.fetchTitle())
                                    Text(schedule2.fetchPlace(), fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
