import java.util.ArrayList;
import java.util.List;

public class AlphaBetaPruning {
	
	double value;
	int count = 1;
	GameState state1;
	int maxDepth;
	int evaluated = 0;
	ArrayList<Integer> notPruned = new ArrayList<Integer>();
    public AlphaBetaPruning() {
    }

    /**
     * This function will print out the information to the terminal,
     * as specified in the homework description.
     */
    public void printStats() {
        // TODO Add your code here
    	System.out.println("Move: " + state1.getLastMove());
    	System.out.println("Value: " + value);
    	System.out.println("Number of Nodes Visited: " + count);
    	System.out.println("Number of Nodes Evaluated: " + evaluated);
    	System.out.println("Max Depth Reached: " + maxDepth);
    	System.out.println("Avg Effective Branching Factor: ");
    }

    /**
     * This function will start the alpha-beta search
     * @param state This is the current game state
     * @param depth This is the specified search depth
     */
    public void run(GameState state, int depth) {
        // TODO Add your code here
    	boolean maxPlayer = state.isMax();
    	maxDepth = depth;
    	value = alphabeta(state, depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, maxPlayer);
    	
    }

    /**
     * This method is used to implement alpha-beta pruning for both 2 players
     * @param state This is the current game state
     * @param depth Current depth of search
     * @param alpha Current Alpha value
     * @param beta Current Beta value
     * @param maxPlayer True if player is Max Player; Otherwise, false
     * @return int This is the number indicating score of the best next move
     */
    private double alphabeta(GameState state, int depth, double alpha, double beta, boolean maxPlayer) {
        // TODO Add your code here
    	if (depth > maxDepth) {
    		maxDepth = depth;
    	}
    	List<GameState> children = state.getSuccessors();
    	double value;
    	if (depth == 0 || children.size() == 0) {
    		state1 = state;
    		evaluated++;
    		return state.evaluate();
    	}
    	if (maxPlayer == true) {
    		value = Double.NEGATIVE_INFINITY;
    		for (int i = 0; i < children.size(); i++) {
    			count ++;
    			value = Math.max(value, alphabeta(children.get(i), depth - 1, alpha, beta, false));
    			alpha = Math.max(alpha, value);
    			if (alpha >= beta) {
    				
    				break;
    			}
    		}
    		return value;
    	}
    	else {
    		value = Double.POSITIVE_INFINITY;
    		for (int i = 0; i < children.size(); i++) {
    			count++;
    			value = Math.min(value, alphabeta(children.get(i), depth - 1, alpha, beta, true));
    			beta = Math.min(beta, value);
    			if (alpha >= beta) {
    				break;
    			}
    		}
    		return value;
    	}
    }
}
