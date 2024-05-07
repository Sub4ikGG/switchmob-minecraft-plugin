package ru.chatan.switchmob

import org.bukkit.Location

interface SwitchMobEffect {
    /**
     * Creates effect on [location]
     */
    fun createEffect(location: Location)
}