package com.example.oos_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.oos_project.ui.theme.OOS_ProjectTheme

/**
 * ================================================== 📌 ChecklistActivity - 체크리스트 화면
 * ==================================================
 *
 * 역할:
 * - 특정 여행의 체크리스트 항목을 표시하고 관리하는 화면입니다
 * - 여행 준비물 체크리스트 항목들(passport, charger, hotelBooked, insurance, exchangeDone)을 체크박스로 표시합니다
 *
 * 데이터 흐름:
 * - Intent로 "travelId"를 받아서 AppData.checklistStates에서 해당 여행의 체크리스트 상태를 찾습니다
 * - travelId와 일치하는 ChecklistState가 없으면 새로 생성하여 AppData.checklistStates에 추가합니다
 * - 사용자가 체크박스를 클릭하면 AppData.checklistStates의 해당 ChecklistState를 업데이트합니다
 *
 * 화면 이동:
 * - TravelDetailActivity 또는 TestScreenActivity에서 이 화면으로 이동합니다 (travelId 전달 또는 미전달)
 * - 뒤로가기 버튼을 누르면 이전 화면으로 돌아갑니다
 */
class ChecklistActivity : ComponentActivity() {
    /**
     * Activity가 생성될 때 호출되는 함수
     * - setContent로 Compose UI를 설정합니다
     * - ChecklistUI() Composable 함수를 호출하여 화면을 구성합니다
     */
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OOS_ProjectTheme { ChecklistUI() } }
    }

    /**
     * ================================================== ChecklistUI - 체크리스트 UI를 표시하는 Composable 함수
     * ==================================================
     *
     * 역할:
     * - travelId로 필터링한 체크리스트 상태를 체크박스로 표시합니다
     * - 각 체크박스를 클릭하면 AppData.checklistStates를 업데이트합니다
     *
     * 데이터 관리:
     * - travelId: Intent로 받은 값으로, 어떤 여행의 체크리스트인지 구분합니다
     * - ChecklistState: passport, charger, hotelBooked, insurance, exchangeDone 등의 Boolean 값으로 각
     * 항목의 체크 여부를 저장합니다
     * - AppData.checklistStates에서 travelId로 필터링하여 ChecklistState를 찾고, 없으면 새로 생성합니다
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ChecklistUI() {
        // Intent로 전달받은 travelId를 가져옵니다
        // "travelId"라는 키로 저장된 값을 읽고, 없으면 빈 문자열("")을 사용합니다
        // travelId는 ChecklistState 객체의 travelId 필드와 비교하여 어떤 여행의 체크리스트인지 구분합니다
        val travelId = intent.getStringExtra("travelId") ?: ""

        // Scaffold는 Material Design의 기본 화면 구조입니다
        Scaffold(
                topBar = {
                    // 화면 상단에 표시되는 앱 바
                    TopAppBar(
                            title = { Text("체크리스트") },
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
            Column(modifier = Modifier.padding(it)) {
                // TODO: 여기서부터 팀원이 이 페이지의 UI 구성

                // travelId를 전달받아 체크리스트 표시
                // - travelId 변수는 이미 위에서 Intent로부터 가져왔습니다
                // - 이 travelId를 사용하여 AppData.checklistStates에서 해당 여행의 체크리스트 상태를 찾습니다

                // AppData.checklistStates에서 travelId로 필터링하여 ChecklistState 찾기
                // - AppData.checklistStates.find { it.travelId == travelId }로 ChecklistState를 찾습니다
                // - find 함수는 조건에 맞는 첫 번째 요소를 반환하고, 없으면 null을 반환합니다
                // - 예: val checklistState = AppData.checklistStates.find { it.travelId == travelId
                // }

                // 없으면 새로 생성하여 추가
                // - ChecklistState가 null이면 새로 생성합니다:
                //   ChecklistState(
                //       travelId = travelId (Intent로 받은 값),
                //       passport = false,
                //       charger = false,
                //       hotelBooked = false,
                //       insurance = false,
                //       exchangeDone = false
                //   )
                // - 생성한 ChecklistState를 AppData.checklistStates.add()로 추가합니다

                // Checkbox로 passport, charger, hotelBooked, insurance, exchangeDone 표시
                // - 각 체크리스트 항목에 대해 Checkbox를 생성합니다
                // - Checkbox의 checked 속성은 ChecklistState의 해당 Boolean 필드와 연결합니다
                // - 예: Checkbox(checked = checklistState.passport, ...) - 여권 준비 체크박스
                // - 예: Checkbox(checked = checklistState.charger, ...) - 충전기 준비 체크박스
                // - 예: Checkbox(checked = checklistState.hotelBooked, ...) - 호텔 예약 체크박스
                // - 예: Checkbox(checked = checklistState.insurance, ...) - 여행 보험 체크박스
                // - 예: Checkbox(checked = checklistState.exchangeDone, ...) - 환전 완료 체크박스

                // 체크박스 변경 시 AppData.checklistStates 업데이트
                // - Checkbox의 onCheckedChange 콜백에서 체크 상태를 변경합니다
                // - AppData.checklistStates에서 해당 ChecklistState를 찾아서 copy()로 업데이트합니다
                // - 예: checklistState.copy(passport = newValue)로 새로운 ChecklistState를 생성
                // - AppData.checklistStates에서 기존 항목을 찾아서 새로 생성한 ChecklistState로 교체합니다
            }
        }
    }
}
