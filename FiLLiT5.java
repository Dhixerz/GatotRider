import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FiLLiT1 here.
 */
public class FiLLiT5 extends BaseWorld {
    private Kartini1 Kartini1;
    private int delayCounter = 0;  
    private boolean transitionStarted = false;  
    private int wrongImageTimer = 0;  
    private boolean showingWrongImage = false;  
    private HealthBar healthBar; 
    
    private GreenfootSound level5Song;
    public FiLLiT5() {    
        super(1280, 720, 1); 
        prepare();
        level5Song = new GreenfootSound("th06_06.mp3");
        level5Song.playLoop();
    }
    
    public void act() {
        if (showingWrongImage) {
            wrongImageTimer++;
            if (wrongImageTimer >= 150) { 
                removeObjects(getObjects(Wrong.class)); 
                wrongImageTimer = 0;
                showingWrongImage = false;  
            }
        } else if (transitionStarted) {
            delayCounter++;
            if (delayCounter >= 300) {  
                Greenfoot.setWorld(new NumQuest5());
            }
        } else {
            if (Greenfoot.isKeyDown("t")) {
                Greenfoot.playSound("VictoryBGM.wav");
                Kartini1.changeImage("Kartini2.png");
                stopMusic();
                transitionStarted = true;  
            } else if (Greenfoot.getKey() != null) {
                showWrongImage();  
            }
        }
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
    
    private void prepare() {
        Wayang1 wayang1 = new Wayang1(500, 500);
        addObject(wayang1, 938, 372);
        wayang1.setLocation(1026, 368);

        int worldWidth = getWidth();  
        int tileWidth = 128;  
        int yPosition = 651; 

        for (int x = 0; x < worldWidth; x += tileWidth) {
            Tiles tile = new Tiles();  
            addObject(tile, x + tileWidth / 2, yPosition);  
        }

        Gatot_FiLLiT gatot_FiLLiT = new Gatot_FiLLiT(90, 130);
        addObject(gatot_FiLLiT, 178, 399);
        gatot_FiLLiT.setLocation(200, 520);

        Kartini1 = new Kartini1();
        addObject(Kartini1, 421, 373);

        // Inisialisasi health bar
        healthBar = getHealthBar(); 

        //timer
        Timer timer = new Timer();  
        addObject(timer, 1180, 50);  
        
        FiLLiT_5 fiLLiT_5 = new FiLLiT_5(300, 300);
        addObject(fiLLiT_5,722,178);

    }
    
    private void showWrongImage() {
        Wrong wrong = new Wrong();
        addObject(wrong, getWidth() / 2, getHeight() / 2); 
        showingWrongImage = true;  
        wrongImageTimer = 0;

        // Kurangi nyawa
        if (healthBar != null) {
            healthBar.decreaseLife(); // Kurangi health -1
        }
    }
}
