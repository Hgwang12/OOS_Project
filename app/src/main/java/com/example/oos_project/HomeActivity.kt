package com.example.oos_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.oos_project.ui.theme.OOS_ProjectTheme

// 페이지 정보를 담는 클래스
class HomePageInfo(val titleText: String) {
    // 제목 가져오는 함수
    fun getTitle(): String {
        return titleText
    }
}

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OOS_ProjectTheme {
                // 페이지 정보 객체 만들기
                val pageInfo = HomePageInfo("홈 페이지")

                // 색상 변수들
                val topBarColor = Color(0xFFE3F2FD)

                // Scaffold로 화면 구조 잡기
                Scaffold(
                        topBar = {
                            TopAppBar(
                                    colors =
                                            TopAppBarDefaults.topAppBarColors(
                                                    containerColor = topBarColor
                                            ),
                                    title = {
                                        Text(pageInfo.getTitle(), fontWeight = FontWeight.Bold)
                                    },
                                    navigationIcon = {
                                        IconButton(
                                                onClick = {
                                                    val intent =
                                                            Intent(
                                                                    this@HomeActivity,
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
                        }
                ) { paddingValues ->
                    Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                        // 나중에 내용 추가할 곳
                    }
                }
            }
        }
    }
}
