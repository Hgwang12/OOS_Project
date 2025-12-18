package com.example.oos_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oos_project.AppData.travelList
import com.example.oos_project.data.model.Travel
import com.example.oos_project.ui.theme.OOS_ProjectTheme

class HomePageInfo(val titleText: String) {
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
                val pageInfo = HomePageInfo("홈 페이지")
                val topBarColor = Color(0xFFE3F2FD)

                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = topBarColor
                            ),
                            title = {
                                Text(pageInfo.getTitle(), fontWeight = FontWeight.Bold)
                            }
                        )
                    }
                ) { paddingValues ->
                    Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                        val travelList = AppData.travelList

                        if (travelList.isEmpty()) {
                            ShowEmptyScreen()
                        } else {
                            ShowScreen()
                        }
                    }
                }
            }
        }
    }
    @Composable
    fun ShowEmptyScreen() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "등록된 일정이 없습니다.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "새로운 여행을 추가해보세요!",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowScreen() {
        val tokyoImg = painterResource(id = R.drawable.tokyopic)        //res에서 도쿄 이미지 로드

        travelList.forEach { travel ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                onClick = {
                    val intent = Intent(this@HomeActivity, TravelDetailActivity::class.java)
                    intent.putExtra("travelId", travel.id)
                    startActivity(intent)
                }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = tokyoImg,
                        contentDescription = "tokyo picture icon",
                        modifier = Modifier.fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = travel.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "지역: ${travel.location}",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "날짜: ${travel.startDate} ~ ${travel.endDate}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
