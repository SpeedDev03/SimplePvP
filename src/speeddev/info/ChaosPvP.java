package speeddev.info;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class ChaosPvP extends JavaPlugin implements Listener {
	
	//This plugin has been renamed to SimplePvP
	
	/* TODO
	 * Duels
	 * Add healing pot to sign
	 * */
	 

	static Plugin plugin;
	  public static Economy econ = null;
	    public static EconomyResponse r;
	    
	    String prefix = "§a§lSimplePvP §6» ";
	    ArrayList<String> level = new ArrayList<String>();
	    ArrayList<String> level1 = new ArrayList<String>();
	    ArrayList<String> level2 = new ArrayList<String>();
	    ArrayList<String> level3 = new ArrayList<String>();
	    ArrayList<String> level4 = new ArrayList<String>();
	    ArrayList<String> level5 = new ArrayList<String>();
	    ArrayList<String> level6 = new ArrayList<String>();
	    ArrayList<String> level7 = new ArrayList<String>();
	    ArrayList<String> level8 = new ArrayList<String>();
	
	public void onEnable() {
		 Bukkit.getServer().getPluginManager().registerEvents(this, this);
		 Bukkit.getServer().getPluginManager().registerEvents(new SignChange(), this);
		 Bukkit.getServer().getPluginManager().registerEvents(new AutoPot(), this);
		 plugin = this;
		 
		 if (!setupEconomy() ) {
	            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	        }
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		  if (cmd.getName().equalsIgnoreCase("stats")) {
			  
			  Player p = (Player) sender;
			  
			  long kills = getConfig().getInt("players." + p.getPlayer().getName() + ".kills");
			  long deaths = getConfig().getInt("players." + p.getPlayer().getName() + ".deaths");
			  @SuppressWarnings("deprecation")
				double money = econ.getBalance(p.getPlayer().getName());
			  double kd = kills;
				
				if(deaths > 0) {
					kd = (double)kills/deaths;
				}

				int ping = ((CraftPlayer) p).getHandle().ping;
				
				  if (args.length == 0) {
			  p.sendMessage("§0--- §9[Statistics] §0---");
			  p.sendMessage("§fName: " + ChatColor.GOLD + p.getName());
			  p.sendMessage("§fMoney: " + ChatColor.GOLD + "$" + money);
			  p.sendMessage("§fKills: " + ChatColor.GOLD + kills);
			  p.sendMessage("§fDeaths: " + ChatColor.GOLD + deaths);
			  p.sendMessage("§fK/D Ratio: " + ChatColor.GOLD + kd);
			  p.sendMessage("§fPing: " + ChatColor.GOLD + ping + "ms");
			  
			  return true;
				  }
				  
				  @SuppressWarnings("deprecation")
				Player target = Bukkit.getServer().getPlayer(args[0]);
                  if (target == null) {
                          p.sendMessage(prefix + ChatColor.RED + "Could not find player!");
                          return true;
                  }
                  
                  long kills1 = getConfig().getInt("players." + target.getPlayer().getName() + ".kills");
    			  long deaths1 = getConfig().getInt("players." + target.getPlayer().getName() + ".deaths");
    			  @SuppressWarnings("deprecation")
    				double money1 = econ.getBalance(target.getPlayer().getName());
    			  
    			  double kd1 = kills1;
  				
  				if(deaths > 0) {
  					kd1 = (double)kills1/deaths1;
  				}
  				
  				int ping1 = ((CraftPlayer) target).getHandle().ping;
                  
                  p.sendMessage("§0--- §9[Statistics] §0---");
    			  p.sendMessage("§fName: " + ChatColor.GOLD + target.getName());
    			  p.sendMessage("§fMoney: " + ChatColor.GOLD + "$" + money1);
    			  p.sendMessage("§fKills: " + ChatColor.GOLD + kills1);
    			  p.sendMessage("§fDeaths: " + ChatColor.GOLD + deaths1);
    			  p.sendMessage("§fK/D Ratio: " + ChatColor.GOLD + kd1);
    			  p.sendMessage("§fPing: " + ChatColor.GOLD + ping1 + "ms");
		  }
		return true;
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	final Scoreboard board = manager.getNewScoreboard();
	final Objective objective = board.registerNewObjective("test", "dummy");
	
	@EventHandler
	public void chatFormat(AsyncPlayerChatEvent event){
	  Player p = event.getPlayer();
	  if(level1.contains(p)){
		  event.setFormat("§7[" + "§b§l1" + "§7]" + " §7§l> " + ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " » " + ChatColor.GRAY + event.getMessage());
		 
	  }  else if(level2.contains(p)){
		  event.setFormat("§7[" + "§b§l2" + "§7]" + " §7§l> " + ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " » " + ChatColor.GRAY + event.getMessage());
	  } 
	  else if(level3.contains(p)){
		  event.setFormat("§7[" + "§b§l3" + "§7]" + " §7§l> " + ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " » " + ChatColor.GRAY + event.getMessage());

	  }  else if(level4.contains(p)){
		  event.setFormat("§7[" + "§b§l4" + "§7]" + " §7§l> "  + ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " » " + ChatColor.GRAY + event.getMessage());
	  }  else if(level5.contains(p)){
		  event.setFormat("§7[" + "§b§l5" + "§7]" + " §7§l> "  + ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " » " + ChatColor.GRAY + event.getMessage());
	  }  else if(level6.contains(p)){
		  event.setFormat("§7[" + "§b§l6" + "§7]" + " §7§l> "  + ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " » " + ChatColor.GRAY + event.getMessage());
	  }  else if(level7.contains(p)){
		  event.setFormat("§7[" + "§b§l7" + "§7]" + " §7§l> "  + ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " » " + ChatColor.GRAY + event.getMessage());
	  }  else if(level8.contains(p)){
		  event.setFormat("§7[" + "§b§l8" + "§7]" + " §7§l> "  + ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " » " + ChatColor.GRAY + event.getMessage());
	  }  
	  
		  event.setFormat("§7[" + "§b§l0" + "§7]" + " §7§l> " + ChatColor.GRAY + p.getDisplayName() + ChatColor.GOLD + " » " + ChatColor.GRAY + event.getMessage());
	  } 
	  
	  
	
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e) {

		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ChaosPvP.plugin, new Runnable() {
			public void run() {
				
				ScoreboardManager manager = Bukkit.getScoreboardManager();
				final Scoreboard board = manager.getNewScoreboard();
				final Objective objective = board.registerNewObjective("test", "dummy");
				
				objective.setDisplaySlot(DisplaySlot.SIDEBAR);
				objective.setDisplayName("§a§lSimplePvP");
				
				Score score18 = objective.getScore("§7§lLevel:");
				score18.setScore(11);

				int level = getConfig().getInt("players." + e.getPlayer().getName() + ".kills");
				
				if(level >= 10) {
					level1.add(e.getPlayer().getName());
					Score score20 = objective.getScore("[1]");
					score20.setScore(10);
				} else if(level >= 20){ 
					level2.add(e.getPlayer().getName());
					Score score20 = objective.getScore("[2]");
					score20.setScore(10);
				} else if(level >= 30) {
					level3.add(e.getPlayer().getName());
					Score score20 = objective.getScore("[3]");
					score20.setScore(10);
				}
				else if(level >= 50) {
					level4.add(e.getPlayer().getName());
					Score score20 = objective.getScore("[4]");
					score20.setScore(10);
				}
				else if(level >= 80) {
					level5.add(e.getPlayer().getName());
					Score score20 = objective.getScore("[5]");
					score20.setScore(10);
				}
				else if(level >= 90) {
					level6.add(e.getPlayer().getName());
					Score score20 = objective.getScore("[6]");
					score20.setScore(10);
				}
				else if(level >= 110) {
					level7.add(e.getPlayer().getName());
					Score score20 = objective.getScore("[7]");
					score20.setScore(10);
				}
				else if(level >= 130) {
					level8.add(e.getPlayer().getName());
					Score score20 = objective.getScore("[8]");
					score20.setScore(10);
				} else if(level < 10 ) {
					Score score20 = objective.getScore("[0]");
					score20.setScore(10);
				}
				

				Score score2 = objective.getScore("§7§lMoney:");
				score2.setScore(9);

				@SuppressWarnings("deprecation")
				double money = econ.getBalance(e.getPlayer().getName());
				
				Score score3 = objective.getScore(ChatColor.WHITE  + String.valueOf(money));
				score3.setScore(8);
				
				Score score5 = objective.getScore("§7§lKills:");
				score5.setScore(7);
				
				long kills = getConfig().getInt("players." + e.getPlayer().getName() + ".kills");
				
				if(kills == 0) {
					Score score6 = objective.getScore("0");
					score6.setScore(6);
				}
				
				Score score6 = objective.getScore(ChatColor.WHITE + String.valueOf(kills));
				score6.setScore(6);
				
				Score score8 = objective.getScore("§7§lDeaths:");
				score8.setScore(5);

				
				long deaths = getConfig().getInt("players." + e.getPlayer().getName() + ".deaths");
				
				Score score9 = objective.getScore(ChatColor.WHITE + String.valueOf(deaths));
				score9.setScore(4);

				Score score12 = objective.getScore("§7§lK/D Ratio:");
				score12.setScore(3);
				
				double kd = kills;
				
				if(deaths > 0) {
					kd = (double)kills/deaths;
				}
				
				Score score17 = objective.getScore(ChatColor.WHITE + String.valueOf(kd));
				score17.setScore(2);
				
				e.getPlayer().setScoreboard(board);

			}
		},0, 20 * 10);
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
    public void killsAndDeaths(PlayerDeathEvent event){
        Player player = event.getEntity(); //The person who died
        if(player.getKiller() instanceof Player){ //Check if the entity who killed the player is a player
            Player killer = player.getKiller(); //The killer 
            this.reloadConfig();
            getConfig().set("players." + player.getName() + ".deaths", getConfig().getInt("players." + player.getName() + ".deaths") + 1); //Add a death to the player
            getConfig().set("players." + killer.getName() + ".kills", getConfig().getInt("players." + killer.getName() + ".kills") + 1); //Add a kill to the killer
            this.saveConfig();
            double health = killer.getHealth();
            player.getKiller().sendMessage(prefix + ChatColor.WHITE + "You have killed " + ChatColor.GOLD + player.getName() + ChatColor.DARK_RED + " §c§l♥" + ChatColor.RED + health);
            player.sendMessage(prefix + ChatColor.WHITE + "You were killed by " + ChatColor.GOLD + player.getKiller().getName() + ChatColor.DARK_RED + " §c§l♥" + ChatColor.RED + health);
            r = econ.depositPlayer(killer.getName(), 10.00);
            if (r.transactionSuccess()) {
                killer.sendMessage(prefix + ChatColor.WHITE + "You recieved " + ChatColor.GOLD + "$10.00 " + ChatColor.WHITE + "for killing " + ChatColor.GOLD + player.getName());
                return;
            }
            
        }
    }
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("DiamondSword")) {
			if(e.getPlayer().hasPermission("sign.change")){
			e.setLine(0, "§a§lDiamondSword");
			e.setLine(2, "§l$10.00");
			}
		}
		if (e.getLine(0).equalsIgnoreCase("DiamondHelmet")) {
			if(e.getPlayer().hasPermission("sign.change")){
			e.setLine(0, "§a§lDiamondHelmet");
			e.setLine(2, "§l$10.00");
			}
		}
		if (e.getLine(0).equalsIgnoreCase("DiamondChest")) {
			if(e.getPlayer().hasPermission("sign.change")){
			e.setLine(0, "§a§lDiamondChest");
			e.setLine(2, "§l$10.00");
			}
		}
		if (e.getLine(0).equalsIgnoreCase("DiamondLeggings")) {
			if(e.getPlayer().hasPermission("sign.change")){
			e.setLine(0, "§a§lDiamondLeggings");
			e.setLine(2, "§l$10.00");
			}
		}
		if (e.getLine(0).equalsIgnoreCase("DiamondBoots")) {
			if(e.getPlayer().hasPermission("sign.change")){
			e.setLine(0, "§a§lDiamondBoots");
			e.setLine(2, "§l$10.00");
			}
		}
		if (e.getLine(0).equalsIgnoreCase("Swiftness")) {
			if(e.getPlayer().hasPermission("sign.change")){
			e.setLine(0, "§a§lSwiftness");
			e.setLine(2, "§l$15.00");
			}
		}
		if (e.getLine(0).equalsIgnoreCase("Harmness")) {
			if(e.getPlayer().hasPermission("sign.change")){
			e.setLine(0, "§a§lHarmness");
			e.setLine(2, "§lFree");
			}
		}
		if (e.getLine(0).equalsIgnoreCase("Poison")) {
			if(e.getPlayer().hasPermission("sign.change")){
			e.setLine(0, "§a§lPoison");
			e.setLine(2, "§l$25.00");
			}
		}
		if (e.getLine(0).equalsIgnoreCase("Golden Apple")) {
			if(e.getPlayer().hasPermission("sign.change")){
			e.setLine(0, "§a§lGolden Apple");
			e.setLine(2, "§l$20.00");
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		PlayerInventory pi = e.getPlayer().getInventory();
		Sign s = (Sign) e.getClickedBlock().getState();
		if (e.getClickedBlock().getState() instanceof Sign) {
			if (s.getLine(0).equalsIgnoreCase("§a§lDiamondSword")) {
				if(econ.getBalance(e.getPlayer().getName()) >= 10.00){
					econ.withdrawPlayer(e.getPlayer().getName(), 10.00);
					pi.addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "Transaction successful" + ChatColor.GOLD + " [$10.00]");
				} else {
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "You don't have enough money!");
				}

			}
			if (s.getLine(0).equalsIgnoreCase("§a§lDiamondHelmet")) {
				if(econ.getBalance(e.getPlayer().getName()) >= 10.00){
					econ.withdrawPlayer(e.getPlayer().getName(), 10.00);
					pi.addItem(new ItemStack(Material.DIAMOND_HELMET, 1));
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "Transaction successful" + ChatColor.GOLD + " [$10.00]");
				} else {
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "You don't have enough money!");
				}

			}
			if (s.getLine(0).equalsIgnoreCase("§a§lDiamondChest")) {
				if(econ.getBalance(e.getPlayer().getName()) >= 10.00){
					econ.withdrawPlayer(e.getPlayer().getName(), 10.00);
					pi.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "Transaction successful" + ChatColor.GOLD + " [$10.00]");
				} else {
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "You don't have enough money!");
				}

			}
			if (s.getLine(0).equalsIgnoreCase("§a§lDiamondLeggings")) {
				if(econ.getBalance(e.getPlayer().getName()) >= 10.00){
					econ.withdrawPlayer(e.getPlayer().getName(), 10.00);
					pi.addItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "Transaction successful" + ChatColor.GOLD + " [$10.00]");
				} else {
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "You don't have enough money!");
				}

			}
			
			if (s.getLine(0).equalsIgnoreCase("§a§lDiamondBoots")) {
				if(econ.getBalance(e.getPlayer().getName()) >= 10.00){
					econ.withdrawPlayer(e.getPlayer().getName(), 10.00);
					pi.addItem(new ItemStack(Material.DIAMOND_BOOTS, 1));
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "Transaction successful" + ChatColor.GOLD + " [$10.00]");
				} else {
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "You don't have enough money!");
				}

			}
			
			if (s.getLine(0).equalsIgnoreCase("§a§lGolden Apple")) {
				if(econ.getBalance(e.getPlayer().getName()) >= 20.00){
					econ.withdrawPlayer(e.getPlayer().getName(), 20.00);
					pi.addItem(new ItemStack(Material.GOLDEN_APPLE, 1));
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "Transaction successful" + ChatColor.GOLD + " [$10.00]");
				} else {
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "You don't have enough money!");
				}

			}
			
			if (s.getLine(0).equalsIgnoreCase("§a§lSwiftness")) {
				if(econ.getBalance(e.getPlayer().getName()) >= 15.00){
					econ.withdrawPlayer(e.getPlayer().getName(), 15.00);
					ItemStack item = new ItemStack(Material.POTION, 1);
					Potion pot = new Potion(1); 
					pot.setType(PotionType.SPEED);
					pot.setSplash(true);
					pot.apply(item);
					e.getPlayer().getInventory().addItem(item);
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "Transaction successful"+ ChatColor.GOLD + " [$15.00]");
				} else {
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "You don't have enough money!");
				}

			}
			
			/*if (s.getLine(0).equalsIgnoreCase("§a§lPosion")) {
				if(econ.getBalance(e.getPlayer().getName()) >= 25.00){
					econ.withdrawPlayer(e.getPlayer().getName(), 25.00);
					ItemStack item = new ItemStack(Material.POTION, 1);
					Potion pot = new Potion(1); 
					pot.setType(PotionType.POISON);
					pot.setSplash(true);
					pot.apply(item);
					e.getPlayer().getInventory().addItem(item);
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "Transaction successful" + ChatColor.GOLD + " [$25.00]");
				} else {
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "You don't have enough money!");
				}

			} */
			
			/* if (s.getLine(0).equalsIgnoreCase("§a§lHarmness")) {
				if(econ.getBalance(e.getPlayer().getName()) >= 25.00){
					econ.withdrawPlayer(e.getPlayer().getName(), 25.00);
					ItemStack item = new ItemStack(Material.POTION, 1);
					Potion pot = new Potion(1); 
					pot.setType(PotionType.INSTANT_DAMAGE);
					pot.setSplash(true);
					pot.apply(item);
					e.getPlayer().getInventory().addItem(item);
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "Transaction successful" + ChatColor.GOLD + " [$25.00]");
				} else {
					e.getPlayer().sendMessage(prefix + ChatColor.WHITE + "You don't have enough money!");
				} 

			} */
		}

	}

}