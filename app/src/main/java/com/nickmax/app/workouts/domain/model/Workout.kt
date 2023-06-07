package com.nickmax.app.workouts.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout (
    val dateStart: Long,
    val dateEnd: Long,
    val averageAngle: Int,
    val averageSuccessRate: Int,
    val hitsRate: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)