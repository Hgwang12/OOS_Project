package com.example.oos_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oos_project.data.model.Schedule
import com.example.oos_project.ui.theme.OOS_ProjectTheme

// AppData는 같은 패키지에 있으므로 import 불필요

/**
 * ================================================== 📌 ScheduleListActivity - 일정 목록을 표시하는 화면
 * ==================================================
 *
 * 역할:
 * - 특정 여행의 일정 목록을 카드 형태로 표시하는 화면입니다
 * - 헴이 기존에 만들었던 UI 디자인을 그대로 유지하면서 AppData를 사용하도록 리팩토링되었습니다
 *
 * 데이터 흐름:
 * - Intent로 "travelId"를 받아서 AppData.scheduleList에서 해당 여행의 일정만 필터링하여 표시합니다
 * - travelId가 비어있으면 AppData.scheduleList의 모든 일정을 표시합니다
 * - AppData.travelList에서 travelId와 일치하는 Travel 객체를 찾아서 제목을 표시합니다
 * - FloatingActionButton을 클릭하면 ScheduleAddActivity로 이동하여 새 일정을 추가할 수 있습니다
 *
 * 화면 이동:
 * - TravelDetailActivity 또는 TestScreenActivity에서 이 화면으로 이동합니다 (travelId 전달 또는 미전달)
 * - 뒤로가기 버튼을 누르면 이전 화면으로 돌아갑니다
 * - FloatingActionButton 클릭 → ScheduleAddActivity로 이동 (travelId 전달)
 */
class ScheduleListActivity : ComponentActivity() {
    /**
     * Activity가 생성될 때 호출되는 함수
     * - setContent로 Compose UI를 설정합니다
     * - ScheduleListUI() Composable 함수를 호출하여 화면을 구성합니다
     */
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OOS_ProjectTheme { ScheduleListUI() } }
    }

    /**
     * ================================================== ScheduleListUI - 일정 목록을 표시하는 Composable 함수
     * ==================================================
     *
     * 역할:
     * - travelId로 필터링한 일정 목록을 카드 형태로 표시합니다
     * - 각 일정 카드는 시간, 제목, 메모를 표시합니다
     * - FloatingActionButton을 통해 새 일정을 추가할 수 있습니다
     *
     * 데이터 필터링:
     * - scheduleList: travelId로 필터링된 일정 목록 (mutableStateOf로 상태 관리)
     * - travel: travelId로 찾은 Travel 객체 (제목 표시용)
     * - travelId가 비어있으면 모든 일정을 표시하고, 있으면 해당 여행의 일정만 표시합니다
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScheduleListUI() {
        // Intent로 전달받은 travelId를 가져옵니다
        // "travelId"라는 키로 저장된 값을 읽고, 없으면 빈 문자열("")을 사용합니다
        // travelId가 비어있으면 모든 일정을 표시하고, 있으면 해당 여행의 일정만 필터링하여 표시합니다
        val travelId = intent.getStringExtra("travelId") ?: ""

        // travelId로 일정 필터링
        // remember { mutableStateOf() }를 사용하여 일정 목록의 상태를 관리합니다
        // travelId가 비어있지 않으면 AppData.scheduleList에서 travelId와 일치하는 일정만 필터링합니다
        // travelId가 비어있으면 AppData.scheduleList의 모든 일정을 표시합니다
        // filter { it.travelId == travelId }: Schedule 객체의 travelId 필드가 전달받은 travelId와 일치하는 것만 선택
        var scheduleList by remember {
            mutableStateOf(
                    if (travelId.isNotEmpty()) {
                        AppData.scheduleList.filter { it.travelId == travelId }
                    } else {
                        AppData.scheduleList.toList()
                    }
            )
        }

        // 여행 정보 가져오기
        // travelId가 비어있지 않으면 AppData.travelList에서 travelId와 일치하는 Travel 객체를 찾습니다
        // find { it.id == travelId }: Travel 객체의 id 필드가 travelId와 일치하는 것을 찾습니다
        // travel 변수는 Travel? 타입이므로 null일 수 있습니다
        // travel이 null이 아니면 TopAppBar의 제목에 여행명을 표시합니다
        val travel =
                if (travelId.isNotEmpty()) {
                    AppData.travelList.find { it.id == travelId }
                } else {
                    null
                }

        // 배경색 변수로 빼기
        // UI 디자인에 사용할 색상들을 변수로 정의합니다
        val topBarColor = Color(0xFFE3F2FD) // TopAppBar 배경색 (연한 파란색)
        val accentColor = Color(0xFF2196F3) // 강조 색상 (파란색, FloatingActionButton, Icon 등에 사용)

        // Scaffold로 화면 구조 잡기
        // Scaffold는 Material Design의 기본 화면 구조로, TopAppBar, 본문 영역, FloatingActionButton을 제공합니다
        Scaffold(
                topBar = {
                    // 화면 상단에 표시되는 앱 바
                    TopAppBar(
                            colors =
                                    TopAppBarDefaults.topAppBarColors(containerColor = topBarColor),
                            title = {
                                // 제목 영역: 여행명과 지역을 표시합니다
                                Column {
                                    // travel이 null이 아니면 여행명을 표시하고, null이면 "여행 일정"을 표시합니다
                                    Text(travel?.title ?: "여행 일정", fontWeight = FontWeight.Bold)
                                    // travel이 null이 아니면 지역 정보를 표시합니다
                                    if (travel != null) {
                                        Text("${travel.location} Travel Plan", fontSize = 12.sp)
                                    }
                                }
                            },
                            navigationIcon = {
                                // 뒤로가기 버튼
                                // 클릭하면 finish()를 호출하여 현재 Activity를 종료하고 이전 화면으로 돌아갑니다
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "뒤로가기"
                                    )
                                }
                            }
                    )
                },
                floatingActionButton = {
                    // 화면 우측 하단에 표시되는 플로팅 액션 버튼
                    // 현재는 비활성화되어 있어서 클릭해도 아무 동작도 하지 않습니다
                    FloatingActionButton(
                            onClick = {
                                // 아무 동작도 하지 않음 (비활성화)
                            },
                            containerColor = accentColor
                    ) { Icon(imageVector = Icons.Default.Add, contentDescription = "추가") }
                }
        ) { paddingValues ->
            // Scaffold의 본문 영역
            // paddingValues는 TopAppBar와 FloatingActionButton의 높이만큼의 패딩을 제공합니다
            Column(
                    modifier = Modifier.padding(paddingValues).padding(horizontal = 16.dp)
            ) {
                // 일정 목록 표시
                // scheduleList의 각 Schedule 객체에 대해 ScheduleCard를 생성하여 표시합니다
                // forEach를 사용하여 리스트의 모든 일정을 순회합니다
                scheduleList.forEach { schedule -> ScheduleCard(schedule = schedule) }
            }
        }
    }

    /**
     * ================================================== ScheduleCard - 개별 일정을 카드 형태로 표시하는
     * Composable 함수 ==================================================
     *
     * 역할:
     * - 하나의 Schedule 객체를 받아서 카드 형태로 표시합니다
     * - 시간, 제목, 메모를 표시합니다
     *
     * UI 구성:
     * - 외부 Card: 전체 일정 카드 (회색 배경)
     * - 내부 Card: 시간 표시 카드 (연한 파란색 배경, 왼쪽에 위치)
     * - Column: 제목과 메모를 세로로 배치 (오른쪽에 위치)
     * - Row: 시간 카드와 제목/메모를 가로로 배치
     */
    @Composable
    fun ScheduleCard(schedule: Schedule) {
        // 카드에 사용할 색상 변수들
        val cardColor = Color(0xFFF5F5F5) // 일정 카드 배경색 (회색)
        val accentColor = Color(0xFF2196F3) // 강조 색상 (파란색, Icon에 사용)
        val timeCardColor = Color(0xFFE3F2FD) // 시간 표시 카드 배경색 (연한 파란색)

        // 일정 카드 (외부 카드)
        // fillMaxWidth(): 화면 전체 너비를 사용
        // padding(vertical = 8.dp): 위아래 여백 8dp
        Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            // 카드 내부: 시간 카드와 제목/메모를 가로로 배치
            Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
            ) {
                // 시간 표시 카드 (내부 카드, 왼쪽에 위치)
                // padding(end = 16.dp): 오른쪽 여백 16dp (제목/메모와의 간격)
                Card(
                        modifier = Modifier.padding(end = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = timeCardColor)
                ) {
                    // 시간 카드 내부: Icon과 시간 텍스트를 가로로 배치
                    Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 정보 아이콘 (파란색)
                        Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "시계",
                                tint = accentColor,
                                modifier = Modifier.padding(end = 8.dp)
                        )
                        // 일정 시간 표시 (예: "09:00", "14:30")
                        // schedule.time은 Schedule 객체의 time 필드입니다
                        Text(schedule.time)
                    }
                }
                // 제목과 메모를 세로로 배치 (오른쪽에 위치)
                Column {
                    // 일정 제목 표시
                    // schedule.title은 Schedule 객체의 title 필드입니다
                    Text(schedule.title)
                    // 일정 메모 표시 (작은 글씨)
                    // schedule.memo는 Schedule 객체의 memo 필드입니다
                    Text(schedule.memo, fontSize = 12.sp)
                }
            }
        }
    }
}
