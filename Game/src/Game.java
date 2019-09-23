import java.awt.*;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 950;
    public static final int HEIGHT = 600;
    public static final int SCALE = 4;
    public final String TITLE = "2D Game";

    private boolean running = false;
    private Thread thread;

    // BufferedImage will load the image before rendering it
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // buffer the entire window
    private BufferedImage spriteSheet = null;
    private BufferedImage orange = null;
    private BufferedImage enemy = null;

    private boolean is_shooting = false;

    private Player p;
    private Controller c;
    private Textures text;

    public void init() {
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("./throwSS.png");
            orange = loader.loadImage("./orange.png");
            enemy = loader.loadImage("./owlet.png");
        } catch(IOException e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyInput(this));

        text = new Textures(this);
        p = new Player(200, 200, text);
        c = new Controller(this, text);
    }

    // start tbe game
    // synchronized prevents other methods from running at the same time
    private synchronized void start() {
        if(running) {
            // already running
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    // stop the game
    private synchronized void stop() {
        if(!running) {
            // not running already
            return;
        }

        running = false;
        try {
            thread.join(); // join all threads together and waits for them to die
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    // game loop (handles updates)
    public void run() {
        init();
        long initTime = System.nanoTime();
        final double amountOfTicks = 60.0; // 60 fps
        double ns = 1000000000 / amountOfTicks; 
        double delta = 0; // calculate time passed so that it catch itself up if ticks / frames are behind
        int updates = 0; // ticks
        int frames = 0; // FPS
        long timer = System.currentTimeMillis();

        while(running) {
            long now = System.nanoTime();
            delta += (now - initTime) / ns; // gives the elapsed time since the run method was entered in seconds
            initTime = now;

            if(delta >= 1) {
                tick();
                updates++;
                delta--;
            }

            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000; // so it wouldn't loop through again
                System.out.println(updates + " Ticks, FPS " + frames);
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    // game updates
    private void tick() {
        p.tick();
        c.tick();
    }

    // game render
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            createBufferStrategy(3); // creates 3 buffers
            return;
        }

        Graphics g = bs.getDrawGraphics(); // draws out the buffers

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        p.render(g);
        c.render(g);

        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            p.setVelX(5);
        } else if(key == KeyEvent.VK_LEFT) {
            p.setVelX(-5);
        } else if(key == KeyEvent.VK_DOWN) {
            p.setVelY(5);
        } else if(key == KeyEvent.VK_UP) {
            p.setVelY(-5);
        } else if(key == KeyEvent.VK_SPACE && !is_shooting) {
            is_shooting = true;
            c.addBullet(new Bullet(p.getX(), p.getY(), text));
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
        } else if(key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
        } else if(key == KeyEvent.VK_DOWN) {
            p.setVelY(0);
        } else if(key == KeyEvent.VK_UP) {
            p.setVelY(0);
        } else if(key == KeyEvent.VK_SPACE) {
            is_shooting = false;
        }
    }

    public static void main(String args[]) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        game.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame(game.TITLE);

        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    // Getter & Setters
    public BufferedImage getSpriteSheet() {
        // return the sprite sheet for player class to use
        return spriteSheet;
    }

    public BufferedImage getOrange(){
        return orange;
    }

    public BufferedImage getEnemy(){
        return enemy;
    }

}