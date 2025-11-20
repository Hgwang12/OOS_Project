package com.example.oos_project.data.model

data class ChecklistState(
    val travelId: String,  // 이 체크리스트가 어느 여행 것인지 표시
    val passport: Boolean,
    val charger: Boolean,
    val hotelBooked: Boolean,
    val insurance: Boolean,
    val exchangeDone: Boolean
)
// 각 Boolean은 체크박스 항목에 대응

