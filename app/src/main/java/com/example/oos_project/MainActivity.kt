package com.example.oos_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 테스트 스크린으로 바로 이동
        val intent = Intent(this, TestScreenActivity::class.java)
        startActivity(intent)
        finish()
    }
}
