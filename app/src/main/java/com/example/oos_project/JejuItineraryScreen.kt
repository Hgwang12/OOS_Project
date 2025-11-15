package com.example.oos_project

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oos_project.ui.theme.OOS_ProjectTheme

// 제주도 여행 일정 화면
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JejuItineraryScreen() {
    // Scaffold로 화면 구조 잡기
    Scaffold(
            // 상단 앱바
            topBar = {
                TopAppBar(
                        // 앱바 배경색
                        colors =
                                TopAppBarDefaults.topAppBarColors(
                                        containerColor = Color(0xFFE3F2FD)
                                ),
                        // 제목
                        title = {
                            Column {
                                Text("도쿄 여행 일정", fontWeight = FontWeight.Bold)
                                Text("Tokyo Travel Plan", fontSize = 12.sp, lineHeight = 12.sp)
                            }
                        },
                        // 왼쪽 뒤로가기 버튼
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "뒤로가기"
                                )
                            }
                        }
                )
            },
            // FAB 버튼
            floatingActionButton = {
                FloatingActionButton(onClick = {}, containerColor = Color(0xFF2196F3)) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "추가")
                }
            }
    ) { paddingValues ->
        // 카드들을 세로로 나열
        Column(
                modifier =
                        Modifier.fillMaxSize()
                                .background(Color(0xFFEFF8FF))
                                .padding(paddingValues)
                                .padding(horizontal = 16.dp)
        ) {
            // 첫 번째 카드
            Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                ) {
                    // 아이콘과 시간을 작은 카드에 넣기
                    Card(
                            modifier = Modifier.padding(end = 16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                    ) {
                        Row(
                                modifier = Modifier.padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "시계",
                                    tint = Color(0xFF2196F3),
                                    modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("09:00")
                        }
                    }
                    // 내용
                    Column {
                        Text("호텔 체크인")
                        Text("신주쿠 호텔 도착", fontSize = 12.sp)
                    }
                }
            }

            // 두 번째 카드
            Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                ) {
                    // 아이콘과 시간을 작은 카드에 넣기
                    Card(
                            modifier = Modifier.padding(end = 16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                    ) {
                        Row(
                                modifier = Modifier.padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "시계",
                                    tint = Color(0xFF2196F3),
                                    modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("11:30")
                        }
                    }
                    // 내용
                    Column {
                        Text("센소지 사원 방문")
                        Text("아사쿠사 관광", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

// 미리보기 함수
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun JejuItineraryScreenPreview() {
    OOS_ProjectTheme { JejuItineraryScreen() }
}
