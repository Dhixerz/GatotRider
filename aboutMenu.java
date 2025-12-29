import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class aboutMenu extends World
{

    /**
     * Constructor for objects of class aboutMenu.
     * 
     */
    private GreenfootSound mainMenuSong;
    public aboutMenu()
    {
        super(1280, 720, 1);
        prepare();
        mainMenuSong = new GreenfootSound("mainmenusong.mp3");
        mainMenuSong.playLoop();
    }
    
    private void prepare() {
        btnBack5 btnBack5 = new btnBack5();
        addObject(btnBack5, 635, 652); 
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
