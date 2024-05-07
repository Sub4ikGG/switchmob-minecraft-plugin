package ru.chatan.switchmob

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import ru.chatan.switchmob.filter.EntityTypeFilter
import ru.chatan.switchmob.filter.RandomEntityTypeFilter

class HitMobEventListener : Listener {
    @EventHandler
    fun onHit(event: EntityDamageByEntityEvent) {
        if (!SwitchMob.isPluginEnabled()) return

        try {
            val isEntityKilled = event.damage >= (event.entity as LivingEntity).health
            if (!isEntityKilled) return
            if (!SwitchChance.canSpawn()) return

            val world = event.entity.world
            val entity = event.entity as? LivingEntity ?: return

            val entityDeathLocation = entity.location
            spawnEffects(world = world, location = entityDeathLocation)
            spawnRandomEntity(
                world = world,
                damager = event.damager,
                location = entityDeathLocation
            )
        }
        catch (_: ClassCastException) {}
        catch (_: TypeCastException) {}
        catch (e: Exception) {
            Bukkit.getLogger().info("[switchmob]: Cause exception ${e.localizedMessage}")
        }
    }

    private fun spawnRandomEntity(
        world: World,
        damager: Entity,
        location: Location,
        entityTypeFilter: EntityTypeFilter = RandomEntityTypeFilter()
    ) {
        val randomEntityType =
            EntityType.entries.filter(entityTypeFilter::filter).random()

        val entity = world.spawnEntity(location, randomEntityType)
        if (entity is Mob && damager is LivingEntity) {
            entity.target = damager
            entity.isAggressive = true
        }
    }

    private fun spawnEffects(
        world: World,
        location: Location
    ) {
        if (SwitchChance.canLightning())
            world.strikeLightningEffect(location)

        if (SwitchChance.canExplosive())
            world.createExplosion(location, 1f, false, false)
    }
}
