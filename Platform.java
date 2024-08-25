import javax.swing.*;
import java.awt.*;

public class Platform extends JFrame{
    ScoreBoard whiteScoreBoard, blackScoreBoard;
    GameLogs gameLogs;

    Platform(){
        this.setTitle("Info");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        // Create the ScoreBoard panels
        whiteScoreBoard = new ScoreBoard(1);
        blackScoreBoard = new ScoreBoard(0);

        // Create the GameLogs panel
        gameLogs = new GameLogs();

        // Manually set the bounds for each panel
        whiteScoreBoard.setBounds(10, 10, 200, 100);
        blackScoreBoard.setBounds(220, 10, 200, 100);
        gameLogs.setBounds(10, 120, 410, 400);

        // Add the panels to the frame
        this.add(whiteScoreBoard);
        this.add(blackScoreBoard);
        this.add(gameLogs);

        // Set frame size and make it visible
        this.setSize(450, 600);
        this.setVisible(true);
    }
}
