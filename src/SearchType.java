
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 *
 * Holds the available search algorithms that can be run in EightPuzzle.java.
 * 
 * @author Lewis Shemery
 * 
 */
public class SearchType {

    public Node startNode;
    public String goalState;

    public SearchType(Node start, String goal) {
        this.startNode = start;
        this.goalState = goal;

    }

    //variable used to keep track of performance
    private int createdStates = 0;
    
    /**
     * 
     * 
     * 
     * 
     */
    
    public void AStar() {        
        // HashSet to store visited nodes so on creation of new nodes, there aren't duplicates.
        Set<String> closedSet = new HashSet<>();
        // PriorityQueue openSet
        PriorityQueue<Node> openSet = new PriorityQueue<>(
                Comparator.comparingInt(node -> node.fScore)
        );

        openSet.add(startNode); // adding the starting Node to the queue

        Node current;
        Node successor;

        while (!openSet.isEmpty()) {
            current = openSet.poll(); // .poll() retrieves and removes the head of the priorty queue
            if (current.state.equals(goalState)) { // if Node reaches the goalState
                printMoves(current);
                return;
            }
            // storing all possible states after one move.
            List<String> nodeSuccessors = NodeSuccessors.getSuccessors(current.state, current.zPos);

            // loop through states. If it hasn't been visited before, add to the closed set
            // and create a new Node with that state.
            for (String state : nodeSuccessors) {
                createdStates++;
                if (closedSet.contains(state)) {
                    continue;
                }
                closedSet.add(state);
                successor = new Node(state);
                calculateCost(successor, current);
                if (!openSet.contains(successor)) {
                    successor.parent = current;
                    openSet.add(successor);
                }
            }
        }
        System.out.println("No solution.");
    }
    
    /**
     * 
     * 
     * 
     * 
     */
    
    public void IDAStar() {
        boolean run = true;
        calculateCost(startNode, startNode); // setting the initial threshold as the fScore of the starting node
        int threshold = startNode.fScore;
        
        startNode.fScore = 0;
        startNode.heuristic = 0;
        startNode.gScore = 0;
        
        while(run){
            
            Node node = IDSearch(startNode, threshold);
            if (node.state.equals(goalState)){ // solved?
                printMoves(node);
                run = false;
            }
            if (node.fScore > 100){ // is the fscore ridiculously high?
                run = false;
            }
            threshold = node.fScore;
        }            
    }

    /**
     * The following functions are utility functions for the Node class. 
     * - IDSearch() - calculateCost() - countMoves()
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * @return
     */
    private Node IDSearch(Node node, int threshold) {
        if (node.state.equals(goalState)) // solved?
            return node;
        if (node.fScore > threshold) // have we gone past our threshold?
            return node;
        
        Node minNode = new Node("");
        minNode.fScore = 100;
        
        // holds all states that are one move away from current state
        List<String> nodeSuccessors = NodeSuccessors.getSuccessors(node.state, node.zPos); 

        for (String state : nodeSuccessors) {
            createdStates++;

            Node successor = new Node(state);
            successor.parent = node;
            calculateCost(successor, node);
            Node testNode = IDSearch(successor, threshold); // recursive call that expands nodes as deep as threshold allows
            if (testNode.state.equals(goalState)){ // solved?
                return testNode;
            }
            if (testNode.fScore < minNode.fScore){ // finds the node with lowest fscore over the threshold and returns it
                minNode = testNode;
            }
        }
        return minNode;
    }

    // finding the indexes of like numbers and calculating the numbers of moves it would take
    // to go from the current state to the goalState defined above.
    private void calculateCost(Node successor, Node current) {
        for (int i = 0; i < successor.state.length(); i++) {
            for (int j = 0; j < goalState.length(); j++) {
                if (successor.state.charAt(i) == goalState.charAt(j) && successor.state.charAt(i) != '0') {
                    successor.heuristic += ((Math.abs(i % 3 - j % 3)) + Math.abs(i / 3 - j / 3)); // manhattan distance              
                }
            }
        }
        successor.gScore = current.gScore + 1;
        successor.fScore =  successor.heuristic + successor.gScore;
    }

    // backtracks through nodes to count moves
    // prints the state of each node in the linked list
    private void printMoves(Node current) {
        int counter = 0;
        while (current.parent != null) {
            counter++;
            int i = 0;
            while (i < 9) {
                for (int j = 0; j < 3; j++) {
                    System.out.printf("%s ", current.state.charAt(i));
                    i++;
                }
                System.out.println();
            }
            System.out.println();

            current = current.parent;
        }
        
        System.out.println("Success!");
        System.out.println("Moves: " + counter);
        System.out.println("Calculated states: " + createdStates);
    }
}
