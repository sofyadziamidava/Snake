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
    Image image1 = ImageIO.read(new File("kaa.jpg"));  //TODO: se till så att bilden kan ritas och visas i fönstret

    public StartPanel() throws IOException {

        this.setLayout(new BorderLayout());
      //  this.add(welcomingMessage);
        this.add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(startButton, BorderLayout.SOUTH);
        startButton.addActionListener(this);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(600, 600);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == startButton) {
                GameWindow gameWindow = new GameWindow();
            }
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        StartPanel sp = new StartPanel();
    }
}
