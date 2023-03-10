package alphabetapruning;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlphaBetaPruningAlgorithm {
    static int minimum = -1000;
    static int maximum = 1000;

    public static int logBaseB(int base, int value) {
        return (int) Math.ceil(Math.log(value) / Math.log(base));
    }


    public static int alphaBetaPruning(int currentNode, int currentDepth, int targetDepth, boolean playerTurn, List<Integer> leaves, int alpha, int beta) {
        if (currentDepth == targetDepth) {
            return leaves.get(currentNode);
        }

        int best;
        if (playerTurn) {
            best = minimum;
            for (int i = 0; i < 2; i++) {
                int value = alphaBetaPruning((currentNode * 2) + i, currentDepth + 1, targetDepth, false, leaves, alpha, beta);
                best = Math.max(best, value);
                alpha = Math.max(alpha, best);

                if (beta <= alpha) {
                    break;
                }
            }
        } else {
            best = maximum;
            for (int i = 0; i < 2; i++) {
                int value = alphaBetaPruning((currentNode * 2) + i, currentDepth + 1, targetDepth, true, leaves, alpha, beta);
                best = Math.min(best, value);
                beta = Math.min(beta, best);

                if (beta <= alpha) {
                    break;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        List<Integer> leaves = new ArrayList<>();
        Scanner file = null;
        try {
            file = new Scanner(new File("minimax.txt"));
            while (file.hasNext()) {
                leaves.add(Integer.parseInt(file.next()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        int currentNode = 14, currentDepth = 5;
        int targetDepth = logBaseB(2, leaves.size());
        boolean firstPlayerTurn = true;
        System.out.println("The Optimal Solution is : " + alphaBetaPruning(currentNode, currentDepth, targetDepth, firstPlayerTurn, leaves, minimum, maximum));
    }
}
