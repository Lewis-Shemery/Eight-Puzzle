 /**
  * 
  * This program will solve 8-puzzle using one of the search algorithms listed below.
  * 
 * @author Lewis Shemery
 * updated: 3/22/2017
 */

public class EightPuzzle {

    // START is the 3x3 8-puzzle board with the initial state 
    // it's expressed from left to right, top to bottom as a String
    final static private String START = "621458307";
    final static private String GOAL = "123456780";
    
    public static void main(String[] args) {
        
        /* creates a starting node with an initial state to pass into SearchType
            - AStar();
            - IDAStar();
            - DFs();
            - BFS();
        */
        SearchType search = new SearchType(new Node(START), GOAL);
        search.IDAStar(); // choose a serch type among the different options and run here
    }
   
}

//options for valid starting states
//621458307
//568041237
//265741803
//143076258
//672483501
//413057628
//182375406
//867254301