package com.chirayu.financeapp.models

sealed class Regularity(val name:String, val short:String) {
    object None : Regularity("None", "None")
    object Daily : Regularity("Daily", "Day")
    object Weekly : Regularity("Weekly", "Week")
    object Monthly : Regularity("Monthly", "Month")
    object Yearly : Regularity("Yearly", "Year")


    val regularities = listOf(Regularity.None, Regularity.Daily)
    val regularity = regularities[0]
}



