package com.example.oos_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class TestScreenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 테스트 스크린 레이아웃
        val layout =
                LinearLayout(this).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(40, 40, 40, 40)
                }

        // 제목
        val title =
                TextView(this).apply {
                    text = "테스트 스크린"
                    textSize = 24f
                }

        // 버튼 6개 - 각 페이지로 이동
        val btnHome = Button(this).apply { text = "홈" }

        val btnScheduleAdd = Button(this).apply { text = "일정 추가" }

        val btnScheduleList = Button(this).apply { text = "일정보기" }

        val btnChecklist = Button(this).apply { text = "체크리스트" }

        val btnExpense = Button(this).apply { text = "예산 관리" }

        // 레이아웃에 추가
        layout.addView(title)
        layout.addView(btnHome)
        layout.addView(btnScheduleAdd)
        layout.addView(btnScheduleList)
        layout.addView(btnChecklist)
        layout.addView(btnExpense)

        setContentView(layout)

        // 버튼 클릭 - 각 페이지로 이동
        btnHome.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        btnScheduleAdd.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, ScheduleAddActivity::class.java)
            startActivity(intent)
        }

        btnScheduleList.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, ScheduleListActivity::class.java)
            startActivity(intent)
        }

        btnChecklist.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, ChecklistActivity::class.java)
            startActivity(intent)
        }

        btnExpense.setOnClickListener {
            val intent = Intent(this@TestScreenActivity, ExpenseActivity::class.java)
            startActivity(intent)
        }
    }
}
