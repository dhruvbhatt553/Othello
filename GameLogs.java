import javax.swing.*;
import java.awt.event.*;

public class GameLogs extends JPanel  {
    JLabel l1, l2;
    private JTextArea area;
    

    GameLogs() {
        setLayout(null);

        l1 = new JLabel();
        l1.setBounds(50, 25, 100, 30);
        l2 = new JLabel();
        l2.setBounds(160, 25, 100, 30);
        area = new JTextArea();
        area.setBounds(20, 75, 250, 200);
       
       

        // add(l1);
        // add(l2);
        add(area);
        
    }

    void append(String newLine){
        area.append("\n-->"+newLine);
    }

    

   
}
