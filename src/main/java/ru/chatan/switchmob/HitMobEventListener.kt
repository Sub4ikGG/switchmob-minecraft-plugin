package ru.chatan.switchmob

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import ru.chatan.switchmob.SwitchMobEffectType.*
import ru.chatan.switchmob.effects.SwitchMobExplosionEffect
import ru.chatan.switchmob.effects.SwitchMobFireworksEffect
import ru.chatan.switchmob.effects.SwitchMobLightningEffect
import ru.chatan.switchmob.effects.SwitchMobSpawnEffect

class HitMobEventListener : Listener {
    @EventHandler
    fun onHit(event: EntityDamageByEntityEvent) {
        if (!SwitchMob.isPluginEnabled()) return

        try {
            val isEntityKilled = event.damage >= (event.entity as LivingEntity).health
            if (!isEntityKilled) return
            if ((SwitchMob.getEffectChance(effectType = SPAWN) ?: SPAWN.baseChance) < SwitchChance.getChance()) return

            val world = event.entity.world
            val entity = event.entity as? LivingEntity ?: return

            val entityDeathLocation = entity.location
            spawnRandomEffect(location = entityDeathLocation)
            SwitchMobSpawnEffect(world = world, damager = event.damager).createEffect(location = entityDeathLocation)
        }
        catch (_: ClassCastException) {}
        catch (_: TypeCastException) {}
        catch (e: Exception) {
            Bukkit.getLogger().info("[switchmob]: Cause exception ${e.localizedMessage}")
        }
    }

    private fun spawnRandomEffect(location: Location) {
        val generatedChance = SwitchChance.getChance()
        val randomEffect = SwitchMobEffectType.entries.filter {
            it != SPAWN && (SwitchMob.getEffectChance(it) ?: it.baseChance) > generatedChance
        }.randomOrNull()

        val effect = when (randomEffect) {
            LIGHTNING -> SwitchMobLightningEffect()
            EXPLOSION -> SwitchMobExplosionEffect()
            FIREWORKS -> SwitchMobFireworksEffect()
            else -> null
        }

        effect?.createEffect(location = location)
    }
}
