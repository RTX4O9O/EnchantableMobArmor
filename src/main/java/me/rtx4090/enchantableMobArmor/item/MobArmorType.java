package me.rtx4090.enchantableMobArmor.item;

import org.bukkit.Material;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class MobArmorType {
     public static final Set<Material> horse = Set.of(
            Material.LEATHER_HORSE_ARMOR,
            Material.COPPER_HORSE_ARMOR,
            Material.IRON_HORSE_ARMOR,
            Material.GOLDEN_HORSE_ARMOR,
            Material.DIAMOND_HORSE_ARMOR,
            Material.NETHERITE_HORSE_ARMOR
    );

    public static final Set<Material> wolf = Set.of(
            Material.WOLF_ARMOR
    );

    public static final Set<Material> nautilus = Set.of(
            Material.COPPER_NAUTILUS_ARMOR,
            Material.IRON_NAUTILUS_ARMOR,
            Material.GOLDEN_NAUTILUS_ARMOR,
            Material.DIAMOND_NAUTILUS_ARMOR,
            Material.NETHERITE_NAUTILUS_ARMOR
    );

    public static final Set<Material> mobArmorTypes = Stream.of(horse, wolf, nautilus)
            .flatMap(Set::stream)
            .collect(java.util.stream.Collectors.toSet());

    public static Map<Material, Set<Material>> armorTypeMap = Map.ofEntries(
            Map.entry(Material.LEATHER_HORSE_ARMOR, horse),
            Map.entry(Material.COPPER_HORSE_ARMOR, horse),
            Map.entry(Material.IRON_HORSE_ARMOR, horse),
            Map.entry(Material.GOLDEN_HORSE_ARMOR, horse),
            Map.entry(Material.DIAMOND_HORSE_ARMOR, horse),
            Map.entry(Material.NETHERITE_HORSE_ARMOR, horse),
            Map.entry(Material.WOLF_ARMOR, wolf),
            Map.entry(Material.COPPER_NAUTILUS_ARMOR, nautilus),
            Map.entry(Material.IRON_NAUTILUS_ARMOR, nautilus),
            Map.entry(Material.GOLDEN_NAUTILUS_ARMOR, nautilus),
            Map.entry(Material.DIAMOND_NAUTILUS_ARMOR, nautilus),
            Map.entry(Material.NETHERITE_NAUTILUS_ARMOR, nautilus)
    );


}
