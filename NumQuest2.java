import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NumQuest1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NumQuest2 extends BaseWorld {

    private boolean showingWrongImage = false;
    private int wrongImageTimer = 0;
    private boolean transitionStarted = false;
    private int transitionTimer = 0;

    private Benar_2_1 benar_2_1; 
    private HealthBar healthBar; 
    
    private GreenfootSound level2Song;
    public NumQuest2() {
        super(1280, 720, 1);
        prepare();
        
        level2Song = new GreenfootSound("Level2.mp3");
        level2Song.playLoop();
    }

    public void act() {
        if (showingWrongImage) {
            wrongImageTimer++;
            if (wrongImageTimer >= 300) { // 5 detik (60 fps * 5)
                removeObjects(getObjects(Wrong.class));
                showingWrongImage = false;
                wrongImageTimer = 0;
            }
        } else if (transitionStarted) {
            transitionTimer++;
            if (transitionTimer >= 300) { // 5 detik (60 fps * 5)
                stopMusic();
                Greenfoot.setWorld(new Labyrntine2());
            }
        } else {
            checkClicks();
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
        Wayang2 wayang2 = new Wayang2(500, 500);
        addObject(wayang2, 1026, 368);

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

        Soal2 soal2 = new Soal2();
        addObject(soal2, 530, 176);

        benar_2_1 = new Benar_2_1();
        addObject(benar_2_1, 441, 316);

        Salah_2_1 salah_2_1 = new Salah_2_1();
        addObject(salah_2_1, 626, 312);

        Salah_2_2 salah_2_2 = new Salah_2_2();
        addObject(salah_2_2, 441, 416);

        Salah_2_3 salah_2_3 = new Salah_2_3();
        addObject(salah_2_3, 626, 412);

        // Inisialisasi HealthBar
        healthBar = getHealthBar();
        
        //timer
        Timer timer = new Timer();  
        addObject(timer, 1180, 50);  
    }

    private void checkClicks() {
        if (Greenfoot.mouseClicked(benar_2_1)) {
            benar_2_1.setImage(new GreenfootImage("Benar_2_2.png"));
            transitionStarted = true;
            transitionTimer = 0;
        } else if (Greenfoot.mouseClicked(getObjects(Salah_2_1.class).get(0)) ||
                   Greenfoot.mouseClicked(getObjects(Salah_2_2.class).get(0)) ||
                   Greenfoot.mouseClicked(getObjects(Salah_2_3.class).get(0))) {
            showWrongImage();

            // Kurangi health
            if (healthBar != null) {
                healthBar.decreaseLife();
            }
        }
    }

    private void showWrongImage() {
        Wrong wrong = new Wrong();
        addObject(wrong, getWidth() / 2, getHeight() / 2);
        showingWrongImage = true;
        wrongImageTimer = 0;
    }
}
