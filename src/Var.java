// FILENAME: Var.java
// AUTHOR: Zachary Krepelka
// DATE: Friday, December 30, 2022
// CLASS: Introduction to Data Structures
// PROJECT: Lisp Interpreter

public class Var {

	private String identifier;
	private Node value;

	public Var(String identifier, Node value) {

		this.identifier = identifier;
		this.value = value;

	} // constructor

	// getters
	public String getIdentifier() {return identifier;}
	public Node getValue() {return value;}

} //class
