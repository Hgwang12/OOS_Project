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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

/**
 * ================================================== 📌 HomePageInfo - 페이지 정보를 담는 클래스
 * ==================================================
 *
 * 역할:
 * - HomeActivity의 페이지 제목을 저장하는 간단한 데이터 클래스입니다
 * - getTitle() 함수로 제목을 가져올 수 있습니다
 */
// 페이지 정보를 담는 클래스
class HomePageInfo(val titleText: String) {
    // 제목 가져오는 함수
    fun getTitle(): String {
        return titleText
    }
}

/**
 * ================================================== 📌 HomeActivity - 여행 목록을 표시하는 홈 화면
 * ==================================================
 *
 * 역할:
 * - AppData.travelList에 저장된 모든 여행 목록을 표시하는 화면입니다
 * - 사용자가 여행을 선택하면 TravelDetailActivity로 이동하여 상세 정보를 볼 수 있습니다
 *
 * 데이터 흐름:
 * - AppData.travelList를 읽어서 여행 목록을 표시합니다
 * - 각 여행 카드를 클릭하면 해당 여행의 id를 travelId로 하여 TravelDetailActivity로 이동합니다
 * - Intent로 travelId를 전달하여 TravelDetailActivity에서 어떤 여행의 정보를 표시할지 결정합니다
 *
 * 화면 이동:
 * - TestScreenActivity에서 이 화면으로 이동합니다
 * - 뒤로가기 버튼을 누르면 TestScreenActivity로 돌아갑니다
 * - (향후 구현) 여행 카드를 클릭하면 TravelDetailActivity로 이동합니다 (travelId 전달)
 */
class HomeActivity : ComponentActivity() {
    /**
     * Activity가 생성될 때 호출되는 함수
     * - enableEdgeToEdge()로 화면을 전체 화면으로 확장합니다
     * - setContent로 Compose UI를 설정합니다
     */
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OOS_ProjectTheme {
                // 페이지 정보 객체 만들기
                // HomePageInfo 클래스를 사용하여 "홈 페이지"라는 제목을 저장합니다
                val pageInfo = HomePageInfo("홈 페이지")

                // 색상 변수들
                // TopAppBar의 배경색을 연한 파란색으로 설정합니다
                val topBarColor = Color(0xFFE3F2FD)

                // Scaffold로 화면 구조 잡기
                // Scaffold는 Material Design의 기본 화면 구조로, TopAppBar와 본문 영역을 제공합니다
                Scaffold(
                        topBar = {
                            // 화면 상단에 표시되는 앱 바
                            TopAppBar(
                                    colors =
                                            TopAppBarDefaults.topAppBarColors(
                                                    containerColor = topBarColor
                                            ),
                                    title = {
                                        // TopAppBar의 제목으로 "홈 페이지"를 표시합니다
                                        Text(pageInfo.getTitle(), fontWeight = FontWeight.Bold)
                                    },
                                    navigationIcon = {
                                        // 뒤로가기 버튼
                                        // 클릭하면 TestScreenActivity로 이동하고 현재 Activity를 종료합니다
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
                    // Scaffold의 본문 영역
                    // paddingValues는 TopAppBar의 높이만큼의 패딩을 제공합니다
                    Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                        // 나중에 내용 추가할 곳
                        // TODO: 여기서 AppData.travelList를 읽어서 여행 목록을 표시합니다
                        // TODO: 각 여행 카드를 클릭하면 TravelDetailActivity로 이동하고 travelId를 전달합니다
                        val travelList = AppData.scheduleList

                        if(travelList.isEmpty()){
                            ShowEmptyScreen()
                        }
                        else{
                            ShowScreen(onCardClick = {
                                val intent = Intent(this@HomeActivity, TravelDetailActivity::class.java)
                                intent.putExtra("travelId", it.id)
                                startActivity(intent)
                            })
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ShowEmptyScreen(){
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
fun ShowScreen(onCardClick: (Travel) -> Unit){

    val tokyoImg = painterResource(id = R.drawable.tokyopic)

    travelList.forEach { travel ->      //travelList에 있는 여행들을 카드형식으로 출력

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onClick = { onCardClick(travel) }
        ) {
            Column(modifier = Modifier.padding(16.dp))
            {
                Image(
                    painter = tokyoImg,
                    contentDescription = "tokyo picture icon",
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(   //여행 제목
                    text = travel.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(      //여행 지역
                    text = "지역: ${travel.location}",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(       //여행 날짜
                    text = "날짜: ${travel.startDate} ~ ${travel.endDate}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
