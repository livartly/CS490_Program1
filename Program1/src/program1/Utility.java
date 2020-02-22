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
	 * @return a string formatted using {@link #formatDateTime(LocalDateTime datetime)}
	 * @see #formatDateTime(LocalDateTime datetime)
	 */
	public static String getCurrentTimeFormatted () {
		return formatDateTime( getCurrentTime() );
	}

	/**
	 * Gets the current local datetime.
	 *
	 * @return The current local datetime.
	 */
	public static LocalDateTime getCurrentTime () {
		return LocalDateTime.now();
	}

	/**
	 * Formats the given date time as of MM/dd/YYYY HH:mm:ss:nn (nn = nanoseconds).
	 *
	 * @param dateTime the datetime to format.
	 * @return a string with the described format.
	 */
	public static String formatDateTime ( LocalDateTime dateTime ) {
		return dateTime.format( DateTimeFormatter.ofPattern( "MM/dd/uuuu HH:mm:ss:nn" ) );
	}

	/**
	 * Returns a random number within the specified range.
	 *
	 * @param min The minimum random number.
	 * @param max The (exclusive) maximum random number
	 * @return The random number within that range.
	 */
	public static int getRandomNumber ( int min, int max ) {
		return ( int ) ( Math.random() * ( max - min ) + min );
	}
}
