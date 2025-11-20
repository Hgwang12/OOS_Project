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
 * ================================================== 📌 ExpenseActivity - 예산 관리 화면
 * ==================================================
 *
 * 역할:
 * - 특정 여행의 지출 항목을 추가하고 목록을 표시하는 화면입니다
 * - 사용자가 지출 항목명, 금액, 날짜를 입력하면 AppData.expenseList에 Expense 객체를 추가합니다
 *
 * 데이터 흐름:
 * - Intent로 "travelId"를 받아서 새로 생성하는 Expense 객체의 travelId 필드에 저장합니다
 * - travelId는 이 지출이 어떤 여행에 속하는지를 나타냅니다
 * - 사용자가 입력한 label(항목명), amount(금액), date(날짜)를 사용하여 Expense 객체를 생성합니다
 * - 생성한 Expense 객체를 AppData.expenseList에 추가합니다
 * - 화면 하단에 travelId로 필터링한 지출 목록을 표시합니다
 *
 * 화면 이동:
 * - TravelDetailActivity 또는 TestScreenActivity에서 이 화면으로 이동합니다 (travelId 전달 또는 미전달)
 * - 뒤로가기 버튼을 누르면 이전 화면으로 돌아갑니다
 */
class ExpenseActivity : ComponentActivity() {
    /**
     * Activity가 생성될 때 호출되는 함수
     * - setContent로 Compose UI를 설정합니다
     * - ExpenseUI() Composable 함수를 호출하여 화면을 구성합니다
     */
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OOS_ProjectTheme { ExpenseUI() } }
    }

    /**
     * ================================================== ExpenseUI - 예산 관리 UI를 표시하는 Composable 함수
     * ==================================================
     *
     * 역할:
     * - 사용자가 지출 정보를 입력할 수 있는 TextField들을 제공합니다
     * - 저장 버튼을 클릭하면 입력한 정보로 Expense 객체를 생성하여 AppData.expenseList에 추가합니다
     * - 화면 하단에 travelId로 필터링한 지출 목록을 표시합니다
     *
     * 데이터 입력 및 저장:
     * - travelId: Intent로 받은 값으로, 새로 생성하는 Expense 객체의 travelId 필드에 저장됩니다
     * - label: 사용자가 입력하는 지출 항목명 (TextField로 입력, 예: "식비", "숙박비")
     * - amount: 사용자가 입력하는 지출 금액 (TextField로 입력, Int 타입으로 변환 필요)
     * - date: 사용자가 입력하는 지출 날짜 (TextField로 입력, 예: "2024-01-15")
     * - 저장 버튼 클릭 시: Expense(id, travelId, label, amount, date) 객체를 생성하여 AppData.expenseList.add()로
     * 추가
     *
     * 데이터 표시:
     * - AppData.expenseList에서 travelId로 필터링한 지출 목록을 표시합니다
     * - travelId가 비어있으면 모든 지출을 표시하고, 있으면 해당 여행의 지출만 표시합니다
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ExpenseUI() {
        // Intent로 전달받은 travelId를 가져옵니다
        // "travelId"라는 키로 저장된 값을 읽고, 없으면 빈 문자열("")을 사용합니다
        // travelId는 새로 생성하는 Expense 객체의 travelId 필드에 저장되어, 이 지출이 어떤 여행에 속하는지 나타냅니다
        val travelId = intent.getStringExtra("travelId") ?: ""

        // Scaffold는 Material Design의 기본 화면 구조입니다
        Scaffold(
                topBar = {
                    // 화면 상단에 표시되는 앱 바
                    TopAppBar(
                            title = { Text("예산 관리") },
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

                // travelId를 전달받아 지출 항목 추가
                // - travelId 변수는 이미 위에서 Intent로부터 가져왔습니다
                // - 이 travelId는 새로 생성하는 Expense 객체의 travelId 필드에 저장됩니다

                // TextField로 label, amount, date 입력받기
                // - remember { mutableStateOf("") }를 사용하여 각 입력 필드의 상태를 관리합니다
                // - 예: var label by remember { mutableStateOf("") }
                // - 예: var amount by remember { mutableStateOf("") }
                // - 예: var date by remember { mutableStateOf("") }
                // - TextField의 value와 onValueChange를 연결하여 사용자 입력을 실시간으로 저장합니다
                // - label: 지출 항목명을 입력받습니다 (예: "식비", "숙박비", "교통비")
                // - amount: 지출 금액을 입력받습니다 (예: "50000", "100000")
                // - date: 지출 날짜를 입력받습니다 (예: "2024-01-15", "2024-01-20")

                // Button으로 지출 추가 → AppData.expenseList에 Expense 객체 추가
                // - 저장 버튼을 클릭하면:
                //   1. 입력한 label, amount, date 값을 가져옵니다
                //   2. amount를 Int 타입으로 변환합니다 (amount.toIntOrNull() ?: 0)
                //   3. Expense 객체를 생성합니다:
                //      Expense(
                //          id = UUID.randomUUID().toString() 또는 AppData.expenseList.size + 1 등으로 고유
                // ID 생성,
                //          travelId = travelId (Intent로 받은 값),
                //          label = label (사용자 입력),
                //          amount = amount.toIntOrNull() ?: 0 (사용자 입력, Int로 변환),
                //          date = date (사용자 입력)
                //      )
                //   4. AppData.expenseList.add(newExpense)로 리스트에 추가합니다
                //   5. 입력 필드를 초기화합니다 (label = "", amount = "", date = "")

                // 지출 목록 표시 (travelId로 필터링)
                // - AppData.expenseList에서 travelId로 필터링한 지출 목록을 표시합니다
                // - travelId가 비어있으면 모든 지출을 표시하고, 있으면 해당 여행의 지출만 표시합니다
                // - 예: val expenseList = if (travelId.isNotEmpty()) {
                //          AppData.expenseList.filter { it.travelId == travelId }
                //        } else {
                //          AppData.expenseList.toList()
                //        }
                // - expenseList.forEach { expense -> ... }로 각 지출을 카드나 리스트 아이템으로 표시합니다
                // - 각 지출 카드에는 label(항목명), amount(금액), date(날짜)를 표시합니다
            }
        }
    }
}
