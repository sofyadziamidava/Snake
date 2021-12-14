import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ScoreboardPanel extends JFrame{
    static String currentPlayerName;
    ArrayList<String> players = new ArrayList<>();
    ArrayList<String> scores = new ArrayList<>();
    String[] sortedPlayers;
    int[] sortedScores;
    ArrayList<String> scoresToShow = new ArrayList<>();

    public ScoreboardPanel() {
        registerCurrentHighScore();
        getAllHighScores();
        sortScores();
        if(sortedPlayers.length <= 10){
            for (int i = sortedPlayers.length - 1; i >= 0; i--) {
                String temp =sortedPlayers[i] + " " + sortedScores[i] + "\n";
                scoresToShow.add(temp);
            }
        } else {
            for (int i = sortedScores.length - 1; i >= sortedPlayers.length - 11; i--) {
                String temp = sortedPlayers[i] + " " + sortedScores[i] + "\n";
                scoresToShow.add(temp);
            }
        }

        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("High Scores:");
        label.setFont(new Font("Ink Free",Font.BOLD,20));
        add(label, BorderLayout.BEFORE_FIRST_LINE);
        String toDisplay = "<html>";
        for (String s: scoresToShow) {
            toDisplay = toDisplay + s + "<br>";
        }
        toDisplay = toDisplay.substring(0, toDisplay.length() -4) + "</html>";
        JLabel labelScores = new JLabel(toDisplay);
        labelScores.setBackground(Color.black);
        labelScores.setFont(new Font("Ink Free",Font.BOLD,35));
        add(labelScores);
        setBackground(Color.yellow);
        this.setVisible(true);
        this.pack();
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void registerCurrentHighScore() {
        File file = new File("src/scores.txt");
        try (FileWriter fileWriter = new FileWriter(file, true);) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\n" + currentPlayerName + "," + GamePanel.score);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void getAllHighScores() {
        Path p = Paths.get("src/scores.txt");
        String tempLine;
        try (BufferedReader reader = Files.newBufferedReader(p);) {
            while ((tempLine = reader.readLine()) != null) {
                String[] playerAndScore = tempLine.split(",");
                players.add(playerAndScore[0]);
                scores.add(playerAndScore[1]);
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void sortScores() {
        sortedPlayers = players.toArray(new String[0]);
        sortedScores = new int[scores.size()];
        for (int i = 0; i < scores.size(); i++) {
            sortedScores[i] = Integer.parseInt(scores.get(i));
        }
        int temp;
        String temp2;
        for (int i = 0; i < sortedScores.length; i++) {
            for (int j = i + 1; j < sortedScores.length; j++) {
                if (sortedScores[i] > sortedScores[j]) {
                    temp = sortedScores[i];
                    sortedScores[i] = sortedScores[j];
                    sortedScores[j] = temp;
                    temp2 = sortedPlayers[i];
                    sortedPlayers[i] = sortedPlayers[j];
                    sortedPlayers[j] = temp2;

                }
            }
        }
    }

    public static void main(String[] args) {
        ScoreboardPanel scoreboardPanel = new ScoreboardPanel();
    }
}