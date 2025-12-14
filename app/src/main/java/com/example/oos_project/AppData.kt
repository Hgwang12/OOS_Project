package com.example.oos_project

import com.example.oos_project.data.model.ChecklistState
import com.example.oos_project.data.model.Expense
import com.example.oos_project.data.model.Schedule
import com.example.oos_project.data.model.Travel

object AppData {

    val travelList = mutableListOf<Travel>()
    val scheduleList = mutableListOf<Schedule>()
    val expenseList = mutableListOf<Expense>()
    val checklistStates = mutableListOf<ChecklistState>()

    init {
        val tokyoTravel = Travel(
            id = "travel-001",            // 이 ID로 다른 데이터들과 연결됨
            title = "도쿄 3박 4일 여행",    // 여행 제목
            location = "일본 도쿄",          // 지역
            startDate = "2025-03-01",      // 여행 시작일
            endDate = "2025-03-04"         // 여행 종료일
        )
        travelList.add(tokyoTravel)

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

