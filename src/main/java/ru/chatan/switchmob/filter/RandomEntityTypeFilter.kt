package ru.chatan.switchmob.filter

import org.bukkit.entity.EntityType

class RandomEntityTypeFilter : EntityTypeFilter {
    override fun filter(entityType: EntityType): Boolean =
        entityType.isAlive &&
                entityType != EntityType.ENDER_DRAGON &&
                entityType != EntityType.WITHER &&
                entityType.isSpawnable
}