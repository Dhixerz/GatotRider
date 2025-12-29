import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class tutorialMenu extends World
{
    private GreenfootSound mainMenuSong;
    public tutorialMenu()
    {    
        super(1280, 720, 1);
        prepare();
        mainMenuSong = new GreenfootSound("mainmenusong.mp3");
        mainMenuSong.playLoop();
    }

    private void prepare() {
        btnBack4 btnBack4 = new btnBack4();
        addObject(btnBack4, 635, 652); 
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
