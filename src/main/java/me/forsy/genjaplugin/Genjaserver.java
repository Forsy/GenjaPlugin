package me.forsy.genjaplugin;


import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Iterator;
        import java.util.Random;
        import java.util.concurrent.TimeUnit;
        import org.bukkit.Bukkit;
        import org.bukkit.Color;
        import org.bukkit.Location;
        import org.bukkit.Material;
        import org.bukkit.World;
        import org.bukkit.block.Block;
        import org.bukkit.block.BlockFace;
        import org.bukkit.enchantments.Enchantment;
        import org.bukkit.entity.EntityType;
        import org.bukkit.entity.LivingEntity;
        import org.bukkit.entity.PigZombie;
        import org.bukkit.entity.Player;
        import org.bukkit.entity.WitherSkeleton;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.Listener;
        import org.bukkit.event.block.BlockPlaceEvent;
        import org.bukkit.event.entity.CreatureSpawnEvent;
        import org.bukkit.event.entity.EntityDamageByEntityEvent;
        import org.bukkit.event.entity.PlayerDeathEvent;
        import org.bukkit.inventory.ItemStack;
        import org.bukkit.inventory.meta.ItemMeta;
        import org.bukkit.inventory.meta.LeatherArmorMeta;
        import org.bukkit.metadata.FixedMetadataValue;
        import org.bukkit.potion.PotionEffect;
        import org.bukkit.potion.PotionEffectType;
        import org.bukkit.scheduler.BukkitRunnable;

public class Genjaserver implements Listener {
    private Main plugin;

    public Genjaserver(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent muerte) {
        Player p = muerte.getEntity().getPlayer();
        Location loc = p.getLocation();
        Location blokloc = loc.subtract(0.0D, 1.0D, 0.0D);
        p.getWorld().strikeLightningEffect(loc);
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent mobspawn) {
        EntityType tipo = mobspawn.getEntityType();
        int chance;
        int equipmentchance;
        if (tipo == EntityType.valueOf("SPIDER")) {
            chance = (new Random()).nextInt(100);
            if (chance > 50) {
                String[] buffs = new String[]{"SPEED", "DAMAGE_RESISTANCE", "REGENERATION", "SLOW_FALLING", "INVISIBILITY", "JUMP", "INCREASE_DAMAGE", "GLOWING"};
                equipmentchance = (new Random()).nextInt(buffs.length);
                ArrayList<Integer> listafinal = new ArrayList();
                ArrayList<Integer> list = new ArrayList();

                int item;
                for(item = 0; item < 8; ++item) {
                    list.add(new Integer(item));
                }

                Collections.shuffle(list);

                for(item = 0; item < 3; ++item) {
                    listafinal.add((Integer)list.get(item));
                }

                Iterator var26 = listafinal.iterator();

                while(var26.hasNext()) {
                    int i = (Integer)var26.next();
                    if (i == 0) {
                        mobspawn.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, (new Random()).nextInt(4)));
                    } else if (i == 1) {
                        mobspawn.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2147483647, (new Random()).nextInt(4)));
                    } else if (i == 2) {
                        mobspawn.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 2147483647, (new Random()).nextInt(4)));
                    } else if (i == 3) {
                        mobspawn.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 2147483647, (new Random()).nextInt(4)));
                    } else if (i == 4) {
                        mobspawn.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, (new Random()).nextInt(4)));
                    } else if (i == 5) {
                        mobspawn.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2147483647, (new Random()).nextInt(4)));
                    } else if (i == 6) {
                        mobspawn.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2147483647, (new Random()).nextInt(5)));
                    } else if (i == 7) {
                        mobspawn.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 2147483647, (new Random()).nextInt(5)));
                    }
                }
            }
        }

        if (tipo == EntityType.valueOf("SKELETON")) {
            chance = (new Random()).nextInt(100);
            ItemMeta skellcrossMeta;
            ItemStack skellcross;
            if (chance > 85) {
                World w = mobspawn.getEntity().getWorld();
                Location loc = mobspawn.getEntity().getLocation();
                mobspawn.getEntity().setHealth(0.0D);
                LivingEntity withsk = (LivingEntity)w.spawnEntity(loc, EntityType.WITHER_SKELETON);
                int witherequipmentchance = (new Random()).nextInt(2);
                if (witherequipmentchance == 0) {
                    skellcross = new ItemStack(Material.BOW, 1);
                    skellcrossMeta = skellcross.getItemMeta();
                    skellcrossMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 20, true);
                    skellcross.setItemMeta(skellcrossMeta);
                    withsk.getEquipment().setItemInMainHand(skellcross);
                    withsk.getEquipment().setItemInMainHandDropChance(0.1F);
                    withsk.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS, 1));
                    withsk.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1));
                    withsk.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
                    withsk.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET, 1));
                } else {
                    skellcross = new ItemStack(Material.BOW, 1);
                    skellcrossMeta = skellcross.getItemMeta();
                    skellcrossMeta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
                    skellcross.setItemMeta(skellcrossMeta);
                    ItemStack lboots = new ItemStack(Material.LEATHER_BOOTS, 1);
                    ItemStack llegg = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                    ItemStack lches = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                    ItemStack lhelm = new ItemStack(Material.LEATHER_HELMET, 1);
                    LeatherArmorMeta lb = (LeatherArmorMeta)lboots.getItemMeta();
                    LeatherArmorMeta lg = (LeatherArmorMeta)llegg.getItemMeta();
                    LeatherArmorMeta lc = (LeatherArmorMeta)llegg.getItemMeta();
                    LeatherArmorMeta lh = (LeatherArmorMeta)lhelm.getItemMeta();
                    lb.setColor(Color.fromRGB(176, 46, 38));
                    lg.setColor(Color.fromRGB(176, 46, 38));
                    lc.setColor(Color.fromRGB(176, 46, 38));
                    lh.setColor(Color.fromRGB(176, 46, 38));
                    lboots.setItemMeta(lb);
                    llegg.setItemMeta(lg);
                    lches.setItemMeta(lc);
                    lboots.setItemMeta(lh);
                    lhelm.setItemMeta(lh);
                    withsk.getEquipment().setItemInMainHand(skellcross);
                    withsk.getEquipment().setItemInMainHandDropChance(0.1F);
                    withsk.getEquipment().setBoots(lboots);
                    withsk.getEquipment().setLeggings(llegg);
                    withsk.getEquipment().setChestplate(lches);
                    withsk.getEquipment().setHelmet(lhelm);
                }
            } else if (chance > 40) {
                LivingEntity skelly = mobspawn.getEntity();
                equipmentchance = (new Random()).nextInt(3);
                switch(equipmentchance) {
                    case 0:
                        skelly.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
                        skelly.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                        skelly.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                        skelly.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
                        break;
                    case 1:
                        ItemStack skellaxe = new ItemStack(Material.IRON_AXE, 1);
                        ItemMeta skellaxeMeta = skellaxe.getItemMeta();
                        skellaxeMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
                        skellaxe.setItemMeta(skellaxeMeta);
                        skelly.getEquipment().setItemInMainHand(skellaxe);
                        skelly.getEquipment().setItemInMainHandDropChance(0.1F);
                        skelly.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS, 1));
                        skelly.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
                        skelly.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
                        skelly.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
                        break;
                    case 2:
                        skellcross = new ItemStack(Material.CROSSBOW, 1);
                        skellcrossMeta = skellcross.getItemMeta();
                        skellcrossMeta.addEnchant(Enchantment.DAMAGE_ALL, 20, true);
                        skellcross.setItemMeta(skellcrossMeta);
                        skelly.getEquipment().setItemInMainHand(skellcross);
                        skelly.getEquipment().setItemInMainHandDropChance(0.1F);
                        skelly.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS, 1));
                        skelly.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS, 1));
                        skelly.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE, 1));
                        skelly.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET, 1));
                }
            }
        }

    }

    @EventHandler
    public void onNetheriteBlockPlaced(BlockPlaceEvent nbplaced) throws InterruptedException {
        Location loc = nbplaced.getBlockAgainst().getLocation();
        Block blockplaced = nbplaced.getBlockPlaced();
        Block against = nbplaced.getBlockAgainst();
        if (String.valueOf(blockplaced.getType()) == "WITHER_SKELETON_SKULL" && String.valueOf(against.getType()) == "NETHERITE_BLOCK") {
            Block bdown = loc.getBlock().getRelative(BlockFace.DOWN);
            Block beast = loc.getBlock().getRelative(BlockFace.EAST);
            Block bwest = loc.getBlock().getRelative(BlockFace.WEST);
            Block bsouth = loc.getBlock().getRelative(BlockFace.SOUTH);
            Block bnorth = loc.getBlock().getRelative(BlockFace.NORTH);
            Block skull = loc.getBlock().getRelative(BlockFace.UP);
            if (bdown.getType() == Material.SOUL_SAND) {
                if (beast.getType() == Material.SOUL_SAND && bwest.getType() == Material.SOUL_SAND) {
                    TimeUnit.MILLISECONDS.sleep(500L);
                    bdown.setType(Material.AIR);
                    skull.setType(Material.AIR);
                    beast.setType(Material.AIR);
                    bwest.setType(Material.AIR);
                    against.setType(Material.AIR);
                    loc.getWorld().strikeLightningEffect(loc);
                    this.spawnWitherKing(against, loc);
                } else if (bsouth.getType() == Material.SOUL_SAND && bnorth.getType() == Material.SOUL_SAND) {
                    Bukkit.broadcastMessage("SPAWNED!!!!!!!!!!!!");
                    TimeUnit.MILLISECONDS.sleep(300L);
                    skull.setType(Material.AIR);
                    bdown.setType(Material.AIR);
                    bsouth.setType(Material.AIR);
                    bnorth.setType(Material.AIR);
                    against.setType(Material.AIR);
                    loc.getWorld().strikeLightningEffect(loc);
                    this.spawnWitherKing(against, loc);
                }
            }
        }

    }

    private void spawnWitherKing(Block against, Location loc) {
        WitherSkeleton wkb = (WitherSkeleton)against.getWorld().spawnEntity(loc, EntityType.WITHER_SKELETON);
        wkb.setCustomName("§f§§llWither King");
        wkb.setCustomNameVisible(true);
        ItemStack wkbsword = new ItemStack(Material.GOLDEN_SWORD, 1);
        ItemMeta wkbswordMeta = wkbsword.getItemMeta();
        wkbswordMeta.addEnchant(Enchantment.DAMAGE_ALL, 23, true);
        wkbswordMeta.addEnchant(Enchantment.SWEEPING_EDGE, 26, true);
        wkbswordMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
        wkbswordMeta.addEnchant(Enchantment.KNOCKBACK, 10, true);
        wkbswordMeta.setDisplayName("Hellforged Wither King Sword");
        wkbsword.setItemMeta(wkbswordMeta);
        ItemStack wkbboots = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta wkbbootsMeta = wkbboots.getItemMeta();
        wkbbootsMeta.addEnchant(Enchantment.FROST_WALKER, 3, true);
        wkbbootsMeta.addEnchant(Enchantment.SOUL_SPEED, 15, true);
        wkbbootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        wkbbootsMeta.setDisplayName("Hellforged Wither King Boots");
        wkbboots.setItemMeta(wkbbootsMeta);
        wkb.getEquipment().setItemInMainHand(new ItemStack(wkbsword));
        wkb.getEquipment().setItemInMainHandDropChance(100.0F);
        wkb.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        wkb.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        wkb.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        wkb.getEquipment().setBoots(wkbboots);
        wkb.getEquipment().setBootsDropChance(100.0F);
        wkb.setMetadata("WitherKing", new FixedMetadataValue(this.plugin, "witherking"));
        this.startMinonSpawn(wkb, (Player)wkb.getTarget());
    }

    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof PigZombie && e.getEntity() instanceof Player && e.getDamager().hasMetadata("ExplodingZombie")) {
            e.getDamager().getWorld().createExplosion(e.getDamager().getLocation(), 3.0F, true);
        }

    }

    private void startMinonSpawn(final WitherSkeleton wkb, final Player target) {
        (new BukkitRunnable() {
            public void run() {
                if (wkb.isDead()) {
                    this.cancel();
                } else {
                    PigZombie babyZombie = (PigZombie)wkb.getLocation().getWorld().spawnEntity(wkb.getLocation(), EntityType.ZOMBIFIED_PIGLIN);
                    babyZombie.setAngry(true);
                    babyZombie.setBaby(true);
                    babyZombie.getEquipment().setHelmet(new ItemStack(Material.TNT));
                    babyZombie.getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_SWORD));
                    babyZombie.setMetadata("ExplodingZombie", new FixedMetadataValue(Genjaserver.this.plugin, "explodingzombie"));
                    babyZombie.setTarget(target);
                }
            }
        }).runTaskTimer(this.plugin, 100L, 100L);
    }
}
