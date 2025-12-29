import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Victory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Victory extends Actor
{
    private String playerName = "";
    private GreenfootImage displayImage;
    private int playerScore;
    
    public Victory(int score) {
        this.playerScore = score;
        updateMessage();
    }
    public void act()
    {
        // Add your action code here.
    }
}
