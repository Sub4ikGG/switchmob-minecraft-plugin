package ru.chatan.switchmob.filter

import org.bukkit.World
import org.bukkit.entity.EntityType

class DefaultEntityTypeFilter(private val world: World) : EntityTypeFilter {
    override fun filter(entityType: EntityType): Boolean =
        entityType.isAlive &&
                entityType != EntityType.ENDER_DRAGON &&
                entityType != EntityType.WITHER &&
                entityType != EntityType.WARDEN &&
                entityType.isSpawnable &&
                entityType.isEnabledByFeature(world)
}