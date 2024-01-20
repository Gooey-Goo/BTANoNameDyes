# BTANoNameDyes
I never bothered to properly document all of the features added to the mod so far, so hopefully this document should fix the issue. 
On a technical note, you can change the starting ID for all blocks and items, in the event there's a conflict with another mod. 

# Blocks
+ Nether Roots
  + Generates naturally in the nether, on netherrack.
  + Crafts 2 crimson dye.
  + Can be grown on netherrack using bone meal.
  + Obtained using shears.
+ Shaggy Mane
  + Generates in plains, grasslands and meadows.
  + Crafts 2 ash gray dye.
  + Grows in areas with light level 10 or above, unlike other mushrooms.
  + Grows twice as fast in rain.
  + 4x more likely to grow on blocks that are adjacent to dirth paths, gravel and pebbles (but not on top of those blocks).
+ Block of Ochre
  + Generates in deserts, the outback and grassy outback.
  + Drops 4 ochre.
  + Can be crafted from 4 ochre.
+ Malachite Ore
  + Should spawn similarily to lapis ore, just a little more rare.
  + Drops 4+9 malachite.
+ Block of Malachite
  + Crafted using 9 malachite.
  + Would craft 9 malachite if I didn't forget to implement that recipe.
+ Indigo Flower
  + Spawns naturally in seasonal forests, rainforests and cinnamon forests.
  + Crafts 2 indigo dye.
  + Can grow on grass, but only in the biomes it spawns naturally in.
+ Cocoa Log
  + DEPRECATED, turns into mossy oak log
+ Ripe Cocoa Log
  + DEPRECATED, turns into mossy oak log
+ Cocoa Leaves
  + DEPRECATED, turns into cacao leaves
+ Cocoa Sapling
  + DEPRECATED, turns into cacao sapling
+ Malachite Bricks
  + 4 malachite crafts 4 bricks.
+ Bleaching Basin
  + Crafted from 2 cobblestone and 5 polished stone.
  + Bleaches blocks in its gui when placed above water and given bleaching powder.
+ Vile Netherrack
  + Generates in clumps in the nether.
  + Can be smelted to produce vile shards.
+ Cinnamon Log
  + Generates as part of cinnamon trees.
  + Crafts into cinnamon wooden planks.
  + Crafting with an axe yields 4 cinnamon (at the cost of 1 durability)
  + Functions like other logs.
+ Cinnamon Leaves
  + Generates as part of cinnamon trees.
  + Drops cinnamon saplings.
  + Functions like other leaves.
+ Cinnamon Sapling
  + Grows cinnamon trees.
  + Functions like other saplings.
+ Gallstone
  + 9 vile shards crafts 1 gallstone.
  + Currently does not craft into 9 vile shards.
+ Vile Reactor
  + 4 gallstones and 4 vile netherrack craft 1 vile reactor
  + Upon detecting 10 kill, the reactor converts nearby netherrack into vile netherrack. It also spawns multiple items.
  + Only works in the nether
+ Ebony Log
  + Generates as part of cinnamon trees.
  + Crafts into black wooden planks.
  + Functions like other logs.
+ Ebony Leaves
  + Generates as part of cinnamon trees.
  + Drops ebony saplings.
  + Functions like other leaves.
+ Ebony Sapling
  + Grows ebony trees.
  + Functions like other saplings.
+ Plaster
  + DEPRECATED, turns into white plastered mud.
+ Plastered Mud
  + Obtained by using dyed plaster on mud.
  + Weak stone-like block, comes in different colours.
+ Plastered Limestone
  + Obtained by using dyed plaster on cobbled limestone.
  + Coloured stone.
+ Painted Plaster
  + 8 plaster and 1 dye crafts 8 painted plaster.
+ Block of Ceramic
  + 4 ceramic crafts 4 ceramic blocks.
  + Another stone like block that can be dyed.
  + Can be crafted into ceramic.
+ Painted Block of Ceramic
  + 8 ceramic and 1 dye crafts 8 painted ceramic blocks.
+ Tiled Ceramic
  + 2 ceramic blocks crafts 2 tiled ceramic blocks. (also works for painted ceramic)
  + Undyed tiled ceramic deprecated, turns into white tiled ceramic

# Items
+ Bleaching Powder
  + 1 bone meal, 1 lime powder and 1 sulphur yields 3 bleaching powder.
  + bleaches 8 blocks when used in a bleaching basin.
+ Snickerdoodles
  + 2 wheat, 1 cinnamon and 1 sugar yields 8 snickerdoodles.
  + heals half a heart
+ Vile Shards
  + Obtained from smelting vile netherrack.
  + Can be smelted into xanthic dye.
+ Ceramic
  + Obtained from smelting clay in a blast furnace.
  + Used to craft ceramic blocks.
+ Lime Powder
  + 1 cobbled limestone yields 4 lime powder.
  + Used for bleaching powder and plaster.
+ Wet Plaster
  + 4 lime powder, 4 clay and 1 sand yields 12 wet plaster.
  + Used for crafting dyed plaster.
+ Dyed Plaster
  + 8 wet plaster and 1 dye yields 8 dyed plaster.
  + Right clicking mud or cobbled limestone will plaster them.
  

# Biomes
+ Cinnamon Forest
  + Humid biome that exclusively spawns cinnamon trees.
  + Typically found around swamps and rainforests.
+ Ebony Forest
  + Biome that spawns oak trees and ebony trees
  + Typically found around seasonal forests

# Obtaining/renewing dyes
+ Brown dye
  OBTAINING Cocoa beans are dungeon loot but also drop from ripe cocoa logs.
  RENEWING Ripe cocoa logs can be generated from the trees grown by cocoa saplings.
+ Crimson
  OBTAINING Crafted from nether roots.
  RENEWING Nether roots can be grown with bone meal.
+ Maroon
  OBTAINING: Crafted from crimson dye and cocoa beans.
+ Ash Gray
  - OBTAINING: Crafted from shaggy manes.
  - RENEWING: Shaggy manes grow eventually, but grow faster near certain blocks.
+ Olive
  - OBTAINING: Crafted from green dye and cocoa beans.
+ Ochre
  - OBTAINING: Dropped by blocks of ochre (which generate in patches), or as loot from trommelling sand and rich scorched dirt.
  - RENEWING: Effectively renewable through trommelling.
+ Buff
  - OBTAINING: Crafted from ochre and bone meal.
+ Verdigris
  - OBTAINING: From malachite, which is obtained from mining malachite ore or as loot from trommelling gravel and rich scorched dirt.
  - RENEWING: Effectively renewable through tromelling.
+ Light Yellow
  - OBTAINING: Crafted from dandelion yellow and bone meal.
+ Indigo
  - OBTAINING: Crafted from indigo flowers.
  - RENEWING: Grass that grows in seasonal forests and rainforests have a chance to grow indigo flowers.
+ Xanthic
  - OBTAINING: Smelted from vile shards.
  - RENEWING: Vile reactors generate vile shards after killing enough mobs around them.
+ Cinnamon
  - OBTAINING: Crafted from a cinnamon log and an axe.
  - RENEWING: Cinnamon logs generate in cinnamon trees.
+ Navy Blue
  - OBTAINING: Crafted from indigo dye and ink sac.