public class Heuristic {

    // Define the score grid as a 2D array
    private static final int[][] SCORE_GRID = {
        {100, -20, 10, 5, 5, 10, -20, 100},
        {-20, -50, -2, -2, -2, -2, -50, -20},
        {10, -2, 5, 1, 1, 5, -2, 10},
        {5, -2, 1, 0, 0, 1, -2, 5},
        {5, -2, 1, 0, 0, 1, -2, 5},
        {10, -2, 5, 1, 1, 5, -2, 10},
        {-20, -50, -2, -2, -2, -2, -50, -20},
        {100, -20, 10, 5, 5, 10, -20, 100}
    };

    /**
     * Evaluates the Othello board for the given player.
     *
     * @param board 2D array representing the board. 
     *              Assume 1 represents the player's discs, -1 represents the opponent's discs, and 0 represents empty squares.
     * @param player The player for whom the evaluation is being calculated (1 or -1).
     * @return The evaluation score of the board.
     */
    public static int evaluateBoard(int[][] board, int player, int opponent) {
        int score = 0;
        
        // Loop through the board and calculate the score
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == player) {
                    score += SCORE_GRID[i][j];
                } else if (board[i][j] == opponent) {
                    score -= SCORE_GRID[i][j];
                }
            }
        }
        
        return score;
    }

    // Helper method to determine the opponent
    private static int opponent(int player) {
        return -player;
    }

    // Example usage
    // public static void main(String[] args) {
    //     // Example board (partial setup)
    //     int[][] board = {
    //         { 1, -1,  1,  0,  0,  0,  0,  0},
    //         { 0,  1, -1,  0,  0,  0,  0,  0},
    //         { 0,  0,  1, -1,  0,  0,  0,  0},
    //         { 0,  0,  0,  1, -1,  0,  0,  0},
    //         { 0,  0,  0,  0,  1, -1,  0,  0},
    //         { 0,  0,  0,  0,  0,  1, -1,  0},
    //         { 0,  0,  0,  0,  0,  0,  1, -1},
    //         { 0,  0,  0,  0,  0,  0,  0,  1}
    //     };

    //     int player = 1;  // Assume 1 is the player
    //     int evaluationScore = evaluateBoard(board, player);
    //     System.out.println("Board evaluation score: " + evaluationScore);
    // }
}
