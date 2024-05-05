package ru.chatan.switchmob

import kotlin.random.Random

object SwitchChance {
    private const val MIN_SPAWN_CHANCE = 30
    private const val LIGHTNING_CHANCE = 90
    private const val EXPLOSIVE_SPAWN_CHANCE = 50

    private fun getChance() = Random.nextInt(0, 100)

    fun canSwitch(): Boolean = MIN_SPAWN_CHANCE >= getChance()
    fun canLightning(): Boolean = LIGHTNING_CHANCE >= getChance()
    fun canExplosive(): Boolean = EXPLOSIVE_SPAWN_CHANCE >= getChance()
}