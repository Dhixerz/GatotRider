import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gatot_StandBy extends Actor {
    private int hSpeed = 5;
    private int vSpeed = 2;
    private int acceleration = 1;
    private int jumpStrength = 20;
    private int maxJumpSpeed = -20;
    private int attackDuration = 20;

    GreenfootImage[] runRight = new GreenfootImage[7];
    GreenfootImage[] runLeft = new GreenfootImage[7];
    GreenfootImage[] jumpImages = new GreenfootImage[2];
    private GreenfootImage idleImage;
    private GreenfootImage basicAttackImage;

    private int animCounter = 0;
    private int frameDelay = 10;
    private int frameDelayCounter = 0;
    private int attackCounter = 0;

    private boolean isFacingLeft = false;
    private boolean isJumping = false;
    private boolean isAttacking = false; // track attack status

    public Gatot_StandBy() {
        initAnimationSprites();
        idleImage = new GreenfootImage("Gatot_StandBy.png");
        basicAttackImage = new GreenfootImage("GatotBasicAttack.png");
        basicAttackImage.scale(100, 130);
        setImage(idleImage);
    }

    public Gatot_StandBy(int height, int width) {
        this();
        idleImage.scale(height, width);
    }

    public void act() {
        if (isAttacking) {
            attackCounter--;
            if (attackCounter <= 0) {
                isAttacking = false;
                setIdleImage();
            }
        } else {
            checkKeys();
        }
        checkFall();
    }

    public void checkKeys() {
        boolean isMoving = false;

        if (Greenfoot.isKeyDown("d")) {
            moveRight();
            animateRunRight();
            isFacingLeft = false;
            isMoving = true;
        }
        if (Greenfoot.isKeyDown("a")) {
            moveLeft();
            animateRunLeft();
            isFacingLeft = true;
            isMoving = true;
        }
        if (Greenfoot.isKeyDown("space")) {
            jump();
        }
        if (Greenfoot.mousePressed(null)) {
            performAttack();
        }

        if (!isMoving && !isJumping && !isAttacking) {
            setIdleImage();
        }
    }

    public void moveRight() {
        if (!isTouchingLeftOrRight(Tiles.class) && !isTouchingLeftOrRight(PlatformTiles.class) && !isTouchingLeftOrRight(Crate1.class)) {
            setLocation(getX() + hSpeed, getY());
        }
        animateRunRight();
    }

    public void moveLeft() {
        if (!isTouchingLeftOrRight(Tiles.class) && !isTouchingLeftOrRight(PlatformTiles.class) && !isTouchingLeftOrRight(Crate1.class)) {
            setLocation(getX() - hSpeed, getY());
        }
        animateRunLeft();
    }

    public void fall() {
        detectPlatform();
        setLocation(getX(), getY() + vSpeed);

        if (vSpeed < maxJumpSpeed) { 
            vSpeed = maxJumpSpeed;
        } else {
            vSpeed += acceleration;
        }
    }

    public boolean onGround() {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight() / 2, Tiles.class);
        Actor platformUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, PlatformTiles.class);
        Actor crateUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, Crate1.class);  // Pengecekan Crate1
        return under != null || platformUnder != null || crateUnder != null;
    }

    public void checkFall() {
        if (onGround()) {
            vSpeed = 0;
            isJumping = false;
        } else {
            isJumping = true;
            animateJump();
            fall();
        }
    }

    public void jump() {
        if (onGround()) { 
            vSpeed = -jumpStrength;
            isJumping = true;
            animateJump();
            fall();
        }
    }

    public void detectPlatform() {
        for (int i = 0; i < vSpeed; i++) {
            Actor tiles = getOneObjectAtOffset(0, getImage().getHeight() / 2 + i, Tiles.class);
            Actor platformTiles = getOneObjectAtOffset(0, getImage().getHeight() / 2 + i, PlatformTiles.class);
            Actor crate = getOneObjectAtOffset(0, getImage().getHeight() / 2 + i, Crate1.class);  // Deteksi Crate1

            if (tiles != null || platformTiles != null || crate != null) {
                vSpeed = 1;  // stop falling
            }
        }

        Actor leftTile = getOneObjectAtOffset(-getImage().getWidth() / 2, 0, PlatformTiles.class);
        Actor rightTile = getOneObjectAtOffset(getImage().getWidth() / 2, 0, PlatformTiles.class);
        Actor leftCrate = getOneObjectAtOffset(-getImage().getWidth() / 2, 0, Crate1.class);
        Actor rightCrate = getOneObjectAtOffset(getImage().getWidth() / 2, 0, Crate1.class);  // Deteksi Crate1

        if (leftTile != null || rightTile != null || leftCrate != null || rightCrate != null) {
            if (isFacingLeft) {
                setLocation(getX() + hSpeed, getY());  
            } else {
                setLocation(getX() - hSpeed, getY());  
            }
        }
    }

    public void performAttack() {
        isAttacking = true;
        attackCounter = attackDuration;

        GreenfootImage attackImage = new GreenfootImage(basicAttackImage);
        if (isFacingLeft) {
            attackImage.mirrorHorizontally();  
        }
        setImage(attackImage);

        GatotAttackEffect attackEffect = new GatotAttackEffect(isFacingLeft);
        attackEffect.getImage().scale(60, 50);

        if (isFacingLeft) {
            getWorld().addObject(attackEffect, getX() - 50, getY());
        } else {
            getWorld().addObject(attackEffect, getX() + 50, getY());
        }
    }

    public void initAnimationSprites() {
        for (int i = 0; i < 7; i++) {
            String filename = "GatotRun" + i + ".png";
            runRight[i] = new GreenfootImage(filename);
            runRight[i].scale(110, 130);
        }

        for (int i = 0; i < 7; i++) {
            String filename = "GatotRun" + i + ".png";
            runLeft[i] = new GreenfootImage(filename);
            runLeft[i].scale(110, 130);
            runLeft[i].mirrorHorizontally();
        }

        for (int i = 0; i < 2; i++) {
            String filename = "GatotJump" + i + ".png";
            jumpImages[i] = new GreenfootImage(filename);
            jumpImages[i].scale(90, 130);
        }
    }

    public void animateRunRight() {
        if (frameDelayCounter++ % frameDelay == 0) {
            setImage(runRight[animCounter % 7]);
            animCounter++;
        }
    }

    public void animateRunLeft() {
        if (frameDelayCounter++ % frameDelay == 0) {
            setImage(runLeft[animCounter % 7]);
            animCounter++;
        }
    }

    public void animateJump() {
        GreenfootImage currentJumpImage = jumpImages[animCounter % 2];

        if (isFacingLeft) {
            currentJumpImage = new GreenfootImage(currentJumpImage);
            currentJumpImage.mirrorHorizontally();
        }

        setImage(currentJumpImage);

        if (frameDelayCounter++ % frameDelay == 0) {
            animCounter++;
        }
    }

    private void setIdleImage() {
        GreenfootImage idle = new GreenfootImage(idleImage);
        if (isFacingLeft) {
            idle.mirrorHorizontally();
        }
        setImage(idle);
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public boolean isTouchingLeftOrRight(Class<?> tileClass) {
        Actor left = getOneObjectAtOffset(-getImage().getWidth() / 2, 0, tileClass);
        Actor right = getOneObjectAtOffset(getImage().getWidth() / 2, 0, tileClass);
        return left != null || right != null;
    }
}
