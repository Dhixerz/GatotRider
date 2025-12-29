import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Labyrntine1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Labyrntine2 extends BaseWorld {

    private int score = 0; 
    private GreenfootSound level2Song;
    /**
     * Constructor for objects of class Labyrntine1.
     * 
     */
    public Labyrntine2() {    
        super(1280, 720, 1); 
        prepare();
        
        level2Song = new GreenfootSound("Level2.mp3");
        level2Song.playLoop();
    }
    
    @Override
    public void stopped() {
    level2Song.stop(); // Menghentikan lagu ketika world tidak aktif
    }
    @Override
    public void started() {
    level2Song.playLoop(); // Memutar ulang lagu saat world aktif
    }
    public void stopMusic() {
    level2Song.stop();
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
            scaleActor(crate, crateWidth, crateHeight); // Scale each crate
            addObject(crate, x, 146);
        }

        // vertical crates (left)
        for (int y = 146; y <= 661; y += crateHeight) {
            Crate crate = new Crate();
            scaleActor(crate, crateWidth, crateHeight);
            addObject(crate, 78, y);
        }

        // vertical crates (right)
        for (int y = 223; y <= 377; y += crateHeight) {
            Crate crate = new Crate();
            scaleActor(crate, crateWidth, crateHeight);
            addObject(crate, 1156, y);
        }

        // horizontal crates (bottom)
        for (int x = 78; x <= 1156; x += crateWidth) {
            Crate crate = new Crate();
            scaleActor(crate, crateWidth, crateHeight);
            addObject(crate, x, 608);
        }
        // player
        Gatot_Labyrntine gatot_Labyrntine = new Gatot_Labyrntine(60, 90);
        scaleActor(gatot_Labyrntine, 60, 90); 
        addObject(gatot_Labyrntine, 159, 237);

        // finish
        Finish_Labyrntine2 finish_Labyrntine2 = new Finish_Labyrntine2();
        scaleActor(finish_Labyrntine2, 50, 50); 
        addObject(finish_Labyrntine2, 1156, 531);

        // coin
        Coin coin1 = new Coin();
        scaleActor(coin1, 100, 100); 
        addObject(coin1, 450, 230);

        Coin coin2 = new Coin();
        scaleActor(coin2, 100, 100); 
        addObject(coin2, 930, 347);

        Coin coin3 = new Coin();
        scaleActor(coin3, 100, 100); 
        addObject(coin3, 620, 515);

        Coin coin4 = new Coin();
        scaleActor(coin4, 100, 100); 
        addObject(coin4, 782, 329);

        // timer
        Timer timer = new Timer();  
        addObject(timer, 1180, 50);  

        Crate crate41 = new Crate();
        addObject(crate41,154,532);
        Crate crate42 = new Crate();
        addObject(crate42,310,220);
        Crate crate43 = new Crate();
        addObject(crate43,537,430);
        Crate crate44 = new Crate();
        addObject(crate44,536,507);
        Crate crate45 = new Crate();
        addObject(crate45,364,368);
        Crate crate46 = new Crate();
        addObject(crate46,848,530);
        Crate crate47 = new Crate();
        addObject(crate47,697,329);
        Crate crate48 = new Crate();
        addObject(crate48,1156,435);
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
