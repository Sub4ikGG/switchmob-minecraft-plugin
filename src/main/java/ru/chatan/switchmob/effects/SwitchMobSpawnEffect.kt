package ru.chatan.switchmob.effects

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Mob
import ru.chatan.switchmob.SwitchMobEffect
import ru.chatan.switchmob.filter.EntityTypeFilter
import ru.chatan.switchmob.filter.DefaultEntityTypeFilter

class SwitchMobSpawnEffect(
    private val world: World,
    private val damager: Entity,
    private val entityTypeFilter: EntityTypeFilter = DefaultEntityTypeFilter(world = world)
) : SwitchMobEffect {
    override fun createEffect(location: Location) {
        val randomEntityType =
            EntityType.entries.filter(entityTypeFilter::filter).random()

        val entity = world.spawnEntity(location, randomEntityType)
        if (entity is Mob && damager is LivingEntity) {
            entity.target = damager
            entity.isAggressive = true
        }
    }
}