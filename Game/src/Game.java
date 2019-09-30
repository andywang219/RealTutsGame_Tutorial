import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable { // taking everything from canvas class and bringing it to Game (ruunable requires a certain method)
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "2D Game";

    private boolean running = false;
    private Thread thread;

    // buffer image: buffer/load the image before it projects and renders it - buffers whole window and access RGB
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;

    private boolean is_shooting = false;

    private int enemy_count = 3; // how many space ships to spawn
    private int enemy_killed = 0; // # of space ships killed

    private Player p;
    private Controller c;
    private Textures tex;

    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;

    public void init() {
        requestFocus(); // bring focus to the screen
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("./res/sprite_sheet.png");
            background = loader.loadImage("./res/background.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyInput(this));
        tex = new Textures(this);
        p = new Player(200, 200, tex);
        c = new Controller(tex, this);

        c.createEnemy(enemy_count);
        ea = c.getEntityA();
        eb = c.getEntityB();
    }

    private synchronized void start() { // to start our thread
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() { // to end our thread
        if (!running) return;
        running = false;
        try {
            thread.join(); // joins all threads together and waits for them to die
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0; // 60 fps
        double ns = 1000000000 / amountOfTicks;
        double delta = 0; // time passed so that it can catch up
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        // updates the game a certain amount of times
        while (running) {
            // this would be the game loop
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000; // prevent looping again
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    // eveything in game that updates
    private void tick() {
        p.tick();
        c.tick();
        if (enemy_killed >= enemy_count) {
            enemy_count += 2; 
            enemy_killed = 0;
            c.createEnemy(enemy_count);
        }
    }

    // eveything in game that renders
    private void render() {
        // buffer strategy handles all buffering behind the scenes
        BufferStrategy bs = this.getBufferStrategy(); // accessed from Canvas; eventually need to dispose of this

        // initialize once and done
        if (bs == null) {
            createBufferStrategy(3); // 3 means 3 buffers (buffer behind buffer behind screen and shoots to screen: improves time/performance)
            return;
        }

        Graphics g = bs.getDrawGraphics();
        ////////////////////////// In between these, we can draw out stuff
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(background, 0, 0, null);
        p.render(g);
        c.render(g);
        //////////////////////////
        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D) {
            p.setVelX(5);
        } else if (key == KeyEvent.VK_A) {
            p.setVelX(-5);
        } else if (key == KeyEvent.VK_S) {
            p.setVelY(5);
        } else if (key == KeyEvent.VK_W) {
            p.setVelY(-5);
        } else if (key == KeyEvent.VK_SPACE && !is_shooting) {
            is_shooting = true;
            c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_A) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_S) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_W) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_SPACE) {
            is_shooting = false;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); // Dimension: class to encapsulate a width and heigh dimension
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public void setEnemy_count(int enemy_count) {
        this.enemy_count = enemy_count;
    }

    public void setEnemy_killed(int enemy_killed) {
        this.enemy_killed = enemy_killed;
    }

    public int getEnemy_count() {
        return enemy_count;
    }

    public int getEnemy_killed() {
        return enemy_killed;
    }
}