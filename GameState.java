import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameState {
    private int size;            // The number of stones
    private boolean[] stones;    // Game state: true for available stones, false for taken ones
    private int lastMove;        // The last move
    private int solution;

    /**
     * Class constructor specifying the number of stones.
     */
    public GameState(int size) {

        this.size = size;

        //  For convenience, we use 1-based index, and set 0 to be unavailable
        this.stones = new boolean[this.size + 1];
        this.stones[0] = false;

        // Set default state of stones to available
        for (int i = 1; i <= this.size; ++i) {
            this.stones[i] = true;
        }

        // Set the last move be -1
        this.lastMove = -1;
        
        this.solution = -1;
    }

    /**
     * Copy constructor
     */
    public GameState(GameState other) {
        this.size = other.size;
        this.stones = Arrays.copyOf(other.stones, other.stones.length);
        this.lastMove = other.lastMove;
        this.solution = other.solution;
    }


    /**
     * This method is used to compute a list of legal moves
     *
     * @return This is the list of state's moves
     */
    public List<Integer> getMoves() {
        // TODO Add your code here
    	List<Integer> list = new ArrayList<Integer>();
    	int lastMove = getLastMove();
    	if (numStonesGone() == 0) {
    		if (Helper.isEven(size) == true) {
    			for (int i = 1; i < size/2; i+=2) {
    				list.add(i);	
    			}
    		}
    		if (Helper.isEven(size) == false) {
    			for (int i = 1; i <= size/2; i+=2) {
    				list.add(i);	
    			}
    		}
    	}
    	
    	else {
    		if (this.stones[1] == true) {
    			list.add(1);
    		}
    		for (int i = 2; i <= size; i++) {
    			if (i % lastMove == 0) {
    			if (this.stones[i] == true) {
    			list.add(i);
    			}
    		}
    		}
    		for (int i = 2; i < lastMove; i ++) {
    			if (lastMove % i == 0) {
    				if (this.stones[i] == true) {
    	    			list.add(i);
    	    			}
    			}
    		}
    	}
        return list;
    }


    /**
     * This method is used to generate a list of successors
     * using the getMoves() method
     *
     * @return This is the list of state's successors
     */
    public List<GameState> getSuccessors() {
        return this.getMoves().stream().map(move -> {
            var state = new GameState(this);
            state.removeStone(move);
            return state;
        }).collect(Collectors.toList());
    }


    /**
     * This method is used to evaluate a game state based on
     * the given heuristic function
     *
     * @return int This is the static score of given state
     */
    public double evaluate() {
        // TODO Add your code here
    	List<Integer> moves = getMoves();
    	int numMoves = moves.size();
    	int lastMove = getLastMove();   
    	//System.out.println(lastMove);
    	int count;
    	int largestPrime;
        if (isMax() == true) {
        	if (numMoves == 0) {
        		return -1.0;
        	}
        	if (this.stones[1] == true) {
        		return 0;
        	}
        	if (lastMove == 1) {
        		if (Helper.isEven(numMoves) == true) {
        			return -0.5;
        		}
        		else {
        			return 0.5;
        		}
        	}
        	if (Helper.isPrime(lastMove) == true) {
        		count = 0;
        		for (int i = 1; i <= size; i++) {
        			if (i % lastMove == 0) {
        				count++;
        			}
        			}
        		if (Helper.isEven(count) == true) {
        			return -0.7;
        		}
        		else {
        			return 0.7;
        		}
        		}
        	else {
        		largestPrime = Helper.getLargestPrimeFactor(lastMove);
        		count = 0;
        		for (int i = 1; i <= size; i++) {
        			if (i % largestPrime == 0) {
        				count++;
        			}
        			}
        		if (Helper.isEven(count) == true) {
        			return -0.6;
        		}
        		else {
        			return 0.6;
        		}
        	}
        	}
        else {
        	if (numMoves == 0) {
        		return 1.0;
        	}
        	if (this.stones[1] == true) {
        		return 0;
        	}
        	if (lastMove == 1) {
        		if (Helper.isEven(numMoves) == true) {
        			return 0.5;
        		}
        		else {
        			return -0.5;
        		}
        	}
        	if (Helper.isPrime(lastMove) == true) {
        		count = 0;
        		for (int i = 1; i <= size; i++) {
        			if (i % lastMove == 0) {
        				count++;
        			}
        			}
        		if (Helper.isEven(count) == true) {
        			return 0.7;
        		}
        		else {
        			return -0.7;
        		}
        		}
        	else {
        		largestPrime = Helper.getLargestPrimeFactor(lastMove);
        		count = 0;
        		for (int i = 1; i <= size; i++) {
        			if (i % largestPrime == 0) {
        				count++;
        			}
        			}
        		if (Helper.isEven(count) == true) {
        			return 0.6;
        		}
        		else {
        			return -0.6;
        		}
        	}
        }
    }

    /**
     * This method is used to take a stone out
     *
     * @param idx Index of the taken stone
     */
    public void removeStone(int idx) {
        this.stones[idx] = false;
        this.lastMove = idx;
    }

    /**
     * These are get/set methods for a stone
     *
     * @param idx Index of the taken stone
     */
    public void setStone(int idx) {
        this.stones[idx] = true;
    }

    public boolean getStone(int idx) {
        return this.stones[idx];
    }

    /**
     * These are get/set methods for lastMove variable
     *
     * @param move Index of the taken stone
     */
    public void setLastMove(int move) {
        this.lastMove = move;
    }

    public int getLastMove() {
        return this.lastMove;
    }

    /**
     * This is get method for game size
     *
     * @return int the number of stones
     */
    public int getSize() {
        return this.size;
    }
    
    public int getSolution() {
    	 return this.solution;
    }
    
    public void setSolution(int solution) {
    	this.solution = solution;
    }
    public int numStonesGone() {
    	int count = 0;
    	
        for (int i = 1; i <= this.size; i++) {
        	if(this.stones[i] == false) {
        		count++;
        	}
    }
        return count;
    }
    
    //returns true if max, false if min
    public boolean isMax() {
    	return Helper.isEven(numStonesGone());
    }
    

}	
