import javax.swing.*;


public class Platform extends JFrame{
    ScoreBoard whiteScoreBoard, blackScoreBoard;
    GameLogs gameLogs;
    JLabel turn;

    Platform(){
        this.setTitle("Info");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        // Create the ScoreBoard panels
        whiteScoreBoard = new ScoreBoard(1);
        blackScoreBoard = new ScoreBoard(0);

        // Create the GameLogs panel
        turn = new JLabel();
        gameLogs = new GameLogs();

        


        // Manually set the bounds for each panel
        whiteScoreBoard.setBounds(10, 10, 200, 100);
        blackScoreBoard.setBounds(220, 10, 200, 100);
        turn.setBounds(10, 120, 420, 20);
        gameLogs.setBounds(10, 150, 410, 400);

        turn.setAlignmentX(CENTER_ALIGNMENT);

        // Add the panels to the frame
        this.add(whiteScoreBoard);
        this.add(blackScoreBoard);
        this.add(turn);
        this.add(gameLogs);

        // Set frame size and make it visible
        this.setSize(450, 600);
        this.setVisible(true);
    }

    

    
    public void setTurn(int flag){
        turn.setText(""+(flag==0?"BLACK":"WHITE")+"\' TURN");
    }
}
