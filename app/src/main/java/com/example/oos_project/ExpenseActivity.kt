package com.example.oos_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oos_project.data.model.Expense
import com.example.oos_project.ui.theme.OOS_ProjectTheme

class ExpenseActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OOS_ProjectTheme { ExpenseUI(onBack = { finish() }) } }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ExpenseUI(onBack: () -> Unit) {
        val travelId = intent.getStringExtra("travelId") ?: ""
        val expenses = remember { AppData.expenseList.filter { it.travelId == travelId }.toMutableStateList() }
        val topBarColor = Color(0xFFE3F2FD)

        var label by remember { mutableStateOf("") }
        var amountString by remember { mutableStateOf("") }
        var date by remember { mutableStateOf("") }

        val addExpense: () -> Unit = {
            val amount = amountString.toIntOrNull() ?: 0
            if (label.isNotEmpty() && amount > 0 && date.isNotEmpty()) {
                val newExpense = Expense(
                    id = "expense-${AppData.expenseList.size + 1}",
                    travelId = travelId,
                    label = label,
                    amount = amount,
                    date = date
                )
                AppData.expenseList.add(newExpense)
                if (travelId.isEmpty() || newExpense.travelId == travelId) {
                    expenses.add(newExpense)
                }
                label = ""
                amountString = ""
                date = ""
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = topBarColor),
                    title = { Text("예산 관리", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "뒤로"
                            )
                        }
                    }
                )
            },
            containerColor = Color(0xFFF5F5F5)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            "새 지출 추가 (여행 ID: ${if (travelId.isNotEmpty()) travelId else "전체"})",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = label,
                            onValueChange = { label = it },
                            label = { Text("항목명 (예: 숙소, 식비)") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = amountString,
                            onValueChange = { amountString = it.filter { char -> char.isDigit() } },
                            label = { Text("금액 (원)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = date,
                            onValueChange = { date = it },
                            label = { Text("날짜 (예: 2025-12-05)") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = addExpense,
                            modifier = Modifier.fillMaxWidth(),
                            enabled = label.isNotEmpty() && amountString.toIntOrNull() != null && date.isNotEmpty()
                        ) {
                            Text("지출 항목 추가")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Divider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "지출 목록 (총 ${expenses.size}개)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(expenses) { expense ->
                        ExpenseListItem(expense = expense)
                    }
                }
            }
        }
    }
}

@Composable
fun ExpenseListItem(expense: Expense) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = expense.label,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${String.format("%,d", expense.amount)}원",
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 17.sp
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "${expense.date} (여행 ID: ${expense.travelId})",
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}
