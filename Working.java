import java.awt.Color;
import java.awt.Image;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class Working {
    int boardState[][];
    int flag = 0;
    GameBoard gb;
    ScoreBoard pb, pw;
    GameLogs gameLogs;
    int px, py;
    Border br;
    Platform aboutGame;
    int totalProfit;
    Vector<Point> vec;
    Point p1;
    Set<Point> highlights;
    Pruning pruning;

    Working(GameBoard gb) {
        boardState = new int[gb.size][gb.size];
        vec = new Vector<>();
        highlights = new HashSet<>();
        aboutGame = new Platform();
        pruning = new Pruning(this);

        aboutGame.setLocation(gb.getWidth(), 0);
        aboutGame.setTurn(flag);

        gb.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("ag1 Closing");
                newGame();
            }
        });

        pb = aboutGame.blackScoreBoard;
        pw = aboutGame.whiteScoreBoard;
        gameLogs = aboutGame.gameLogs;

        br = new LineBorder(Color.gray, 5, true);
        for (int i = 0; i < gb.size; i++) {
            for (int j = 0; j < gb.size; j++) {
                boardState[i][j] = 3;
            }
        }

        boardState[gb.size / 2][gb.size / 2] = 0;
        boardState[gb.size / 2 - 1][gb.size / 2 - 1] = 0;
        boardState[gb.size / 2][gb.size / 2 - 1] = 1;
        boardState[gb.size / 2 - 1][gb.size / 2] = 1;

        boardState[gb.size / 2 - 2][gb.size / 2 - 2] = 2;
        boardState[gb.size / 2 - 1][gb.size / 2 - 2] = 2;
        boardState[gb.size / 2 - 2][gb.size / 2 - 1] = 2;
        boardState[gb.size / 2][gb.size / 2 - 2] = 2;
        boardState[gb.size / 2 - 2][gb.size / 2] = 2;

        boardState[gb.size / 2 + 1][gb.size / 2 + 1] = 2;
        boardState[gb.size / 2 + 1][gb.size / 2] = 2;
        boardState[gb.size / 2][gb.size / 2 + 1] = 2;
        boardState[gb.size / 2 - 1][gb.size / 2 + 1] = 2;
        boardState[gb.size / 2 + 1][gb.size / 2 - 1] = 2;

        boardState[gb.size / 2 - 2][gb.size / 2 + 1] = 2;
        boardState[gb.size / 2 + 1][gb.size / 2 - 2] = 2;

        this.gb = gb;

        // left-top
        addml(gb.allbuttons[gb.size / 2 - 2][gb.size / 2 - 2]);
        addml(gb.allbuttons[gb.size / 2 - 1][gb.size / 2 - 2]);
        addml(gb.allbuttons[gb.size / 2 - 2][gb.size / 2 - 1]);
        addml(gb.allbuttons[gb.size / 2][gb.size / 2 - 2]);
        addml(gb.allbuttons[gb.size / 2 - 2][gb.size / 2]);

        // top-right and bottom-left corners
        addml(gb.allbuttons[gb.size / 2 - 2][gb.size / 2 + 1]);
        addml(gb.allbuttons[gb.size / 2 + 1][gb.size / 2 - 2]);

        // right-bottom
        addml(gb.allbuttons[gb.size / 2 + 1][gb.size / 2 - 1]);
        addml(gb.allbuttons[gb.size / 2 - 1][gb.size / 2 + 1]);
        addml(gb.allbuttons[gb.size / 2][gb.size / 2 + 1]);
        addml(gb.allbuttons[gb.size / 2 + 1][gb.size / 2]);
        addml(gb.allbuttons[gb.size / 2 + 1][gb.size / 2 + 1]);

    }

    // ADDING MS
    void addml(JButton jb) {
        jb.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {

                removeBorders();
                checkFeasibility(jb);

                giveBorders();
            }
        });
    }

    void controlTurns(int attemptBy, JButton bu1) {
        if (attemptBy != flag) {
            gameLogs.append("This is " + (flag == 0 ? "Black/'s" : "White/'s") + " Turn. Please Wait");
        } else {
            px = Integer.parseInt("" + bu1.getText().substring(0, 2));
            py = Integer.parseInt("" + bu1.getText().substring(2));
            System.out.println("In Control" + px + " " + py);

            if (boardState[px][py] == 2 && vec.size() >= 1) {
                takeTurn(bu1);
            } else if (boardState[px][py] != 2) {
                gameLogs.append("You can not put piece here.");
            } else {
                gameLogs.append("You can only put a piece if it can flip atleast 1 of the opponent piece");
            }
        }
    }

    void takeTurn(JButton bu1) {

        px = Integer.parseInt("" + bu1.getText().substring(0, 2));
        py = Integer.parseInt("" + bu1.getText().substring(2));

        gb.allbuttons[px][py].setIcon(new ImageIcon(new ImageIcon("../public/img" + flag + ".jpg").getImage()
                .getScaledInstance(GameBoard.d1.width / gb.size, GameBoard.d1.width / gb.size,
                        Image.SCALE_SMOOTH)));
        gb.allbuttons[px][py].setToolTipText(null);

        boardState[px][py] = flag;
        totalProfit = vec.size();
        setImages();

        gameLogs.append("" + (flag == 0 ? "Black" : "White") + " On [" + (px + 1) + "][" + (py + 1) + "]");

        if (flag == 1) {
            pw.updateBy(totalProfit + 1);
            pb.updateBy(-totalProfit);
        } else {
            pb.updateBy(totalProfit + 1);
            pw.updateBy(-totalProfit);
        }
        setFlags(px, py);
        flag = 1 - flag;
        aboutGame.setTurn(flag);

        gb.th.running(2, 150);
        if (flag == 1 && gb.pla == 1) {
            System.out.println("Machine Thinks");
            // Point p = think();
            Point p = pruning.findBestMove(boardState, flag);
            if (p != null) {
                System.out.println("here");
                applyMove(p);
                // controlTurns(flag, gb.allbuttons[p.getX()][p.getY()]);
            } else {
                flag = 1 - flag;
            }
        }

        if (pb.po + pw.po == gb.size * gb.size) {
            gameLogs.append("Game Over final score : " + pb.po + " : " + pw.po);
            flag = -1;
            aboutGame.turn.setText("Game Over");
            if (pb.po > pw.po) {
                if (gb.pla == 1) {
                    pw.jl.setIcon(new ImageIcon(new ImageIcon("../public/iwin.jpeg").getImage()
                            .getScaledInstance(pb.jl.getWidth(), pb.jl.getHeight(),
                                    Image.SCALE_SMOOTH)));
                }
                gameLogs.append("Black Wins");

            } else if (pb.po < pw.po) {
                if (gb.pla == 1) {
                    pb.jl.setIcon(new ImageIcon(new ImageIcon("../public/1win.jpg").getImage()
                            .getScaledInstance(pb.jl.getWidth(), pb.jl.getHeight(),
                                    Image.SCALE_SMOOTH)));
                }
                gameLogs.append("White Wins");
            } else {
                gameLogs.append("Tie");
            }
            gameLogs.append("Close OthelloBoard window to return to home.");
        }
    }

    // SET FLAGS

    void setFlags(int x, int y) {
        if (x + 1 <= gb.size - 1 && boardState[x + 1][y] == 3) {
            boardState[x + 1][y] = 2;
            addml(gb.allbuttons[x + 1][y]);
        }
        if (y + 1 <= gb.size - 1 && boardState[x][y + 1] == 3) {
            boardState[x][y + 1] = 2;
            addml(gb.allbuttons[x][y + 1]);
        }
        if (x + 1 <= gb.size - 1 && y + 1 <= gb.size - 1 && boardState[x + 1][y + 1] == 3) {
            boardState[x + 1][y + 1] = 2;
            addml(gb.allbuttons[x + 1][y + 1]);
        }
        if (x - 1 >= 0 && boardState[x - 1][y] == 3) {
            boardState[x - 1][y] = 2;
            addml(gb.allbuttons[x - 1][y]);
        }
        if (y - 1 >= 0 && boardState[x][y - 1] == 3) {
            boardState[x][y - 1] = 2;
            addml(gb.allbuttons[x][y - 1]);
        }
        if (x - 1 >= 0 && y - 1 >= 0 && boardState[x - 1][y - 1] == 3) {
            boardState[x - 1][y - 1] = 2;
            addml(gb.allbuttons[x - 1][y - 1]);
        }
        if (x + 1 <= gb.size - 1 && y - 1 >= 0 && boardState[x + 1][y - 1] == 3) {
            boardState[x + 1][y - 1] = 2;
            addml(gb.allbuttons[x + 1][y - 1]);
        }
        if (x - 1 >= 0 && y + 1 <= gb.size - 1 && boardState[x - 1][y + 1] == 3) {
            boardState[x - 1][y + 1] = 2;
            addml(gb.allbuttons[x - 1][y + 1]);
        }
        // display();
    }

    private void setImages() {
        for (int i = 0; i < vec.size(); i++) {
            p1 = (Point) vec.get(i);
            gb.allbuttons[p1.getX()][p1.getY()]
                    .setIcon(new ImageIcon(new ImageIcon("../public/img" + flag + ".jpg").getImage()
                            .getScaledInstance(GameBoard.d1.width / gb.size, GameBoard.d1.width / gb.size,
                                    Image.SCALE_SMOOTH)));
            boardState[p1.getX()][p1.getY()] = flag;
        }
        removeBorders();
    }

    // PROFIT SHOW

    public int profit(int x, int y, int player, int boardState[][]) {
        int opp = 1 - player;
        int i, j, dirProfit, totalProfit = 0;

        // "\"
        dirProfit = 0;
        i = x - 1;
        j = y - 1;
        while (i != -1 && j != -1) {
            if (boardState[i][j] == player) {
                dirProfit = 1;
                break;
            } else if (boardState[i][j] > 1) {
                break;
            } else {
                i--;
                j--;
            }
        }
        i = x - 1;
        j = y - 1;
        if (dirProfit == 1) {
            while (i != -1 && j != -1 && boardState[i][j] == opp) {
                vec.addElement(new Point(i, j));
                totalProfit++;
                i--;
                j--;
            }
        }

        // "/"
        dirProfit = 0;
        i = x + 1;
        j = y - 1;
        while (i != gb.size && j != -1) {
            if (boardState[i][j] == player) {
                dirProfit = 1;
                break;
            } else if (boardState[i][j] > 1) {
                break;
            } else {
                i++;
                j--;
            }
        }
        i = x + 1;
        j = y - 1;
        if (dirProfit == 1) {
            while (i != gb.size && j != -1 && boardState[i][j] == opp) {
                vec.addElement(new Point(i, j));
                totalProfit++;
                i++;
                j--;
            }
        }

        // "//"
        dirProfit = 0;
        i = x - 1;
        j = y + 1;
        while (i != -1 && j != gb.size) {
            if (boardState[i][j] == player) {
                dirProfit = 1;
                break;
            } else if (boardState[i][j] > 1) {
                break;
            } else {
                i--;
                j++;
            }
        }
        i = x - 1;
        j = y + 1;
        if (dirProfit == 1) {
            while (i != -1 && j != gb.size && boardState[i][j] == opp) {
                vec.addElement(new Point(i, j));
                totalProfit++;
                i--;
                j++;
            }
        }

        // "\\"
        dirProfit = 0;
        i = x + 1;
        j = y + 1;
        while (i != gb.size && j != gb.size) {
            if (boardState[i][j] == player) {
                dirProfit = 1;
                break;
            } else if (boardState[i][j] > 1) {
                break;
            } else {
                i++;
                j++;
            }
        }
        i = x + 1;
        j = y + 1;
        if (dirProfit == 1) {
            while (i != gb.size && j != gb.size && boardState[i][j] == opp) {
                vec.addElement(new Point(i, j));
                totalProfit++;
                i++;
                j++;
            }
        }

        // "<-"
        dirProfit = 0;
        i = x - 1;
        j = y;
        while (i != -1) {
            if (boardState[i][j] == player) {
                dirProfit = 1;
                break;
            } else if (boardState[i][j] > 1) {
                break;
            } else {
                i--;
            }
        }
        i = x - 1;
        if (dirProfit == 1) {
            while (i != -1 && boardState[i][j] == opp) {
                vec.addElement(new Point(i, j));
                totalProfit++;
                i--;
            }
        }

        // "->"
        dirProfit = 0;
        i = x + 1;
        j = y;
        while (i != gb.size) {
            if (boardState[i][j] == player) {
                dirProfit = 1;
                break;
            } else if (boardState[i][j] > 1) {
                break;
            } else {
                i++;
            }
        }
        i = x + 1;
        if (dirProfit == 1) {
            while (i != gb.size && boardState[i][j] == opp) {
                vec.addElement(new Point(i, j));
                totalProfit++;
                i++;
            }
        }

        // "^"
        dirProfit = 0;
        j = y - 1;
        i = x;
        while (j != -1) {
            if (boardState[i][j] == player) {
                dirProfit = 1;
                break;
            } else if (boardState[i][j] > 1) {
                break;
            } else {
                j--;
            }
        }
        j = y - 1;
        if (dirProfit == 1) {
            while (j != -1 && boardState[i][j] == opp) {
                vec.addElement(new Point(i, j));
                totalProfit++;
                j--;
            }
        }

        // "down"
        dirProfit = 0;
        j = y + 1;
        i = x;
        while (j != gb.size) {
            if (boardState[i][j] == player) {
                dirProfit = 1;
                break;
            } else if (boardState[i][j] > 1) {
                break;
            } else {
                j++;
            }
        }
        j = y + 1;
        if (dirProfit == 1) {
            while (j != gb.size && boardState[i][j] == opp) {
                vec.addElement(new Point(i, j));
                totalProfit++;
                j++;
            }
        }

        return totalProfit;
    }

    void giveBorders() {
        for (int i = 0; i < vec.size(); i++) {
            p1 = (Point) vec.get(i);
            gb.allbuttons[p1.getX()][p1.getY()].setBorder(br);
        }
    }

    void removeBorders() {
        for (int i = 0; i < vec.size(); i++) {
            p1 = (Point) vec.get(i);
            gb.allbuttons[p1.getX()][p1.getY()].setBorder(null);
        }
        vec.clear();
    }

    void checkFeasibility(JButton jb) {
        int h = Integer.parseInt("" + jb.getText().substring(0, 2));
        int v = Integer.parseInt("" + jb.getText().substring(2));

        if (boardState[h][v] == 2) {
            int totalProfit = profit(h, v, flag, boardState);
            if (totalProfit >= 1) {
                gb.allbuttons[h][v].setToolTipText("POINTS UP BY : " + (totalProfit + 1));
                return;
            }
        }
        gb.allbuttons[h][v].setToolTipText("Inactive");
    }

    Vector<Point> getValidMoves(int[][] board, int player) {
        Vector<Point> list = new Vector<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2) {
                    vec.clear();
                    if (profit(i, j, player, board) >= 1) {
                        list.add(new Point(i, j));
                    }
                    vec.clear();
                }
            }
        }
        return list;
    }

    void applyMove(Point p) {
        profit(p.getX(), p.getY(), flag, boardState);
        controlTurns(flag, gb.allbuttons[p.getX()][p.getY()]);
    }

    Point think() {
        int x1 = 0, y1 = 0;
        int maxProfit = -1, t = 0;
        Vector<Point> toChange = null;
        for (int i = 0; i < gb.size; i++) {
            for (int j = 0; j < gb.size; j++) {
                if (boardState[i][j] == 2) {
                    t = profit(i, j, 1, boardState);
                    if (t > maxProfit) {
                        toChange = new Vector<>(vec);
                        maxProfit = t;
                        x1 = i;
                        y1 = j;
                    }
                    vec.clear();
                }
            }
        }
        if (maxProfit >= 1) {
            vec = toChange;
            return new Point(x1, y1);
        }
        return null;
    }

    void newGame() {
        if (aboutGame != null) {
            aboutGame.dispose();
            aboutGame = null;
        }
        if (gb != null) {
            gb.dispose();
            gb = null;
        }
        new OthelloGame();
    }
}