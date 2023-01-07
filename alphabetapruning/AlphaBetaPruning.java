package alphabetapruning;

import java.util.Scanner;

/**
 * This code is developed by Prosanto Deb.
 * Roll : ASH1925005M
 */

public class AlphaBetaPruning {

    public static int logBaseB(int base, int value) {
        return (int) Math.ceil(Math.log(value) / Math.log(base));
    }

    public static int getMin(int[] arr) {
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        return min;
    }

    public static int getMax(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    public static int alphaBetaPruning(int currentNode, int currentDepth, int targetDepth, boolean playerTurn, int[] leaves, int alpha, int beta) {
        if (currentDepth == targetDepth) {
            return leaves[currentNode];
        }

        int best;
        if (playerTurn) {
            best = Integer.MIN_VALUE;
            for (int i = 0; i < 2; i++) {
                int value = alphaBetaPruning((currentNode * 2) + i, currentDepth + 1, targetDepth, false, leaves, alpha, beta);
                best = getMax(new int[]{best, value});
                alpha = getMax(new int[]{alpha, best});

                if (beta <= alpha) {
                    break;
                }
            }
        } else {
            best = Integer.MAX_VALUE;
            for (int i = 0; i < 2; i++) {
                int value = alphaBetaPruning((currentNode * 2) + i, currentDepth + 1, targetDepth, true, leaves, alpha, beta);
                best = getMin(new int[]{best, value});
                beta = getMin(new int[]{beta, best});

                if (beta <= alpha) {
                    break;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        // Array of size 16 to
        // store leaf values
        int[] leaves = new int[64];

        // Array of size 2 to store moves
        int[] moves = new int[] {6, 4};

        // Index variable to store
        // values in specific position
        int index = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        for (int m = 0; m < 2; m++) {
                            for (int n = 0; n < 2; n++) {
                                int firstPlayer = moves[i] + moves[k] + moves[m];
                                int secondPlayer = moves[j] + moves[l] + moves[n];

                                if (firstPlayer > secondPlayer) {
                                    leaves[index] = 1;
                                } else if (firstPlayer < secondPlayer) {
                                    leaves[index] = -1;
                                } else {
                                    leaves[index] = 0;
                                }

                                index++;
                            }
                        }
                    }
                }
            }

        }


        // Finding depth of tree
        int targetDepth = logBaseB(2, leaves.length);

        // Scanner object to take user input
        Scanner keyBoard = new Scanner(System.in);

        System.out.print("Enter your move 6 or 4 -> ");
        int move = keyBoard.nextInt();
        int gameMove;

        if (move == 6) {
            gameMove = 0;
        } else if  (move == 4) {
            gameMove = 1;
        } else {
            System.out.println("Invalid Move!!!");
            return;
        }

        int optimalMove = alphaBetaPruning(gameMove, 1, targetDepth, false, leaves, Integer.MIN_VALUE, Integer.MAX_VALUE);

        if (optimalMove == 0) {
            System.out.println("You may draw!!!");
        } else if (optimalMove == 1) {
            System.out.println("Congrats Buddy!!!! you have a chance of winning.");
        } else if (optimalMove == -1) {
            System.out.println("Sorry Man! you may loose.");
        }

        int turn = 2;

        while (turn <= 6) {
            boolean firstPlayerTurn;

            System.out.print("Enter ");

            if ((turn % 2) == 0) {
                firstPlayerTurn = true;
                System.out.print("opponent's ");
            } else {
                firstPlayerTurn = false;
                System.out.print("your ");
            }

            System.out.print("move 6 or 4 -> ");

            move = keyBoard.nextInt();

            if (move == 6) {
                gameMove = gameMove * 2;
            } else if (move == 4) {
                gameMove = gameMove * 2 + 1;
            } else {
                System.out.println("Invalid Move!!!");
                return;
            }

            optimalMove = alphaBetaPruning(gameMove, turn, targetDepth, firstPlayerTurn, leaves, Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (optimalMove == 0) {
                if (turn == 6) {
                    System.out.println("Match drawn!!!");
                } else {
                    System.out.println("You may draw!!!");
                }
            } else if (optimalMove == 1) {
                if (turn == 6) {
                    System.out.println("Ahaaa! You won!!!");
                } else {
                    System.out.println("Congrats Buddy!!!! you have a chance of winning.");
                }
            } else if (optimalMove == -1) {
                if (turn == 6) {
                    System.out.println("Ohh! You lost!!!");
                } else {
                    System.out.println("Sorry Man! you may loose.");
                }
            }

            turn++;
        }
    }
}
