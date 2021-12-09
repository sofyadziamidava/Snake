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

    final int[] x = new int[gameUnits];
    final int[] y = new int[gameUnits];


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

    public void gameDesign(Graphics g){
        super.paintComponent(g);
        elementDesign(g);
    }

    public void elementDesign(Graphics g){
        if(running) {
            g.setColor(Color.red);
            g.fillOval(snackX, snackY, boxSize, boxSize);

            for (int i = 0; i < snakeSize; i++) {
                if (i == 0) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(x[i], y[i], boxSize, boxSize);
                } else {
                    g.setColor(Color.yellow);
                    g.fillRect(x[i], y[i], boxSize, boxSize);
                }
            }
            g.setColor(Color.black);
            g.setFont(new Font("Ink free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + score, (width - metrics.stringWidth("Score: " + score)) / 2,
                    g.getFont().getSize());
        } else {
            endOfTheGame();
        }

    }

    public void displayApple(){
        snackX = random.nextInt((int) (width / boxSize)) * boxSize;
        snackY = random.nextInt((int) (width / boxSize)) * boxSize;
    }

    public void moveSnake(){
        for (int i = snakeSize; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if(course.equals("Right")){
            x[0] = x[0] + boxSize;
        } else if (course.equals("Left")){
            x[0] = x[0] - boxSize;
        }else if (course.equals("Up")){
            y[0] = y[0] - boxSize;
        }else  if (course.equals("Down")){
            y[0] = y[0] + boxSize;
        }

    }

    public void changeDirection(){

    }

    public void eatSnack(){
        if(x[0] == snackX && y[0] == snackY){
            snakeSize++;
            score++;
            displayApple();
        }
    }

    public void endOfTheGame(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
