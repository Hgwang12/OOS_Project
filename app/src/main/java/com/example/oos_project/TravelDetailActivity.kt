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
 * ================================================== 📌 TravelDetailActivity - 여행 상세 정보 화면
 * ==================================================
 *
 * 역할:
 * - 특정 여행의 상세 정보를 표시하는 화면입니다
 * - 여행의 일정, 체크리스트, 예산 관리 등으로 이동할 수 있는 메뉴를 제공합니다
 *
 * 데이터 흐름:
 * - Intent로 "travelId"를 받아서 어떤 여행의 정보를 표시할지 결정합니다
 * - AppData.travelList에서 travelId와 일치하는 Travel 객체를 찾아서 정보를 표시합니다
 * - 하위 화면(ScheduleListActivity, ScheduleAddActivity 등)으로 이동할 때도 travelId를 전달합니다
 *
 * 화면 이동:
 * - TestScreenActivity 또는 HomeActivity에서 이 화면으로 이동합니다 (travelId 전달)
 * - 뒤로가기 버튼을 누르면 이전 화면으로 돌아갑니다
 * - (향후 구현) 일정보기 버튼 → ScheduleListActivity로 이동 (travelId 전달)
 * - (향후 구현) 일정추가 버튼 → ScheduleAddActivity로 이동 (travelId 전달)
 * - (향후 구현) 체크리스트 버튼 → ChecklistActivity로 이동 (travelId 전달)
 * - (향후 구현) 예산관리 버튼 → ExpenseActivity로 이동 (travelId 전달)
 */
class TravelDetailActivity : ComponentActivity() {
    /**
     * Activity가 생성될 때 호출되는 함수
     * - setContent로 Compose UI를 설정합니다
     * - TravelDetailUI() Composable 함수를 호출하여 화면을 구성합니다
     */
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OOS_ProjectTheme { TravelDetailUI() } }
    }

    /**
     * ================================================== TravelDetailUI - 여행 상세 정보를 표시하는 Composable
     * 함수 ==================================================
     *
     * 역할:
     * - Intent로 받은 travelId를 사용하여 AppData.travelList에서 해당 여행을 찾습니다
     * - 여행의 제목, 지역, 기간 등의 정보를 표시합니다
     * - 일정, 체크리스트, 예산 관리 등으로 이동할 수 있는 버튼들을 제공합니다
     *
     * 데이터 표시:
     * - travel 변수: AppData.travelList에서 travelId와 일치하는 Travel 객체
     * - travel이 null이면 "여행 상세"라는 기본 제목을 표시합니다
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TravelDetailUI() {
        // Intent로 전달받은 travelId를 가져옵니다
        // "travelId"라는 키로 저장된 값을 읽고, 없으면 빈 문자열("")을 사용합니다
        // travelId는 Travel 객체의 고유 식별자로, 어떤 여행의 정보를 표시할지 결정합니다
        val travelId = intent.getStringExtra("travelId") ?: ""

        // AppData.travelList에서 travelId와 일치하는 Travel 객체를 찾습니다
        // find 함수는 리스트에서 조건에 맞는 첫 번째 요소를 반환하고, 없으면 null을 반환합니다
        // travel 변수는 Travel? 타입이므로 null일 수 있습니다
        val travel = AppData.travelList.find { it.id == travelId }

        // Scaffold는 Material Design의 기본 화면 구조입니다
        // TopAppBar와 본문 영역을 제공합니다
        Scaffold(
                topBar = {
                    // 화면 상단에 표시되는 앱 바
                    TopAppBar(
                            // 제목: travel이 null이 아니면 여행 제목을 표시하고, null이면 "여행 상세"를 표시합니다
                            title = { Text(travel?.title ?: "여행 상세") },
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
                // Travel 정보 표시 (여행명, 지역, 기간 등)
                // - travel?.title: 여행명
                // - travel?.location: 지역
                // - travel?.startDate: 여행 시작일
                // - travel?.endDate: 여행 종료일

                // 일정보기 버튼 → ScheduleListActivity로 이동 (travelId 전달)
                // - Intent에 "travelId"를 저장하여 전달
                // - ScheduleListActivity에서 travelId로 필터링하여 해당 여행의 일정만 표시

                // 일정추가 버튼 → ScheduleAddActivity로 이동 (travelId 전달)
                // - Intent에 "travelId"를 저장하여 전달
                // - ScheduleAddActivity에서 travelId를 사용하여 새로운 Schedule 객체를 생성할 때 travelId 필드에 저장

                // 체크리스트 버튼 → ChecklistActivity로 이동 (travelId 전달)
                // - Intent에 "travelId"를 저장하여 전달
                // - ChecklistActivity에서 travelId로 필터링하여 해당 여행의 체크리스트 상태를 표시

                // 예산관리 버튼 → ExpenseActivity로 이동 (travelId 전달)
                // - Intent에 "travelId"를 저장하여 전달
                // - ExpenseActivity에서 travelId로 필터링하여 해당 여행의 지출 목록을 표시
            }
        }
    }
}
