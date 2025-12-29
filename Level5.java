import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Level5 extends BaseWorld {
    private int zombieCount = 0;            // Jumlah zombie di dunia
    private long lastZombieSpawnTime = 0;   // Waktu spawn zombie terakhir
    private int zombieSpawnDelay = 2000;    // Delay untuk spawn zombie (ms)

    private int viewWidth;  // Lebar viewport
    private int viewHeight; // Tinggi viewport
    private int worldWidth; // Lebar dunia

    private Timer timer;
    private GreenfootSound level5Song;
    public Level5() {
        super(1280, 720, 25000); // Viewport 1280x720

        this.viewWidth = 1280;
        this.viewHeight = 720;
        this.worldWidth = 25000;

        setBackground(new GreenfootImage("BG5.png")); 
        prepareLevel();
        
        level5Song = new GreenfootSound("th06_06.mp3");
        level5Song.playLoop();
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
    level5Song.stop(); // Menghentikan lagu ketika world tidak aktif
    }
    @Override
    public void started() {
    level5Song.playLoop(); // Memutar ulang lagu saat world aktif
    }
    public void stopMusic() {
    level5Song.stop();
    }
    
    private void prepareLevel() {
        // player
        Gatot_StandBy player = new Gatot_StandBy(90, 130);
        setPlayer(player, 100, 459); // Posisi awal pemain

        // tiles
        createGroundTiles(128, 651);

        // sign
        addObject(new Sign5(), 25000, 459); // Tanda akhir level

        // zombie
        spawnInitialZombies();

        // snake
        spawnInitialSnakes();

        // snake random
        spawnMultipleSnakes(25);
        
        //timer
        timer = new Timer();  // Initialize the timer
        addObject(timer, 1180, 50);  // Add the timer to the world

        // Set 1
        PlatformTiles platform1 = new PlatformTiles();
        addObject(platform1, 800, 400);
        Heart heart1 = new Heart();
        heart1.getImage().scale(100, 100);
        addObject(heart1, 850, 150);
        Coin coin1 = new Coin();
        coin1.getImage().scale(100, 100);
        addObject(coin1, 900, 170);

        // Set 2
        Crate1 crate2_1 = new Crate1();
        addObject(crate2_1, 1400, 407);
        Crate1 crate2_2 = new Crate1();
        addObject(crate2_2, 1500, 330);
        Coin coin2 = new Coin();
        coin2.getImage().scale(100, 100);
        addObject(coin2, 1500, 170);

        // Set 3
        PlatformTiles platform3 = new PlatformTiles();
        addObject(platform3, 2100, 421);
        Crate1 crate3 = new Crate1();
        addObject(crate3, 2200, 330);
        Heart heart3 = new Heart();
        heart3.getImage().scale(100, 100);
        addObject(heart3, 2200, 100);

        // Set 4
        Crate1 crate4 = new Crate1();
        addObject(crate4, 2800, 350);
        Coin coin4 = new Coin();
        coin4.getImage().scale(100, 100);
        addObject(coin4, 2850, 150);

        // Set 5
        PlatformTiles platform5_1 = new PlatformTiles();
        addObject(platform5_1, 3500, 421);
        PlatformTiles platform5_2 = new PlatformTiles();
        addObject(platform5_2, 3700, 350);
        Coin coin5 = new Coin();
        coin5.getImage().scale(100, 100);
        addObject(coin5, 3650, 250);
        Heart heart5 = new Heart();
        heart5.getImage().scale(100, 100);
        addObject(heart5, 3750, 200);

        // Set 6
        Crate1 crate6_1 = new Crate1();
        addObject(crate6_1, 4300, 407);
        Crate1 crate6_2 = new Crate1();
        addObject(crate6_2, 4400, 330);
        Crate1 crate6_3 = new Crate1();
        addObject(crate6_3, 4500, 250);
        Coin coin6 = new Coin();
        coin6.getImage().scale(100, 100);
        addObject(coin6, 4550, 150);

        // Set 7
        PlatformTiles platform7 = new PlatformTiles();
        addObject(platform7, 5200, 400);
        Crate1 crate100 = new Crate1();
        addObject(crate100, 5000, 320);
        for (int i = 0; i < 5; i++) {
            Coin coin7 = new Coin();
            coin7.getImage().scale(100, 100);
            addObject(coin7, 4800 + (i * 100), 90);
        }

        // Set 8
        PlatformTiles platform8 = new PlatformTiles();
        addObject(platform8, 6000, 421);
        Crate1 crate8 = new Crate1();
        addObject(crate8, 6100, 330);
        Coin coin8 = new Coin();
        coin8.getImage().scale(100, 100);
        addObject(coin8, 6150, 250);

        // Set 9
        PlatformTiles platform9_1 = new PlatformTiles();
        addObject(platform9_1, 6800, 421);
        PlatformTiles platform9_2 = new PlatformTiles();
        addObject(platform9_2, 7000, 350);
        Heart heart9 = new Heart();
        heart9.getImage().scale(100, 100);
        addObject(heart9, 7050, 270);
        Coin coin9 = new Coin();
        coin9.getImage().scale(100, 100);
        addObject(coin9, 7100, 200);

        // Set 10
        for (int i = 0; i < 5; i++) {
            Crate1 crate10 = new Crate1();
            addObject(crate10, 7700 + (i * 80), 407);
        }

        // Set 11
        PlatformTiles platform11 = new PlatformTiles();
        addObject(platform11, 8400, 421);
        Coin coin11 = new Coin();
        coin11.getImage().scale(100, 100);
        addObject(coin11, 8450, 370);

        // Set 12
        Crate1 crate12_1 = new Crate1();
        addObject(crate12_1, 9200, 407);
        Crate1 crate12_2 = new Crate1();
        addObject(crate12_2, 9300, 330);
        Crate1 crate12_3 = new Crate1();
        addObject(crate12_3, 9400, 250);
        Heart heart12 = new Heart();
        heart12.getImage().scale(100, 100);
        addObject(heart12, 9450, 150);
        
        // Set 13
        Crate1 crate13_1 = new Crate1();
        addObject(crate13_1, 10000, 407);
        Crate1 crate13_2 = new Crate1();
        addObject(crate13_2, 10100, 330);
        Coin coin13 = new Coin();
        coin13.getImage().scale(100, 100);
        addObject(coin13, 10100, 230);

        // Set 14
        PlatformTiles platform14 = new PlatformTiles();
        addObject(platform14, 10800, 421);
        Crate1 crate14 = new Crate1();
        addObject(crate14, 10900, 330);
        Heart heart14 = new Heart();
        heart14.getImage().scale(100, 100);
        addObject(heart14, 10950, 250);

        // Set 15
        PlatformTiles platform15 = new PlatformTiles();
        addObject(platform15, 11600, 421);
        for (int i = 0; i < 4; i++) {
            Coin coin15 = new Coin();
            coin15.getImage().scale(100, 100);
            addObject(coin15, 11600 + (i * 120), 370);
        }

        // Set 16
        Crate1 crate16_1 = new Crate1();
        addObject(crate16_1, 12500, 407);
        Crate1 crate16_2 = new Crate1();
        addObject(crate16_2, 12600, 330);
        Crate1 crate16_3 = new Crate1();
        addObject(crate16_3, 12700, 250);
        Heart heart16 = new Heart();
        heart16.getImage().scale(100, 100);
        addObject(heart16, 12750, 150);

        // Set 17
        PlatformTiles platform17_1 = new PlatformTiles();
        addObject(platform17_1, 13500, 421);
        Crate1 crate17_1 = new Crate1();
        addObject(crate17_1, 13600, 330);
        PlatformTiles platform17_2 = new PlatformTiles();
        addObject(platform17_2, 13700, 250);
        Coin coin17 = new Coin();
        coin17.getImage().scale(100, 100);
        addObject(coin17, 13750, 200);

        // Set 18
        PlatformTiles platform18 = new PlatformTiles();
        addObject(platform18, 14500, 400);
        Coin coin18 = new Coin();
        coin18.getImage().scale(100, 100);
        addObject(coin18, 14700, 250);

        // Set 19
        PlatformTiles platform19_1 = new PlatformTiles();
        addObject(platform19_1, 15500, 421);
        PlatformTiles platform19_2 = new PlatformTiles();
        addObject(platform19_2, 15700, 350);
        PlatformTiles platform19_3 = new PlatformTiles();
        addObject(platform19_3, 15900, 280);
        Heart heart19 = new Heart();
        heart19.getImage().scale(100, 100);
        addObject(heart19, 15750, 250);
        for (int i = 0; i < 2; i++) {
            Coin coin19 = new Coin();
            coin19.getImage().scale(100, 100);
            addObject(coin19, 15550 + (i * 400), 370 - (i * 70));
        }

        // Set 20
        Crate1 crate20_1 = new Crate1();
        addObject(crate20_1, 16500, 407);
        Crate1 crate20_2 = new Crate1();
        addObject(crate20_2, 16600, 330);
        Crate1 crate20_3 = new Crate1();
        addObject(crate20_3, 16700, 250);
        Heart heart20 = new Heart();
        heart20.getImage().scale(100, 100);
        addObject(heart20, 16750, 150);
        
        // Set 21
        PlatformTiles platform21_1 = new PlatformTiles();
        addObject(platform21_1, 17000, 421);
        PlatformTiles platform21_2 = new PlatformTiles();
        addObject(platform21_2, 17200, 350);
        PlatformTiles platform21_3 = new PlatformTiles();
        addObject(platform21_3, 17400, 280);
        for (int i = 0; i < 3; i++) {
            Coin coin21 = new Coin();
            coin21.getImage().scale(100, 100);
            addObject(coin21, 17050 + (i * 200), 250 - (i * 50));
        }
        Heart heart21 = new Heart();
        heart21.getImage().scale(100, 100);
        addObject(heart21, 17450, 200);

        // Set 22
        Crate1 crate22_1 = new Crate1();
        addObject(crate22_1, 18000, 407);
        Crate1 crate22_2 = new Crate1();
        addObject(crate22_2, 18100, 330);
        Crate1 crate22_3 = new Crate1();
        addObject(crate22_3, 18200, 250);
        Crate1 crate22_4 = new Crate1();
        addObject(crate22_4, 18300, 330);
        Crate1 crate22_5 = new Crate1();
        addObject(crate22_5, 18400, 407);
        Heart heart22 = new Heart();
        heart22.getImage().scale(100, 100);
        addObject(heart22, 18200, 150);

        // Set 23
        PlatformTiles platform23_1 = new PlatformTiles();
        addObject(platform23_1, 19000, 421);
        PlatformTiles platform23_2 = new PlatformTiles();
        addObject(platform23_2, 19200, 320);
        PlatformTiles platform23_3 = new PlatformTiles();
        addObject(platform23_3, 19400, 250);
        for (int i = 0; i < 3; i++) {
            Coin coin23 = new Coin();
            coin23.getImage().scale(100, 100);
            addObject(coin23, 19050 + (i * 200), 200 - (i * 50));
        }
        Heart heart23 = new Heart();
        heart23.getImage().scale(100, 100);
        addObject(heart23, 19450, 180);

        // Set 24
        Crate1 crate24_1 = new Crate1();
        addObject(crate24_1, 20000, 407);
        Crate1 crate24_2 = new Crate1();
        addObject(crate24_2, 20100, 330);
        Crate1 crate24_3 = new Crate1();
        addObject(crate24_3, 20200, 250);
        Crate1 crate24_4 = new Crate1();
        addObject(crate24_4, 20300, 170);
        Heart heart24 = new Heart();
        heart24.getImage().scale(100, 100);
        addObject(heart24, 20350, 100);

        PlatformTiles platform25 = new PlatformTiles();
        addObject(platform25, 22500, 421);
        Crate1 crate25 = new Crate1();
        addObject(crate25, 22600, 330);
        Coin coin25 = new Coin();
        coin25.getImage().scale(100, 100);
        addObject(coin25, 22700, 250);
        Heart heart25 = new Heart();
        heart25.getImage().scale(100, 100);
        addObject(heart25, 22800, 150);
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
        
        zombie zombie26 = new zombie();
        addObject(zombie26, 20000, 526);
        
        zombie zombie27 = new zombie();
        addObject(zombie27, 21000, 526);
        
        zombie zombie28 = new zombie();
        addObject(zombie28, 22000, 526);
        
        zombie zombie29 = new zombie();
        addObject(zombie29, 23000, 526);
        
        zombie zombie30 = new zombie();
        addObject(zombie30, 24000, 526);

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
        
        snake snake19 = new snake();
        addObject(snake19, 20000, 551);
        
        snake snake20 = new snake();
        addObject(snake20, 21200, 551);
        
        snake snake21 = new snake();
        addObject(snake21, 22400, 551);
        
        snake snake22 = new snake();
        addObject(snake22, 23500, 551);
        
        snake snake23 = new snake();
        addObject(snake23, 24200, 551);
        
        snake snake24 = new snake();
        addObject(snake24, 24700, 551);
        
        snake snake25 = new snake();
        addObject(snake25, 24900, 551);
    }

    private void createGroundTiles(int tileWidth, int groundY) {
        int numTiles = (int) Math.ceil((double) 25000 / tileWidth); // 4000 lebar dunia
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

        if (zombieCount < 24 && System.currentTimeMillis() - lastZombieSpawnTime >= zombieSpawnDelay) {
            addZombie(zombieX, groundY);
            lastZombieSpawnTime = System.currentTimeMillis();
        }
    }

    /**
     * Logika kamera untuk mengikuti pemain di Level1.
     * Kamera bergerak mengikuti posisi pemain.
     */
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
