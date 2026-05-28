package me.rtx4090.enchantableMobArmor.listener;

import me.rtx4090.enchantableMobArmor.item.AnvilCostMultiplier;
import me.rtx4090.enchantableMobArmor.item.MobArmorEnchantment;
import me.rtx4090.enchantableMobArmor.item.MobArmorType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.view.AnvilView;

public class PrepareAnvilListener implements Listener {
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        if (event.getView().getPlayer().getGameMode() == org.bukkit.GameMode.CREATIVE) return; // only for survival players
        Bukkit.getLogger().info("[debug] PrepareAnvilEvent triggered by survival player");
        AnvilView anvilView = event.getView();
        ItemStack inputItem = anvilView.getTopInventory().getItem(0);
        ItemStack additionalItem = anvilView.getTopInventory().getItem(1);
        ItemStack resultItem;

        if (inputItem == null || additionalItem == null) return; // whether nothing or just renaming
        Bukkit.getLogger().info("[debug] Input: " + inputItem.getType() + ", Additional: " + additionalItem.getType());
        if (inputItem.getType() != additionalItem.getType() && additionalItem.getType() != Material.ENCHANTED_BOOK)
            return; // not combining same type or enchanting with book
        Bukkit.getLogger().info("[debug] passed \"not combining same type or enchanting with book\" check");
        if (!MobArmorType.mobArmorTypes.contains(inputItem.getType())) return; // not for this plugin
        Bukkit.getLogger().info("[debug] passed \"not for this plugin\" check");
        Set<Enchantment> allowedEnchantments = MobArmorEnchantment.EnchantmentMap.get(MobArmorType.armorTypeMap.get(inputItem.getType()));
        Map<Enchantment, Integer> currentEnchantments = inputItem.getEnchantments();

        // --- read enchantments from the additional item correctly ---
        Map<Enchantment, Integer> additionalEnchantments = new HashMap<>();
        boolean additionalIsBook = additionalItem.getType() == Material.ENCHANTED_BOOK;
        if (additionalIsBook) {
            Bukkit.getLogger().info("[debug] Additional item is an enchanted book, reading stored enchantments");
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) additionalItem.getItemMeta();
            additionalEnchantments.putAll(meta.getStoredEnchants());
        } else {
            Bukkit.getLogger().info("[debug] Additional item is correspond armor, reading enchantments");
            additionalEnchantments.putAll(additionalItem.getEnchantments());
        }

        // remove disallowed enchants
        if (additionalIsBook && additionalEnchantments.isEmpty()) return;
        if (!additionalEnchantments.isEmpty() && additionalEnchantments.keySet().stream().noneMatch(allowedEnchantments::contains)) return;
        CombineSet combineSet = combinedEnchantments(currentEnchantments, additionalEnchantments);
        Map<Enchantment, Integer> resultEnchantments = combineSet.map;
        boolean allowedAction = combineSet.bool;
        resultEnchantments.keySet().removeIf(enchantment -> !allowedEnchantments.contains(enchantment));
        Bukkit.getLogger().info("[debug] Combined enchantments: " + resultEnchantments);
        if (!allowedAction) return;

        //  build result
        resultItem = inputItem.clone();
        ItemMeta resultMeta = resultItem.getItemMeta();

        // handle rename
        if (anvilView.getRenameText() != null && !anvilView.getRenameText().isEmpty()) {
            resultMeta.setDisplayName(anvilView.getRenameText());
        } else if (anvilView.getRenameText() != null && anvilView.getRenameText().isEmpty()) {
            resultMeta.setDisplayName(null);
        }

        // handle enchant
        for (Map.Entry<Enchantment, Integer> entry : resultEnchantments.entrySet()) { //enchant
            resultMeta.addEnchant(entry.getKey(), entry.getValue(), false);
        }

        // Scale the underlying Prior Work Penalty for the next time it's used on an anvil
        int firstCost = (inputItem.hasItemMeta() && inputItem.getItemMeta() instanceof Repairable)
                ? ((Repairable) inputItem.getItemMeta()).getRepairCost() : 0;
        if (resultMeta instanceof Repairable) {
            ((Repairable) resultMeta).setRepairCost(firstCost * 2 + 1);
        }

        resultItem.setItemMeta(resultMeta);
        event.setResult(resultItem);

        // Dynamically compute final cost
        int finalCost = calculateAnvilCost(inputItem, additionalItem, resultEnchantments, additionalIsBook, anvilView);

        // Apply costs directly to the AnvilInventory to prevent max cap limit
        anvilView.setMaximumRepairCost(999);

        // Schedule the exact XP cost update to run 1 tick later so the client visually reflects it correctly
        Bukkit.getScheduler().runTask(org.bukkit.plugin.java.JavaPlugin.getProvidingPlugin(getClass()), () -> {
            anvilView.setRepairCost(finalCost);
        });


        /*if (MobArmorType.horse.contains(item.getType())) {
            // handle horse armor
            allowedEnchantments = MobArmorEnchantment.getEnchantments(MobArmorType.horse);


        } else if (MobArmorType.wolf.contains(item.getType())) {
            // handle wolf armor
            allowedEnchantments = MobArmorEnchantment.getEnchantments(MobArmorType.horse);

        } else if (MobArmorType.nautilus.contains(item.getType())) {
            // handle nautilus armor
            allowedEnchantments = MobArmorEnchantment.getEnchantments(MobArmorType.horse);

        } else {
            return; // why would this happen?
        }*/


    }


    private int calculateAnvilCost(ItemStack inputItem, ItemStack additionalItem, Map<Enchantment, Integer> resultEnchantments, boolean additionalIsBook, AnvilView anvilView) {
        int cost = 0;

        // 1. Accumulate Base Prior Work Penalties (Anvil Work Costs)
        int firstCost = (inputItem.hasItemMeta() && inputItem.getItemMeta() instanceof Repairable)
                ? ((Repairable) inputItem.getItemMeta()).getRepairCost() : 0;
        int secondCost = (additionalItem.hasItemMeta() && additionalItem.getItemMeta() instanceof Repairable)
                ? ((Repairable) additionalItem.getItemMeta()).getRepairCost() : 0;

        cost += firstCost + secondCost;

        // 2. Calculate Enchantment Level Costs (Only pay for upgrades or additions)
        Map<Enchantment, Integer> currentEnchantments = inputItem.getEnchantments();
        for (Map.Entry<Enchantment, Integer> entry : resultEnchantments.entrySet()) {
            Enchantment enchant = entry.getKey();
            int newLevel = entry.getValue();
            int currentLevel = currentEnchantments.getOrDefault(enchant, 0);

            // If it's a completely new enchant, or an upgraded level, add the XP fee
            if (newLevel > currentLevel) {
                int multiplier = additionalIsBook
                        ? (int) Math.ceil(AnvilCostMultiplier.ANVIL_COST_MULTIPLIERS.get(enchant) / 2.0)
                        : AnvilCostMultiplier.ANVIL_COST_MULTIPLIERS.getOrDefault(enchant, 1);
                cost += newLevel * multiplier;
            }
        }

        // 3. Handle Rename Fee (+1 level)
        ItemMeta inputMeta = inputItem.getItemMeta();
        String currentName = (inputMeta != null && inputMeta.hasDisplayName()) ? inputMeta.getDisplayName() : "";
        String renameText = anvilView.getRenameText();

        boolean isRenaming = renameText != null && !renameText.isEmpty();
        if (isRenaming && !renameText.equals(currentName)) {
            cost += 1;
        } else if (renameText != null && renameText.isEmpty() && inputMeta != null && inputMeta.hasDisplayName()) {
            cost += 1; // Clearing a custom name back to default
        }

        // Vanilla standard minimum cost rule
        return Math.max(1, cost);
    }

    private CombineSet combinedEnchantments(Map<Enchantment, Integer> a, Map<Enchantment, Integer> b) {
        Map<Enchantment, Integer> result = new HashMap<>(a);
        boolean allowedCombine = false;

        for (Map.Entry<Enchantment, Integer> entry : b.entrySet()) {
            Enchantment incomingEnch = entry.getKey();
            int incomingLevel = entry.getValue();

            // Skip if the incoming enchantment conflicts with existing ones
            if (result.keySet().stream().anyMatch(existingEnch ->
                    !existingEnch.equals(incomingEnch) && existingEnch.conflictsWith(incomingEnch))) {
                continue;
            }
            allowedCombine = true;
            // Merge enchantments: combine levels or take the higher level
            result.merge(incomingEnch, incomingLevel, (l1, l2) ->
                    (l1.equals(l2) && l1 < incomingEnch.getMaxLevel()) ? l1 + 1 : Math.max(l1, l2));
        }
        return new CombineSet<>(result, allowedCombine);
    }

    public record CombineSet<K, V>(Map<Enchantment, Integer> map, boolean bool) {}
}

