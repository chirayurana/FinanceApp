package com.chirayu.financeapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chirayu.financeapp.components.TableRow
import com.chirayu.financeapp.components.UnstyledTextField
import com.chirayu.financeapp.models.Regularity
import com.chirayu.financeapp.ui.theme.BackgroundElevated
import com.chirayu.financeapp.ui.theme.DividerColor
import com.chirayu.financeapp.ui.theme.Primary
import com.chirayu.financeapp.ui.theme.Shapes
import com.chirayu.financeapp.ui.theme.TopAppBarBackground
import com.chirayu.financeapp.ui.theme.Typography
import com.chirayu.financeapp.viewmodels.AddViewModel
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Add(navController: NavController, addViewModel: AddViewModel = viewModel()){
    val state by addViewModel.uiState.collectAsState()
    val regularities = listOf(
        Regularity.None,
        Regularity.Daily,
        Regularity.Weekly,
        Regularity.Monthly,
        Regularity.Yearly
    )
    val categories = listOf(
        "Shopping",
        "Health",
        "Bills",
        "Housing",
        "Education"
    )

    Scaffold(
        topBar = {
            MediumTopAppBar(title = { Text(text = "Add") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground
            ))
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .clip(Shapes.medium)
                    .background(BackgroundElevated)
                    .fillMaxWidth()
                ) {
                    //Amount Table Row
                    TableRow(label = "Amount"){
                        UnstyledTextField(
                            value = state.amount,
                            onValueChange = addViewModel::setAmount,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {Text(text = "0")},
                            arrangement = Arrangement.End,
                            maxLines = 1,
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                            )
                    }
                    Divider(
                        modifier = Modifier
                            .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    //Regularity Table Row
                    TableRow(label = "Regularity")
                    {
                        var regularityMenuOpened by remember{
                            mutableStateOf(false)
                        }
                        TextButton(onClick = {regularityMenuOpened = true},
                            shape = Shapes.large)
                            {
                            Text(text = state.regularity?.name ?: Regularity.None.name)
                            DropdownMenu(expanded = regularityMenuOpened, onDismissRequest = {regularityMenuOpened = false}) {
                                regularities.forEach{ regularity ->
                                    DropdownMenuItem(
                                        text = {Text(regularity.name)},

                                        onClick = {
                                            addViewModel.setRegularity(regularity)
                                            regularityMenuOpened = false
                                        })

                                }
                            }
                            
                        }
                    }
                    Divider(
                        modifier = Modifier
                            .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )

                    var datePickerShowing by remember {
                        mutableStateOf(false)
                    }
                    //Date Table Row
                    TableRow(label = "Date"){
                        TextButton(onClick = { datePickerShowing = true}) {
                            Text(state.date.toString())
                        }
                        if(datePickerShowing){
                            DatePickerDialog(
                                onDismissRequest = {datePickerShowing = false},
                                onDateChange = {it ->
                                    addViewModel.setDate(it)
                                    datePickerShowing = false
                                },
                                initialDate = state.date,
                                title = { Text(text = "Select Date" , style = Typography.titleMedium)}
                            )
                        }

                    }
                    Divider(
                        modifier = Modifier
                            .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    //Notes Table Row
                    TableRow(label = "Note"){
                        UnstyledTextField(
                            value = state.note,
                            placeholder = { Text(text = "Leave some notes here") },
                            arrangement = Arrangement.End,
                            onValueChange = addViewModel::setNotes,
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Right
                            ),
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .padding(start = 16.dp), thickness = 1.dp, color = DividerColor
                    )
                    //Category Table Row
                    TableRow(label = "Category"){
                        var categoriesMenuOpened by remember{
                            mutableStateOf(false)
                        }
                        TextButton(onClick = {categoriesMenuOpened = true}) {
                            Text(text = state.category ?: "Select a Category")
                            DropdownMenu(expanded = categoriesMenuOpened, onDismissRequest = {categoriesMenuOpened = false}) {
                                categories.forEach{ category ->
                                    DropdownMenuItem(
                                        text = {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Surface(
                                                    modifier = Modifier.size(10.dp),
                                                    shape = CircleShape,
                                                    color = Primary
                                                ) {}
                                                Text(category, modifier = Modifier.padding(start = 8.dp))
                                            }
                                           },
                                        onClick = {
                                            addViewModel.setCategory(category)
                                            categoriesMenuOpened = false
                                        })

                                }
                            }

                        }
                    }

                }
                Button(onClick = addViewModel::submitExpenses,
                    modifier = Modifier.padding(16.dp),
                    shape = Shapes.large) {
                    Text(text = "Submit Expense")
                }

            }

        }
    )
}