package com.example.oos_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oos_project.data.model.ChecklistState
import com.example.oos_project.ui.theme.OOS_ProjectTheme

class ChecklistActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OOS_ProjectTheme { ChecklistUI() } }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ChecklistUI() {
        val travelId = intent.getStringExtra("travelId") ?: ""
        val totalCount = 5
        val topBarColor = Color(0xFFE3F2FD)

        val currentChecklistState = remember(travelId) {
            AppData.checklistStates.find { it.travelId == travelId }
                ?: ChecklistState(
                    travelId = travelId,
                    passport = false,
                    charger = false,
                    hotelBooked = false,
                    insurance = false,
                    exchangeDone = false,
                ).also { AppData.checklistStates.add(it) }
        }

        val checklistIndex = AppData.checklistStates.indexOfFirst { it.travelId == travelId }
        val latestState = if (checklistIndex != -1) {
            AppData.checklistStates[checklistIndex]
        } else {
            currentChecklistState
        }

        val checkedCount = listOf(
            latestState.passport,
            latestState.charger,
            latestState.hotelBooked,
            latestState.insurance,
            latestState.exchangeDone
        ).count { it }

        val uncheckedCount = totalCount - checkedCount
        val checkedWeight = checkedCount.toFloat()
        val uncheckedWeight = uncheckedCount.toFloat()

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = topBarColor),
                    title = { Text("체크리스트", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = { finish() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6E6FA)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = "$checkedCount / $totalCount",
                            fontSize = 30.sp
                        )

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(bottom = 20.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Row(modifier = Modifier.fillMaxSize()) {
                                if (checkedWeight > 0) {
                                    Box(
                                        modifier = Modifier
                                            .weight(checkedWeight)
                                            .fillMaxHeight()
                                            .background(Color(0xFF673AB7)),
                                    )
                                }
                                if (uncheckedWeight > 0) {
                                    Box(
                                        modifier = Modifier
                                            .weight(uncheckedWeight)
                                            .fillMaxHeight()
                                            .background(Color.LightGray),
                                    )
                                }
                            }
                        }
                    }
                    Text("준비 항목")

                    val checklistItems = listOf(
                        "여권(passport)" to latestState.passport,
                        "충전기(charger)" to latestState.charger,
                        "호텔 예약(hotelBooked)" to latestState.hotelBooked,
                        "여행 보험(insurance)" to latestState.insurance,
                        "환전 완료(exchangeDone)" to latestState.exchangeDone
                    )

                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(checklistItems.size) { index ->
                            val (label, isChecked) = checklistItems[index]

                            ChecklistItemCard(
                                label = label,
                                isChecked = isChecked,
                                onCheckedChange = { newCheckedState ->
                                    if (checklistIndex != -1) {
                                        val updatedState = when (index) {
                                            0 -> latestState.copy(passport = newCheckedState)
                                            1 -> latestState.copy(charger = newCheckedState)
                                            2 -> latestState.copy(hotelBooked = newCheckedState)
                                            3 -> latestState.copy(insurance = newCheckedState)
                                            4 -> latestState.copy(exchangeDone = newCheckedState)
                                            else -> latestState
                                        }
                                        AppData.checklistStates[checklistIndex] = updatedState
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ChecklistItemCard(
        label: String,
        isChecked: Boolean,
        onCheckedChange: (Boolean) -> Unit
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = onCheckedChange
                )
                Text(label)
            }
        }
    }
}
