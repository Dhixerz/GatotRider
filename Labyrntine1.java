import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Labyrntine1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Labyrntine1 extends BaseWorld {

    private int score = 0; 
    private GreenfootSound level1Song;
    /**
     * Constructor for objects of class Labyrntine1.
     * 
     */
    public Labyrntine1() {    
        super(1280, 720, 1); 
        prepare();
        level1Song = new GreenfootSound("Level1.mp3");
        level1Song.playLoop();
    }
    
    @Override
    public void stopped() {
    level1Song.stop(); // Menghentikan lagu ketika world tidak aktif
    }
    @Override
    public void started() {
    level1Song.playLoop(); // Memutar ulang lagu saat world aktif
    }
    public void stopMusic() {
    level1Song.stop();
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
        for (int x = 232; x <= 1216; x += crateWidth) {
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
        for (int y = 223; y <= 608; y += crateHeight) {
            Crate crate = new Crate();
            scaleActor(crate, crateWidth, crateHeight);
            addObject(crate, 1156, y);
        }

        // horizontal crates (bottom)
        for (int x = 78; x <= 1079; x += crateWidth) {
            Crate crate = new Crate();
            scaleActor(crate, crateWidth, crateHeight);
            addObject(crate, x, 608);
        }

        // Crates tantangan
        Crate crate1 = new Crate();
        scaleActor(crate1, crateWidth, crateHeight);
        addObject(crate1, 540, 320);

        Crate crate2 = new Crate();
        scaleActor(crate2, crateWidth, crateHeight);
        addObject(crate2, 620, 320);

        Crate crate3 = new Crate();
        scaleActor(crate3, crateWidth, crateHeight);
        addObject(crate3, 700, 320);

        Crate crate4 = new Crate();
        scaleActor(crate4, crateWidth, crateHeight);
        addObject(crate4, 700, 430);

        Crate crate5 = new Crate();
        scaleActor(crate5, crateWidth, crateHeight);
        addObject(crate5, 620, 430);

        // player
        Gatot_Labyrntine gatot_Labyrntine = new Gatot_Labyrntine(60, 90);
        scaleActor(gatot_Labyrntine, 60, 90); 
        addObject(gatot_Labyrntine, 390, 520);

        //finish
        Finish_Labyrntine finish_Labyrntine = new Finish_Labyrntine();
        scaleActor(finish_Labyrntine, 50, 50); 
        addObject(finish_Labyrntine, 158, 143);

        // coin
        Coin coin1 = new Coin();
        scaleActor(coin1, 100, 100);
        addObject(coin1, 450, 230);

        Coin coin2 = new Coin();
        scaleActor(coin2, 100, 100);
        addObject(coin2, 900, 350);

        Coin coin3 = new Coin();
        scaleActor(coin3, 100, 100);
        addObject(coin3, 170, 400);

        Coin coin4 = new Coin();
        scaleActor(coin4, 100, 100);
        addObject(coin4, 500, 500);

        Coin coin5 = new Coin();
        scaleActor(coin5, 100, 100);
        addObject(coin5, 700, 530);

        // timer
        Timer timer = new Timer();  
        addObject(timer, 1180, 50);  
        
        Crate crate46 = new Crate();
        addObject(crate46,394,389);
        coin2.setLocation(848,234);
        Crate crate47 = new Crate();
        addObject(crate47,858,529);
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
