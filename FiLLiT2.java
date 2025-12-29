import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FiLLiT1 here.
 */
public class FiLLiT2 extends BaseWorld {
    private Borobudur1 Borobudur1;
    private int delayCounter = 0;  
    private boolean transitionStarted = false;  
    private int wrongImageTimer = 0;  
    private boolean showingWrongImage = false;  
    private HealthBar healthBar; 
    
    private GreenfootSound level2Song;
    public FiLLiT2() {    
        super(1280, 720, 1); 
        prepare();
        
        level2Song = new GreenfootSound("Level2.mp3");
        level2Song.playLoop();
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
                Greenfoot.setWorld(new NumQuest2());
            }
        } else {
            if (Greenfoot.isKeyDown("b")) {
                Greenfoot.playSound("VictoryBGM.wav");
                Borobudur1.changeImage("Borobudur2.png");
                stopMusic();
                transitionStarted = true;  
            } else if (Greenfoot.getKey() != null) {
                showWrongImage();  
            }
        }
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

        Borobudur1 = new Borobudur1();
        addObject(Borobudur1, 421, 373);

        // Inisialisasi health bar
        healthBar = getHealthBar(); 

        //timer
        Timer timer = new Timer();  
        addObject(timer, 1180, 50);  
        
        FiLLiT_2 fiLLiT_2 = new FiLLiT_2(300, 300);
        addObject(fiLLiT_2,722,178);

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
