public class Helper {
	
	/** 
    * Class constructor.
    */
	private Helper () {}

	/**
	* This method is used to check if a number is prime or not
	* @param x A positive integer number
	* @return boolean True if x is prime; Otherwise, false
	*/
	public static boolean isPrime(int x) {
		
		// TODO Add your code here
		 // Corner case 
        if (x <= 1) 
            return false; 
  
        // Check from 2 to n-1 
        for (int i = 2; i < x; i++) 
            if (x % i == 0) 
                return false; 

		return true;
	}

	/**
	* This method is used to get the largest prime factor 
	* @param x A positive integer number
	* @return int The largest prime factor of x
	*/
	public static int getLargestPrimeFactor(int x) {

    	// TODO Add your code here

		// Initialize the maximum prime 
        // factor variable with the 
        // lowest one 
        int maxPrime = -1; 
  
        // Print the number of 2s 
        // that divide n 
        while (x % 2 == 0) { 
            maxPrime = 2; 
  
            // equivalent to n /= 2 
            x >>= 1; 
        } 
  
        // x must be odd at this point, 
        // thus skip the even numbers 
        // and iterate only for odd 
        // integers 
        for (int i = 3; i <= Math.sqrt(x); i += 2) { 
            while (x % i == 0) { 
                maxPrime = i; 
                x = x / i; 
            } 
        } 
  
        // This condition is to handle 
        // the case when n is a prime 
        // number greater than 2 
        if (x > 2) 
            maxPrime = x; 
  
        return maxPrime; 

    }
	
	//returns true if x is even, false if x is odd
	public static boolean isEven(int x) {
		if (x % 2 == 0) {
			return true;
		}
		else return false;
	}
}