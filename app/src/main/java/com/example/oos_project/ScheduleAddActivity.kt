package com.example.oos_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.oos_project.data.model.Schedule
import com.example.oos_project.ui.theme.OOS_ProjectTheme
import java.util.UUID

class ScheduleAddActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OOS_ProjectTheme { ScheduleAddUI() } }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScheduleAddUI() {
        val travelId = intent.getStringExtra("travelId") ?: ""
        val topBarColor = Color(0xFFE3F2FD)

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = topBarColor),
                    title = { Text("일정 추가", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = { finish() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "뒤로"
                            )
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 20.dp)
            ) {
                var title by remember { mutableStateOf("") }
                var time by remember { mutableStateOf("") }
                var memo by remember { mutableStateOf("") }

                Spacer(modifier = Modifier.height(20.dp))
                InputInformation(
                    title = "일정 제목",
                    inputex = "예: 맛집 탐방",
                    input = title,
                    onValueChange = { title = it }
                )

                Spacer(modifier = Modifier.height(20.dp))
                InputInformation(
                    title = "시간",
                    inputex = "예: 10:00",
                    input = time,
                    onValueChange = { time = it }
                )

                Spacer(modifier = Modifier.height(20.dp))
                InputInformation(
                    title = "메모",
                    inputex = "예: 예약 필수, 3번 출구 앞",
                    input = memo,
                    onValueChange = { memo = it }
                )

                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    onClick = {
                        val newId = UUID.randomUUID().toString()
                        val newSchedule = Schedule(
                            id = newId,
                            travelId = travelId,
                            title = title,
                            time = time,
                            memo = memo
                        )
                        AppData.scheduleList.add(newSchedule)
                        finish()
                    }
                ) {
                    Text("저장하기")
                }
            }
        }
    }

    @Composable
    fun InputInformation(
        title: String,
        inputex: String,
        input: String,
        onValueChange: (String) -> Unit
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = title)
                Spacer(modifier = Modifier.height(5.dp))
                TextField(
                    value = input,
                    label = { Text(inputex) },
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        }
    }
}
