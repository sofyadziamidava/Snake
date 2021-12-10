import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JFrame implements ActionListener {

    //JLabel welcomingMessage = new JLabel("Let's play snake!");
    JButton startButton = new JButton("Let's play snake!");
    JPanel mainPanel = new JPanel();
    Image image1 = ImageIO.read(new File("src/kaa.jpg"));  //TODO: se till så att bilden kan ritas och visas i fönstret

    public StartPanel() throws IOException, InterruptedException {

        this.setLayout(new BorderLayout());

        JLabel background = new JLabel(new ImageIcon("src/kaa.jpg"));

        background.setLayout(new BorderLayout());
        startButton = new JButton("Let's play snake!");
        background.add(startButton, BorderLayout.SOUTH);
        startButton.addActionListener(this);
        add(background);
        this.setVisible(true);
        this.pack();
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == startButton) {
                Thread.sleep(3000);
                dispose();
                GameWindow gameWindow = new GameWindow();
            }
        } catch (IOException | InterruptedException io) {
            io.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        StartPanel sp = new StartPanel();
    }
}

