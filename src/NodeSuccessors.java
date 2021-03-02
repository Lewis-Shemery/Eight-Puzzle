import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Lewis Shemery
 */
public class NodeSuccessors {
    public static List<String> getSuccessors(String state, int zPos){
        List<String> successors = new ArrayList<>();
        
        // uses the index of zero to return all valid states after one move.
        // helper method at bottom
        switch(zPos){
                case 0: {
                    successors.add(swap(state,zPos,1));
                    successors.add(swap(state,zPos,3));
                    break;
                }
                case 1: {
                    successors.add(swap(state,zPos,0));
                    successors.add(swap(state,zPos,2));
                    successors.add(swap(state,zPos,4));                
                    break;
                }
                case 2: {
                    successors.add(swap(state,zPos,1));
                    successors.add(swap(state,zPos,5));               
                    break;
                }
                case 3: {
                    successors.add(swap(state,zPos,0));
                    successors.add(swap(state,zPos,4));
                    successors.add(swap(state,zPos,6));                
                    break;
                }
                case 4: {
                    successors.add(swap(state,zPos,1));
                    successors.add(swap(state,zPos,3));
                    successors.add(swap(state,zPos,5));
                    successors.add(swap(state,zPos,7));               
                    break;
                }
                case 5: {
                    successors.add(swap(state,zPos,2));
                    successors.add(swap(state,zPos,4));
                    successors.add(swap(state,zPos,8));              
                    break;
                }
                case 6: {
                    successors.add(swap(state,zPos,3));
                    successors.add(swap(state,zPos,7));              
                    break;
                }
                case 7: {
                    successors.add(swap(state,zPos,4));
                    successors.add(swap(state,zPos,6));
                    successors.add(swap(state,zPos,8));
             
                    break;
                }
                case 8: {
                    successors.add(swap(state,zPos,7));
                    successors.add(swap(state,zPos,5));                                
                    break;
                }
        }
        
        return successors;
    }
    
    // helper method to get successors
    private static String swap (String state, int a, int b) {
      return state.replace(state.charAt(a),'*').replace(state.charAt(b), state.charAt(a)).replace('*', state.charAt(b));
    }    
}