import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener{

    Snake snake;
    Apple apple;
    static final int width = 600;
    static final int height = 600;
    static final int boxSize = 30;
    static final int gameUnits = ((width*height / boxSize));
    boolean gameRunning = false;

    Image image1 = ImageIO.read(new File("src/grass.jpg"));

    final int[] x = new int[gameUnits];
    final int[] y = new int[gameUnits];

    static String course = "Right";
    int score = 0;
    Timer timer;

    public GamePanel() throws IOException {
        this.snake = new Snake(4, boxSize, 100);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.LIGHT_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startOfTheGame();
    }

    public void startOfTheGame(){
        this.apple = new Apple(width, boxSize);
        gameRunning = true;
        timer = new Timer(snake.getSpeed(), this);
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
            g.fillOval(apple.getXpos(), apple.getYpos(), boxSize, boxSize);

            for (int i = 0; i < snake.getSize(); i++) {
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

    public void moveSnake(){
        for (int i = snake.getSize(); i > 0; i--) {
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
        for (int i = snake.getSize(); i > 0 ; i--) {
            if((x[0] == x[i])&& (y[0] == y[i])) {
                gameRunning = false;
                    timer.stop();
            }
        }
    }

    public void eatSnack(){
        if(x[0] == apple.getXpos() && y[0] == apple.getYpos()){
            snake.increaseSize(1);
            score++;
            apple.setNewPos();                     //displayApple();
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
