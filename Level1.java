import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Level1 extends BaseWorld {
    private int zombieCount = 0;            // Jumlah zombie di dunia
    private long lastZombieSpawnTime = 0;   // Waktu spawn zombie terakhir
    private int zombieSpawnDelay = 2000;    // Delay untuk spawn zombie (ms)

    private int viewWidth;  // Lebar viewport
    private int viewHeight; // Tinggi viewport
    private int worldWidth; // Lebar dunia

    private Timer timer;
    private GreenfootSound level1Song;
    public Level1() {
        super(1280, 720, 6000); // Viewport 1280x720

        this.viewWidth = 1280;
        this.viewHeight = 720;
        this.worldWidth = 6000;

        setBackground(new GreenfootImage("BG.png")); 
        prepareLevel();
        
        level1Song = new GreenfootSound("Level1.mp3");
        level1Song.playLoop();
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
    level1Song.stop(); // Menghentikan lagu ketika world tidak aktif
    }
    @Override
    public void started() {
    level1Song.playLoop(); // Memutar ulang lagu saat world aktif
    }
    public void stopMusic() {
    level1Song.stop();
    }

    private void prepareLevel() {
        // player
        Gatot_StandBy player = new Gatot_StandBy(90, 130);
        setPlayer(player, 100, 459); // Posisi awal pemain

        // tiles
        createGroundTiles(128, 651);

        // sign
        addObject(new Sign(), 6000, 459); // Tanda akhir level

        // zombie
        spawnInitialZombies();

        // snake
        spawnInitialSnakes();

        // snake random
        spawnMultipleSnakes(5);

        //timer
        timer = new Timer();  // Initialize the timer
        addObject(timer, 1180, 50);  // Add the timer to the world

        // platform
        PlatformTiles platformTiles = new PlatformTiles();
        addObject(platformTiles,1200, 416);

        PlatformTiles platformTiles1 = new PlatformTiles();
        addObject(platformTiles1,1270, 416);

        // coin
        Coin coin1 = new Coin();
        coin1.getImage().scale(100, 100);
        addObject(coin1, 1450, 210);

        PlatformTiles platformTiles2 = new PlatformTiles();
        addObject(platformTiles2,1450, 270);

        PlatformTiles platformTiles3 = new PlatformTiles();
        addObject(platformTiles3,2500, 416);

        PlatformTiles platformTiles4 = new PlatformTiles();
        addObject(platformTiles4,2570, 416);

        Heart heart = new Heart();
        heart.getImage().scale(100, 100); 
        addObject(heart, 2850, 180);

        PlatformTiles platformTiles5 = new PlatformTiles();
        addObject(platformTiles5,2850, 265);

        PlatformTiles platformTiles6 = new PlatformTiles();
        addObject(platformTiles6,3200, 416);

        PlatformTiles platformTiles7 = new PlatformTiles();
        addObject(platformTiles7,4000, 369);

        PlatformTiles platformTiles8 = new PlatformTiles();
        addObject(platformTiles8,4500, 341);

        //crate1
        Crate1 crate1 = new Crate1();
        addObject(crate1,2603,357);

        Crate1 crate12 = new Crate1();
        addObject(crate12,4200,256);

        Heart heart1 = new Heart();
        heart1.getImage().scale(100, 100); 
        addObject(heart1, 4350, 50);

        Crate1 crate13 = new Crate1();
        addObject(crate13,4350,142);

    }

    private void spawnInitialZombies() {
        zombie zombie1 = new zombie();
        addObject(zombie1, 894, 526);

        zombie zombie2 = new zombie();
        addObject(zombie2, 1100, 526);

        zombie zombie3 = new zombie();
        addObject(zombie3, 1500, 526);

        zombie zombie4 = new zombie();
        addObject(zombie4, 2600, 526);

        zombie zombie5 = new zombie();
        addObject(zombie5, 2000, 526);

        zombie zombie6 = new zombie();
        addObject(zombie6, 1900, 526);
    }

    private void spawnInitialSnakes() {
        snake snake1 = new snake();
        addObject(snake1,899,551);

        snake snake2 = new snake();
        addObject(snake2,1000,551);

        snake snake3 = new snake();
        addObject(snake3,1200,551);
    }

    private void createGroundTiles(int tileWidth, int groundY) {
        int numTiles = (int) Math.ceil((double) 6000 / tileWidth); // 6000 lebar dunia
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

        if (zombieCount < 8 && System.currentTimeMillis() - lastZombieSpawnTime >= zombieSpawnDelay) {
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
            timer.setLocation(getCameraOffsetX() + 1180, 50);  // Keep the timer at the same position
        }
    }

    private int getCameraOffsetX() {
        if (getPlayer() == null) return 0;

        int playerX = getPlayer().getX();
        return Math.max(0, Math.min(playerX - viewWidth / 2, worldWidth - viewWidth));
    }
}
