import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Counter extends Actor {
    private int score; 
    private static int globalScore = 0; // simpan skor secara global

    public Counter() {
        this.score = globalScore; // ambil nilai global dri counter
        updateImage();
    }

    public void addScore(int points) {
    score += points; 
    globalScore = score; 
    High_Score.checkAndSetHighScore(score); 
    updateImage();
    }

    private void updateImage() {
        setImage(new GreenfootImage("Score: " + score, 40, Color.WHITE, new Color(0, 0, 0, 0)));
    }

    public int getScore() {
        return score;
    }

    public static int getGlobalScore() {
        return globalScore;
    }

    public static void setGlobalScore(int score) {
        globalScore = score;
    }
}
