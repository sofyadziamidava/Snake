import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreboardPanel {
    static String currentPlayerName;
    ArrayList<String> players;
    ArrayList<String> scores;
    ArrayList<String> onlyBestPlayers;
    ArrayList<String> onlyBestScores;

    public ScoreboardPanel(){
        if(GamePanel.gameRunning == false){
          registerCurrentHighScore();
        }
        getAllHighScores();
        Collections.sort(scores);
        if (players.size() > 10){
        }
    }

    public void getAllHighScores(){
        Path p = Paths.get("C:\\Users\\Sonya\\IdeaProjects\\Snake\\src\\scores.txt");
        players = new ArrayList<>();
        String tempLine;
        try (BufferedReader reader = Files.newBufferedReader(p);) {
            while ((tempLine = reader.readLine()) != null) {
                String[] playerAndScore= tempLine.split(",");
                players.add(playerAndScore[0]);
                scores.add(playerAndScore[1]);
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void registerCurrentHighScore(){
        File file = new File( "C:\\Users\\Sonya\\IdeaProjects\\Snake\\src\\scores.txt");
        try (FileWriter fileWriter = new FileWriter(file, true);) {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(currentPlayerName + "," +GamePanel.score);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
