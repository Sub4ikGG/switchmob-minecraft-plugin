package ru.chatan.switchmob.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import ru.chatan.switchmob.SwitchMob

class TogglePluginCommand : Command(
    COMMAND_NAME,
    DESCRIPTION,
    USAGE,
    listOf("smt")
) {
    companion object {
        const val COMMAND_NAME = "switchmobtoggle"
        const val DESCRIPTION = "Toggle plugin"
        const val USAGE = "/$COMMAND_NAME [true/false]"
    }

    override fun execute(p0: CommandSender, p1: String, p2: Array<out String>?): Boolean {
        val toggle = p2?.firstOrNull()?.toBooleanStrictOrNull()
        if (toggle == null) {
            p0.sendMessage("${SwitchMob.TAG}: $USAGE")
            return false
        }

        SwitchMob.togglePlugin(toggle = toggle)

        if (toggle)
            p0.sendMessage("${SwitchMob.TAG}: Plugin enabled.")
        else p0.sendMessage("${SwitchMob.TAG}: Plugin disabled.")

        return true
    }
}