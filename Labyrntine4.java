import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Labyrntine1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Labyrntine4 extends BaseWorld {

    private int score = 0; 
    private GreenfootSound level4Song;
    /**
     * Constructor for objects of class Labyrntine1.
     * 
     */
    public Labyrntine4() {    
        super(1280, 720, 1); 
        prepare();
        level4Song = new GreenfootSound("Level4.mp3");
        level4Song.playLoop();
    }
    
    @Override
    public void stopped() {
    level4Song.stop(); // Menghentikan lagu ketika world tidak aktif
    }
    @Override
    public void started() {
    level4Song.playLoop(); // Memutar ulang lagu saat world aktif
    }
    public void stopMusic() {
    level4Song.stop();
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
        for (int y = 377; y <= 608; y += crateHeight) {
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

        // player 
        Gatot_Labyrntine gatot_Labyrntine = new Gatot_Labyrntine(60, 90);
        scaleActor(gatot_Labyrntine, 60, 90); 
        addObject(gatot_Labyrntine, 1080, 520);

        // finish 
        Finish_Labyrntine4 finish_Labyrntine4 = new Finish_Labyrntine4();
        scaleActor(finish_Labyrntine4, 50, 50); 
        addObject(finish_Labyrntine4, 1156, 223);

        // coin
        Coin coin1 = new Coin();
        scaleActor(coin1, 100, 100); 
        addObject(coin1, 450, 230);
        
        Coin coin5 = new Coin();
        scaleActor(coin5, 100, 100); 
        addObject(coin5, 627, 371);
        
        Coin coin2 = new Coin();
        scaleActor(coin2, 100, 100); 
        addObject(coin2, 165, 395);
        
        Coin coin3 = new Coin();
        scaleActor(coin3, 100, 100); 
        addObject(coin3, 510, 512);
        
        Coin coin4 = new Coin();
        scaleActor(coin4, 100, 100); 
        addObject(coin4, 998, 234);

        // timer
        Timer timer = new Timer();  
        addObject(timer, 1180, 50);  
        Crate crate41 = new Crate();
        addObject(crate41,1077,322);
        Crate crate42 = new Crate();
        addObject(crate42,1076,400);
        Crate crate43 = new Crate();
        addObject(crate43,997,322);
        Crate crate44 = new Crate();
        addObject(crate44,538,410);
        Crate crate45 = new Crate();
        addObject(crate45,538,350);
        Crate crate46 = new Crate();
        addObject(crate46,262,397);
        Crate crate47 = new Crate();
        addObject(crate47,338,397);
        Crate crate48 = new Crate();
        addObject(crate48,414,397);
        Crate crate49 = new Crate();
        addObject(crate49,359,221);
        Crate crate50 = new Crate();
        addObject(crate50,1155,323);
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
