package me.rtx4090.enchantableMobArmor.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.Map;
import java.util.Set;

public class MobArmorEnchantment {
    private static final Set<Enchantment> horse = Set.of(
            Enchantment.THORNS,
            Enchantment.FEATHER_FALLING,
            Enchantment.PROTECTION,
            Enchantment.FIRE_PROTECTION,
            Enchantment.BLAST_PROTECTION,
            Enchantment.PROJECTILE_PROTECTION,
            Enchantment.BINDING_CURSE,
            Enchantment.VANISHING_CURSE
    );

    private static final Set<Enchantment> wolf = Set.of(
            Enchantment.THORNS,
            Enchantment.FIRE_PROTECTION,
            Enchantment.BLAST_PROTECTION,
            Enchantment.PROTECTION,
            Enchantment.UNBREAKING,
            Enchantment.BINDING_CURSE,
            Enchantment.MENDING
    );

    private static final Set<Enchantment> nautilus = Set.of(
            Enchantment.THORNS,
            Enchantment.FEATHER_FALLING,
            Enchantment.PROTECTION,
            Enchantment.FIRE_PROTECTION,
            Enchantment.PROJECTILE_PROTECTION,
            Enchantment.BLAST_PROTECTION,
            Enchantment.BINDING_CURSE,
            Enchantment.VANISHING_CURSE
    );

    public static final Map<Set<Material>, Set<Enchantment>> EnchantmentMap = Map.of(
            MobArmorType.horse, horse,
            MobArmorType.wolf, wolf,
            MobArmorType.nautilus, nautilus
    );

}
