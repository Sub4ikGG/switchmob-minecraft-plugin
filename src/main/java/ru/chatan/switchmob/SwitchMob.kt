package ru.chatan.switchmob

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import ru.chatan.switchmob.commands.ChangeChanceCommand
import ru.chatan.switchmob.commands.TogglePluginCommand

class SwitchMob : JavaPlugin() {

    override fun onEnable() {
        super.onEnable()

        Bukkit.getPluginManager().registerEvents(HitMobEventListener(), this)
        Bukkit.getLogger().info("Switchmob enabled!")

        Bukkit.getCommandMap().register(TogglePluginCommand.COMMAND_NAME, TogglePluginCommand())
        Bukkit.getCommandMap().register(ChangeChanceCommand.COMMAND_NAME, ChangeChanceCommand())
    }

    companion object {
        private var PLUGIN_ENABLED = true
        const val TAG = "[SwitchMob]"

        fun togglePlugin(toggle: Boolean) {
            PLUGIN_ENABLED = toggle
        }

        fun isPluginEnabled() = PLUGIN_ENABLED
    }
}