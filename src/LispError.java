// FILENAME: LispError.java
// AUTHOR: Zachary Krepelka
// DATE: Monday, January 2, 2023
// CLASS: Introduction to Data Structures
// PROJECT: Lisp Interpreter

public class LispError extends Exception {

	private final static String heading = "\n\tERROR:\n\t";

	private static String context(String operator) {

		return
			"An error occured under the \"" +
			operator + "\" operator:\n\t";

	} // method

	public LispError(String message) { // one parameter

		super(Interpreter.ansi.colorize(

			ANSI.FOREGROUND,
			ANSI.BRIGHT,
			ANSI.RED,

			heading + message + "\n"

		));

	} // constructor

	public LispError(String operator, String message) { // two parameters

		super(Interpreter.ansi.colorize(

			ANSI.FOREGROUND,
			ANSI.BRIGHT,
			ANSI.RED,

			heading + context(operator) + message + "\n"

		));

	} // constructor (overloaded)

	public LispError( // three parameters

			String operator,
			String expected,
			Node received
	) {
		super(Interpreter.ansi.colorize(

			ANSI.FOREGROUND,
			ANSI.BRIGHT,
			ANSI.RED,

			heading                  +
			context(operator)        +
			"Expected a "            +
			expected                 +
			" but received a "       +
			received.getTypeString() +
			".\n"

		));

	} // constructor (overloaded x2)

} // class
