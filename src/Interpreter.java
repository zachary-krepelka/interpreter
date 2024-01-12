// FILENAME: Interpreter.java
// AUTHOR: Zachary Krepelka
// DATE: Wednesday, August 31, 2022
// CLASS: Introduction to Data Structures
// PROJECT: Lisp Interpreter

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class Interpreter {

	private final static int

		NULL = 0,
		NUMBER = 1,
		BOOLEAN = 2,
		LIST = 3;

	private static int

		depth = 1,
		lvlOnePar = 0,
		parentheses = 0;

	private static boolean

		repl = false,
		crashOnError = true,
		developerMode = false;

	private static String expr, tree, tabs;

	public static ANSI ansi = new ANSI(false);
	private static Scanner keyboard = new Scanner(System.in);

	private static ArrayList<Func> functionSymbolTable = new ArrayList<>();
	private static ArrayList<Var>  variableSymbolTable = new ArrayList<>();

/*******************************************************************************

			      CAR-RELATED METHODS

*******************************************************************************/

	private static Node readCar(Scanner scnr) throws LispError {

		String token = adv(scnr);

		switch(token) {

			case "#t": return new Node(true);

			case "#f": return new Node(false);

			case ")":  return null;

			case "(":

				 Node car = readCar(scnr);

				return
					car == null ? new Node() :
					new Node(car, readCdr(scnr));

			default:

				if (isInt(token))
					return new Node(new BigInteger(token));

				throw new LispError("readCar", "Unknown Token");

		} // switch

	} // method

	private static void printCar(Node node) {

		switch (node.getType()) {

		case NUMBER:

			expr += node.getNumber().toString() + " ";

			break;

		case BOOLEAN:

			expr += "#" + (node.getBoolean() ? "t" : "f") + " ";

			break;

		case LIST:

			expr += "( ";
			printCar(node.getCar());
			printCdr(node.getCdr());

			break;

		case NULL:

			expr += "( ) ";

		} // switch

	} // method

	private static void treeCar(Node node) {

		switch (node.getType()) {

		case NUMBER:

			tree += node.getNumber().toString() + " ";

			break;

		case BOOLEAN:

			tree += "\\#" + (node.getBoolean() ? "t" : "f") + " ";

			break;

		case LIST:

			tree += "[.L ";
			treeCar(node.getCar());
			treeCdr(node.getCdr());

			break;

		case NULL:

			tree += "N ";

		} // switch

	} // method

/*******************************************************************************

			      CDR-RELATED METHODS

*******************************************************************************/

	private static Node readCdr(Scanner scnr) throws LispError {

		Node car = readCar(scnr);

		return car == null ? new Node() : new Node(car, readCdr(scnr));

	} // method

	private static void printCdr(Node node) {

		if (node.getType() == NULL) {

			expr += ") ";

		} else {

			printCar(node.getCar());
			printCdr(node.getCdr());

		} // if

	} // method

	private static void treeCdr(Node node) {

		if (node.getType() == NULL) {

			tree += "N ] ";

		} else {

			tree += "[.L ";
			treeCar(node.getCar());
			treeCdr(node.getCdr());
			tree += "] ";

		} // if

	} // method

/*******************************************************************************

			      STRING ACCUMULATORS

*******************************************************************************/

	private static String toSexpr(Node data) {

		expr = "";
		printCar(data);
		return expr;

	} // method

	private static String toTree(Node data) {

		tree = "";
		treeCar(data);

		if (data.getType() != LIST)

			tree = "[." + tree + "]";

		String expr = toSexpr(data);

		return

			"\\documentclass{standalone}" +"\n"+
			"\\usepackage{qtree}"         +"\n"+
			"\\begin{document}"           +"\n"+
			"% " + expr                   +"\n"+
			"\\Tree " + tree              +"\n"+
			"\\end{document}"             +"\n";

	} // method

/*******************************************************************************

				EVALUATE METHOD

*******************************************************************************/

	private static Node eval(Scanner scnr) throws LispError {

		String token = adv(scnr);

		boolean precededByOpenParenthesis = token.equals("(");

		if (precededByOpenParenthesis)
			token = adv(scnr);

		switch (token) {

			case "car"      :return car          (scnr); // Part 2
			case "cdr"      :return cdr          (scnr);
			case "null?"    :return isNull       (scnr);
			case "atom?"    :return isAtom       (scnr);
			case "read"     :return read         (scnr);
			case "print"    :return print        (scnr);
			case "="        :return equal        (scnr, false);
			case "equal?"   :return equal        (scnr,  true);
			case "list-ref" :return listRef      (scnr); // Part 3
			case "length"   :return length       (scnr);
			case "cons"     :return cons         (scnr);
			case "append"   :return append       (scnr);
			case "list"     :return list         (scnr);
			case "list?"    :return isList       (scnr);
			case "boolean?" :return isBoolean    (scnr);
			case "number?"  :return isNumber     (scnr);
			case "zero?"    :return isZero       (scnr);
			case "even?"    :return isEven       (scnr);
			case "odd?"     :return isOdd        (scnr);
			case "and"      :return and          (scnr);
			case "or"       :return or           (scnr);
			case "not"      :return not          (scnr);
			case "*"        :return multiply     (scnr);
			case "+"        :return add          (scnr);
			case "-"        :return subtract     (scnr);
			case "/"        :return divide       (scnr);
			case "%"        :return reduce       (scnr);
			case "^"        :return exponentiate (scnr);
			case "copy"     :return copy         (scnr);
			case ")"        :return null               ;
			case "#t"       :return new Node     (true); // Part 4
			case "#f"       :return new Node     (false);
			case "<"        :return lessThan     (scnr);
			case ">"        :return greaterThan  (scnr);
			case "<="       :return leq          (scnr);
			case ">="       :return geq          (scnr);
			case "!="       :return notEqual     (scnr);
			case "cond"     :return cond         (scnr);
			case "'"        :return quote        (scnr); // Part 5
			case "define"   :return define       (scnr);
			case "assert"   :return myAssert     (scnr); // Part 6
			case "tree"     :return tree         (scnr);
			case "memory"   :return memory       (scnr);
			case "reset"    :return reset        (scnr);

			case "exit": System.exit(0);

		default:

			// Part 4 Again

			if (isInt(token))

				return new Node(new BigInteger(token));

			// Part 5 Again

			if (precededByOpenParenthesis) {

				// It's not a keyword, so it must be a function.

				// Sequential Search on function symbol table

				for (
					int i = 0;
					i < functionSymbolTable.size();
					i++
				)
					if (
						functionSymbolTable.
						get(i).
						getName().
						equals(token)
					)
						return evalFunc(i, scnr);
			} else {

				// It's not a keyword, and it's not preceded by
				// an open parenthesis, so it must be a
				// variable.

				// Sequential Search on variable symbol table

				for (
					int i = 0;
					i < variableSymbolTable.size();
					i++
				)
					if (
						variableSymbolTable.
						get(i).
						getIdentifier().
						equals(token)
					)
						return
							variableSymbolTable.
							get(i).
							getValue();
			} // if

			throw new LispError("Unkown token: " + token + ".");

		} // switch

	} // method

/*******************************************************************************

			     THE ADVANCEMENT METHOD

*******************************************************************************/

	public static String adv(Scanner scnr) throws LispError {

		// This method advances the scanner to the next token.
		// It also collects statistics and enables comments.

		String token = scnr.next();

		 // This allows for the use of inline comments.

		while (token.startsWith(";")) {

			scnr.nextLine();
			token = scnr.next();

		} // while

		 // This allows for the use of prologue comments.

		// It also gives us a nice little 'do nothing' keyword: #|#

		if (token.startsWith("#|")) {

			while (!token.endsWith("|#"))

				token = scnr.next();

			token = scnr.next();

		} // if

		// Now we collect some statistics.

		if (token.equals("(")) {

			parentheses++;

			if (depth == 1)
				lvlOnePar++;

		} // if

		if (token.equals(")")) {

			parentheses--;

			if (depth == 1)
				lvlOnePar--;

		} // if

		if (developerMode) {

			tabs =
				"\t".repeat(
					parentheses - 1 +
					(token.equals(")") ? 1 : 0));

			// TODO: maybe write this to a log file instead.

			System.out.print(

				tabs        +
				token       +
				" ["        +
				parentheses +
				", "        +
				depth       +
				"]\n"
			);

		} // if

		return token;

	} // method

/*******************************************************************************

			       METHODS FOR PART 2

*******************************************************************************/

	private static Node car(Scanner scnr) throws LispError {

		Node arg = eval(scnr);

		if (arg.getType() != LIST)

			throw new LispError("car", "list", arg);

		adv(scnr);

		return arg.getCar();

	} // method

	private static Node cdr(Scanner scnr) throws LispError {

		Node arg = eval(scnr);

		if (arg.getType() != LIST)

			throw new LispError("cdr", "list", arg);

		adv(scnr);

		return arg.getCdr();

	} // method

	private static Node isNull(Scanner scnr) throws LispError {

		return new Node(eval(scnr).getType() == NULL, scnr);

	} // method

	private static Node isAtom(Scanner scnr) throws LispError {

		int type = eval(scnr).getType();

		return new Node(type == BOOLEAN || type == NUMBER, scnr);

		// What should I do about NULL,
		// which is considered both a list and an atom?

	} // method

	private static Node read(Scanner scnr) throws LispError {

		if (developerMode)
			System.out.print(tabs);

		System.out.print(">>> ");

		Node node = readCar(keyboard);

		// Consume end-of-line
		keyboard.nextLine();

		adv(scnr);

		return node;

	} // method

	private static Node print(Scanner scnr) throws LispError {

		Node arg = eval(scnr); adv(scnr);

		if (repl) System.out.println();

		System.out.println(toSexpr(arg));

		return arg;

	} // method

	private static Node equal(

		Scanner scnr, boolean recursive

	) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr); adv(scnr);

		if (!recursive && arg1.getType() == LIST)

			throw new LispError("=", "atom", arg1);

		if (!recursive && arg2.getType() == LIST)

			throw new LispError("=", "atom", arg2);

		return new Node(equalHelper(arg1, arg2));

	} //method

	private static boolean equalHelper(

		Node arg1, Node arg2

	) throws LispError {

		switch(arg1.getType()) {

			case NULL:

				return arg2.getType() == NULL;

			case NUMBER:

				return arg2.getType() == NUMBER &&
				arg1.getNumber().equals(arg2.getNumber());

			case BOOLEAN:

				return arg2.getType() == BOOLEAN &&
				arg1.getBoolean() == arg2.getBoolean();

			case LIST:

				return
					arg2.getType() == LIST &&
					equalHelper(
						arg1.getCar(),
						arg2.getCar()
					) &&
					equalHelper(
						arg1.getCdr(),
						arg2.getCdr()
					);

			default: throw new LispError("Unkown type.");

		} // switch

	} //method

/*******************************************************************************

			       METHODS FOR PART 3

*******************************************************************************/

	private static Node listRef(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr);

		if (arg1.getType() != LIST && arg1.getType() != NULL)

			throw new LispError("list-ref", "list", arg1);

		if (arg2.getType() != NUMBER)

			throw new LispError("list-ref", "number", arg2);

		/// We switch from BigInteger to primitive int.

		int index = arg2.getNumber().intValue();

		if (index < 0)

			throw new LispError("list-ref",

				"The index is too small.");

		int count = 0;
		adv(scnr);

		do {
			if (count == index)
				return arg1.getCar();

			arg1 = arg1.getCdr();

			count++;

		} while (arg1.getType() != NULL);

			throw new LispError("list-ref",

				"The index is too large.");

	} // method

	private static Node length(Scanner scnr) throws LispError {

		Node node = eval(scnr); adv(scnr);

		if (node.getType() == NULL)
			return new Node(BigInteger.ZERO);

		if (node.getType() != LIST)
			throw new LispError("length", "list", node);

		int count = 0;

		do {

			node = node.getCdr();
			count++;

		} while(node.getType() != NULL);

		return new Node(BigInteger.valueOf(count));

	} // method

	private static Node cons(Scanner scnr) throws LispError {

		return new Node(eval(scnr), eval(scnr), scnr);

	} // method

	private static Node append(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr);

		if (arg1.getType() != LIST && arg1.getType() != NULL)

			throw new LispError("append", "list", arg1);

		if (arg2.getType() != LIST && arg2.getType() != NULL)

			throw new LispError("append", "list", arg2);

		Node node = appendHelper(arg1, arg2); adv(scnr);

		return node;

	} // method

	private static Node appendHelper(Node node1, Node node2) {

		return
			node1.getType() == NULL ?  node2 :
			new Node(
				node1.getCar(),
				appendHelper(node1.getCdr(), node2));

	} // method

	private static Node list(Scanner scnr) throws LispError {

		Node node = eval(scnr);

		return node == null ? new Node() : new Node(node, list(scnr));

	} // method

		// What should I do about NULL,
		// which is considered both a list and an atom?

	private static Node isList(Scanner scnr) throws LispError {

		Node arg = eval(scnr);

		return new Node(
			arg.getType() == LIST ||
			arg.getType() == NULL, scnr);

	} // method

	private static Node isBoolean(Scanner scnr) throws LispError {

		return new Node(eval(scnr).getType() == BOOLEAN, scnr);

	} // method

	private static Node isNumber(Scanner scnr) throws LispError {

		return new Node(eval(scnr).getType() == NUMBER, scnr);

	} // method

	private static Node isZero(Scanner scnr) throws LispError {

		Node arg = eval(scnr);

		if (arg.getType() != NUMBER)
			throw new LispError("zero?", "number", arg);

		return new Node(arg.getNumber().equals(BigInteger.ZERO), scnr);

	} // method

	private static Node isEven(Scanner scnr) throws LispError {

		Node arg = eval(scnr);

		if (arg.getType() != NUMBER)
			throw new LispError("even?", "number", arg);

		return new Node(!arg.getNumber().testBit(0), scnr);

	} // method

	private static Node isOdd(Scanner scnr) throws LispError {

		Node arg = eval(scnr);

		if (arg.getType() != NUMBER)
			throw new LispError("odd?", "number", arg);

		return new Node(arg.getNumber().testBit(0), scnr);

	} // method

	private static Node and(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr);

		if (arg1.getType() != BOOLEAN)
			throw new LispError("and", "boolean", arg1);

		if (arg2.getType() != BOOLEAN)
			throw new LispError("and", "boolean", arg2);

		return new Node(arg1.getBoolean() & arg2.getBoolean(), scnr);

		// no short curcuit

	} // method

	private static Node or(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr);

		if (arg1.getType() != BOOLEAN)
			throw new LispError("or", "boolean", arg1);

		if (arg2.getType() != BOOLEAN)
			throw new LispError("or", "boolean", arg2);

		return new Node(arg1.getBoolean() | arg2.getBoolean(), scnr);

		// no short curcuit

	} // method

	private static Node not(Scanner scnr) throws LispError {

		Node arg = eval(scnr);

		if (arg.getType() != BOOLEAN)
			throw new LispError("not", "boolean", arg);

		return new Node(!arg.getBoolean(), scnr);

	} // method

	private static Node multiply(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr);

		if (arg1.getType() != NUMBER)
			throw new LispError("*", "number", arg1);

		if (arg2.getType() != NUMBER)
			throw new LispError("*", "number", arg2);

		return new Node(
			arg1.getNumber().multiply(arg2.getNumber()), scnr);

	} // method

	private static Node add(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr);

		if (arg1.getType() != NUMBER)
			throw new LispError("+", "number", arg1);

		if (arg2.getType() != NUMBER)
			throw new LispError("+", "number", arg2);

		return new Node(arg1.getNumber().add(arg2.getNumber()), scnr);

	} // method

	private static Node subtract(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr);

		if (arg1.getType() != NUMBER)
			throw new LispError("-", "number", arg1);

		if (arg2.getType() != NUMBER)
			throw new LispError("-", "number", arg2);

		return new Node(
			arg1.getNumber().subtract(arg2.getNumber()), scnr);

	} // method

	private static Node divide(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr);

		if (arg1.getType() != NUMBER)
			throw new LispError("/", "number", arg1);

		if (arg2.getType() != NUMBER)
			throw new LispError("/", "number", arg2);

		// Integer Division
		return new Node(
			arg1.getNumber().divide(arg2.getNumber()), scnr);

	} // method

	private static Node reduce(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr);

		if (arg1.getType() != NUMBER)
			throw new LispError("%", "number", arg1);

		if (arg2.getType() != NUMBER)
			throw new LispError("%", "number", arg2);

		return new Node(arg1.getNumber().mod(arg2.getNumber()), scnr);

	} // method

	private static Node exponentiate(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr); adv(scnr);

		if (arg1.getType() != NUMBER)
			throw new LispError("^", "number", arg1);

		if (arg2.getType() != NUMBER)
			throw new LispError("^", "number", arg2);

		return new Node(
			arg1.getNumber().pow(arg2.getNumber().intValue()));

	} // method

	private static Node copy(Scanner scnr) throws LispError {

		Node node = eval(scnr); adv(scnr);

		return copyHelper(node);

	} // method

	private static Node copyHelper(Node node) throws LispError {

		switch(node.getType()) {

			case NULL:

				return new Node();

			case NUMBER:

				return new Node(node.getNumber());

			case BOOLEAN:

				return new Node(node.getBoolean());

			case LIST:

				return new Node(
					copyHelper(node.getCar()),
					copyHelper(node.getCdr()));

			default: throw new LispError("Unkown type.");

		} // switch

	} // method

/*******************************************************************************

			       METHODS FOR PART 4

*******************************************************************************/

	private static Node lessThan(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr); adv(scnr);

		if (arg1.getType() != NUMBER)
			throw new LispError("<", "number", arg1);

		if (arg2.getType() != NUMBER)
			throw new LispError("<", "number", arg2);

		return new Node(
			arg1.getNumber().compareTo(arg2.getNumber()) == -1);

	} // method

	private static Node greaterThan(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr); adv(scnr);

		if (arg1.getType() != NUMBER)
			throw new LispError(">", "number", arg1);

		if (arg2.getType() != NUMBER)
			throw new LispError(">", "number", arg2);

		return new Node(
			arg1.getNumber().compareTo(arg2.getNumber()) == 1);

	} // method

	private static Node leq(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr); adv(scnr);

		if (arg1.getType() != NUMBER)
			throw new LispError("<=", "number", arg1);

		if (arg2.getType() != NUMBER)
			throw new LispError("<=", "number", arg2);

		return new Node(
			arg1.getNumber().compareTo(arg2.getNumber()) <= 0);

	} // method

	private static Node geq(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr); adv(scnr);

		if (arg1.getType() != NUMBER)
			throw new LispError(">=", "number", arg1);

		if (arg2.getType() != NUMBER)
			throw new LispError(">=", "number", arg2);

		return new Node(
			arg1.getNumber().compareTo(arg2.getNumber()) >= 0);

	} // method

	private static Node notEqual(Scanner scnr) throws LispError {

		Node arg1 = eval(scnr), arg2 = eval(scnr); adv(scnr);

		if (arg1.getType() == LIST)

			throw new LispError("!=", "atom", arg1);

		if (arg2.getType() == LIST)
			throw new LispError("!=", "atom", arg2);

		return new Node(!equalHelper(arg1, arg2));

	} // method

	private static Node cond(Scanner scnr) throws LispError {

		Node condition = null, consequent = null;
		boolean ignore = false;
		int end = parentheses - 1;

		while(true) {

			adv(scnr); // Consume parenteses

			if (parentheses == end) break;

			if (!ignore) {

				condition = eval(scnr);

				if (condition.getType() != BOOLEAN)

					throw new LispError(

						"cond", "boolean", condition);

			} else {

				int initial = parentheses;

				if (developerMode)
					System.out.print(
						tabs + "beginConditionSkip\n");

				do {adv(scnr);} while (parentheses > initial);

				if (developerMode)
					System.out.print(
						tabs + "endConditionSkip\n");

			} // if

			if (!ignore & condition.getBoolean()) {

				consequent = eval(scnr);
				ignore = true;

			} else {

				int initial = parentheses;

				if (developerMode)
					System.out.print(
						tabs + "beginConsequentSkip\n");

				do {adv(scnr);} while (parentheses > initial);

				if (developerMode)
					System.out.print(
						tabs + "endConsequentSkip\n");

			} // if

			adv(scnr); // Consume closing parenthesis

		} // while

		if (!ignore)

			throw new LispError("cond",

				"All boolean conditions were false.");

		return consequent;

	} // method

/*******************************************************************************

			       METHODS FOR PART 5

*******************************************************************************/

	private static Node quote(Scanner scnr) throws LispError {

		String MLC = ""; // MultiList Literal Constant

		int initial = parentheses;

		do { MLC += adv(scnr) + " ";} while (parentheses > initial);

		return readCar(new Scanner(MLC));

	} // method

	/* The above method allows for comments within multilist literal
	 * constants.  So for instance, we have as a valid statement
	 *
	 *	( print
	 *		' (
	 *			;1
	 *			2 ; two
	 *			;3
	 *			4 ; four
	 *			;5
	 *			6 ; six
	 *			;7
	 *			8 ; eight
	 *			;9
	 *		)
	 *	)
	 *
	 * This would return ( 2 4 6 8 ).
	 */

	private static Node evalFunc(int index, Scanner scnr) throws LispError {

		// We look up the function in the symbol table.

		Func func = functionSymbolTable.get(index);

		// We bind the actual parameters to the formal parameters.

		for (String parameter : func.getParameters())

			variableSymbolTable.
				add(0, new Var(parameter, eval(scnr)));

		// We evaluate the function body.

		depth++;

		Node result = eval(new Scanner(func.getBody()));

		depth--;

		// We pop the variables from the stack.

		for (int i = 0; i < func.getParameters().size(); i++)

			variableSymbolTable.remove(0);

		// We read the closing parenthesis.

		adv(scnr);

		return result;

	} // method

	private static Node define(Scanner scnr) throws LispError {


		String identifier = adv(scnr);

		if (!isAlphabeticOnly(identifier))

			throw new LispError("define",

				"Invalide name: alphabetic characters only.");

		String str = "";

		int initial = parentheses;

		do {
			str += adv(scnr) + " ";

		} while (parentheses > initial || str.equals("' "));

		adv(scnr);

		if (!str.contains("lambda")) {

			Node value = eval(new Scanner(str));

			variableSymbolTable.add(new Var(identifier, value));

			return value;

		} // if

		Scanner subScnr = new Scanner(str);

		subScnr.next(); // (
		subScnr.next(); // lambda
		subScnr.next(); // (

			// Could be better.
			// Lambda should have its own treatment.

		ArrayList<String> parameters = new ArrayList<String>();

		do {
			String parameter = subScnr.next();

			if (parameter.equals(")")) break;

			if (!isAlphabeticOnly(parameter)) {

				subScnr.close();

				throw new LispError("define",
				"Invalide name: alphabetic characters only.");
			}

			parameters.add(parameter);

		} while(true);

		String body = "";

		int count = 0;

		do {
			String token = subScnr.next();

			if (token.equals("(")) count++;

			if (token.equals(")")) count--;

			body += token + " ";

		} while (count > 0);

		functionSymbolTable.add(new Func(identifier, parameters, body));

		subScnr.close();

		return new Node();

	} // method

/*******************************************************************************

			     MY EDITS A YEAR LATER

*******************************************************************************/

private static Node myAssert(Scanner scnr) throws LispError {

	Node condition = eval(scnr);

	if (condition.getType() != BOOLEAN)

		throw new LispError("cond", "boolean", condition);

	if (!condition.getBoolean())

		throw new LispError("Assertion failed.");

	return new Node(true, scnr);

} // method

private static void recover(Scanner scnr) throws LispError {

	// This methods tries to recover from an error.

	depth = 1;

	parentheses = -100; // left-justify tokens in developer's mode

	// read over remaining part of current expression

	while (lvlOnePar > 0) adv(scnr);

	parentheses = 0;

} // method

private static Node tree(Scanner scnr) throws LispError {

	Node arg = eval(scnr); adv(scnr);

	if (repl) System.out.println();

	System.out.println(toTree(arg));

	return arg;

} // method

private static Node memory(Scanner scnr) throws LispError {

	if (repl) System.out.println();

	for (Func function : functionSymbolTable)
		System.out.println(function.getName());

	for (Var variable : variableSymbolTable)
		System.out.println(variable.getIdentifier());

	adv(scnr); return new Node();

} // method

private static Node reset(Scanner scnr) throws LispError {

	adv(scnr);

	functionSymbolTable.clear();
	variableSymbolTable.clear();

	parentheses = 0;
	depth = 1;
	lvlOnePar = 0;

	return new Node();

} // method

private static String accentuate(String str) { // for the REPL

	str = "\n\t" + ansi.brightBlueFG(str);

	if (developerMode)
		str += ansi.yellowFG(" Parentheses: " + parentheses);

	return str + "\n";

} // method

private static void repl() {

	System.out.println("Welcome to Zach's Lisp Interpreter.\n");

	String prompt = ansi.brightGreenFG("zli> ");

	//   zli  -->   Zach's Lisp Interpreter

	while(true) {

		System.out.print(prompt);

		Scanner cmd = new Scanner(keyboard.nextLine());

		try {

			System.out.println(accentuate(toSexpr(eval(cmd))));

		} catch(LispError e) {

			System.out.println(e.getMessage());

		} // try-catch

	}  // while

} // method

/*******************************************************************************

				TESTING METHODS

*******************************************************************************/

	private static boolean isInt(String str) {

		if (str == null) return false;

		if (str.length() == 0) return false;

		if (str.charAt(0) == '-') str = str.substring(1);

		for (int i = 0; i < str.length(); i++)

			if (48 > str.charAt(i) || str.charAt(i) > 57)

				return false;

		return true;

	} // method

	private static boolean isAlphabeticOnly(String str) {

		if (str == null) return false;

		if (str.length() == 0) return false;

		str = str.toLowerCase();

		for (int i = 0; i < str.length(); i++)

			if (
				( 97 > str.charAt(i) ||
				str.charAt(i) > 122) &&
				str.charAt(i) != 45 // hyphen
			)

				return false;
		return true;

	} // method

/*******************************************************************************

				  MAIN METHOD

*******************************************************************************/

	public static void main(String[] tokens) throws LispError {

		HashMap<String, Consumer<String>> options = new HashMap<>();

		options.put(
			"--log",
			x -> {developerMode = true;}
		);

		options.put(
			"--recover",
			x -> {crashOnError = false;}
		);

		options.put(
			"--color",
			x -> {ansi.turnOnLocally();}
		);

		ArrayList<String> args = Parser.parse(options, tokens);

		repl = args.isEmpty(); // read--eval--print loop

		if (repl) repl(); else {

			Scanner sourceCode = null;

			try {
				File file = new File(args.get(0));
				sourceCode = new Scanner(file);

			} catch (FileNotFoundException e) {

				System.err.println(
					ansi.brightGreenFG(
						"File not found."));
				System.exit(1);

			} // try-catch

			while (sourceCode.hasNext()) {

				try {

					eval(sourceCode);

				} catch(LispError e) {

					System.out.println(e.getMessage());

					if (crashOnError) break;

					recover(sourceCode);

				} // try-catch

			} // while

			sourceCode.close();

		} // if

	} // main

} // class
