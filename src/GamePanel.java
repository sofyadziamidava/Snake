import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{

    static final int width = 600;
    static final int height = 600;
    static final int boxSize = 30;
    static final int gameUnits = ((width*height / boxSize));
    static final int speed = 100;
    boolean gameRunning = false;
    int snackX;
    int snackY;
    Image image1 = ImageIO.read(new File("src/grass.jpg"));

    final int[] x = new int[gameUnits];
    final int[] y = new int[gameUnits];

    int snakeSize = 4;
    static String course = "Right";
    int score = 0;
    Timer timer;
    Random random;

    public GamePanel() throws IOException {
        random = new Random();
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.LIGHT_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startOfTheGame();
    }

    public void startOfTheGame(){
        displayApple();
        gameRunning = true;
        timer = new Timer(speed, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image1, 0 , 0, null);
        elementDesign(g);
    }

    public void elementDesign(Graphics g){
        if(gameRunning) {
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
            endOfTheGame(g);
        }

    }

    public void displayApple(){
        snackX = random.nextInt(width / boxSize) * boxSize;
        snackY = random.nextInt(width / boxSize) * boxSize;
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

    public void checkGameOverWallCollision(){
        if(x[0] < 0){
            gameRunning = false;
        }
        //Kollar om ormen går in i högra väggen
        if(x[0] > width){
            gameRunning = false;
        }
        //Kollar om ormen går in i översta väggen
        if(y[0] < 0){
            gameRunning = false;
        }
        //Kollar om ormen går in i nederst väggen
        if(x[0] > height){
            gameRunning = false;
        }
        if(!gameRunning){
            timer.stop();
        }
    }

    public void checkGameOverInjured(){
        for (int i = snakeSize; i > 0 ; i--) {
            if((x[0] == x[i])&& (y[0] == y[i])) {
                gameRunning = false;
                    timer.stop();
            }
        }
    }

    public void eatSnack(){
        if(x[0] == snackX && y[0] == snackY){
            snakeSize++;
            score++;
            displayApple();
        }
    }

    public void endOfTheGame(Graphics g){
       // g.drawImage(image2, gameUnits , gameUnits, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + score, (width - metrics1.stringWidth("Score: " + score)) /2, g.getFont().getSize());

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (width - metrics2.stringWidth("Game over")) /2, height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning){
            moveSnake();
            eatSnack();
            checkGameOverInjured();
            checkGameOverWallCollision();

        }
        repaint();

    }

}
