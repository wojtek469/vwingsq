package pl.vojteq.wings;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class specialwingsCMD implements CommandExecutor {

    public specialwingsCMD(Main M) {
        M.getCommand("specialwings").setExecutor(this);
    }

    public ItemStack createPurpleLeatherChestplate() {
        ItemStack wings = new ItemStack(Material.LEATHER_CHESTPLATE);

        LeatherArmorMeta meta = (LeatherArmorMeta) wings.getItemMeta();
        meta.setColor(Color.PURPLE);
        meta.setDisplayName("§a§lWINGS");

        List<String> lore = new ArrayList<>();
        lore.add("§cJust fly §6whenever§c you want!");
        meta.setLore(lore);

        meta.addEnchant(Enchantment.UNBREAKING, 10, true);

        wings.setItemMeta(meta);

        return wings;
    }

    public void giveWings(Player p) {
        ItemStack purpleChestplate = createPurpleLeatherChestplate();
        p.getInventory().setChestplate(purpleChestplate);
        p.sendMessage("§a§lSUCCESS: §cYou get your §6wings§c!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4Error: §cOnly §6players§c can use this command!");
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 1) {
            String give1 = args[0];
            if (give1.equals("give")) {
                giveWings(p);
                return true;
            }
        } else if (args.length == 2) {
            String args1 = args[0];
            int args2;

            try {
                args2 = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                p.sendMessage("§4Error: §cSpeed value must be a §6number§c!");
                return false;
            }

            if (args1.equals("speed")) {
                float speed = args2 / 10.0f;
                if (speed >= -1.0f && speed <= 1.0f) {
                    p.setFlySpeed(speed);
                    p.sendMessage("§a§lSUCCESS: §cYou set your §6flight§c speed to §6" + args2 + "§c!");
                    return true;
                } else {
                    p.sendMessage("§4Error: §cThe speed value must be between §6-1.0 §cand §61.0§c!");
                    return false;
                }
            }
        }

        p.sendMessage("§4Error: §cCorrect usage: §6/specialwings speed <value> §cor §6/specialwings give");
        return false;
    }
}
