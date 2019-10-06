/*
 * Matt Jankowski
 * AI (CS 411) Hw 4
 * 15 puzzle BFS - BFS Class
 * To God be the Glory
 */

import java.util.ArrayList;
import java.util.Arrays;

public class IDDFS{

    private int goal[] = new int[]{
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    private long starting_time;
    private double infinity = Double.POSITIVE_INFINITY;
    public static int nodeCount = 0; //total number of nodes created


    public IDDFS(int[] initial_board) {
        for (int limit = 0; limit < infinity; limit++){
            new DLS(initial_board, limit);
        }

    }

    //generate all children of the parent
    //2 min -> if blank is in corner    3 -> if blank is on side    4 max -> if blank within
    //TRANSITION FUNCTION
    public ArrayList<Node> generateChildren(Node parent) {
        Node U = parent.moveUp();
        Node D = parent.moveDown();
        Node L = parent.moveLeft();
        Node R = parent.moveRight();

        ArrayList<Node> children = new ArrayList<>(4);
        //Add the created nodes to the arrayList (as long as they're not null)
        if (U != null) children.add(U);
        if (D != null) children.add(D);
        if (L != null) children.add(L);
        if (R != null) children.add(R);

        return children;
    }

    //print success messages
    public void success(Node solutionNode) {
        printMoves(solutionNode);
        printNumNodes(solutionNode);
        printTime();
        printMemory();
    }

    //check if the board matches goal, if so, return true
    public boolean matchesGoal(Node v) {
        return Arrays.equals(v.getBoard(), goal);
    }

    //calculate and print move sequence
    public void printMoves(Node node) {
        String moves = "";
        while (node.getDirection() != '#') { //while not root node
            moves = Character.toString(node.getDirection()).concat(moves); //add direction of node
            node = node.getParent(); //update
        }
        System.out.println("Moves: " + moves);
    }

    //print how many nodes were expanded (created)
    public void printNumNodes(Node node) {
        System.out.println("Number of Nodes expanded: " + nodeCount);
    }

    //calculate and print the time taken to run program
    public void printTime() {
        long ending_time = System.currentTimeMillis(); //record time at end of run
        float total_seconds = (float) (ending_time - starting_time) / 1000; //get difference between start and end.
        System.out.println("Time Taken: " + total_seconds + " s"); //print result
    }

    //calculate and print runtime of program
    //referenced memory usage documentation at: https://www.vogella.com/tutorials/JavaPerformance/article.html
    public void printMemory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); //garbage collector frees up space
        long memory = runtime.totalMemory() - runtime.freeMemory(); //calculate memory
        System.out.println("Memory Used: " + memory / 1024L + " kB");
    }


}


