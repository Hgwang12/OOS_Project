package com.example.oos_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.oos_project.ui.theme.OOS_ProjectTheme

/**
 * ==================================================
 * 📌 ScheduleAddActivity - 일정 추가 화면
 * ==================================================
 *
 * 역할:
 * - 새로운 일정을 추가하는 화면입니다
 * - 사용자가 제목, 시간, 메모를 입력하면 AppData.scheduleList에 Schedule 객체를 추가합니다
 *
 * 데이터 흐름:
 * - Intent로 "travelId"를 받아서 새로 생성하는 Schedule 객체의 travelId 필드에 저장합니다
 * - travelId는 이 일정이 어떤 여행에 속하는지를 나타냅니다
 * - 사용자가 입력한 제목, 시간, 메모를 사용하여 Schedule 객체를 생성합니다
 * - 생성한 Schedule 객체를 AppData.scheduleList에 추가합니다
 *
 * 화면 이동:
 * - TravelDetailActivity 또는 ScheduleListActivity에서 이 화면으로 이동합니다 (travelId 전달)
 * - 일정 추가 후 finish()를 호출하여 이전 화면으로 돌아갑니다
 * - 뒤로가기 버튼을 눌러도 이전 화면으로 돌아갑니다
 */
class ScheduleAddActivity : ComponentActivity() {
    /**
     * Activity가 생성될 때 호출되는 함수
     * - setContent로 Compose UI를 설정합니다
     * - ScheduleAddUI() Composable 함수를 호출하여 화면을 구성합니다
     */
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OOS_ProjectTheme { ScheduleAddUI() } }
    }

    /**
     * ==================================================
     * ScheduleAddUI - 일정 추가 UI를 표시하는 Composable 함수
     * ==================================================
     *
     * 역할:
     * - 사용자가 일정 정보를 입력할 수 있는 TextField들을 제공합니다
     * - 저장 버튼을 클릭하면 입력한 정보로 Schedule 객체를 생성하여 AppData.scheduleList에 추가합니다
     *
     * 데이터 입력 및 저장:
     * - travelId: Intent로 받은 값으로, 새로 생성하는 Schedule 객체의 travelId 필드에 저장됩니다
     * - title: 사용자가 입력하는 일정 제목 (TextField로 입력)
     * - time: 사용자가 입력하는 일정 시간 (TextField로 입력, 예: "09:00")
     * - memo: 사용자가 입력하는 일정 메모 (TextField로 입력)
     * - 저장 버튼 클릭 시: Schedule(id, travelId, title, time, memo) 객체를 생성하여 AppData.scheduleList.add()로 추가
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScheduleAddUI() {
        // Intent로 전달받은 travelId를 가져옵니다
        // "travelId"라는 키로 저장된 값을 읽고, 없으면 빈 문자열("")을 사용합니다
        // travelId는 새로 생성하는 Schedule 객체의 travelId 필드에 저장되어, 이 일정이 어떤 여행에 속하는지 나타냅니다
        val travelId = intent.getStringExtra("travelId") ?: ""

        // Scaffold는 Material Design의 기본 화면 구조입니다
        Scaffold(
                topBar = {
                    // 화면 상단에 표시되는 앱 바
                    TopAppBar(
                            title = { Text("일정 추가") },
                            navigationIcon = {
                                // 뒤로가기 버튼
                                // 클릭하면 finish()를 호출하여 현재 Activity를 종료하고 이전 화면으로 돌아갑니다
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
            // Scaffold의 본문 영역
            // it은 TopAppBar의 높이만큼의 패딩 값입니다
            Column(modifier = Modifier
                              .padding(it)
                              .padding(horizontal = 20.dp)) {
                // TODO: 여기서부터 팀원이 이 페이지의 UI 구성
                
                // travelId를 전달받아 일정 생성
                // - travelId 변수는 이미 위에서 Intent로부터 가져왔습니다
                // - 이 travelId는 새로 생성하는 Schedule 객체의 travelId 필드에 저장됩니다
                
                // TextField로 제목, 시간, 메모 입력받기
                // - remember { mutableStateOf("") }를 사용하여 각 입력 필드의 상태를 관리합니다
                // - 예: var title by remember { mutableStateOf("") }
                // - TextField의 value와 onValueChange를 연결하여 사용자 입력을 실시간으로 저장합니다
                // - 제목(title): 일정의 제목을 입력받습니다
                // - 여행지(region): 여행지를 입력받습니다
                // - 기간(time): 여행의 기간을 입력받습니다. 예) 2025.12.01 ~ 2025.12.05
                // - 메모(memo): 일정에 대한 간단한 메모를 입력받습니다
                var title by remember { mutableStateOf("") }
                var region by remember {mutableStateOf("")}
                var time by remember { mutableStateOf("") }
                var memo by remember { mutableStateOf("") }

                //제목 입력
                Spacer(modifier = Modifier.height(20.dp))
                InputInformation(
                    title = "일정 제목",
                    inputex = "예: 맛집 탐방",
                    input = title,
                    onValueChange = { title = it }
                )

                //여행지 입력
                Spacer(modifier = Modifier.height(20.dp))
                InputInformation(
                    title = "여행지",
                    inputex = "예: 제주도",
                    input = region,
                    onValueChange = { region = it }
                )

                // 시간 입력
                Spacer(modifier = Modifier.height(20.dp))
                InputInformation(
                    title = "기간",
                    inputex = "예: 2025.12.01 ~ 2025.12.05",
                    input = time,
                    onValueChange = { time = it }
                )

                // 메모 입력
                Spacer(modifier = Modifier.height(20.dp))
                InputInformation(
                    title = "메모",
                    inputex = "예: 예약 필수, 3번 출구 앞",
                    input = memo,
                    onValueChange = { memo = it }
                )

                Spacer(modifier = Modifier.height(30.dp))
                // Button으로 일정 추가 → AppData.scheduleList에 Schedule 객체 추가
                // - 저장 버튼을 클릭하면:
                //   1. 입력한 title, time, memo 값을 가져옵니다
                //   2. Schedule 객체를 생성합니다:
                //      Schedule(
                //          id = UUID.randomUUID().toString() 또는 AppData.scheduleList.size + 1 등으로 고유 ID 생성,
                //          travelId = travelId (Intent로 받은 값),
                //          title = title (사용자 입력),
                //          time = time (사용자 입력),
                //          memo = memo (사용자 입력)
                //      )
                //   3. AppData.scheduleList.add(newSchedule)로 리스트에 추가합니다
                
                // 일정 추가 후 finish()로 이전 화면으로 돌아가기
                // - 저장 버튼 클릭 시 일정을 추가한 후 finish()를 호출하여 이전 화면(ScheduleListActivity 등)으로 돌아갑니다
            }
        }
    }
    @Composable
    fun InputInformation(
        title: String,
        inputex: String,    // 입력 예시
        input: String,      // 진짜 입력받을 내용
        onValueChange : (String) -> Unit
    ){
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        ){
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = title)
                Spacer(modifier = Modifier.height(5.dp))

                TextField( // 내용 입력받는 텍스트 필드
                    value = input,
                    label = { Text(inputex) },        // 입력 예시를 레이블로 표현
                    onValueChange = onValueChange,    // 사용자가 입력할 때마다 업데이트
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true                 // 한 줄만 입력받도록 설정
                )
            }
        }
    }
}
