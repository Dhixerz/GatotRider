import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossFight1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossFight2 extends BaseWorld {  
    private GreenfootSound level2Song;
    public BossFight2() {    
        super(1280, 720, 1); 
        prepare();
        
        level2Song = new GreenfootSound("th06_13.mp3");
        level2Song.playLoop();
    }
    
    private void prepare() {
        int worldWidth = getWidth();  
        int tileWidth = 128;  
        int yPosition = 651; 

        for (int x = 0; x < worldWidth; x += tileWidth) {
            Tiles tile = new Tiles();  
            addObject(tile, x + tileWidth / 2, yPosition);  
        }

        Gatot_StandBy gatot_StandBy = new Gatot_StandBy(90, 130);
        addObject(gatot_StandBy, 114, 480);
        gatot_StandBy.setLocation(111, 487);

        constrainEntityToWorldBounds(gatot_StandBy);
        
        //timer
        Timer timer = new Timer(); 
        addObject(timer, 1180, 50); 
        
        //add platform
        PlatformTiles platformTiles = new PlatformTiles();
        addObject(platformTiles, 200, 381);
        
        PlatformTiles platformTiles2 = new PlatformTiles();
        addObject(platformTiles2, 1080, 381);
        
        PlatformTiles platformTiles3 = new PlatformTiles();
        addObject(platformTiles3, 482, 243);
        
        PlatformTiles platformTiles4 = new PlatformTiles();
        addObject(platformTiles4, 784, 243);
        
        //add heart
        Heart heart1 = new Heart();
        heart1.getImage().scale(100, 100);
        addObject(heart1, 480, 150);
        
        Heart heart2 = new Heart();
        heart2.getImage().scale(100, 100);
        addObject(heart2, 785, 150);
        
        //add boss
        BossMonster2 bossMonster2 = new BossMonster2();
        addObject(bossMonster2,1080,490);
    }

    public void act() {
        for (Actor actor : getObjects(Actor.class)) {
            constrainEntityToWorldBounds(actor);
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

    private void constrainEntityToWorldBounds(Actor actor) {
        int worldWidth = getWidth();
        int worldHeight = getHeight();
        int x = actor.getX();
        int y = actor.getY();

        if (x < 0) {
            actor.setLocation(0, y);
        } else if (x >= worldWidth) {
            actor.setLocation(worldWidth - 1, y);
        }

        if (y < 0) {
            actor.setLocation(x, 0);
        } else if (y >= worldHeight) {
            actor.setLocation(x, worldHeight - 1);
        }
    }
}