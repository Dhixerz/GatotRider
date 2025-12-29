import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BossMonster extends Actor {
    private GreenfootImage idleSprite;
    private GreenfootImage[] moveSprites;
    private GreenfootImage[] attackSprites;
    private int currentSpriteIndex = 0;
    
    private int animationCounter = 1;
    private int animationDelay = 10;

    private boolean isAttacking = false;
    private boolean isMoving = false;
    private boolean isDead = false;
    private boolean isFacingRight = true; // Default boss menghadap ke kanan

    private int health = 50; // Health Boss
    private Gatot_StandBy target; // Gatot_StandBy
    private final int groundY = 350;

    private boolean attackOnCooldown = false; // Status cooldown
    private long lastAttackTime = 0; // Waktu serangan terakhir
    private final int cooldownDuration = 1000; // Cooldown dalam ms (2 detik)

    private boolean hasAttacked = false; // Status apakah sudah menyerang dalam satu siklus animasi

    public BossMonster() {
        // Load idle sprite
        idleSprite = new GreenfootImage("boss1.png");
        idleSprite.scale(250, 250); // Perbesar sprite idle

        // Load movement sprites
        moveSprites = new GreenfootImage[7];
        for (int i = 0; i < 7; i++) {
            moveSprites[i] = new GreenfootImage("bossWalk" + (i + 2) + ".png");
            moveSprites[i].scale(250, 250);
        }

        // Load attack sprites
        attackSprites = new GreenfootImage[6];
        for (int i = 0; i < 6; i++) {
            attackSprites[i] = new GreenfootImage("bossAttack" + (i + 1) + ".png");
            attackSprites[i].scale(280, 300);
        }
    }
    
    public void addedToWorld(World world) {
        target = (Gatot_StandBy) world.getObjects(Gatot_StandBy.class).get(0);
    }

    public void act() {
        if (!isDead) {
            stayOnGround(); 
            moveToPlayer(); // Move mengikuti Gatot_StandBy
            checkHitByAttack(); // Check jika kena hit GatotAttackEffect
        }

        if (isAttacking) {
            animate(attackSprites);
        } else if (isMoving) {
            animate(moveSprites);
        } else {
            animateIdle();
        }
    }

    private void animate(GreenfootImage[] sprites) {
        if (animationCounter % animationDelay == 0) {
            currentSpriteIndex = (currentSpriteIndex + 1) % sprites.length; // Hindari out of range
            setImage(sprites[currentSpriteIndex]);

            // Jika animasi selesai saat menyerang, aktifkan cooldown
            if (isAttacking && currentSpriteIndex == sprites.length - 1) {
                isAttacking = false;
                attackOnCooldown = true;
                lastAttackTime = System.currentTimeMillis();
                hasAttacked = false; // Reset status serangan untuk animasi berikutnya
            }
        }
        animationCounter++;
    }

    private void animateIdle() {
        if (animationCounter % animationDelay == 0) {
            setImage(idleSprite); // Idle hanya menggunakan satu sprite
            currentSpriteIndex = 0; // Reset indeks untuk animasi berikutnya
        }
        animationCounter++;
    }

    private void moveToPlayer() {
        if (target != null && !isDead) {
            int dx = target.getX() - getX(); 
            int distance = Math.abs(dx); 
            int speed = 1; // Kecepatan gerak boss
    
            // Periksa arah dan putar sesuai posisi target
            if (dx < 0 && !isFacingRight) { // Player di kiri, boss harus menghadap kiri
                mirrorFacingDirection(true); // Menghadap kiri
            } else if (dx > 0 && isFacingRight) { // Player di kanan, boss harus menghadap kanan
                mirrorFacingDirection(false); // Menghadap kanan
            }
    
            // Jika jarak cukup dekat, boss menyerang
            if (distance <= 90) {
                if (!attackOnCooldown) { // Hanya serang jika tidak dalam cooldown
                    isAttacking = true;
                    isMoving = false;
                    attackPlayer(); // Panggil serangan
                }
            } else { // Jika tidak, boss bergerak mendekati player
                isAttacking = false;
                isMoving = true;
    
                // Update posisi boss
                setLocation(getX() + (dx > 0 ? speed : -speed), getY());
            }
        }
    
        // Reset cooldown jika sudah selesai
        if (attackOnCooldown && (System.currentTimeMillis() - lastAttackTime >= cooldownDuration)) {
            attackOnCooldown = false;
        }
    }

    private void mirrorFacingDirection(boolean faceRight) {
        if (faceRight != isFacingRight) { // Hanya ubah arah jika berbeda
            for (GreenfootImage img : moveSprites) {
                img.mirrorHorizontally(); // Balik semua sprite gerakan
            }
            idleSprite.mirrorHorizontally(); // Balik sprite idle
            for (GreenfootImage img : attackSprites) {
                img.mirrorHorizontally(); // Balik semua sprite serangan
            }
            isFacingRight = faceRight; // Perbarui status arah
        }
    }

    private void checkHitByAttack() {
        // Check boss hit GatotAttackEffect
        GatotAttackEffect attack = (GatotAttackEffect) getOneIntersectingObject(GatotAttackEffect.class);
        if (attack != null) {
            health -= 1; // Decrease health
            BaseWorld world = (BaseWorld) getWorld();
            if (world != null) {
                world.removeObject(attack); // Remove attack object
            }
    
            if (health <= 0) {
                world.getCounter().addScore(50);
                isDead = true; // boss dead
                if (world != null) {
                    world.getCounter().addScore(10); // Bonus score 
                    if (world instanceof BossFight1) { 
                        ((BossFight1) world).stopMusic(); // Stop music
                    }
                }
                getWorld().removeObject(this); // Remove boss from the world
                Greenfoot.setWorld(new Level2()); // Pindah ke world level2
            }
        }
    }

    private void stayOnGround() {
        if (getY() < groundY) {
            setLocation(getX(), groundY);
        }
    }

    private void attackPlayer() {
        if (isAttacking && !hasAttacked) { // menyerang satu kali per animasi
            Gatot_StandBy player = (Gatot_StandBy) getOneIntersectingObject(Gatot_StandBy.class);
            if (player != null) {
                BaseWorld world = (BaseWorld) getWorld();
                if (world != null) {
                    HealthBar healthBar = world.getHealthBar();
                    healthBar.decreaseLife(); // Kurangi nyawa pemain hanya satu
                    hasAttacked = true; // Tandai bahwa boss telah menyerang
                }
            }
        }

        if (currentSpriteIndex == attackSprites.length - 1) {
            hasAttacked = false; // menyerang lagi di animasi berikutnya
        }
    }
}