package com.nickmax.app.workouts.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
