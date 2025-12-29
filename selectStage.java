import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class selectStage extends World
{
    private GreenfootSound mainMenuSong;
    /**
     * Constructor for objects of class selectStage.
     * 
     */
    public selectStage()
    {    
        super(1280, 720, 1); 
        prepare();
        mainMenuSong = new GreenfootSound("mainmenusong.mp3");
        mainMenuSong.playLoop();
    }

    private void prepare()
    {
        StageSelect StageSelect = new StageSelect();
        NewGame NewGame = new NewGame();
        btnBack3 btnBack3 = new btnBack3();

        addObject(StageSelect, 893, 365);
        addObject(NewGame, 387, 365);
        addObject(btnBack3, 635, 652);
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
