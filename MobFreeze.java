package me.reverseeh.mobfreeze;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Arrays;

public final class MobFreeze extends JavaPlugin implements Listener {

    String[] boss = {"ENDER_DRAGON", "WITHER", "WARDEN"};

    @Override
    public void onEnable() {
        System.out.println("Mob Freeze works properly");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityClick(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Material material = player.getInventory().getItemInMainHand().getType();
        Entity entity = event.getRightClicked();
        if(event.getHand().name().equals("HAND") && !Arrays.asList(boss).contains(entity.getType().toString())) {
            if(material == Material.ICE || material == Material.PACKED_ICE || material == Material.BLUE_ICE) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                Mob mob = (Mob) event.getRightClicked();
                mob.setAI(false);
            } else if(event.getHand().name().equals("HAND") && material == Material.FLINT_AND_STEEL) {
                Mob mob = (Mob) event.getRightClicked();
                mob.setAI(true);
            }
        }
    }
}