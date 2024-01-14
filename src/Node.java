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

	private int        type;
	private BigInteger number;
	private boolean    bool;
	private Node       car, cdr;
	private Func       func;

	public Node() {

		type = NULL;

	} // constructor

	public Node(Scanner scnr) throws LispError {

		type = NULL;
		Interpreter.adv(scnr);

	} // constructor

	public Node(BigInteger number) {

		type = NUMBER;
		this.number = number;

	} // constructor

	public Node(BigInteger number, Scanner scnr) throws LispError {

		type = NUMBER;
		this.number = number;
		Interpreter.adv(scnr);

	} // constructor

	public Node(boolean bool) {

		type = BOOLEAN;
		this.bool = bool;

	} // constructor

	public Node(boolean bool, Scanner scnr) throws LispError {

		type = BOOLEAN;
		this.bool = bool;
		Interpreter.adv(scnr);

	} // constructor

	public Node(Node car, Node cdr) {

		type = LIST;
		this.car = car;
		this.cdr = cdr;

	} // constructor

	public Node(Node car, Node cdr, Scanner scnr) throws LispError {

		type = LIST;
		this.car = car;
		this.cdr = cdr;
		Interpreter.adv(scnr);

	} // constructor

	public Node(Func func) {

		type = FUNC;
		this.func = func;

	} // constructor

	// getters

	public  int         getType()     {return  type;}
	public  BigInteger  getNumber()   {return  number;}
	public  boolean     getBoolean()  {return  bool;}
	public  Node        getCar()      {return  car;}
	public  Node        getCdr()      {return  cdr;}
	public  Func        getFunc()     {return func;}

	public String getTypeString() {

		switch(type) {

			case NULL    :return "null"    ;
			case NUMBER  :return "number"  ;
			case BOOLEAN :return "boolean" ;
			case LIST    :return "list"    ;
			default      :return "unkowwn" ;

		} //switch

	} //method

} //class
