package ru.chatan.switchmob.commands.changechance

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import ru.chatan.switchmob.SwitchMob
import ru.chatan.switchmob.SwitchMobEffectType

class ChangeChanceCommand : Command(
    COMMAND_NAME,
    DESCRIPTION,
    USAGE,
    listOf("smchance")
) {
    companion object {
        private val arguments = SwitchMobEffectType.entries.map { it.argument }.toMutableList()

        const val COMMAND_NAME = "switchmobchance"
        const val DESCRIPTION = "Change plugin chance"
        val USAGE = "/$COMMAND_NAME [${arguments.joinToString(", ")}] [0-100]"
    }

    override fun execute(p0: CommandSender, p1: String, p2: Array<out String>?): Boolean {
        val switchMobEffectType = SwitchMobEffectType.fromArgument(argument = p2?.getOrNull(0).orEmpty())
        val chance = p2?.getOrNull(1)?.toIntOrNull().takeIf { it in 0..100 }

        if (switchMobEffectType == null || chance == null) {
            showUsageToCommandSender(p0)
            return false
        }

        SwitchMob.changeSwitchMobEffectChance(effectType = switchMobEffectType, chance = chance)
        p0.sendMessage("${SwitchMob.TAG}: Change ${switchMobEffectType.argument} chance to $chance%")
        return true
    }

    private fun showUsageToCommandSender(p0: CommandSender) {
        p0.sendMessage("${SwitchMob.TAG}: $USAGE")
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>?): MutableList<String> {
        return when (args?.size) {
            1 -> getTargetArguments()
            2 -> getChanceArguments()
            else -> mutableListOf()
        }
    }

    private fun getTargetArguments() = arguments
    private fun getChanceArguments() = mutableListOf("0", "25", "50", "75", "100")
}