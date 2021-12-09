import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{

    static final int width = 700;
    static final int height = 700;
    static final int boxSize = 30;
    static final int gameUnits = ((width*height / boxSize));
    static final int speed = 50;
    boolean running = false;
    int snackX;
    int snackY;

    final int[][] coordinates = new int[gameUnits][gameUnits];

    int snakeSize = 4;
    String course = "Right";
    int score = 0;
    Timer timer;
    Random random;

    public GamePanel(){
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.LIGHT_GRAY);
        this.setFocusable(true);

        startOfTheGame();
    }

    public void startOfTheGame(){
        displayApple();
        running = true;
        timer = new Timer(speed, this);
        timer.start();

    }

    public void displayApple(){
        snackX = random.nextInt((int) (width / boxSize)) * boxSize;
        snackY = random.nextInt((int) (width / boxSize)) * boxSize;
    }

    public void moveSnake(){

    }

    public void changeDirection(){

    }

    public void eatApple(){

    }

    public void endOfTheGame(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
