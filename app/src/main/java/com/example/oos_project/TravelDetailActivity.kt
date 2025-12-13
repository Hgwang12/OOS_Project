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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Column(modifier = Modifier.padding(it).padding(16.dp)) {
                // ==================================================
                // 1. 여행 기본 정보 표시
                // ==================================================
                // travel 객체가 null이 아니면 여행 정보를 표시하고, null이면 기본 메시지를 표시합니다
                if (travel != null) {
                    // 여행 제목을 큰 글씨로 표시합니다
                    Text(
                            text = travel.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // 여행 지역을 표시합니다
                    // travel.location은 Travel 객체의 location 필드입니다 (예: "일본 도쿄")
                    Text(
                            text = "📍 ${travel.location}",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                    )

                    // 여행 기간을 표시합니다
                    // travel.startDate와 travel.endDate는 Travel 객체의 시작일과 종료일입니다
                    Text(
                            text = "📅 ${travel.startDate} ~ ${travel.endDate}",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                    )
                } else {
                    // travel이 null이면 여행 정보를 찾을 수 없다는 메시지를 표시합니다
                    Text(
                            text = "여행 정보를 찾을 수 없습니다.",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                // ==================================================
                // 2. 통계 정보 표시 (일정 개수, 지출 합계, 체크리스트 완료율)
                // ==================================================
                // travelId로 필터링하여 해당 여행의 통계를 계산합니다
                // AppData의 각 리스트에서 travelId와 일치하는 데이터만 가져옵니다

                // 일정 개수 계산
                // AppData.scheduleList에서 travelId와 일치하는 Schedule 객체의 개수를 세어봅니다
                // filter { it.travelId == travelId }: Schedule 객체의 travelId 필드가 현재 travelId와 같은 것만
                // 선택
                val scheduleCount =
                        if (travelId.isNotEmpty()) {
                            AppData.scheduleList.count { it.travelId == travelId }
                        } else {
                            0
                        }

                // 지출 합계 계산
                // AppData.expenseList에서 travelId와 일치하는 Expense 객체들의 amount를 모두 더합니다
                // filter { it.travelId == travelId }: Expense 객체의 travelId 필드가 현재 travelId와 같은 것만
                // 선택
                // sumOf { it.amount }: 선택된 Expense 객체들의 amount 필드를 모두 더함
                val expenseSum =
                        if (travelId.isNotEmpty()) {
                            AppData.expenseList.filter { it.travelId == travelId }.sumOf {
                                it.amount
                            }
                        } else {
                            0
                        }

                // 체크리스트 완료율 계산
                // AppData.checklistStates에서 travelId와 일치하는 ChecklistState를 찾습니다
                // ChecklistState는 passport, charger, hotelBooked, insurance, exchangeDone 5개 항목이
                // 있습니다
                // 체크된 항목(true)의 개수를 세어서 5로 나누어 완료율을 계산합니다
                val checklistState =
                        if (travelId.isNotEmpty()) {
                            AppData.checklistStates.find { it.travelId == travelId }
                        } else {
                            null
                        }
                // 체크된 항목 개수 계산 (true인 항목만 카운트)
                val checkedCount =
                        checklistState?.let {
                            var count = 0
                            if (it.passport) count++
                            if (it.charger) count++
                            if (it.hotelBooked) count++
                            if (it.insurance) count++
                            if (it.exchangeDone) count++
                            count
                        }
                                ?: 0

                // 통계 정보를 카드 형태로 표시합니다
                // Row를 사용하여 일정, 지출, 준비 상태를 가로로 배치합니다
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                    // 일정 개수 카드
                    Card(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // 일정 개수를 큰 숫자로 표시합니다
                            Text(
                                    text = "$scheduleCount",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                            )
                            // "일정" 라벨을 표시합니다
                            Text(text = "일정", fontSize = 12.sp)
                        }
                    }

                    // 지출 합계 카드
                    Card(modifier = Modifier.weight(1f).padding(horizontal = 4.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // 지출 합계를 큰 숫자로 표시합니다
                            // expenseSum을 10000으로 나누어 "만원" 단위로 표시합니다
                            Text(
                                    text = "${expenseSum / 10000}만",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                            )
                            // "지출" 라벨을 표시합니다
                            Text(text = "지출", fontSize = 12.sp)
                        }
                    }

                    // 체크리스트 완료율 카드
                    Card(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // 체크된 항목 수와 전체 항목 수를 표시합니다 (예: "3/5")
                            Text(
                                    text = "$checkedCount/5",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                            )
                            // "준비" 라벨을 표시합니다
                            Text(text = "준비", fontSize = 12.sp)
                        }
                    }
                }

                // ==================================================
                // 3. 여행 관리 버튼들
                // ==================================================
                // 각 버튼을 클릭하면 해당 관리 화면으로 이동합니다
                // 모든 버튼은 travelId를 Intent로 전달하여 어떤 여행의 데이터를 표시할지 결정합니다

                Spacer(modifier = Modifier.height(16.dp))

                // 일정 보기 버튼
                // 클릭하면 ScheduleListActivity로 이동하여 해당 여행의 일정 목록을 볼 수 있습니다
                Button(
                        onClick = {
                            // ScheduleListActivity로 이동하는 Intent 생성
                            val intent =
                                    Intent(
                                            this@TravelDetailActivity,
                                            ScheduleListActivity::class.java
                                    )
                            // travelId를 Intent에 저장하여 ScheduleListActivity로 전달합니다
                            // ScheduleListActivity에서 이 travelId로 필터링하여 해당 여행의 일정만 표시합니다
                            intent.putExtra("travelId", travelId)
                            startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) { Text("일정 보기") }

                // 일정 추가 버튼
                // 클릭하면 ScheduleAddActivity로 이동하여 새로운 일정을 추가할 수 있습니다
                Button(
                        onClick = {
                            // ScheduleAddActivity로 이동하는 Intent 생성
                            val intent =
                                    Intent(
                                            this@TravelDetailActivity,
                                            ScheduleAddActivity::class.java
                                    )
                            // travelId를 Intent에 저장하여 ScheduleAddActivity로 전달합니다
                            // ScheduleAddActivity에서 이 travelId를 사용하여 새로 생성하는 Schedule 객체의 travelId
                            // 필드에 저장합니다
                            intent.putExtra("travelId", travelId)
                            startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) { Text("일정 추가") }

                // 체크리스트 버튼
                // 클릭하면 ChecklistActivity로 이동하여 여행 준비 체크리스트를 확인할 수 있습니다
                Button(
                        onClick = {
                            // ChecklistActivity로 이동하는 Intent 생성
                            val intent =
                                    Intent(this@TravelDetailActivity, ChecklistActivity::class.java)
                            // travelId를 Intent에 저장하여 ChecklistActivity로 전달합니다
                            // ChecklistActivity에서 이 travelId로 필터링하여 해당 여행의 체크리스트 상태를 표시합니다
                            intent.putExtra("travelId", travelId)
                            startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                ) { Text("체크리스트") }

                // 예산 관리 버튼
                // 클릭하면 ExpenseActivity로 이동하여 지출 내역을 확인하고 추가할 수 있습니다
                Button(
                        onClick = {
                            // ExpenseActivity로 이동하는 Intent 생성
                            val intent =
                                    Intent(this@TravelDetailActivity, ExpenseActivity::class.java)
                            // travelId를 Intent에 저장하여 ExpenseActivity로 전달합니다
                            // ExpenseActivity에서 이 travelId로 필터링하여 해당 여행의 지출 목록을 표시합니다
                            intent.putExtra("travelId", travelId)
                            startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth()
                ) { Text("예산 관리") }
            }
        }
    }
}
