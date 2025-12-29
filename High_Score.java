import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class High_Score extends Actor {
    private static int highScore = 0; // skor tertinggi global

    public High_Score() {
        updateImage();
    }

    public static void checkAndSetHighScore(int currentScore) {
        if (currentScore > highScore) {
            highScore = currentScore; // apdet skor tertinggi
        }
    }

    private void updateImage() {
        setImage(new GreenfootImage("High Score: " + highScore, 40, Color.YELLOW, new Color(0, 0, 0, 0)));
    }

    public void act() {
        updateImage(); // selalu tampilkan skor tertinggi terbaru
    }

    public static int getHighScore() {
        return highScore;
    }
}
