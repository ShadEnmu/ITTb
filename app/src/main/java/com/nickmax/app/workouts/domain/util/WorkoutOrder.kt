package com.nickmax.app.workouts.domain.util

sealed class WorkoutOrder(val orderType: OrderType) {
    class Date(orderType: OrderType): WorkoutOrder(orderType)
    class SuccessRate(orderType: OrderType): WorkoutOrder(orderType)
}