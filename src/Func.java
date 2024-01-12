// FILENAME: Func.java
// AUTHOR: Zachary Krepelka
// DATE: Friday, December 30, 2022
// CLASS: Introduction to Data Structures
// PROJECT: Lisp Interpreter

import java.util.ArrayList;

public class Func {

	private String name, body;

	private ArrayList<String> parameters;

	public Func(
		String name,
		ArrayList<String> parameters,
		String body
	) {
		this.name = name;
		this.parameters = parameters;
		this.body = body;

	} // constructor

	public void print() { // Bebugging only

		System.out.println("Name: " + name);

		System.out.print("Parameters: ");

		for ( String parameter : parameters )

			System.out.print(parameter + " ");

		System.out.println("\nBody: " + body);

	} //method

	// getters
	public String            getName()       {return name;}
	public String            getBody()       {return body;}
	public ArrayList<String> getParameters() {return parameters;}

} //class
