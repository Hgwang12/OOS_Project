package com.example.oos_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * ==================================================
 * 📌 MainActivity - 앱 시작점
 * ==================================================
 *
 * 역할:
 * - 앱이 시작되면 가장 먼저 실행되는 Activity입니다
 * - 사용자에게 UI를 보여주지 않고 바로 TestScreenActivity로 이동합니다
 *
 * 데이터 흐름:
 * - Intent로 데이터를 전달하지 않습니다
 * - TestScreenActivity에서 각 페이지로 이동할 수 있는 메뉴를 제공합니다
 *
 * 화면 이동:
 * - MainActivity → TestScreenActivity (즉시 이동, finish()로 MainActivity 종료)
 */
class MainActivity : Activity() {
    /**
     * Activity가 생성될 때 호출되는 함수
     * - 앱이 시작되면 이 함수가 실행됩니다
     * - TestScreenActivity로 Intent를 생성하여 이동합니다
     * - finish()를 호출하여 MainActivity를 종료하므로 사용자는 MainActivity를 볼 수 없습니다
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TestScreenActivity로 이동하는 Intent 생성
        // TestScreenActivity는 각 페이지로 이동할 수 있는 테스트용 메뉴 화면입니다
        val intent = Intent(this, TestScreenActivity::class.java)
        startActivity(intent)
        // MainActivity를 종료하여 뒤로가기 버튼을 눌러도 MainActivity로 돌아오지 않도록 합니다
        finish()
    }
}
