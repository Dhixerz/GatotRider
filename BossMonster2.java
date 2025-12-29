import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BossMonster2 extends Actor {
    private GreenfootImage idleSprite;
    private GreenfootImage[] moveSprites;
    private GreenfootImage[] attackSprites;
    private int currentSpriteIndex = 0;

    private int animationCounter = 1;
    private int animationDelay = 10;

    private boolean isAttacking = false;
    private boolean isMoving = false;
    private boolean isDead = false;
    public boolean isFacingRight = true; // Default boss menghadap ke kanan

    private int health = 65; // Health Boss
    private Gatot_StandBy target; // Gatot_StandBy
    private final int groundY = 350;

    private boolean attackOnCooldown = false; // Status cooldown
    private long lastAttackTime = 0; // Waktu serangan terakhir
    private final int cooldownDuration = 1000; // Cooldown dalam ms (2 detik)

    private boolean hasAttacked = false; // Status apakah sudah menyerang dalam satu siklus animasi

    public BossMonster2() {
        // Load idle sprite
        idleSprite = new GreenfootImage("boss2idle1.png");
        idleSprite.scale(180, 180);
    
        // Load movement sprites
        moveSprites = new GreenfootImage[4];
        for (int i = 0; i < 4; i++) {
            moveSprites[i] = new GreenfootImage("boss2idle" + (i + 2) + ".png");
            moveSprites[i].scale(180, 180); 
        }
    
        // Load attack sprites
        attackSprites = new GreenfootImage[5];
        for (int i = 0; i < 5; i++) {
            attackSprites[i] = new GreenfootImage("boss2attack" + (i + 1) + ".png");
            attackSprites[i].scale(180, 180); 
        }
    }


    public void addedToWorld(World world) {
        target = (Gatot_StandBy) world.getObjects(Gatot_StandBy.class).get(0);
    }

    public void act() {
        if (!isDead) {
            stayOnGround(); 
            moveToPlayer(); // Move mengikuti Gatot_StandBy
            checkHitByAttack(); // Check hit GatotAttackEffect
        }

        // Handle animation
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
            int dx = target.getX() - getX(); // Jarak horizontal antara boss dan player
            int distance = Math.abs(dx); // Jarak absolut
            int speed = 2; // Kecepatan gerak boss
    
            // Periksa arah dan putar sesuai posisi target
            if (dx < 0 && !isFacingRight) { // Player di kiri, boss harus menghadap kanan
                mirrorFacingDirection(true); // Menghadap kanan
            } else if (dx > 0 && isFacingRight) { // Player di kanan, boss harus menghadap kiri
                mirrorFacingDirection(false); // Menghadap kiri
            }
    
            // Jika jarak cukup dekat, boss menyerang
            if (distance <= 90) {
                if (!attackOnCooldown) { // Hanya serang jika tidak dalam cooldown
                    isAttacking = true;
                    isMoving = false;
                    attackPlayer(); // Panggil logika serangan
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
        // Check if boss is hit by GatotAttackEffect
        GatotAttackEffect attack = (GatotAttackEffect) getOneIntersectingObject(GatotAttackEffect.class);
        if (attack != null) {
            health -= 1; // Decrease health
            BaseWorld world = (BaseWorld) getWorld();
            if (world != null) {
                world.removeObject(attack); // Remove the attack object
            }

            if (health <= 0) {
                world.getCounter().addScore(50);
                isDead = true; // Set boss as dead
                if (world != null) {
                    world.getCounter().addScore(10); // Bonus score for defeating boss
                    if (world instanceof BossFight2) { 
                        ((BossFight2) world).stopMusic(); // Stop music
                    }
                }
                getWorld().removeObject(this); // Remove boss from the world
                Greenfoot.setWorld(new Level3());
            }
        }
    }

    private void stayOnGround() {
        if (getY() < groundY) {
            setLocation(getX(), groundY);
        }
    }

    private void attackPlayer() {
        if (isAttacking && !hasAttacked) { // Pastikan hanya menyerang satu kali per animasi
            Gatot_StandBy player = (Gatot_StandBy) getOneIntersectingObject(Gatot_StandBy.class);
            if (player != null) {
                BaseWorld world = (BaseWorld) getWorld();
                if (world != null) {
                    HealthBar healthBar = world.getHealthBar();
                    healthBar.decreaseLife(); // Kurangi nyawa pemain hanya satu kali
                    hasAttacked = true; // Tandai bahwa boss telah menyerang
                }
            }
        }

        // Reset hasAttacked di akhir animasi serangan
        if (currentSpriteIndex == attackSprites.length - 1) {
            hasAttacked = false; // menyerang lagi di animasi berikutnya
        }
    }
}