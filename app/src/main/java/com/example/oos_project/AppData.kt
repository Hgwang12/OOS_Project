package com.example.oos_project

import com.example.oos_project.data.model.ChecklistState
import com.example.oos_project.data.model.Expense
import com.example.oos_project.data.model.Schedule
import com.example.oos_project.data.model.Travel

/**
 * ==================================================
 * 📌 AppData - 앱 전체 데이터 저장소
 * ==================================================
 *
 * 이 object는 앱 전체에서 공유되는 모든 데이터를 저장하는 중앙 저장소입니다.
 * ViewModel이나 Repository 대신 이 object를 사용하여 앱 전체의 상태를 관리합니다.
 *
 * 데이터 흐름:
 * - 모든 Activity에서 이 object의 리스트들을 직접 읽고 씁니다
 * - travelId를 통해 각 데이터가 어떤 여행에 속하는지 연결됩니다
 * - 예: Schedule.travelId == Travel.id 이면 그 일정은 해당 여행의 일정입니다
 */
object AppData {

    /**
     * 앱 전체에서 공유되는 여행 목록
     * - HomeActivity에서 여행 목록을 표시할 때 사용
     * - TravelDetailActivity에서 특정 여행 정보를 찾을 때 사용
     * - 각 여행은 고유한 id(String)를 가지고 있어서 다른 데이터와 연결됩니다
     * - 예: travelList[0].id == "travel-001" 이면, scheduleList에서 travelId == "travel-001"인 일정들이 이 여행의 일정입니다
     */
    val travelList = mutableListOf<Travel>()

    /**
     * 일정 목록
     * - ScheduleAddActivity에서 새로운 일정을 추가하면 이 리스트에 추가됩니다
     * - ScheduleListActivity에서 travelId로 필터링하여 특정 여행의 일정만 표시합니다
     * - 각 Schedule 객체는 travelId 필드를 가지고 있어서 어떤 여행의 일정인지 구분합니다
     * - 예: schedule.travelId == "travel-001" 이면 이 일정은 travelList에서 id가 "travel-001"인 여행의 일정입니다
     */
    val scheduleList = mutableListOf<Schedule>()

    /**
     * 지출 목록
     * - ExpenseActivity에서 새로운 지출을 추가하면 이 리스트에 추가됩니다
     * - 각 Expense 객체는 travelId 필드를 가지고 있어서 어떤 여행의 지출인지 구분합니다
     * - ExpenseActivity에서 travelId로 필터링하여 특정 여행의 지출만 표시합니다
     */
    val expenseList = mutableListOf<Expense>()

    /**
     * 체크리스트 상태 목록
     * - ChecklistActivity에서 체크리스트 항목의 체크 상태를 저장합니다
     * - 각 ChecklistState 객체는 travelId 필드를 가지고 있어서 어떤 여행의 체크리스트인지 구분합니다
     * - passport, charger, hotelBooked, insurance, exchangeDone 등의 Boolean 값으로 각 항목의 체크 여부를 저장합니다
     */
    val checklistStates = mutableListOf<ChecklistState>()

    // ==================================================
    // 🔥 앱 구동 시 단 한 번 실행되는 init 블록
    // ==================================================
    // 이 블록은 AppData object가 처음 사용될 때 한 번만 실행됩니다
    // 모든 Activity에서 UI를 확인할 수 있도록 참고용 테스트 데이터를 추가합니다
    init {
        // -----------------------------------------
        // 1) Travel 더미 데이터 (도쿄 여행)
        // -----------------------------------------
        // travel-001 ID를 가진 도쿄 여행 데이터를 추가합니다
        // 이 ID는 다른 데이터들(Schedule, Expense, ChecklistState)과 연결하는 데 사용됩니다
        val tokyoTravel = Travel(
            id = "travel-001",            // 이 ID로 다른 데이터들과 연결됨
            title = "도쿄 3박 4일 여행",    // 여행 제목
            location = "일본 도쿄",          // 지역
            startDate = "2025-03-01",      // 여행 시작일
            endDate = "2025-03-04"         // 여행 종료일
        )
        travelList.add(tokyoTravel)

        // -----------------------------------------
        // 2) Schedule 더미 데이터 (travelId 연결)
        // -----------------------------------------
        // travelId = "travel-001"로 설정하여 위에서 추가한 도쿄 여행에 연결합니다
        // ScheduleListActivity에서 travelId로 필터링하면 이 일정들이 표시됩니다
        scheduleList.add(
            Schedule(
                id = "schedule-001",
                travelId = "travel-001",     // travel-001 == 도쿄 여행에 연결됨
                title = "센소지 방문",
                time = "10:00",
                memo = "아사쿠사역 1번 출구에서 도보 5분"
            )
        )

        scheduleList.add(
            Schedule(
                id = "schedule-002",
                travelId = "travel-001",
                title = "스카이트리 전망대",
                time = "15:00",
                memo = "미리 예약 필요"
            )
        )

        // -----------------------------------------
        // 3) Expense 더미 데이터
        // -----------------------------------------
        // travelId = "travel-001"로 설정하여 도쿄 여행의 지출로 연결합니다
        // ExpenseActivity에서 travelId로 필터링하면 이 지출들이 표시됩니다
        expenseList.add(
            Expense(
                id = "expense-001",
                travelId = "travel-001",
                label = "숙소 결제",
                amount = 480000,
                date = "2025-03-01"
            )
        )

        expenseList.add(
            Expense(
                id = "expense-002",
                travelId = "travel-001",
                label = "지하철 교통비",
                amount = 15000,
                date = "2025-03-02"
            )
        )

        // -----------------------------------------
        // 4) Checklist 더미 데이터
        // -----------------------------------------
        // travelId = "travel-001"로 설정하여 도쿄 여행의 체크리스트로 연결합니다
        // ChecklistActivity에서 travelId로 필터링하면 이 체크리스트 상태가 표시됩니다
        checklistStates.add(
            ChecklistState(
                travelId = "travel-001",
                passport = true,        // 여권 준비 완료
                charger = false,        // 충전기 준비 미완료
                hotelBooked = true,     // 호텔 예약 완료
                insurance = false,      // 여행 보험 미완료
                exchangeDone = true     // 환전 완료
            )
        )
    }
}

