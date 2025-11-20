package com.example.oos_project.data.model

data class Expense(
    val id: String,
    val travelId: String,  // Travel과 연결
    val label: String,     // 지출 항목명
    val amount: Int,       // 금액
    val date: String       // 지출 날짜
)

