import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HealthBar extends Actor {
    private GreenfootImage healthBarImage;
    private int lives = 6; // nyawa saat ini
    private final int maxLives = 6; // mksimum nyawa

    public HealthBar() {
        healthBarImage = new GreenfootImage(302, 32); // 50px per nyawa
        setImage(healthBarImage);
        drawHealthBar();
    }

    public void act() {
    }

    public void decreaseLife() {
        if (lives > 0) {
            lives--;
            drawHealthBar();
        }

        if (lives <= 0) {
            BaseWorld world = (BaseWorld) getWorld();
            // Hentikan musik jika world memiliki musik
            if (world instanceof FiLLiT1) {
                ((FiLLiT1) world).stopMusic();
            } else if (world instanceof Level1) {
                ((Level1) world).stopMusic();
            } else if (world instanceof NumQuest1) {
                ((NumQuest1) world).stopMusic();
            } else if (world instanceof Labyrntine1) {
                ((Labyrntine1) world).stopMusic();
            } else if (world instanceof BossFight1) {
                ((BossFight1) world).stopMusic();
            } else if (world instanceof Level2) {
                ((Level2) world).stopMusic();
            } else if (world instanceof FiLLiT2) {
                ((FiLLiT2) world).stopMusic();
            } else if (world instanceof NumQuest2) {
                ((NumQuest2) world).stopMusic();
            } else if (world instanceof Labyrntine2) {
                ((Labyrntine2) world).stopMusic();
            } else if (world instanceof BossFight2) {
                ((BossFight2) world).stopMusic();
            } else if (world instanceof Level3) {
                ((Level3) world).stopMusic();
            } else if (world instanceof FiLLiT3) {
                ((FiLLiT3) world).stopMusic();
            } else if (world instanceof NumQuest3) {
                ((NumQuest3) world).stopMusic();
            } else if (world instanceof Labyrntine3) {
                ((Labyrntine3) world).stopMusic();
            } else if (world instanceof BossFight3) {
                ((BossFight3) world).stopMusic();
            } else if (world instanceof Level4) {
                ((Level4) world).stopMusic();
            } else if (world instanceof FiLLiT4) {
                ((FiLLiT4) world).stopMusic();
            } else if (world instanceof NumQuest4) {
                ((NumQuest4) world).stopMusic();
            } else if (world instanceof Labyrntine4) {
                ((Labyrntine4) world).stopMusic();
            } else if (world instanceof BossFight4) {
                ((BossFight4) world).stopMusic();
            } else if (world instanceof Level5) {
                ((Level5) world).stopMusic();
            } else if (world instanceof FiLLiT5) {
                ((FiLLiT5) world).stopMusic();
            } else if (world instanceof NumQuest5) {
                ((NumQuest5) world).stopMusic();
            } else if (world instanceof Labyrntine5) {
                ((Labyrntine5) world).stopMusic();
            } else if (world instanceof BossFight5) {
                ((BossFight5) world).stopMusic();
            }
            int finalScore = world.getCounter().getScore();
            Greenfoot.setWorld(new Game_Over(finalScore)); // Pindah ke Game_Over world
        }
    }


    public void increaseLife() {
        if (lives < maxLives) {
            lives++;
            drawHealthBar();
        }
    }

    public boolean canIncreaseLife() {
        return lives < maxLives; // return true jika HealthBar belum penuh
    }

    private void drawHealthBar() {
        healthBarImage.setColor(Color.BLACK);
        healthBarImage.fillRect(0, 0, 301, 31); // panjang sesuai jumlah lives

        for (int i = 0; i < lives; i++) {
            healthBarImage.setColor(Color.GREEN);
            healthBarImage.fillRect(1 + i * 50, 1, 48, 30); // 50 pixel untuk setiap nyawa
        }
    }
}
