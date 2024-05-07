package ru.chatan.switchmob.effects

import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.Location
import org.bukkit.entity.Firework
import ru.chatan.switchmob.SwitchMobEffect

class SwitchMobFireworksEffect : SwitchMobEffect {
    override fun createEffect(location: Location) {
        val firework = location.world.spawn(location, Firework::class.java)
        val fireworkMeta = firework.fireworkMeta

        val effect = FireworkEffect.builder()
            .withColor(Color.RED)
            .withFade(Color.BLUE)
            .with(FireworkEffect.Type.entries.random())
            .flicker(true)
            .trail(true)
            .build()

        fireworkMeta.addEffect(effect)
        fireworkMeta.power = 2
        firework.fireworkMeta = fireworkMeta

        firework.detonate()
    }
}