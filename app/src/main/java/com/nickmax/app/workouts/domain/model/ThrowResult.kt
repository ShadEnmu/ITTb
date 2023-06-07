package com.nickmax.app.workouts.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Workout::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("parentId"),
        onDelete = ForeignKey.CASCADE,
    )]
)
data class ThrowResult(
    val successRate: Int,
    val throwAngle: Int,
    val time: String,
    val isThrowSuccessful: Boolean,
    val parentId: Int? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
