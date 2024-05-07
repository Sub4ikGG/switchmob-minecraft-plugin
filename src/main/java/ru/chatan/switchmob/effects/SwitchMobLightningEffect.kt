package ru.chatan.switchmob.effects

import org.bukkit.Location
import ru.chatan.switchmob.SwitchMobEffect

class SwitchMobLightningEffect : SwitchMobEffect {
    override fun createEffect(location: Location) {
        location.world.strikeLightningEffect(location)
    }
}