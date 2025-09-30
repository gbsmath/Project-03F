import java.awt.*;

public class Flappy extends GBSGame {

    // Game Variables
    // - Only declare variables here, initialize them in setup()
    double birdHeight;

    public void setup() {
        birdHeight = 300;
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void draw(Graphics g) {
        
    }



    // Ignore these lines unless you want to change the framerate. 
    // Game is designed for 400x600
    // If using dt properly in update, should work no matter what framerate you select
    public static void main(String[] args) {
        Flappy f = new Flappy();
        f.setResolution(400, 600);
        f.setFrameRate(60);
        f.createWindow();
        f.setup();
    }
}