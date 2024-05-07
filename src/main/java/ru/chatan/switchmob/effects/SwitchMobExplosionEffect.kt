package ru.chatan.switchmob.effects

import org.bukkit.Location
import ru.chatan.switchmob.SwitchMobEffect

class SwitchMobExplosionEffect : SwitchMobEffect {
    override fun createEffect(location: Location) {
        location.world.createExplosion(location, 1f, false, false)
    }
}