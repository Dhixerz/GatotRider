import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class snake extends Actor {
    private GreenfootImage idleImage = new GreenfootImage("snake.png");
    private GreenfootImage[] snakeImages = new GreenfootImage[5];
    private int animationCounter = 0;
    private int frameDelay = 10;
    private int frameDelayCounter = 0;
    private boolean isAlive = true;
    private boolean isMirrored = false;
    private boolean isIdle = true;
    private int idleDelayTimer = 300; // 5 detik pada 60 FPS
    private int idleDelayCounter = 0;
    private boolean playerAlreadyHit = false; // player hit stat

    public snake() {
        idleImage.scale(70, 110);
        setImage(idleImage);
        for (int i = 0; i < snakeImages.length; i++) {
            snakeImages[i] = new GreenfootImage("snake" + i + ".png");
            snakeImages[i].scale(70, 110);
        }
    }

    public void act() {
        if (isAlive) {
            if (isIdle) {
                if (idleDelayCounter >= idleDelayTimer) {
                    isIdle = false; 
                    idleDelayCounter = 0; 
                    animationCounter = 0; 
                } else {
                    idleDelayCounter++;
                }
            } else {
                animateSnake();
                if (animationCounter >= snakeImages.length) {
                    isIdle = true;
                    setImage(idleImage); 
                }
            }

            checkForHitByGatotAttackEffect();
            if (getWorld() != null) { 
                checkForHitByPlayer();
            }

            if (getWorld() != null) { 
                checkAndMirrorImage();
            }
        }
    }

    private void animateSnake() {
        if (frameDelayCounter++ % frameDelay == 0) {
            Gatot_StandBy player = (Gatot_StandBy) getWorld().getObjects(Gatot_StandBy.class).get(0);
            if (player != null) {
                int snakeX = getX();
                int playerX = player.getX();
                if (snakeX > playerX) {
                    setImage(getMirroredImage(snakeImages[animationCounter % snakeImages.length]));
                } else {
                    setImage(snakeImages[animationCounter % snakeImages.length]);
                }
            }
            animationCounter++;
        }
    }

    private GreenfootImage getMirroredImage(GreenfootImage originalImage) {
        GreenfootImage mirroredImage = new GreenfootImage(originalImage);
        mirroredImage.mirrorHorizontally();
        mirroredImage.scale(70, 110); 
        return mirroredImage;
    }

    private void checkForHitByGatotAttackEffect() {
        // snake hit klo bkn posisi idle
        if (!isIdle && isTouching(GatotAttackEffect.class)) {
            isAlive = false;
            removeTouching(GatotAttackEffect.class);

            // +skor
            BaseWorld world = (BaseWorld) getWorld(); 
            world.getCounter().addScore(1);

            getWorld().removeObject(this);
            return; 
        }
    }

    private void checkForHitByPlayer() {
        Gatot_StandBy player = (Gatot_StandBy) getOneIntersectingObject(Gatot_StandBy.class);

        if (player != null && !isIdle) {
            if (!playerAlreadyHit) {
                playerAlreadyHit = true; 
                BaseWorld world = (BaseWorld) getWorld(); 
                world.getHealthBar().decreaseLife(); // -nyawa 
            }
        } else {
            playerAlreadyHit = false; // reset status jika tidak menyentuh lagi
        }
    }

    private void checkAndMirrorImage() {
        Gatot_StandBy player = (Gatot_StandBy) getWorld().getObjects(Gatot_StandBy.class).get(0);

        if (player != null) {
            int snakeX = getX();
            int playerX = player.getX();
            if (snakeX > playerX && !isMirrored) {
                mirrorImage(true);
                isMirrored = true;
            } else if (snakeX < playerX && isMirrored) {
                mirrorImage(false);
                isMirrored = false;
            }
        }
    }

    private void mirrorImage(boolean mirror) {
        if (!isIdle) {
            for (int i = 0; i < snakeImages.length; i++) {
                GreenfootImage img = new GreenfootImage("snake" + i + ".png");
                img.scale(70, 110);
                if (mirror) {
                    img.mirrorHorizontally();
                }
                snakeImages[i] = img;
            }
            setImage(snakeImages[animationCounter % snakeImages.length]);
        }
    }
}