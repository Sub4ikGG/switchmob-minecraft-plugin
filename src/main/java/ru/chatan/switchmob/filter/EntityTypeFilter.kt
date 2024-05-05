package ru.chatan.switchmob.filter

import org.bukkit.entity.EntityType

interface EntityTypeFilter {
    fun filter(entityType: EntityType): Boolean
}