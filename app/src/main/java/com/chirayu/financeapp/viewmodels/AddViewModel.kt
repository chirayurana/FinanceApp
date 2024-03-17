package com.chirayu.financeapp.viewmodels

import androidx.lifecycle.ViewModel
import com.chirayu.financeapp.models.Regularity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate


data class AddScreenState(
    val amount: String = "",
    val regularity: Regularity? = null,
    val date: LocalDate = LocalDate.now(),
    val note: String = "",
    val category:String? = null//TODO: Replace it when you build the Category Model


)

class AddViewModel: ViewModel()
{
    private val _uiState = MutableStateFlow(AddScreenState())
    val uiState: StateFlow<AddScreenState> = _uiState.asStateFlow()

    fun setAmount(amount: String){
        _uiState.update { currentState ->
            currentState.copy(
                amount = amount.trim(),
            )
        }
    }
    fun setRegularity(regularity: Regularity){
        _uiState.update { currentState ->
            currentState.copy(
                regularity = regularity,
            )
        }
    }
    fun setDate(date:LocalDate){
        _uiState.update { currentState ->
            currentState.copy(
                date = date,
            )
        }
    }
    fun setNotes(note:String){
        _uiState.update { currentState ->
            currentState.copy(
                note = note,
            )
        }
    }
    fun setCategory(category:String){ //TODO: Replace String with actual Class
        _uiState.update { currentState ->
            currentState.copy(
                category = category,
            )
        }
    }

    fun submitExpenses(){
        //TODO: Save to Database
        

    }
}