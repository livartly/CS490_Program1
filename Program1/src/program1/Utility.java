package program1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides static utility functions to the rest of the project.
 */
public class Utility {

	/**
	 * Returns a string representing the current time, accurate to the nanosecond.
	 * (Nanosecond accuracy is restrained by the system.)
	 *
	 * @return a string in the format of MM/dd/YYYY HH:mm:ss:nn (nn = nanoseconds)
	 */
	public static String getCurrentTime () {
		return LocalDateTime.now().format( DateTimeFormatter.ofPattern( "MM/dd/uuuu HH:mm:ss:nn" ) );
	}

	public static int getRandomNumber ( int min, int max ) {
		return ( int ) ( Math.random() * ( max - min ) + min );
	}
}
