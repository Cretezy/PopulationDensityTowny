package com.cretezy.populationdensitytowny

import com.palmergames.bukkit.towny.event.TownPreClaimEvent
import me.ryanhamshire.PopulationDensity.PopulationDensity
import me.ryanhamshire.PopulationDensity.RegionCoordinates
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


class ClaimListener(val populationDensityTowny: PopulationDensityTowny) : Listener {
    @EventHandler
    fun onTownPreClaimEvent(event: TownPreClaimEvent) {
        val location = Location(
                populationDensityTowny.server.getWorld(event.townBlock.world.name),
                event.townBlock.worldCoord.x * 16.0,
                0.0,
                event.townBlock.worldCoord.z * 16.0
        )

        val regionCoordinates = RegionCoordinates.fromLocation(location) ?: return

        val center = PopulationDensity.getRegionCenter(regionCoordinates, false)
        location.y = center.y

        val distance = populationDensityTowny.config.getDouble("distance")
        val circular = populationDensityTowny.config.getBoolean("circular")

        val distanceX = abs(location.chunk.x - center.chunk.x)
        val distanceZ = abs(location.chunk.z - center.chunk.z)

        val shouldBlock = if (circular) {
            sqrt(
                    distanceX.toDouble().pow(2.0)
                            +
                            distanceZ.toDouble().pow(2.0)
            ) <= distance
        } else {
            distanceX <= 1 && distanceZ <= 1
        }


        if (shouldBlock) {
            event.isCancelled = true

            val message = populationDensityTowny.config.getString("message")

            message?.let {
                event.player.sendMessage(ChatColor.translateAlternateColorCodes('&', it))
            }
        }
    }
}
