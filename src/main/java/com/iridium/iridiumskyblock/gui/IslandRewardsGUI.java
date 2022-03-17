package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumcore.utils.InventoryUtils;
import com.iridium.iridiumcore.utils.ItemStackUtils;
import com.iridium.iridiumcore.utils.Placeholder;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.PlaceholderBuilder;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.IslandReward;
import com.iridium.iridiumskyblock.database.User;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class IslandRewardsGUI extends IslandGUI {

    public IslandRewardsGUI(Island island, Inventory previousInventory) {
        super(IridiumSkyblock.getInstance().getInventories().islandReward, previousInventory, island);
    }

    @Override
    public void addContent(Inventory inventory) {
        inventory.clear();

        InventoryUtils.fillInventory(inventory, IridiumSkyblock.getInstance().getInventories().islandReward.background);

        List<Placeholder> placeholders = new PlaceholderBuilder().applyIslandPlaceholders(getIsland()).build();
        
        int maxSize = inventory.getSize() - 1; // slot 0
        
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (IslandReward islandReward : IridiumSkyblock.getInstance().getDatabaseManager().getIslandRewardTableManager().getEntries(getIsland())) {
            if (atomicInteger.get() > maxSize) break;
            inventory.setItem(atomicInteger.getAndIncrement(), ItemStackUtils.makeItem(islandReward.getReward().item, placeholders));
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        List<IslandReward> islandRewards = IridiumSkyblock.getInstance().getDatabaseManager().getIslandRewardTableManager().getEntries(getIsland());
        Player player = (Player)event.getWhoClicked();
        User user = IridiumSkyblock.getInstance().getUserManager().getUser(player);
        Optional<Island> island = user.getIsland();
        if (islandRewards.size() > event.getSlot()) {
            IslandReward islandReward = islandRewards.get(event.getSlot());
            islandReward.getReward().claim((Player) event.getWhoClicked(), getIsland());
            IridiumSkyblock.getInstance().getDatabaseManager().getIslandRewardTableManager().delete(islandReward);
            player.openInventory(new IslandRewardsGUI(island.get(), player.getOpenInventory().getTopInventory()).getInventory());
        }
    }
}
