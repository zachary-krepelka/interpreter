// FILENAME: Node.java
// AUTHOR: Zachary Krepelka
// DATE: Wednesday, August 31, 2022
// CLASS: Introduction to Data Structures
// PROJECT: Lisp Interpreter

import java.math.BigInteger;
import java.util.Scanner;

public class Node {

	private final static int

		NULL = 0,
		NUMBER = 1,
		BOOLEAN = 2,
		LIST = 3,
		FUNC = 4;

	private final static boolean

		OPEN = true,
		CLOSE = false;

	private int        type;
	private BigInteger number;
	private boolean    bool;
	private Node       car, cdr;
	private Func       func;

	/*****/

	public Node() {

		type = NULL;

	} // constructor

	public Node(BigInteger number) {

		type = NUMBER;
		this.number = number;

	} // constructor

	public Node(boolean bool) {

		type = BOOLEAN;
		this.bool = bool;

	} // constructor

	public Node(Node car, Node cdr) {

		type = LIST;
		this.car = car;
		this.cdr = cdr;

	} // constructor

	/*****/

	public Node(Scanner scnr, String context) throws LispError {

		type = NULL;
		Interpreter.consumeParenthesis(CLOSE, scnr, context);

	} // constructor

	public Node(

		BigInteger number,
		Scanner scnr,
		String context

	) throws LispError {

		type = NUMBER;
		this.number = number;
		Interpreter.consumeParenthesis(CLOSE, scnr, context);

	} // constructor

	public Node(

		boolean bool,
		Scanner scnr,
		String context

	) throws LispError {

		type = BOOLEAN;
		this.bool = bool;
		Interpreter.consumeParenthesis(CLOSE, scnr, context);

	} // constructor

	public Node(

		Node car,
		Node cdr,
		Scanner scnr,
		String context

	) throws LispError {

		type = LIST;
		this.car = car;
		this.cdr = cdr;
		Interpreter.consumeParenthesis(CLOSE, scnr, context);

	} // constructor

	public Node(Func func) {

		type = FUNC;
		this.func = func;

	} // constructor

	// getters

	public int        getType()       {return type             ;}
	public String     getTypeString() {return typeString(type) ;}
	public BigInteger getNumber()     {return number           ;}
	public boolean    getBoolean()    {return bool             ;}
	public Node       getCar()        {return car              ;}
	public Node       getCdr()        {return cdr              ;}
	public Func       getFunc()       {return func             ;}

	private static String typeString(int type) {

		switch(type) {

			case NULL    :return "null"     ;
			case NUMBER  :return "number"   ;
			case BOOLEAN :return "boolean"  ;
			case LIST    :return "list"     ;
			case FUNC    :return "function" ;
			default      :return "unkowwn"  ;

		} //switch

	} // method

	// assertions

	public Node assertType(int type, String context) throws LispError {

		if (this.type == type)

			return this;

		else

			throw new LispError(context, typeString(type), this);

	} // method

} // class
