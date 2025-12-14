package com.example.oos_project

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oos_project.data.model.Schedule
import com.example.oos_project.ui.theme.OOS_ProjectTheme

class ScheduleListActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OOS_ProjectTheme { ScheduleListUI() } }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScheduleListUI() {
        val travelId = intent.getStringExtra("travelId") ?: ""

        var scheduleList by remember {
            mutableStateOf(
                if (travelId.isNotEmpty()) {
                    AppData.scheduleList.filter { it.travelId == travelId }
                } else {
                    AppData.scheduleList.toList()
                }
            )
        }

        val travel = if (travelId.isNotEmpty()) {
            AppData.travelList.find { it.id == travelId }
        } else {
            null
        }

        val topBarColor = Color(0xFFE3F2FD)
        val accentColor = Color(0xFF2196F3)

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = topBarColor),
                    title = {
                        Column {
                            Text(travel?.title ?: "여행 일정", fontWeight = FontWeight.Bold)
                            if (travel != null) {
                                Text("${travel.location} Travel Plan", fontSize = 12.sp)
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { finish() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "뒤로가기"
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        val intent = Intent(this@ScheduleListActivity, ScheduleAddActivity::class.java)
                        intent.putExtra("travelId", travelId)
                        startActivity(intent)
                    },
                    containerColor = accentColor
                ) { Icon(imageVector = Icons.Default.Add, contentDescription = "추가") }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                scheduleList.forEach { schedule -> ScheduleCard(schedule = schedule) }
            }
        }
    }

    @Composable
    fun ScheduleCard(schedule: Schedule) {
        val cardColor = Color(0xFFF5F5F5)
        val accentColor = Color(0xFF2196F3)
        val timeCardColor = Color(0xFFE3F2FD)

        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier.padding(end = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = timeCardColor)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "시계",
                            tint = accentColor,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(schedule.time)
                    }
                }
                Column {
                    Text(schedule.title)
                    Text(schedule.memo, fontSize = 12.sp)
                }
            }
        }
    }
}
