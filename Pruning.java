import java.util.Vector;

public class Pruning {
    Working w;

    Pruning(Working w){
        this.w = w;
    }

    Point findBestMove(int[][] board, int depth) {
        int bestValue = Integer.MIN_VALUE;
        Point bestPoint = null;
    
        for (Point point : getValidMoves(board, 1)) {
            int newBoard[][] = getCopy(board);
            applyMove(newBoard, point, 1);
            int pointValue = minimax(newBoard, depth - 1, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            
            if (pointValue > bestValue) {
                bestValue = pointValue;
                bestPoint = point;
            }
        }
    
        return bestPoint;
    }


    public int minimax(int[][] board, int depth, int maximizingPlayer, int alpha, int beta) {
        if (depth == 0 || gameOver(board)) {
            return evaluateBoard(board);
        }

        if (maximizingPlayer==1) {
            int maxEval = Integer.MIN_VALUE;
            for (Point p : getValidMoves(board, 1)) {
                int newBoard[][] = getCopy(board);
                applyMove(newBoard, p, 1);
                // board[p.getX()][p.getY()] = 1;
                int eval = minimax(newBoard, depth - 1, 0, alpha, beta);
                // board[p.getX()][p.getY()] = 3;
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;  
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Point p: getValidMoves(board, 0)) {
                int newBoard[][] = getCopy(board);
                applyMove(newBoard, p, 0);
                // board[p.getX()][p.getY()] = 0;
                int eval = minimax(newBoard, depth - 1, 1, alpha, beta);
                // minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;  
                }
            }
            return minEval;
        }
    }

    private Vector<Point> getValidMoves(int[][] board, int player) {
        return w.getValidMoves(board, player);
    }

    private void applyMove(int[][] board, Point attempt, int player) {
        int x = attempt.getX();
        int y = attempt.getY();
        board[x][y]=player;
        if (x + 1 <= board.length - 1 && board[x + 1][y] == 3) {
            board[x + 1][y] = 2;
        }
        if (y + 1 <= board.length - 1 && board[x][y + 1] == 3) {
            board[x][y + 1] = 2;
        }
        if (x + 1 <= board.length - 1 && y + 1 <= board.length - 1 && board[x + 1][y + 1] == 3) {
            board[x + 1][y + 1] = 2;
        }
        if (x - 1 >= 0 && board[x - 1][y] == 3) {
            board[x - 1][y] = 2;
        }
        if (y - 1 >= 0 && board[x][y - 1] == 3) {
            board[x][y - 1] = 2;
        }
        if (x - 1 >= 0 && y - 1 >= 0 && board[x - 1][y - 1] == 3) {
            board[x - 1][y - 1] = 2;
        }
        if (x + 1 <= board.length - 1 && y - 1 >= 0 && board[x + 1][y - 1] == 3) {
            board[x + 1][y - 1] = 2;
        }
        if (x - 1 >= 0 && y + 1 <= board.length - 1 && board[x - 1][y + 1] == 3) {
            board[x - 1][y + 1] = 2;
        }
    }

    private boolean gameOver(int[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]>=2)
                return false;
            }
        }
        return true;
    }

    private int evaluateBoard(int[][] board) {
        return Heuristic.evaluateBoard(board, 1, 0);
    }

    private int[][] getCopy(int arr[][]){
        int newArr[][]  = new int[arr.length][arr[0].length];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }
}
