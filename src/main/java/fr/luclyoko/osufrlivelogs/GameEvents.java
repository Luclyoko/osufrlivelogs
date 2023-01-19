package fr.luclyoko.osufrlivelogs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class GameEvents implements Listener {

    private Main main;

    public GameEvents(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        main.getLogManager().addLog(event.getPlayer().getName() + " joined the server.");
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        main.getLogManager().addLog(player.getName() + " left the server.");
        saveInventoryToLog(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        main.getLogManager().addLog("Death coordinates : " + event.getPlayer().getLocation());
        saveInventoryToLog(player);
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player && event.getDamager() instanceof Player damager)) return;

        main.getLogManager().addLog(player.getName() + " took damages (" + event.getDamage() + ") from " + damager.getName() + " from the cause " + event.getCause() + " with " + damager.getInventory().getItemInMainHand());
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) || event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)) return;

        main.getLogManager().addLog(player.getName() + " took damages (" + event.getDamage() + ") from the cause " + event.getCause());
    }

    private void saveInventoryToLog(Player player) {
        PlayerInventory playerInventory = player.getInventory();
        main.getLogManager().addLog("===== INVENTORY CONTENTS =====");
        for (ItemStack itemStack : playerInventory.getContents()) {
            if (itemStack != null && !itemStack.getType().isAir()) main.getLogManager().addLog(itemStack.toString());
        }
        main.getLogManager().addLog("===== ENDER CHEST CONTENTS =====");
        for (ItemStack itemStack : player.getEnderChest().getContents()) {
            if (itemStack != null && !itemStack.getType().isAir()) main.getLogManager().addLog(itemStack.toString());
        }
        main.getLogManager().addLog("====================");
    }
}
