import javax.swing.*;

public class GameLogs extends JPanel {
    JLabel l1, l2;
    private JTextArea area;

    GameLogs() {
        setLayout(null);

        area = new JTextArea();
        area.setBounds(20, 75, 250, 200);

        area.setLineWrap(true);

        area.setWrapStyleWord(true);
        area.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBounds(20, 75, 250, 200);
        add(scrollPane);
    }

    void append(String newLine) {
        area.append("\n-->" + newLine);
    }

}
