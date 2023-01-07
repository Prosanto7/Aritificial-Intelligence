package mazegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeGame {

    public static void printMaze(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void up(String[][] maze) {
        for (int i = 1; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if ((maze[i - 1][j].equals(".") || maze[i - 1][j].equals("X"))
                        && !maze[i-1][j].equals("#")
                        && !maze[i][j].equals("#")
                        && !maze[i][j].equals("*")
                        && !maze[i][j].equals("X") ) {
                    maze[i-1][j] = ".";
                    maze[i][j] = "X";
                } else if (maze[i - 1][j].equals("*")) {
                    maze[i][j] = "X";
                }
            }
        }
    }

    public static void down(String[][] maze) {
        for (int i = maze.length - 2; i >= 0; i--) {
            for (int j = 0; j < maze[i].length; j++) {
                if ((maze[i + 1][j].equals(".") || maze[i + 1][j].equals("X"))
                        && !maze[i + 1][j].equals("#")
                        && !maze[i][j].equals("#")
                        && !maze[i][j].equals("*")
                        && !maze[i][j].equals("X") ) {
                    maze[i + 1][j] = ".";
                    maze[i][j] = "X";
                } else if (maze[i + 1][j].equals("*")) {
                    maze[i][j] = "X";
                }
            }
        }
    }

    public static void right(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = maze[i].length - 2; j >= 0; j--) {
                if ((maze[i][j + 1].equals(".") || maze[i][j + 1].equals("X"))
                        && !maze[i][j + 1].equals("#")
                        && !maze[i][j].equals("#")
                        && !maze[i][j].equals("*")
                        && !maze[i][j].equals("X") ) {
                    maze[i][j + 1] = ".";
                    maze[i][j] = "X";
                } else if (maze[i][j + 1].equals("*")) {
                    maze[i][j] = "X";
                }
            }
        }
    }

    public static void left(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 1; j < maze[i].length; j++) {
                if ((maze[i][j - 1].equals(".") || maze[i][j - 1].equals("X"))
                        && !maze[i][j - 1].equals("#")
                        && !maze[i][j].equals("#")
                        && !maze[i][j].equals("*")
                        && !maze[i][j].equals("X") ) {
                    maze[i][j - 1] = ".";
                    maze[i][j] = "X";
                } else if (maze[i][j - 1].equals("*")) {
                    maze[i][j] = "X";
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner fileInput = null;
        try{
            fileInput = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int row = Integer.parseInt(fileInput.next());
        int column = Integer.parseInt(fileInput.next());

        String maze[][] = new String[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                maze[i][j] = fileInput.next();
            }
        }

        printMaze(maze);

        System.out.println("UP 1");
        up(maze);
        printMaze(maze);

        System.out.println("UP 2");
        up(maze);
        printMaze(maze);

        System.out.println("Right 3");
        right(maze);
        printMaze(maze);

        System.out.println("UP 4");
        up(maze);
        printMaze(maze);

        System.out.println("Right 5");
        right(maze);
        printMaze(maze);

        System.out.println("Right 6");
        right(maze);
        printMaze(maze);

        System.out.println("Right 7");
        right(maze);
        printMaze(maze);

        System.out.println("Down 8");
        down(maze);
        printMaze(maze);

        System.out.println("Down 9");
        down(maze);
        printMaze(maze);

        System.out.println("Right 10");
        right(maze);
        printMaze(maze);

        System.out.println("Left 11");
        left(maze);
        printMaze(maze);

    }
}
