package com.example.oos_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

/**
 * ================================================== 📌 TestScreenActivity - 테스트용 메뉴 화면
 * ==================================================
 *
 * 역할:
 * - 앱의 각 페이지로 이동할 수 있는 테스트용 메뉴를 제공합니다
 * - 개발 및 테스트 단계에서 각 Activity로 빠르게 이동하기 위한 화면입니다
 *
 * 데이터 흐름:
 * - AppData.travelList를 읽어서 첫 번째 여행의 id를 travelId로 사용합니다
 * - TravelDetailActivity로 이동할 때 travelId를 Intent로 전달합니다
 * - 다른 Activity로 이동할 때는 travelId를 전달하지 않습니다 (해당 Activity에서 필요시 빈 문자열로 처리)
 *
 * 화면 이동:
 * - MainActivity에서 이 화면으로 이동합니다
 * - 이 화면에서 6개의 버튼을 통해 각 Activity로 이동할 수 있습니다:
 * 1. 홈 → HomeActivity
 * 2. 여행 상세 → TravelDetailActivity (travelId 전달)
 * 3. 일정 추가 → ScheduleAddActivity
 * 4. 일정보기 → ScheduleListActivity
 * 5. 체크리스트 → ChecklistActivity
 * 6. 예산 관리 → ExpenseActivity
 */
class TestScreenActivity : Activity() {
    /**
     * Activity가 생성될 때 호출되는 함수
     * - LinearLayout을 사용하여 수직으로 버튼들을 배치합니다
     * - 각 버튼에 클릭 리스너를 설정하여 해당 Activity로 이동합니다
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 수직 방향으로 요소들을 배치하는 레이아웃 생성
        // padding을 40으로 설정하여 화면 가장자리와의 여백을 만듭니다
        val layout =
                LinearLayout(this).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(40, 40, 40, 40)
                }

        // 화면 상단에 표시될 제목 텍스트
        val title =
                TextView(this).apply {
                    text = "테스트 스크린"
                    textSize = 24f
                }

        // 각 페이지로 이동하는 버튼들 생성
        // 버튼 7개 - 각 페이지로 이동
        val btnHome = Button(this).apply { text = "홈" }

        val btnTravelDetail = Button(this).apply { text = "여행 상세" }

        val btnScheduleAdd = Button(this).apply { text = "일정 추가" }

        val btnScheduleList = Button(this).apply { text = "일정보기" }

        val btnChecklist = Button(this).apply { text = "체크리스트" }

        val btnExpense = Button(this).apply { text = "예산 관리" }

        // 레이아웃에 제목과 버튼들을 순서대로 추가
        layout.addView(title)
        layout.addView(btnHome)
        layout.addView(btnTravelDetail)
        layout.addView(btnScheduleAdd)
        layout.addView(btnScheduleList)
        layout.addView(btnChecklist)
        layout.addView(btnExpense)

        // 생성한 레이아웃을 화면에 표시
        setContentView(layout)

        // ==================================================
        // 버튼 클릭 리스너 설정 - 각 페이지로 이동
        // ==================================================

        // 홈 버튼 클릭 → HomeActivity로 이동
        // HomeActivity는 AppData.travelList를 표시하는 화면입니다
        // Intent로 데이터를 전달하지 않습니다
        btnHome.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        // 여행 상세 버튼 클릭 → TravelDetailActivity로 이동
        // travelId를 Intent로 전달합니다
        // AppData.travelList에 여행이 있으면 첫 번째 여행의 id를 사용하고, 없으면 빈 문자열을 전달합니다
        btnTravelDetail.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, TravelDetailActivity::class.java)
            // 테스트용: AppData에 travel이 있으면 첫 번째 travelId 사용, 없으면 빈 문자열
            // travelId는 Travel 객체의 고유 식별자로, 다른 데이터(Schedule, Expense 등)와 여행을 연결하는 데 사용됩니다
            val travelId =
                    if (AppData.travelList.isNotEmpty()) {
                        AppData.travelList[0].id
                    } else {
                        ""
                    }
            // Intent에 "travelId"라는 키로 travelId 값을 저장하여 TravelDetailActivity로 전달합니다
            intent.putExtra("travelId", travelId)
            startActivity(intent)
        }

        // 일정 추가 버튼 클릭 → ScheduleAddActivity로 이동
        // Intent로 데이터를 전달하지 않습니다 (ScheduleAddActivity에서 travelId를 받지 않음)
        btnScheduleAdd.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, ScheduleAddActivity::class.java)
            startActivity(intent)
        }

        // 일정보기 버튼 클릭 → ScheduleListActivity로 이동
        // Intent로 데이터를 전달하지 않습니다 (ScheduleListActivity에서 travelId를 받지 않으면 전체 일정 표시)
        btnScheduleList.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, ScheduleListActivity::class.java)
            startActivity(intent)
        }

        // 체크리스트 버튼 클릭 → ChecklistActivity로 이동
        // Intent로 데이터를 전달하지 않습니다
        btnChecklist.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, ChecklistActivity::class.java)
            startActivity(intent)
        }

        // 예산 관리 버튼 클릭 → ExpenseActivity로 이동
        // Intent로 데이터를 전달하지 않습니다
        btnExpense.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, ExpenseActivity::class.java)
            startActivity(intent)
        }
    }
}
