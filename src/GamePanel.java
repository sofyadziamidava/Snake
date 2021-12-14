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
    boolean gameRunning = false;

    Image image1 = ImageIO.read(new File("src/grass.jpg"));

    static String course = "Right";
    Timer timer;
    Game game;

    public GamePanel() throws IOException {
        this.game = new Game(new Snake(4, boxSize, 100, gameUnits), new Apple(width, boxSize), width, height);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.LIGHT_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startOfTheGame();
    }

    public void startOfTheGame(){
        gameRunning = true;
        timer = new Timer(game.getSpeed(), this);
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
            g.fillOval(game.apple.getXpos(), game.apple.getYpos(), boxSize, boxSize);

            for (int i = 0; i < game.getSnakeSize(); i++) {
                if (i == 0) {
                    g.setColor(Color.ORANGE);
                } else {
                    g.setColor(Color.yellow);
                }
                g.fillRect(game.snake.getXPosBodyPart(i), game.snake.getYPosBodyPart(i), boxSize, boxSize);
            }
            g.setColor(Color.black);
            g.setFont(new Font("Ink free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + game.getScore(), (width - metrics.stringWidth("Score: " + game.getScore())) / 2,
                    g.getFont().getSize());
        } else {
            StartPanel.gameWindow.dispose();
            new ScoreboardPanel();
            g.dispose();
        }
    }

    public void checkWallCollision(){
        if (game.wallCollision()) {
            gameRunning = false;
            timer.stop();
        }
    }

    public void checkIfInjured(){
        if (game.snakeInjured()){
            gameRunning = false;
            timer.stop();
        }
    }

    public void endOfTheGame(Graphics g){
       // g.drawImage(image2, gameUnits , gameUnits, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + game.getScore(), (width - metrics1.stringWidth("Score: " + game.getScore())) /2, g.getFont().getSize());

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (width - metrics2.stringWidth("Game over")) /2, height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning){
            game.moveSnake(course);
            game.eatApple();
            checkIfInjured();
            checkWallCollision();
        }
        repaint();
    }



}
