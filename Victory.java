import greenfoot.*;
import java.awt.Color;

public class Victory extends Actor {
    private String playerName = "";
    private GreenfootImage displayImage;
    private int playerScore;

    public Victory(int score) {
        this.playerScore = score;
        updateMessage();
    }

    public void act() {
        String key = Greenfoot.getKey();
        if (key != null) {
            if (key.equals("enter") && !playerName.isEmpty()) {
                historyMenu.addHighScore(playerName, playerScore);
                Greenfoot.setWorld(new historyMenu()); 
            } else if (key.equals("backspace") && playerName.length() > 0) {
                playerName = playerName.substring(0, playerName.length() - 1);
            } else if (key.length() == 1 && playerName.length() < 10) { // Maksimal 10 karakter
                playerName += key.toUpperCase();
            }
            updateMessage();
        }
    }

    private void updateMessage() {
        displayImage = new GreenfootImage(600, 200); 
        displayImage.setColor(greenfoot.Color.BLACK);  
        displayImage.fill();                          
        displayImage.setColor(greenfoot.Color.RED);    
        displayImage.setFont(displayImage.getFont().deriveFont(50f)); 
        displayImage.drawString("Victory!", 150, 50); 
    
        displayImage.setColor(greenfoot.Color.WHITE);  
        displayImage.setFont(displayImage.getFont().deriveFont(30f));
        displayImage.drawString("Enter Name: " + playerName, 100, 120);
        displayImage.drawString("Score: " + playerScore, 100, 170);
    
        setImage(displayImage);
    }
}
