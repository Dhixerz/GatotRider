import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Labyrntine1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Labyrntine5 extends BaseWorld {

    private int score = 0; 
    private GreenfootSound level5Song;
    /**
     * Constructor for objects of class Labyrntine1.
     * 
     */
    public Labyrntine5() {    
        super(1280, 720, 1); 
        prepare();
        level5Song = new GreenfootSound("th06_06.mp3");
        level5Song.playLoop();
    }
    
    @Override
    public void stopped() {
    level5Song.stop(); // Menghentikan lagu ketika world tidak aktif
    }
    @Override
    public void started() {
    level5Song.playLoop(); // Memutar ulang lagu saat world aktif
    }
    public void stopMusic() {
    level5Song.stop();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        // Crate1
        int crateWidth = 77;
        int crateHeight = 77;

        // horizontal crates
        for (int x = 155; x <= 1216; x += crateWidth) {
            Crate crate = new Crate();
            scaleActor(crate, crateWidth, crateHeight); 
            addObject(crate, x, 146);
        }

        // vertical crates (left)
        for (int y = 146; y <= 661; y += crateHeight) {
            Crate crate = new Crate();
            scaleActor(crate, crateWidth, crateHeight);
            addObject(crate, 78, y);
        }

        // vertical crates (right)
        for (int y = 223; y <= 608; y += crateHeight) {
            Crate crate = new Crate();
            scaleActor(crate, crateWidth, crateHeight);
            addObject(crate, 1156, y);
        }

        // horizontal crates (bottom)
        for (int x = 617; x <= 1156; x += crateWidth) {
            Crate crate = new Crate();
            scaleActor(crate, crateWidth, crateHeight);
            addObject(crate, x, 608);
        }

        // additional horizontal crates (bottom)
        for (int x = 155; x <= 463; x += crateWidth) {
            Crate crate = new Crate();
            scaleActor(crate, crateWidth, crateHeight);
            addObject(crate, x, 608);
        }
        // player
        Gatot_Labyrntine gatot_Labyrntine = new Gatot_Labyrntine(60, 90);
        scaleActor(gatot_Labyrntine, 60, 90); 
        addObject(gatot_Labyrntine, 686, 234);

        // finish
        Finish_Labyrntine5 finish_Labyrntine5 = new Finish_Labyrntine5();
        scaleActor(finish_Labyrntine5, 50, 50); 
        addObject(finish_Labyrntine5, 540, 608);

        // coin
        Coin coin1 = new Coin();
        scaleActor(coin1, 100, 100); 
        addObject(coin1, 450, 230);
        
        Coin coin5 = new Coin();
        scaleActor(coin5, 100, 100); 
        addObject(coin5, 832, 234);
        
        Coin coin2 = new Coin();
        scaleActor(coin2, 100, 100); 
        addObject(coin2, 166, 232);
        
        Coin coin3 = new Coin();
        scaleActor(coin3, 100, 100); 
        addObject(coin3, 542, 360);
        
        Coin coin4 = new Coin();
        scaleActor(coin4, 100, 100); 
        addObject(coin4, 864, 514);

        // timer
        Timer timer = new Timer();  
        addObject(timer, 1180, 50);  
        Crate crate41 = new Crate();
        addObject(crate41,460,353);
        Crate crate42 = new Crate();
        addObject(crate42,256,275);
        Crate crate43 = new Crate();
        addObject(crate43,1074,250);
        Crate crate44 = new Crate();
        addObject(crate44,994,250);
        Crate crate45 = new Crate();
        addObject(crate45,913,250);
    }

    private void scaleActor(Actor actor, int width, int height) {
        GreenfootImage image = actor.getImage();
        image.scale(width, height);
        actor.setImage(image);
    }

    public void addScore(int points) {
        score += points;
        System.out.println("Score: " + score);
    }
}
