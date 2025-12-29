import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot, and MouseInfo)

public class selectLevel extends World
{
    private GreenfootSound mainMenuSong;
    public selectLevel()
    {
        super(1280, 720, 1);
        setBackground(new GreenfootImage("selectLevelBg.png"));

        prepare();
        mainMenuSong = new GreenfootSound("mainmenusong.mp3");
        mainMenuSong.playLoop();
    }
  
    private void prepare() {
        
        stage1 stage1 = new stage1();
        addObject(stage1,217, 262);
        
        stage2 stage2 = new stage2();
        addObject(stage2, 643, 265);
        
        stage3 stage3 = new stage3();
        addObject(stage3, 1068, 261);
        
        stage4 stage4 = new stage4();
        addObject(stage4, 361, 494);
        
        stage5 stage5 = new stage5();
        addObject(stage5, 920, 495);
        
        btnBack btnBack = new btnBack();
        addObject(btnBack, getWidth() / 2, 620);

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
