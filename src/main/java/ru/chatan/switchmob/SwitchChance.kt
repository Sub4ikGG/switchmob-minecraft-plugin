package ru.chatan.switchmob

import kotlin.random.Random

object SwitchChance {
    fun getChance() = Random.nextInt(0, 100)
}