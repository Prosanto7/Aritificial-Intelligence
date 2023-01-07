package minimax;

import java.util.Scanner;

public class MiniMax {
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

    public static int minimax(int currentNode, int currentDepth, int targetDepth, boolean playerTurn, int[] leaves) {
        if (currentDepth == targetDepth) {
            return leaves[currentNode];
        }

        if (playerTurn) {
            return getMax(new int[] {minimax(currentNode * 2, currentDepth + 1, targetDepth, false, leaves),
                    minimax((currentNode * 2) + 1, currentDepth + 1, targetDepth, false, leaves)});
        } else {
            return getMin(new int[] {minimax(currentNode * 2, currentDepth + 1, targetDepth, true, leaves),
                    minimax((currentNode * 2) + 1, currentDepth + 1, targetDepth, true, leaves)});
        }
    }


    public static void main(String[] args) {
        // Array of size 16 to
        // store leaf values
        int[] leaves = new int[16];

        // Array of size 2 to store moves
        int[] moves = new int[] {6, 4};

        // Index variable to store
        // values in specific position
        int index = 0;

        // Constructing leaf nodes
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        int firstPlayer = moves[i] + moves[k];
                        int secondPlayer = moves[j] + moves[l];

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

        // Printing the leaves
//        for (int i : leaves) {
//            System.out.print( i + " ");
//        }
//        System.out.println();

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

        int optimalMove = minimax(gameMove, 1, targetDepth, false, leaves);

        if (optimalMove == 0) {
            System.out.println("You may draw!!!");
        } else if (optimalMove == 1) {
            System.out.println("Congrats Buddy!!!! you have a chance of winning.");
        } else if (optimalMove == -1) {
            System.out.println("Sorry Man! you may loose.");
        }

        int turn = 2;

        while (turn <= 4) {
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
                break;
            }


            optimalMove = minimax(gameMove, turn, targetDepth, firstPlayerTurn, leaves);
            //System.out.println(playerName + "'s turn -> " + turn + " Move -> " + gameMove + " Optimal Move -> " + optimalMove);

            if (optimalMove == 0) {
                if (turn == 4) {
                    System.out.println("Match drawn!!!");
                } else {
                    System.out.println("You may draw!!!");
                }
            } else if (optimalMove == 1) {
                System.out.println("Congrats Buddy!!!! you have a chance of winning.");
            } else if (optimalMove == -1) {
                System.out.println("Sorry Man! you may loose.");
            }

            turn++;
        }
    }
}
