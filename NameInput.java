import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class NameInput extends Actor {
    private String playerName = "";
    private GreenfootImage displayImage;
    private ArrayList<String> highScores = new ArrayList<>();

    public NameInput() {
        updateImage();
    }

    public void act() {
        String key = Greenfoot.getKey();
        if (key != null) {
            if (key.equals("enter")) {
                saveNameAndScore();
            } else if (key.equals("backspace") && playerName.length() > 0) {
                playerName = playerName.substring(0, playerName.length() - 1);
            } else if (key.length() == 1 && playerName.length() < 10) { // Maks 10 karakter
                playerName += key.toUpperCase();
            }
            updateImage();
        }
    }

    private void updateImage() {
        displayImage = new GreenfootImage("Enter Name: " + playerName, 30, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(displayImage);
    }

    private void saveNameAndScore() {
        BaseWorld world = (BaseWorld) getWorld();
        Counter counter = world.getCounter();
        String entry = playerName + " - " + counter.getScore();
        highScores.add(entry);
        playerName = ""; // Reset name input
        counter.setGlobalScore(0); // Reset score
        displayHighScores();
    }

    private void displayHighScores() {
        BaseWorld world = (BaseWorld) getWorld();
        int startY = 100;
        for (String entry : highScores) {
            Name nameDisplay = new Name(entry);
            world.addObject(nameDisplay, 640, startY); 
            startY += 30; // Jarak antar baris
        }
        getWorld().removeObject(this); // Hapus input field setelah selesai
    }
}
