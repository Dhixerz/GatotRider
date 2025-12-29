import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainMenu extends World
{

    /**
     * Constructor for objects of class MainMenu.
     * 
     */
    private GreenfootSound mainMenuSong;
    public MainMenu()
    {    
        super(1280, 720, 1); 
        setBackground(new GreenfootImage("Menu.png"));
        prepare();
        mainMenuSong = new GreenfootSound("mainmenusong.mp3");
        mainMenuSong.playLoop();
    }
    
    private void prepare() {
        btnPlay btnPlay = new btnPlay();
        btnTutor btnTutor = new btnTutor();
        btnHistory btnHistory = new btnHistory();
        btnAbout btnAbout = new btnAbout();

        addObject(btnPlay, 639, 336);
        addObject(btnTutor, 640, 483);
        addObject(btnHistory, 510, 483);
        addObject(btnAbout, 771, 483);
    }
    
    public void act() {
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
