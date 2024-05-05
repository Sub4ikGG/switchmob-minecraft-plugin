package ru.chatan.switchmob

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class SwitchMob : JavaPlugin() {

    override fun onEnable() {
        super.onEnable()

        Bukkit.getPluginManager().registerEvents(HitMobEventListener(), this)
        Bukkit.getLogger().info("Switchmob enabled!")
    }
}