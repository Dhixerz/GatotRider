import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Level2 extends BaseWorld {
    private int zombieCount = 0;            // Jumlah zombie di dunia
    private long lastZombieSpawnTime = 0;   // Waktu spawn zombie terakhir
    private int zombieSpawnDelay = 2000;    // Delay untuk spawn zombie (ms)

    private int viewWidth;  // Lebar viewport
    private int viewHeight; // Tinggi viewport
    private int worldWidth; // Lebar dunia

    private Timer timer;
    private GreenfootSound level2Song;
    public Level2() {
        super(1280, 720, 10000); // Viewport 1280x720

        this.viewWidth = 1280;
        this.viewHeight = 720;
        this.worldWidth = 10000;

        setBackground(new GreenfootImage("BG2.png")); 
        prepareLevel();
        
        level2Song = new GreenfootSound("Level2.mp3");
        level2Song.playLoop();
    }

    public void act() {
        // Logika kamera untuk mengikuti pemain
        followPlayerWithCamera(); // Fokus kamera pada pemain
        spawnZombiesWithDelay();  // Spawn zombie
        keepCounterSticky();      // Jaga posisi skor tetap
        keepHealthBarSticky();    // Jaga posisi health bar tetap
        keepTimerSticky();
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

    private void prepareLevel() {
        // player
        Gatot_StandBy player = new Gatot_StandBy(90, 130);
        setPlayer(player, 100, 459); // Posisi awal pemain

        // tiles
        createGroundTiles(128, 651);

        // sign
        addObject(new Sign2(), 10000, 459); // Tanda akhir level

        // zombie
        spawnInitialZombies();

        // snake
        spawnInitialSnakes();

        // snake random
        spawnMultipleSnakes(10);
        
        //timer
        timer = new Timer();  // Initialize the timer
        addObject(timer, 1180, 50);  // Add the timer to the world

        // Set 1
        PlatformTiles platform1 = new PlatformTiles();
        addObject(platform1, 400, 400); 
        
        Crate1 crate1 = new Crate1();
        addObject(crate1, 600, 407); 
        
        Crate1 crate2 = new Crate1();
        addObject(crate2, 600, 330); 
        
        Coin coin1 = new Coin();
        coin1.getImage().scale(100, 100);
        addObject(coin1, 600, 170); 
        
        Heart heart1 = new Heart();
        heart1.getImage().scale(100, 100);
        addObject(heart1, 800, 300); 
        
        // Set 2
        PlatformTiles platform2 = new PlatformTiles();
        addObject(platform2, 1200, 380); 
        
        Crate1 crate3 = new Crate1();
        addObject(crate3, 1400, 407); 
        
        Crate1 crate4 = new Crate1();
        addObject(crate4, 1400, 330); 
        
        Coin coin2 = new Coin();
        coin2.getImage().scale(100, 100);
        addObject(coin2, 1400, 170);
        
        // Set 3
        PlatformTiles platform3 = new PlatformTiles();
        addObject(platform3, 2000, 400); 
        
        PlatformTiles platform4 = new PlatformTiles();
        addObject(platform4, 2200, 340); 
        
        Crate1 crate5 = new Crate1();
        addObject(crate5, 2300, 407); 
        
        Coin coin3 = new Coin();
        coin3.getImage().scale(100, 100);
        addObject(coin3, 2250, 170); 
        
        Heart heart2 = new Heart();
        heart2.getImage().scale(100, 100);
        addObject(heart2, 2500, 200); 
        
        // Set 4
        PlatformTiles platform5 = new PlatformTiles();
        addObject(platform5, 3000, 380); 
        
        Crate1 crate6 = new Crate1();
        addObject(crate6, 3200, 407); 
        
        Crate1 crate7 = new Crate1();
        addObject(crate7, 3350, 330); 
        
        Coin coin4 = new Coin();
        coin4.getImage().scale(100, 100);
        addObject(coin4, 3350, 130); 
        
        // Set 5
        PlatformTiles platform6 = new PlatformTiles();
        addObject(platform6, 4500, 360); 
        
        PlatformTiles platform7 = new PlatformTiles();
        addObject(platform7, 4700, 290); 
        
        Heart heart3 = new Heart();
        heart3.getImage().scale(100, 100);
        addObject(heart3, 4600, 200); 
        
        Crate1 crate8 = new Crate1();
        addObject(crate8, 4900, 407); 
        
        Crate1 crate9 = new Crate1();
        addObject(crate9, 5000, 330); 
        
        Coin coin5 = new Coin();
        coin5.getImage().scale(100, 100);
        addObject(coin5, 5000, 130); 
        
        // Set 6
        PlatformTiles platform8 = new PlatformTiles();
        addObject(platform8, 5500, 360);
        
        Heart heart4 = new Heart();
        heart4.getImage().scale(100, 100);
        addObject(heart4, 5800, 200); 
        
        Coin coin6 = new Coin();
        coin6.getImage().scale(100, 100);
        addObject(coin6, 5700, 120); 
        
        // Set 7
        PlatformTiles platform9 = new PlatformTiles();
        addObject(platform9, 6200, 350); 
        
        Crate1 crate10 = new Crate1();
        addObject(crate10, 6400, 407); 
        
        Coin coin7 = new Coin();
        coin7.getImage().scale(100, 100);
        addObject(coin7, 6300, 170); 
        
        Heart heart5 = new Heart();
        heart5.getImage().scale(100, 100);
        addObject(heart5, 6500, 120); 
        
        // Set 8
        Crate1 crate11 = new Crate1();
        addObject(crate11, 6800, 407);
        
        Crate1 crate12 = new Crate1();
        addObject(crate12, 6800, 330);
        
        PlatformTiles platform10 = new PlatformTiles();
        addObject(platform10, 7000, 400);
        
        Coin coin8 = new Coin();
        coin8.getImage().scale(100, 100);
        addObject(coin8, 6900, 150); 
        
        Heart heart6 = new Heart();
        heart6.getImage().scale(100, 100);
        addObject(heart6, 7200, 100); 
        
        // Set 9
        PlatformTiles platform11 = new PlatformTiles();
        addObject(platform11, 7600, 360); 
        
        PlatformTiles platform12 = new PlatformTiles();
        addObject(platform12, 7800, 300); 
        
        Coin coin9 = new Coin();
        coin9.getImage().scale(100, 100);
        addObject(coin9, 7700, 170); 
        
        Crate1 crate13 = new Crate1();
        addObject(crate13, 8000, 407); 
        
        Heart heart7 = new Heart();
        heart7.getImage().scale(100, 100);
        addObject(heart7, 8100, 150); 
        
        // Set 10
        PlatformTiles platform13 = new PlatformTiles();
        addObject(platform13, 8600, 400); 
        
        Crate1 crate14 = new Crate1();
        addObject(crate14, 8800, 407); 
        
        Crate1 crate15 = new Crate1();
        addObject(crate15, 8800, 330); 
        
        Coin coin10 = new Coin();
        coin10.getImage().scale(100, 100);
        addObject(coin10, 8700, 170); 
        
        Heart heart8 = new Heart();
        heart8.getImage().scale(100, 100);
        addObject(heart8, 9000, 100); 
    }
    private void spawnInitialZombies() {
        zombie zombie1 = new zombie();
        addObject(zombie1, 900, 526);

        zombie zombie2 = new zombie();
        addObject(zombie2, 1200, 526);

        zombie zombie3 = new zombie();
        addObject(zombie3, 1500, 526);

        zombie zombie4 = new zombie();
        addObject(zombie4, 2600, 526);

        zombie zombie5 = new zombie();
        addObject(zombie5, 2000, 526);

        zombie zombie6 = new zombie();
        addObject(zombie6, 2300, 526);
        
        zombie zombie7 = new zombie();
        addObject(zombie7, 3500, 526);
        
        zombie zombie8 = new zombie();
        addObject(zombie8, 4000, 526);
        
        zombie zombie9 = new zombie();
        addObject(zombie9, 4800, 526);
        
        zombie zombie10 = new zombie();
        addObject(zombie10, 5400, 526);
        
        zombie zombie11 = new zombie();
        addObject(zombie11, 5900, 526);
        
        zombie zombie12 = new zombie();
        addObject(zombie12, 6800, 526);
        
        zombie zombie13 = new zombie();
        addObject(zombie13, 7400, 526);
        
        zombie zombie14 = new zombie();
        addObject(zombie14, 8000, 526);
        
        zombie zombie15 = new zombie();
        addObject(zombie15, 9000, 526);

    }

    private void spawnInitialSnakes() {
        snake snake1 = new snake();
        addObject(snake1,899,551);

        snake snake2 = new snake();
        addObject(snake2,1000,551);

        snake snake3 = new snake();
        addObject(snake3,1200,551);
        
        snake snake4 = new snake();
        addObject(snake4, 6200, 551);
        
        snake snake5 = new snake();
        addObject(snake5, 6600, 551);
        
        snake snake6 = new snake();
        addObject(snake6, 7900, 551);
        
        snake snake7 = new snake();
        addObject(snake7, 8600, 551);
        
        snake snake8 = new snake();
        addObject(snake8, 9100, 551);
        
        snake snake9 = new snake();
        addObject(snake9, 9500, 551);
        
        snake snake10 = new snake();
        addObject(snake10, 9800, 551);
    }

    private void createGroundTiles(int tileWidth, int groundY) {
        int numTiles = (int) Math.ceil((double) 10000 / tileWidth); // 10000 lebar dunia
        for (int i = 0; i < numTiles; i++) {
            Tiles tile = new Tiles();
            addObject(tile, i * tileWidth, groundY);
        }
    }

    private void spawnMultipleSnakes(int count) {
        List<Tiles> tiles = getObjects(Tiles.class); 

        for (int i = 0; i < count; i++) {
            if (!tiles.isEmpty()) {
                int snakeX;
                int snakeY;
                Tiles randomTile;

                do {
                    randomTile = tiles.get(Greenfoot.getRandomNumber(tiles.size()));
                    snakeX = randomTile.getX();
                } while (snakeX <= 130);

                snakeY = randomTile.getY() - 100;

                snake snake = new snake();
                addObject(snake, snakeX, snakeY);
            }
        }
    }

    private void addZombie(int x, int y) {
        zombie newZombie = new zombie();
        addObject(newZombie, x, y);
        zombieCount++;
    }

    private void spawnZombiesWithDelay() {
        int zombieX = 3980; // Posisi spawn zombie
        int groundY = 526;  // Posisi zombie di atas ground

        if (zombieCount < 12 && System.currentTimeMillis() - lastZombieSpawnTime >= zombieSpawnDelay) {
            addZombie(zombieX, groundY);
            lastZombieSpawnTime = System.currentTimeMillis();
        }
    }

    private void followPlayerWithCamera() {
        if (getPlayer() == null) return;

        // Posisi pemain
        int playerX = getPlayer().getX();
        int playerY = getPlayer().getY();

        // posisi kamera, pemain mid
        int viewX = Math.max(viewWidth / 2, Math.min(playerX, worldWidth - viewWidth / 2));
        int offsetX = viewX - viewWidth / 2;

        // geser seluruh dunia berdasarkan offsetX
        for (Object obj : getObjects(null)) {
            Actor actor = (Actor) obj;
            int newX = actor.getX() - offsetX;
            actor.setLocation(newX, actor.getY());
        }

        // supaya ga keluar viewport
        int limitedPlayerX = Math.max(getPlayer().getImage().getWidth() / 2,
                Math.min(playerX, worldWidth - getPlayer().getImage().getWidth() / 2));
        if (playerX != limitedPlayerX) {
            getPlayer().setLocation(limitedPlayerX, playerY);
        }
    }

    protected void keepCounterSticky() {
        if (getCounter() != null) {
            getCounter().setLocation(getCameraOffsetX() + 1000, 50);
        }
    }

    protected void keepHealthBarSticky() {
        if (getHealthBar() != null) {
            getHealthBar().setLocation(getCameraOffsetX() + 180, 50);
        }
    }
    
    // Sticky for the timer
    private void keepTimerSticky() {
        if (timer != null) {
            timer.setLocation(getCameraOffsetX() + 1180, 50);  
        }
    }

    private int getCameraOffsetX() {
        if (getPlayer() == null) return 0;

        int playerX = getPlayer().getX();
        return Math.max(0, Math.min(playerX - viewWidth / 2, worldWidth - viewWidth));
    }
}
