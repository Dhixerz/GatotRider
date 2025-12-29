import greenfoot.*;

public class Name extends Actor {
    private GreenfootImage displayImage;

    public Name(String text) {
        displayImage = new GreenfootImage(text, 50, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(displayImage);
    }
}
