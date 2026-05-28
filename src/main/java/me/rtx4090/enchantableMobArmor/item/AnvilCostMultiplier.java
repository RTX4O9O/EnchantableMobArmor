package me.rtx4090.enchantableMobArmor.item;

import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class AnvilCostMultiplier {

    public static final Map<Enchantment, Integer> ANVIL_COST_MULTIPLIERS = new HashMap<>();

    static {
        // 1x Multipliers
        ANVIL_COST_MULTIPLIERS.put(Enchantment.PROTECTION, 1);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.SHARPNESS, 1);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.EFFICIENCY, 1);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.POWER, 1);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.UNBREAKING, 1);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.PIERCING, 1);

        // 2x Multipliers
        ANVIL_COST_MULTIPLIERS.put(Enchantment.FIRE_PROTECTION, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.FEATHER_FALLING, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.PROJECTILE_PROTECTION, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.SMITE, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.BANE_OF_ARTHROPODS, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.KNOCKBACK, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.FIRE_ASPECT, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.LOOTING, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.SWEEPING_EDGE, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.FORTUNE, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.PUNCH, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.FLAME, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.LUCK_OF_THE_SEA, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.LURE, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.LOYALTY, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.QUICK_CHARGE, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.MENDING, 2);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.DENSITY, 2);

        // 4x Multipliers
        ANVIL_COST_MULTIPLIERS.put(Enchantment.BLAST_PROTECTION, 4);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.RESPIRATION, 4);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.AQUA_AFFINITY, 4);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.DEPTH_STRIDER, 4);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.FROST_WALKER, 4);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.INFINITY, 4);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.IMPALING, 4);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.RIPTIDE, 4);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.BREACH, 4);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.WIND_BURST, 4);

        // 8x Multipliers
        ANVIL_COST_MULTIPLIERS.put(Enchantment.THORNS, 8);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.BINDING_CURSE, 8);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.VANISHING_CURSE, 8);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.SOUL_SPEED, 8);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.SWIFT_SNEAK, 8);
        ANVIL_COST_MULTIPLIERS.put(Enchantment.CHANNELING, 8);
    }
}
