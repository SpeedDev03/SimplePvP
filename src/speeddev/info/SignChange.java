package speeddev.info;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class SignChange implements Listener{
	

	//This plugin has been renamed to SimplePvP
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("IronSword")) {
			e.setLine(0, "§a§lIronSword");
			e.setLine(2, "§lFree");
		}
		
		if (e.getLine(0).equalsIgnoreCase("IronHelmet")) {
			e.setLine(0, "§a§lIronHelmet");
			e.setLine(2, "§lFree");
		}
		
		if (e.getLine(0).equalsIgnoreCase("IronChestPlate")) {
			e.setLine(0, "§a§lIronChestPlate");
			e.setLine(2, "§lFree");
		}
		
		if (e.getLine(0).equalsIgnoreCase("IronLeggings")) {
			e.setLine(0, "§a§lIronLeggings");
			e.setLine(2, "§lFree");
		}
		
		if (e.getLine(0).equalsIgnoreCase("IronBoots")) {
			e.setLine(0, "§a§lIronBoots");
			e.setLine(2, "§lFree");
		}
		
		if (e.getLine(0).equalsIgnoreCase("Bow")) {
			e.setLine(0, "§a§lBow");
			e.setLine(2, "§lFree");
		}
		
		if (e.getLine(0).equalsIgnoreCase("Arrow")) {
			e.setLine(0, "§a§lArrow");
			e.setLine(2, "§lFree");
		}
		if (e.getLine(0).equalsIgnoreCase("Healing")) {
			e.setLine(0, "§a§lHealing");
			e.setLine(2, "§lFree");
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		if (e.getClickedBlock().getState() instanceof Sign) {
			PlayerInventory pi = e.getPlayer().getInventory();
			Sign s = (Sign) e.getClickedBlock().getState();
			if (s.getLine(0).equalsIgnoreCase("§a§lIronSword")) {
					pi.addItem(new ItemStack(Material.IRON_SWORD, 1));

			}
			
			if (s.getLine(0).equalsIgnoreCase("§a§lIronHelmet")) {
				pi.addItem(new ItemStack(Material.IRON_HELMET, 1));

		}
			
			if (s.getLine(0).equalsIgnoreCase("§a§lIronChestPlate")) {
				pi.addItem(new ItemStack(Material.IRON_CHESTPLATE, 1));

		}
			
			if (s.getLine(0).equalsIgnoreCase("§a§lIronLeggings")) {
				pi.addItem(new ItemStack(Material.IRON_LEGGINGS, 1));

		}
			
			/* if (s.getLine(0).equalsIgnoreCase("§a§lIronBoots")) {
				Player p = e.getPlayer();
				e.getPlayer().performCommand("/give  373:8197 1");

		} */
			if (s.getLine(0).equalsIgnoreCase("§a§lHealing")) {
				pi.addItem(new ItemStack(Material.POTION, 1));

		}
			
			if (s.getLine(0).equalsIgnoreCase("§a§lBow")) {
				pi.addItem(new ItemStack(Material.BOW, 1));

		}
			
			if (s.getLine(0).equalsIgnoreCase("§a§lArrow")) {
				pi.addItem(new ItemStack(Material.ARROW, 16));

		}
		}
	}

}
