package ru.chatan.switchmob

enum class SwitchMobEffectType(val argument: String, val baseChance: Int) {
    SPAWN("spawn", 70),
    LIGHTNING("lightning", 90),
    EXPLOSION("explosion", 50),
    FIREWORKS("fireworks", 1);

    companion object {
        fun fromArgument(argument: String): SwitchMobEffectType? {
            return entries.find { it.argument == argument }
        }
    }
}