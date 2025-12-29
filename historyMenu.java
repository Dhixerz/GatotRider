import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class historyMenu extends World
{

    /**
     * Constructor for objects of class Start_Menu.
     * 
     */
    private static ArrayList<String> highScores = new ArrayList<>();
    private GreenfootSound mainMenuSong;
    public historyMenu()
    {
        super(1280, 720, 1);
        prepare();
        displayHighScores();
        mainMenuSong = new GreenfootSound("mainmenusong.mp3");
        mainMenuSong.playLoop();
    }
    
    private void prepare() {
        High_Score high_Score = new High_Score();
        addObject(high_Score,152,158);
        high_Score.setLocation(168,123);
        btnBack2 btnBack2 = new btnBack2();
        addObject(btnBack2,91,616);
    }

    public static void addHighScore(String name, int score) {
        highScores.add(name + " - " + score);
        if (highScores.size() > 5) {
            highScores.remove(0); // Batas tampilan skor maksimal 5
        }
    }

    private void displayHighScores() {
        int startY = 300; // Posisi awal untuk menampilkan skor
        for (String entry : highScores) {
            Name nameDisplay = new Name(entry);
            addObject(nameDisplay, 640, startY);
            startY += 30; // Jarak antar baris
        }
    }
    
    @Override
    public void stopped() {
    mainMenuSong.stop(); // Menghentikan lagu ketika world tidak aktif
    }
    @Override
    public void started() {
    mainMenuSong.playLoop(); // Memutar ulang lagu saat world aktif
    }
    public void stopMusic() {
    mainMenuSong.stop();
    }
}