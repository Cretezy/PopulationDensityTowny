# PopulationDensityTowny

Block claiming Population Density region posts in Towny.

## Installation

Download `PopulationDensityTowny.jar` from the [Spigot resource page]() and place inside your `plugins` folder.

## Config

```yaml
# Distance to block from claiming.
# 0 = Disable only the region post chunk
# 1 = Disable region post chunk, and all chunks adjacent
distance: 1
# If the distance check is circular or square
circular: false
# Message to send to players when trying to claim a region post
# This is null by default because Towny will also send the msg_claim_error message
# You should edit the msg_claim_error instead to prevent a double message
message: null
```
