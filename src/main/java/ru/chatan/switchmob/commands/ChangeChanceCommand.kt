package ru.chatan.switchmob.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import ru.chatan.switchmob.SwitchChance
import ru.chatan.switchmob.SwitchMob

class ChangeChanceCommand : Command(
    COMMAND_NAME,
    DESCRIPTION,
    USAGE,
    listOf("smc")
) {
    companion object {
        const val COMMAND_NAME = "smchance"
        const val DESCRIPTION = "Change plugin chance"
        const val USAGE = "/$COMMAND_NAME [spawn, lightning, explosion] [0-100]"
    }

    override fun execute(p0: CommandSender, p1: String, p2: Array<out String>?): Boolean {
        val chanceTarget = p2?.getOrNull(0)
        val percentage = p2?.getOrNull(1)?.toIntOrNull().takeIf { it in 0..100 }

        if (chanceTarget == null || percentage == null) {
            p0.sendMessage("${SwitchMob.TAG}: $USAGE")
            return false
        }

        when (chanceTarget) {
            "spawn" -> SwitchChance.changeSpawnPercentage(percentage = percentage)
            "lightning" -> SwitchChance.changeLightningChance(percentage = percentage)
            "explosion" -> SwitchChance.changeExplosionChance(percentage = percentage)
        }

        p0.sendMessage("${SwitchMob.TAG}: Change $chanceTarget chance to $percentage%")
        return true
    }
}