import javax.swing.*;
import java.io.IOException;
import java.util.Random;

public class GameWindow extends JFrame {


    GameWindow() throws IOException {
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static class Apple {

        int xpos;
        int ypos;
        int width;
        int boxSize;
        Random random;

        public Apple(int width, int boxSize) {
            this.random = new Random();
            this.width = width;
            this. boxSize = boxSize;
            setNewPos();
        }

        public int getXpos() {
            return xpos;
        }

        public void setNewPos(){
            this.xpos = random.nextInt(width / boxSize) * boxSize;
            this.ypos = random.nextInt(width / boxSize) * boxSize;
        }

        public int getYpos() {
            return ypos;
        }

    }
}
