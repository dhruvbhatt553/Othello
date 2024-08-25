import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class ScoreBoard extends JPanel {
    int po;
    JLabel jl;

    ScoreBoard(int flag) {
        jl = new JLabel();
        po = 2;

        // Set initial text
        jl.setText("2");

        // Use a layout manager to center the label
        setLayout(new GridBagLayout());

        // Add label to panel
        add(jl);

        // Set a border for the panel
        Border br = new LineBorder(Color.BLACK, 5, true);
        setBorder(br);

        // Set background color
        setBackground(Color.cyan);

        // Set text color based on the flag
        if (flag == 1) {
            jl.setForeground(Color.white);
        } else {
            jl.setForeground(Color.black);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dynamically adjust font size based on the panel's height
        int fontSize = getHeight() / 3;
        jl.setFont(new Font("MV Boli", Font.BOLD, fontSize));

        // Center the text in the label
        jl.setHorizontalAlignment(SwingConstants.CENTER);
    }

    void updateBy(int x) {
        po += x;
        jl.setText("" + po);
    }
}
