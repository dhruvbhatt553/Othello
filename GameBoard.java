import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GameBoard extends JFrame implements ActionListener {
    int size, tbutton;
    static Dimension d1;
    ImageIcon img0, img1;
    Working w;
    String setn;
    Sound th;
    int pla;
    JButton allbuttons[][];

    GameBoard(int size, int pla) {
        this.pla = pla;
        this.size = size;
        th = new Sound();
        d1 = Toolkit.getDefaultToolkit().getScreenSize();
        d1.height -= 80;
        d1.width = d1.height;
        setResizable(false);
        setSize(d1);
        // this.addWindowListener(new java.awt.event.WindowAdapter() {
        //     public void windowClosing(java.awt.event.WindowEvent e) {
        //         // w.pb.dispose();
        //         // w.pw.dispose();
        //        newGame();
        //     }
        // });

        allbuttons = new JButton[size][size];
        tbutton = size * size;
        setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                setn = "";
                if (i < 10) {
                    setn = "0";
                }
                setn += i;
                if (j < 10) {
                    setn += "0";
                }
                setn += j;
                allbuttons[i][j] = new JButton();
                allbuttons[i][j].setFont(new Font("Dialog", Font.PLAIN, 0));
                allbuttons[i][j].setText(setn);
                allbuttons[i][j].setBackground(Color.yellow);
                allbuttons[i][j].addActionListener(this);

                this.add(allbuttons[i][j]);

            }
        img0 = new ImageIcon(
                new ImageIcon("../public/img0.jpg").getImage().getScaledInstance(d1.width / size, d1.width / size,
                        Image.SCALE_SMOOTH));
        img1 = new ImageIcon(
                new ImageIcon("../public/img1.jpg").getImage().getScaledInstance(d1.width / size, d1.width / size,
                        Image.SCALE_SMOOTH));

        allbuttons[size / 2 - 1][size / 2 - 1].setIcon(img0);
        allbuttons[size / 2][size / 2].setIcon(img0);
        allbuttons[size / 2 - 1][size / 2].setIcon(img1);
        allbuttons[size / 2][size / 2 - 1].setIcon(img1);

        setVisible(true);
        w = new Working(this);

    }

    // void newGame(){
    //     System.out.println("NG GameBoard");
    //     w.aboutGame.dispose();
    //     new OthelloGame();
    // }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(w.flag!=-1){
            w.controlTurns(w.flag,(JButton) e.getSource());
        }
    }

    public static void main(String[] args) {
        new GameBoard(8, 0);
    }
}