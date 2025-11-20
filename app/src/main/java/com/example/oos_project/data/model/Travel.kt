package com.example.oos_project.data.model

data class Travel(
    val id: String,        // 여행 고유 ID (UUID)
    val title: String,     // 여행명
    val location: String,  // 지역
    val startDate: String, // 여행 시작일
    val endDate: String    // 여행 종료일
)

