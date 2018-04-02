package speeddev.info;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

public class AutoPot implements Listener {
	
	//This plugin has been renamed to SimplePvP
	
	  @EventHandler
	  public void onPlayerInteract(PlayerInteractEvent e)
	  {
	    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && (e.getItem() != null) && (e.getItem().getType() == Material.POTION))
	    {
	      Potion p = Potion.fromItemStack(e.getItem());
	      if (p.isSplash()) {
	        return;
	      }
	      e.getPlayer().addPotionEffects(p.getEffects());
	      e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.DRINK, 1.0F, 1.0F);
	      
	      e.getPlayer().setItemInHand(new ItemStack(Material.GLASS_BOTTLE));
	      e.setCancelled(true);
	    }
	  }

}
