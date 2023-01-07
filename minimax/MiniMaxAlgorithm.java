package minimax;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiniMaxAlgorithm {
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

    public static int minimax(int currentNode, int currentDepth, int targetDepth, boolean playerTurn, List<Integer> leaves) {
        if (currentDepth == targetDepth) {
            return leaves.get(currentNode);
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

        int currentNode = 1, currentDepth = 1;
        int targetDepth = logBaseB(2, leaves.size());
        boolean firstPlayerTurn = false;
        System.out.println("The Optimal Solution is : " + minimax(currentNode, currentDepth, targetDepth, firstPlayerTurn, leaves));
    }
}
