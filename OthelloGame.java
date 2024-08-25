
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class OthelloGame extends JFrame implements ActionListener {
    JButton sp, dp, exit, i;
    JLabel spl, dpl, back, lab;
    Font f;
    GameBoard ms;
    Image i1, img2;
    ImageIcon iback;
    JTextField jt;

    OthelloGame() {

        jt = new JTextField();
        sp = new JButton("Single Player");
        dp = new JButton("Double Player");
        spl = new JLabel();
        dpl = new JLabel();

        img2 = (new ImageIcon("../public/try2.jpg")).getImage();

        f = new Font("MV Boli", Font.ITALIC, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 30));
        sp.setFont(f);
        dp.setFont(f);
        sp.setFocusable(false);
        dp.setFocusable(false);
        sp.setBackground(Color.GREEN);
        dp.setBackground(Color.GREEN);

        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        iback = new ImageIcon(img2.getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH));
        back = new JLabel("", iback, JLabel.CENTER);
        back.setBounds(0, 0, getWidth(), getHeight());

        back.setOpaque(true);

        setLayout(null);

        add(back);
        back.add(sp);
        back.add(dp);

        System.out.print("" + (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4 + "  "
                + (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
        sp.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
        dp.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
        lab = new JLabel();
        lab.setSize(sp.getSize());

        jt.setSize(sp.getSize());
        sp.setLocation(2 * sp.getWidth() / 3, sp.getHeight() / 4);
        dp.setLocation(2 * sp.getX() + sp.getWidth(), sp.getY());
        sp.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                lab.setIcon(new ImageIcon(new ImageIcon("../public/ult.jpg").getImage()
                        .getScaledInstance(lab.getWidth(), lab.getHeight(),
                                Image.SCALE_SMOOTH)));
                lab.setVisible(true);
                sp.add(lab);

            }

            public void mouseExited(MouseEvent e) {
                lab.setVisible(false);
            }
        });

        dp.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                lab.setIcon(new ImageIcon(new ImageIcon("../public/imgdp.jpg").getImage()
                        .getScaledInstance(lab.getWidth(), lab.getHeight(),
                                Image.SCALE_SMOOTH)));
                lab.setVisible(true);
                dp.add(lab);

            }

            public void mouseExited(MouseEvent e) {
                lab.setVisible(false);
            }
        });
        sp.addActionListener(this);
        dp.addActionListener(this);
        setVisible(true);

    }

    void temp() {
        add(sp);
        add(dp);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == sp) {
            new GameBoard(8, 1);
        } else {
            new GameBoard(8, 0);

        }
        dispose();
    }

    public static void main(String[] args) {
        new OthelloGame();
    }
}