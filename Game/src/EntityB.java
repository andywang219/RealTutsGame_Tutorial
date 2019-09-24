import java.awt.Graphics;
import java.awt.Rectangle;

// Entity B's do not collide with one another
public interface EntityB {

    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public double getX();
    public double getY();

}