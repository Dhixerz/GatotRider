import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Level4 extends BaseWorld {
    private int zombieCount = 0;            // Jumlah zombie di dunia
    private long lastZombieSpawnTime = 0;   // Waktu spawn zombie terakhir
    private int zombieSpawnDelay = 2000;    // Delay untuk spawn zombie (ms)

    private int viewWidth;  // Lebar viewport
    private int viewHeight; // Tinggi viewport
    private int worldWidth; // Lebar dunia

    private Timer timer;
    private GreenfootSound level4Song;
    public Level4() {
        super(1280, 720, 20000); // Viewport 1280x720

        this.viewWidth = 1280;
        this.viewHeight = 720;
        this.worldWidth = 20000;

        setBackground(new GreenfootImage("BG4.png")); 
        prepareLevel();
        
        level4Song = new GreenfootSound("Level4.mp3");
        level4Song.playLoop();
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
    level4Song.stop(); // Menghentikan lagu ketika world tidak aktif
    }
    @Override
    public void started() {
    level4Song.playLoop(); // Memutar ulang lagu saat world aktif
    }
    public void stopMusic() {
    level4Song.stop();
    }

    private void prepareLevel() {
        // player
        Gatot_StandBy player = new Gatot_StandBy(90, 130);
        setPlayer(player, 100, 459); // Posisi awal pemain

        // tiles
        createGroundTiles(128, 651);

        // sign
        addObject(new Sign4(), 20000, 459); // Tanda akhir level

        // zombie
        spawnInitialZombies();

        // snake
        spawnInitialSnakes();

        // snake random
        spawnMultipleSnakes(20);
        
        //timer
        timer = new Timer();  // Initialize the timer
        addObject(timer, 1180, 50);  // Add the timer to the world

        // Set 1
        PlatformTiles platform1 = new PlatformTiles();
        addObject(platform1, 400, 420);
        
        Crate1 crate1 = new Crate1();
        addObject(crate1, 500, 407);
        
        Coin coin1 = new Coin();
        coin1.getImage().scale(100, 100);
        addObject(coin1, 500, 300); 
        
        Heart heart1 = new Heart();
        heart1.getImage().scale(100, 100);
        addObject(heart1, 600, 280);
        
        // Set 2
        PlatformTiles platform2 = new PlatformTiles();
        addObject(platform2, 1000, 400);
        
        Crate1 crate2 = new Crate1();
        addObject(crate2, 1100, 407);
        
        Crate1 crate3 = new Crate1();
        addObject(crate3, 1100, 330);
        
        Coin coin2 = new Coin();
        coin2.getImage().scale(100, 100);
        addObject(coin2, 1150, 250); 
        
        // Set 3
        PlatformTiles platform3 = new PlatformTiles();
        addObject(platform3, 1800, 360);
        
        PlatformTiles platform4 = new PlatformTiles();
        addObject(platform4, 2100, 300);
        
        Coin coin3 = new Coin();
        coin3.getImage().scale(100, 100);
        addObject(coin3, 1950, 200); 
        
        Heart heart2 = new Heart();
        heart2.getImage().scale(100, 100);
        addObject(heart2, 2200, 220); 
        
        // Set 4
        Crate1 crate4 = new Crate1();
        addObject(crate4, 2800, 407);
        
        Coin coin4 = new Coin();
        coin4.getImage().scale(100, 100);
        addObject(coin4, 2800, 300); 
        
        // Set 5
        PlatformTiles platform5 = new PlatformTiles();
        addObject(platform5, 3600, 421);
        
        Crate1 crate5 = new Crate1();
        addObject(crate5, 3700, 407);
        
        Crate1 crate6 = new Crate1();
        addObject(crate6, 3700, 330);
        
        Coin coin5 = new Coin();
        coin5.getImage().scale(100, 100);
        addObject(coin5, 3750, 250); 
        
        // Set 6
        PlatformTiles platform6 = new PlatformTiles();
        addObject(platform6, 4500, 380);
        
        Coin coin6 = new Coin();
        coin6.getImage().scale(100, 100);
        addObject(coin6, 4550, 200); 
        
        Crate1 crate7 = new Crate1();
        addObject(crate7, 4700, 407);
        
        // Set 7
        PlatformTiles platform7 = new PlatformTiles();
        addObject(platform7, 5400, 400);
        
        Crate1 crate8 = new Crate1();
        addObject(crate8, 5500, 407);
        
        Coin coin7 = new Coin();
        coin7.getImage().scale(100, 100);
        addObject(coin7, 5500, 300); 
        
        // Set 8
        PlatformTiles platform8 = new PlatformTiles();
        addObject(platform8, 6200, 360);
        
        PlatformTiles platform9 = new PlatformTiles();
        addObject(platform9, 6500, 300);
        
        Coin coin8 = new Coin();
        coin8.getImage().scale(100, 100);
        addObject(coin8, 6400, 220); 
        
        Heart heart3 = new Heart();
        heart3.getImage().scale(100, 100);
        addObject(heart3, 6700, 200); 
        
        // Set 9
        Crate1 crate9_1 = new Crate1();
        addObject(crate9_1, 7300, 407);
        
        Crate1 crate9_2 = new Crate1();
        addObject(crate9_2, 7400, 330);
        
        Crate1 crate9_3 = new Crate1();
        addObject(crate9_3, 7500, 260);
        
        Coin coin9 = new Coin();
        coin9.getImage().scale(100, 100);
        addObject(coin9, 7550, 150); 
        
        // Set 10
        PlatformTiles platform10 = new PlatformTiles();
        addObject(platform10, 8200, 421);
        
        Crate1 crate10 = new Crate1();
        addObject(crate10, 8300, 407);
        
        Crate1 crate11 = new Crate1();
        addObject(crate11, 8300, 330);
        
        Coin coin10 = new Coin();
        coin10.getImage().scale(100, 100);
        addObject(coin10, 8350, 250);
        
        // Set 11
        PlatformTiles platform11 = new PlatformTiles();
        addObject(platform11, 9200, 360);
        
        Coin coin11 = new Coin();
        coin11.getImage().scale(100, 100);
        addObject(coin11, 9300, 220);
        
        Heart heart4 = new Heart();
        heart4.getImage().scale(100, 100);
        addObject(heart4, 9500, 200);
        
        // Set 12
        PlatformTiles platform12_1 = new PlatformTiles();
        addObject(platform12_1, 10200, 400);
        PlatformTiles platform12_2 = new PlatformTiles();
        addObject(platform12_2, 10400, 320);
        Coin coin12_1 = new Coin();
        coin12_1.getImage().scale(100, 100);
        addObject(coin12_1, 10300, 270);
        Coin coin12_2 = new Coin();
        coin12_2.getImage().scale(100, 100);
        addObject(coin12_2, 10500, 200);
        Heart heart12 = new Heart();
        heart12.getImage().scale(100, 100);
        addObject(heart12, 10600, 180);

        // Set 13
        Crate1 crate13_1 = new Crate1();
        addObject(crate13_1, 11000, 407);
        Crate1 crate13_2 = new Crate1();
        addObject(crate13_2, 11100, 330);
        Crate1 crate13_3 = new Crate1();
        addObject(crate13_3, 11200, 250);
        Heart heart13 = new Heart();
        heart13.getImage().scale(100, 100);
        addObject(heart13, 11250, 150);

        // Set 14
        PlatformTiles platform14_1 = new PlatformTiles();
        addObject(platform14_1, 11800, 400);
        PlatformTiles platform14_2 = new PlatformTiles();
        addObject(platform14_2, 12000, 350);
        Coin coin14_1 = new Coin();
        coin14_1.getImage().scale(100, 100);
        addObject(coin14_1, 11900, 250);
        Coin coin14_2 = new Coin();
        coin14_2.getImage().scale(100, 100);
        addObject(coin14_2, 12100, 200);

        // Set 15
        Crate1 crate15_1 = new Crate1();
        addObject(crate15_1, 12600, 407);
        Crate1 crate15_2 = new Crate1();
        addObject(crate15_2, 12700, 330);
        Crate1 crate15_3 = new Crate1();
        addObject(crate15_3, 12800, 250);
        Crate1 crate15_4 = new Crate1();
        addObject(crate15_4, 12900, 170);
        Heart heart15 = new Heart();
        heart15.getImage().scale(100, 100);
        addObject(heart15, 12950, 100);

        // Set 16
        Crate1 crate16_1 = new Crate1();
        addObject(crate16_1, 13500, 407);
        Crate1 crate16_2 = new Crate1();
        addObject(crate16_2, 13600, 330);
        PlatformTiles platform16_1 = new PlatformTiles();
        addObject(platform16_1, 13700, 250);
        Coin coin16 = new Coin();
        coin16.getImage().scale(100, 100);
        addObject(coin16, 13750, 150);

        // Set 17
        PlatformTiles platform17_1 = new PlatformTiles();
        addObject(platform17_1, 14300, 360);
        PlatformTiles platform17_2 = new PlatformTiles();
        addObject(platform17_2, 14500, 300);
        Coin coin17_1 = new Coin();
        coin17_1.getImage().scale(100, 100);
        addObject(coin17_1, 14400, 220);
        Heart heart17 = new Heart();
        heart17.getImage().scale(100, 100);
        addObject(heart17, 14600, 200);

        // Set 18
        Crate1 crate18_1 = new Crate1();
        addObject(crate18_1, 15200, 407);
        Crate1 crate18_2 = new Crate1();
        addObject(crate18_2, 15300, 330);
        Crate1 crate18_3 = new Crate1();
        addObject(crate18_3, 15400, 250);
        Coin coin18 = new Coin();
        coin18.getImage().scale(100, 100);
        addObject(coin18, 15450, 200);
        Heart heart18 = new Heart();
        heart18.getImage().scale(100, 100);
        addObject(heart18, 15500, 150);

        // Set 19
        PlatformTiles platform19 = new PlatformTiles();
        addObject(platform19, 16000, 380);
        for (int i = 0; i < 4; i++) {
            Coin coin19 = new Coin();
            coin19.getImage().scale(100, 100);
            addObject(coin19, 16050 + (i * 150), 280 - (i * 20));
        }
        Heart heart19 = new Heart();
        heart19.getImage().scale(100, 100);
        addObject(heart19, 16500, 200);

        // Set 20
        PlatformTiles platform20_1 = new PlatformTiles();
        addObject(platform20_1, 17500, 421);
        PlatformTiles platform20_2 = new PlatformTiles();
        addObject(platform20_2, 17700, 360);
        Coin coin20_1 = new Coin();
        coin20_1.getImage().scale(100, 100);
        addObject(coin20_1, 17600, 300);
        Coin coin20_2 = new Coin();
        coin20_2.getImage().scale(100, 100);
        addObject(coin20_2, 17800, 250);
        Heart heart20 = new Heart();
        heart20.getImage().scale(100, 100);
        addObject(heart20, 17900, 200);

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
        
        zombie zombie16 = new zombie();
        addObject(zombie16, 9400, 526);
        
        zombie zombie17 = new zombie();
        addObject(zombie17, 10700, 526);
        
        zombie zombie18 = new zombie();
        addObject(zombie18, 12000, 526);
        
        zombie zombie19 = new zombie();
        addObject(zombie19, 13000, 526);
        
        zombie zombie20 = new zombie();
        addObject(zombie20, 14000, 526);
        
        zombie zombie21 = new zombie();
        addObject(zombie21, 15000, 526);
        
        zombie zombie22 = new zombie();
        addObject(zombie22, 16000, 526);
        
        zombie zombie23 = new zombie();
        addObject(zombie23, 17000, 526);
        
        zombie zombie24 = new zombie();
        addObject(zombie24, 18000, 526);
        
        zombie zombie25 = new zombie();
        addObject(zombie25, 19000, 526);

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
        
        snake snake11 = new snake();
        addObject(snake11, 11000, 551);
        
        snake snake12 = new snake();
        addObject(snake12, 11500, 551);
        
        snake snake13 = new snake();
        addObject(snake13, 12700, 551);
        
        snake snake14 = new snake();
        addObject(snake14, 14600, 551);
        
        snake snake15 = new snake();
        addObject(snake15, 15800, 551);
        
        snake snake16 = new snake();
        addObject(snake16, 17000, 551);
        
        snake snake17 = new snake();
        addObject(snake17, 18200, 551);
        
        snake snake18 = new snake();
        addObject(snake18, 19100, 551);
    }

    private void createGroundTiles(int tileWidth, int groundY) {
        int numTiles = (int) Math.ceil((double) 20000 / tileWidth); // 20000 lebar dunia
        for (int i = 0; i < numTiles; i++) {
            Tiles2 tile = new Tiles2();
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

        if (zombieCount < 20 && System.currentTimeMillis() - lastZombieSpawnTime >= zombieSpawnDelay) {
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
