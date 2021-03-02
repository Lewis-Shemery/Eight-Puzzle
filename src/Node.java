/**
 *
 * @author Lewis Shemery
 */
public class Node {
    
    String state;
    int fScore = 0; // gScore + heuristic
    int gScore = 0; // distance from start
    int heuristic = 0; // manhatan distance from a number in its current state to its goal state
    int zPos = 0; // index of zero in starting state
    Node parent; // used to count moves
    
    public Node(String state){
        this.state = state;
        this.zPos = state.indexOf('0');
    }
}
