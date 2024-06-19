package pl.vojteq.wings;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class WingListener implements Listener{	   
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		ItemStack wings = p.getInventory().getChestplate();
		
		if (wings != null && wings.getType() == Material.LEATHER_CHESTPLATE) {
			LeatherArmorMeta meta = (LeatherArmorMeta) wings.getItemMeta();
			if (meta != null && meta.getColor().equals(Color.PURPLE)) {
				p.setAllowFlight(true);
				p.setFlying(true);
				//startWingEffect(p);
			} else {
				disableFlightIfNecessary(p);
			}
		} else {
			disableFlightIfNecessary(p);
		}
	}
	
	
	private void disableFlightIfNecessary(Player p) {
		if (p.getAllowFlight() && !p.hasPermission("specialwings.bypass")) {
			p.setAllowFlight(false);
			p.setFlying(false);
		}
	}
}
