import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

// LEAVE THIS FILE ALONE
// but feel free to explore :)

public class GBSGame extends JPanel implements Runnable, KeyListener {

    private Thread gameThread;
    private JFrame f;

    private int FPS;
    private int WIDTH;
    private int HEIGHT;

    private static ArrayList<String> keys = new ArrayList<>();
    private static ArrayList<String> keyEvents = new ArrayList<>();
    
    public void setResolution(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
    }

    public void setFrameRate(int f) {
        FPS = f;
    }

    public void createWindow() {
        if (gameThread != null && gameThread.isAlive()) {
            gameThread.interrupt();
        }
        f = new JFrame();
        f.add(this);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.addKeyListener(this);
        f.setFocusable(true);
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        // This will call the "run" method of the object passed into the thread.
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        long currentTime = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update(delta/FPS);
                repaint();
                delta--; 
            }
        }
    }

    // Meant to override
    public void update(double dt) {

    }

    // Meant to override
    public void draw(Graphics g) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public Image getImage(String filePath) {
        return javax.swing.ImageIcon.class.getResource(filePath) != null
            ? new javax.swing.ImageIcon(getClass().getResource(filePath)).getImage()
            : new javax.swing.ImageIcon(filePath).getImage();
    }

    public static boolean keyDown(String key) {
        if (key.equals("space")) {
            key = " ";
        }
        return keys.contains(key);
    }

    public static boolean keyPressed(String key) {
        if (key.equals("space")) {
            key = " ";
        }
        boolean keyPressedBool = keyEvents.contains(key);
        while (keyEvents.contains(key)) {
            keyEvents.remove(key);
        }
        return keyPressedBool;
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keys.contains(e.getKeyChar() + "")) {
            keyEvents.add(e.getKeyChar() + "");
        }
        keys.add(e.getKeyChar() + "");
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String key = e.getKeyChar() + "";
        while (keys.contains(key)) {
            keys.remove(key);
        }
        while (keyEvents.contains(key)) {
            keyEvents.remove(key);
        }
    }
}