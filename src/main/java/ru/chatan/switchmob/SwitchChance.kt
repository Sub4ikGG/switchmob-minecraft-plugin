package ru.chatan.switchmob

import kotlin.random.Random

object SwitchChance {
    private var MIN_SPAWN_CHANCE = 30
    private var LIGHTNING_CHANCE = 90
    private var EXPLOSION_CHANCE = 50

    private fun getChance() = Random.nextInt(0, 100)

    fun canSpawn(): Boolean = MIN_SPAWN_CHANCE >= getChance()
    fun canLightning(): Boolean = LIGHTNING_CHANCE >= getChance()
    fun canExplosive(): Boolean = EXPLOSION_CHANCE >= getChance()

    fun changeSpawnPercentage(percentage: Int) {
        MIN_SPAWN_CHANCE = percentage
    }

    fun changeLightningChance(percentage: Int) {
        LIGHTNING_CHANCE = percentage
    }

    fun changeExplosionChance(percentage: Int) {
        EXPLOSION_CHANCE = percentage
    }
}