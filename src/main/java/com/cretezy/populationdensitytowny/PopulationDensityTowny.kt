package com.cretezy.populationdensitytowny

import org.bukkit.plugin.java.JavaPlugin

class PopulationDensityTowny : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        server.pluginManager.registerEvents(ClaimListener(this), this)
    }
}
