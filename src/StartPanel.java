import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartPanel extends JFrame implements ActionListener {
    JButton startButton;
    JTextArea input;

    public StartPanel() {

        this.setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("src/kaa.jpg"));
        background.setLayout(new BorderLayout());
        startButton = new JButton("Let's play snake!");
        input = new JTextArea();
        background.add(input, BorderLayout.BEFORE_FIRST_LINE);
        input.setPreferredSize(new Dimension(400, 25));
        background.add(startButton, BorderLayout.AFTER_LAST_LINE);
        startButton.addActionListener(this);
        startButton.setFont(new Font("Ink Free",Font.BOLD,35));
        startButton.setFocusable(false);
        startButton.setBackground(Color.WHITE);
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
        Player currenPlayer = new Player();
        currenPlayer.name = input.getText();
        if (!currenPlayer.name.isEmpty() || !currenPlayer.name.isBlank()){
        try {
            if (e.getSource() == startButton) {
                Thread.sleep(3000);
                dispose();
                GameWindow gameWindow = new GameWindow();
            }
        } catch (IOException | InterruptedException io) {
            io.printStackTrace();
        }} else{
            JOptionPane.showMessageDialog(null, "please enter your username first");
        }
    }


    public static void main(String[] args) {
        StartPanel sp = new StartPanel();
    }
}

