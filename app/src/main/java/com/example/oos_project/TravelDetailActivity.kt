package com.example.oos_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oos_project.ui.theme.OOS_ProjectTheme

class TravelDetailActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OOS_ProjectTheme { TravelDetailUI() } }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TravelDetailUI() {
        val travelId = intent.getStringExtra("travelId") ?: ""
        val travel = AppData.travelList.find { it.id == travelId }
        val topBarColor = Color(0xFFE3F2FD)

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = topBarColor),
                    title = { Text(travel?.title ?: "여행 상세", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = { finish() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "뒤로"
                            )
                        }
                    }
                )
            }
        ) {
            Column(modifier = Modifier.padding(it).padding(16.dp)) {
                if (travel != null) {
                    Text(
                        text = travel.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "지역: ${travel.location}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    Text(
                        text = "기간: ${travel.startDate} ~ ${travel.endDate}",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                } else {
                    Text(
                        text = "여행 정보를 찾을 수 없습니다.",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                val scheduleCount = if (travelId.isNotEmpty()) {
                    AppData.scheduleList.count { it.travelId == travelId }
                } else {
                    0
                }

                val expenseSum = if (travelId.isNotEmpty()) {
                    AppData.expenseList.filter { it.travelId == travelId }.sumOf { it.amount }
                } else {
                    0
                }

                val checklistState = if (travelId.isNotEmpty()) {
                    AppData.checklistStates.find { it.travelId == travelId }
                } else {
                    null
                }

                val checkedCount = checklistState?.let {
                    var count = 0
                    if (it.passport) count++
                    if (it.charger) count++
                    if (it.hotelBooked) count++
                    if (it.insurance) count++
                    if (it.exchangeDone) count++
                    count
                } ?: 0

                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                    Card(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "$scheduleCount",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "일정", fontSize = 12.sp)
                        }
                    }

                    Card(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "${expenseSum / 10000}만",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "지출", fontSize = 12.sp)
                        }
                    }

                    Card(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "$checkedCount/5",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "준비", fontSize = 12.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val intent = Intent(this@TravelDetailActivity, ScheduleListActivity::class.java)
                        intent.putExtra("travelId", travelId)
                        startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) { Text("일정 보기") }

                Button(
                    onClick = {
                        val intent = Intent(this@TravelDetailActivity, ChecklistActivity::class.java)
                        intent.putExtra("travelId", travelId)
                        startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) { Text("체크리스트") }

                Button(
                    onClick = {
                        val intent = Intent(this@TravelDetailActivity, ExpenseActivity::class.java)
                        intent.putExtra("travelId", travelId)
                        startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("예산 관리") }
            }
        }
    }
}
