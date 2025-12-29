import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class victorious extends World
{
    private Victory victoryActor;
    public victorious(int score) {
        super(1280, 720, 1);
        Greenfoot.playSound("VictoryBGM.wav");
        prepare(score);
    }

    private void prepare(int score) {
        Victory victoryActor = new Victory(score);
        addObject(victoryActor, getWidth() / 2, getHeight() / 2); 
    }
}
