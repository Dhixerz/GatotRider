import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)

public class Gatot_Labyrntine extends Actor {
    private int speed = 5; // Kecepatan gerakan
    private int moveDirectionX = 0; // Arah gerakan x
    private int moveDirectionY = 0; // Arah gerakan y

    public Gatot_Labyrntine(int height, int width) {
        getImage().scale(height, width);
    }

    public void act() {
        handleInput(); 
        moveStep();    
    }

    private void handleInput() {
        if (moveDirectionX == 0 && moveDirectionY == 0) {
            if (Greenfoot.isKeyDown("up")) {
                moveDirectionX = 0;
                moveDirectionY = -speed;
            } else if (Greenfoot.isKeyDown("down")) {
                moveDirectionX = 0;
                moveDirectionY = speed;
            } else if (Greenfoot.isKeyDown("left")) {
                moveDirectionX = -speed;
                moveDirectionY = 0;
            } else if (Greenfoot.isKeyDown("right")) {
                moveDirectionX = speed;
                moveDirectionY = 0;
            }
        }
    }

    private void moveStep() {
        if (moveDirectionX == 0 && moveDirectionY == 0) {
            return; 
        }

        int nextX = getX() + moveDirectionX;
        int nextY = getY() + moveDirectionY;

        if (nextX < 0 || nextX >= getWorld().getWidth() || nextY < 0 || nextY >= getWorld().getHeight()) {
            stopMovement();
            return;
        }

        if (isCrateBlocking(nextX, nextY)) {
            stopMovement();
            return;
        }

        // tidak ada halangan = set lokasi baru
        setLocation(nextX, nextY);
    }

    private boolean isCrateBlocking(int nextX, int nextY) {
        int gatotLeft = nextX - getImage().getWidth() / 2;
        int gatotRight = nextX + getImage().getWidth() / 2;
        int gatotTop = nextY - getImage().getHeight() / 2;
        int gatotBottom = nextY + getImage().getHeight() / 2;

        for (Object obj : getWorld().getObjects(Crate.class)) {
            Crate crate = (Crate) obj;
            int crateLeft = crate.getX() - crate.getImage().getWidth() / 2;
            int crateRight = crate.getX() + crate.getImage().getWidth() / 2;
            int crateTop = crate.getY() - crate.getImage().getHeight() / 2;
            int crateBottom = crate.getY() + crate.getImage().getHeight() / 2;

            // Periksa sisi sesuai arah gerakan
            if (moveDirectionX < 0 && gatotLeft <= crateRight && gatotRight > crateLeft && gatotBottom > crateTop && gatotTop < crateBottom) {
                return true; // Gerakan ke kiri
            }
            if (moveDirectionX > 0 && gatotRight >= crateLeft && gatotLeft < crateRight && gatotBottom > crateTop && gatotTop < crateBottom) {
                return true; // Gerakan ke kanan
            }
            if (moveDirectionY < 0 && gatotTop <= crateBottom && gatotBottom > crateTop && gatotLeft < crateRight && gatotRight > crateLeft) {
                return true; // Gerakan ke atas
            }
            if (moveDirectionY > 0 && gatotBottom >= crateTop && gatotTop < crateBottom && gatotLeft < crateRight && gatotRight > crateLeft) {
                return true; // Gerakan ke bawah
            }
        }
        return false; // Tidak ada crate yang menghalangi
    }

    private void stopMovement() {
        moveDirectionX = 0;
        moveDirectionY = 0;
    }
}
