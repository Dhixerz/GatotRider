import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class Game_Over extends World {
    private GameOver gameOverActor;
    public Game_Over(int score) {
        super(1280, 720, 1);
        Greenfoot.playSound("GameOverBGM.wav");
        prepare(score);
    }

    private void prepare(int score) {
        gameOverActor = new GameOver(score);
        addObject(gameOverActor, 930, getHeight() / 2); 
    }
}