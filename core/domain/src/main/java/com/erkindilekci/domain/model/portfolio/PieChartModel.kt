package com.erkindilekci.domain.model.portfolio

data class PieChartModel(
    val price: Double,
    val symbol: String,
    val isClicked: Boolean = false
)
