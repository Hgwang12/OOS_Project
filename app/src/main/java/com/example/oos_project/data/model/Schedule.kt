package com.example.oos_project.data.model

data class Schedule(
    val id: String,        // 일정 고유 ID (UUID)
    val travelId: String,  // 이 일정이 속한 Travel의 ID
    val title: String,     // 일정 제목
    val time: String,      // 일정 시간
    val memo: String       // 간단 메모
)

