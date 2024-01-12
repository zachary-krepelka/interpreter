// FILENAME: Parser.java
// AUTHOR: Zachary Paul Krepelka
// DATE: Thursday, August 17, 2023
// DESCRIPTION: A command-line interface argument parser.

/*

	I wrote this class with the help of an article I found online.

		https://medium.com/ingeniouslysimple/
		      //building-a-cli-parser-from-scratch-a2beba0d9fcb

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class Parser {

	public static ArrayList<String> parse(
		HashMap<
			String, // option
			Consumer<String> // value-assigning function
		> options,
		String[] tokens
	) {
		String prevToken = null;
		Consumer<String> currentCallback = null;
		ArrayList<String> arguments = new ArrayList<>();

		for(String token : tokens) {

			if (options.containsKey(token)) {

				if (currentCallback != null)
					currentCallback.accept(null);

				currentCallback = options.get(token);

			} else if (currentCallback != null) {

				try {
					currentCallback.accept(token);

				} catch (ParserError e) {

					e.print(prevToken, token);

				} //try-catch

				currentCallback = null;

			} else {

				// If a token is not recognized, it's assumed to
				// be an argument.  Error handling is left to
				// the implementing program.

				arguments.add(token);

			} //if

			prevToken = token;

		} //for-each

		if (currentCallback != null)
			currentCallback.accept(null);

		return arguments;

	} //method

	public static int parseInt(String token) {

		try {

			return Integer.parseInt(token);

		} catch (NumberFormatException e) {

			throw new ParserError(ParserError.EXPECTS_NUMBER);

		} //try-catch

	} //method

	public static double parseDouble(String token) {

		try {

			return Double.parseDouble(token);

		} catch (NumberFormatException e) {

			throw new ParserError(ParserError.EXPECTS_FLOAT);

		} //try-catch

	} //method

	public static char parseChar(String token) {

		if (token.length() != 1)
			throw new ParserError(ParserError.EXPECTS_CHARACTER);

		return token.charAt(0);

	} //method

} //class

class ParserError extends RuntimeException {

	private int errorCode;

	public ParserError(int num) {

		super();
		errorCode = num;

	} //constructor

	public static final int

		EXPECTS_NUMBER    = 1,
		EXPECTS_FLOAT     = 2,
		EXPECTS_CHARACTER = 3;

	public void print(String prev, String curr) {

		String expected = null;

		switch(errorCode) {

			case EXPECTS_NUMBER:

				expected = "a whole number";

				break;

			case EXPECTS_FLOAT:

				expected = "a floating-point number";

				break;

			case EXPECTS_CHARACTER:

				expected = "a character";

				break;

		} //switch

		System.err.println(
			"Error: option " + prev + " expects " + expected +
			" but received the token '" + curr + "'.");

	} //method

} //class

/*

 AN EXAMPLE OF AN IMPLEMENTING CLASS:

 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.function.Consumer;

 public class Example {

 	private static int num = 0;
 	private static char chr = '.';
 	private static boolean flg = false;
 	private static String msg = null;

 	public static void main(String[] tokens) {

 		HashMap<String, Consumer<String>> options = new HashMap<>();

 		options.put("-num", x -> { num = Parser.parseInt(x); });
 		options.put("-chr", x -> { chr = Parser.parseChar(x); });
 		options.put("-flg", x -> { flg = true; });
 		options.put("-msg", x -> { msg = x; });

 		ArrayList<String> args = Parser.parse(options, tokens);

 		System.out.println("Number: "     +   num);
 		System.out.println("Character: "  +   chr);
 		System.out.println("Boolean: "    +   flg);
 		System.out.println("String: "     +   msg);
 		System.out.print("Additional arguments: ");

 		if (args.size() == 0)
 			System.out.print("none.");
 		else
 			for (String arg : args)
 				System.out.print(arg + " ");

 		System.out.println();

 	} //main

 } //class
*/
