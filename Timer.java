import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Timer extends Actor {
    private int timeLeft;  
    private long lastUpdateTime;  

    public Timer() {
        this.timeLeft = 300;  
        updateImage();
    }

    public void act() {
        if (System.currentTimeMillis() - lastUpdateTime >= 1000) {
            lastUpdateTime = System.currentTimeMillis();
            if (timeLeft > 0) {
                timeLeft--;
            }
            updateImage();

            if (timeLeft == 0) {
                int finalScore = Counter.getGlobalScore(); // Ambil skor global
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
                Greenfoot.setWorld(new Game_Over(finalScore)); 
            }
        }
    }

    private void updateImage() {
        setImage(new GreenfootImage("Time: " + timeLeft, 40, Color.WHITE, new Color(0, 0, 0, 0)));
    }
}

