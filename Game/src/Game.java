import java.awt.*;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 2 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "2D Game";

    private boolean running = false;
    private Thread thread;

    // BufferedImage will load the image before rendering it
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // buffer the entire window

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

        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
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

}