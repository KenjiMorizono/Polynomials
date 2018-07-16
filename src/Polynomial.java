import java.util.Scanner;
import java.io.*;
import java.lang.Math;

/* Doubly Linked List with no tail reference to represent a given polynomial
 * Kenji Morizono MSU Denver Fall 2017 CS 2050
 * 
 */
public class Polynomial {
	Term head;
	
	public Polynomial(){
		head = null;
		
	}
	
	public void userInToList(Scanner userIn, StringBuffer logger){
		String userInput;
		String [] splitPlus;
		
		System.out.println("Please enter your polynomial, use ^ denote an exponent: ");
		userInput = userIn.nextLine();
		logger.append("User Input Polynomial: " + userInput);
		logger.append(System.lineSeparator());
		logger.append(System.lineSeparator());
		
		
		splitPlus = userInput.split("[\\+\\s+]+");
			
		Term current = new Term();
		Term tempTerm = new Term();
		// Start creating linked list and
		// Set each node.hasVar to true if an expression related to the node has x in it
		
		for (int i = 0; i < splitPlus.length; i++){ 
			if (i == 0){ 
				if (splitPlus[i].contains("x")){
					tempTerm.hasVar = true;
					
				}
				current = tempTerm;
				head = tempTerm;
				tempTerm = new Term();
				
			}
			else {
				if (splitPlus[i].contains("x")){
					tempTerm.hasVar = true;
					
				}
				tempTerm.previous = current;
				current.next = tempTerm;
				current = current.next;
				tempTerm = new Term();
				
			}
		}
		//Creates list of polynomials
		
		current = this.head;
		for (int i = 0; i < splitPlus.length; i++){
			if (current == null){
				
				break;
			}
			if (current.hasVar){
				if (splitPlus[i].indexOf("x") == 0){
					current.coefficient = 1;
					if (splitPlus[i].contains("^")){
						current.exponent = Integer.parseInt(splitPlus[i].substring(splitPlus[i].indexOf("^") + 1, splitPlus[i].length()));
						
					}
					current = current.next;
				}
				else {
					current.coefficient = Integer.parseInt(splitPlus[i].substring(0, splitPlus[i].indexOf("x")));
					if (splitPlus[i].contains("^")){
						current.exponent = Integer.parseInt(splitPlus[i].substring(splitPlus[i].indexOf("^") + 1, splitPlus[i].length()));
						
					}
					else {
						current.exponent = 1;
						
					}
					current = current.next;
				}
				
			}
			else {
				current.coefficient = Integer.parseInt(splitPlus[i]);
				current = current.next;
			}

		}
		//Get rid of anything with a coefficient of 0
		this.removeZeros();
		
		
	}
	
	public void fileInstructionToList(String polynomial){ //TODO
		String [] splitPlus;
		
		splitPlus = polynomial.split("[\\+\\s+]+");
			
		Term current = new Term();
		Term tempTerm = new Term();
		// Start creating linked list and
		// Set each node.hasVar to true if an expression related to the node has x in it
		
		for (int i = 0; i < splitPlus.length; i++){ 
			if (i == 0){ 
				if (splitPlus[i].contains("x")){
					tempTerm.hasVar = true;
					
				}
				current = tempTerm;
				head = tempTerm;
				tempTerm = new Term();
				
			}
			else {
				if (splitPlus[i].contains("x")){
					tempTerm.hasVar = true;
					
				}
				tempTerm.previous = current;
				current.next = tempTerm;
				current = current.next;
				tempTerm = new Term();
				
			}
		}
		//Creates list of polynomials
		
		current = this.head;
		for (int i = 0; i < splitPlus.length; i++){
			if (current == null){
				
				break;
			}
			if (current.hasVar){
				if (splitPlus[i].indexOf("x") == 0){
					current.coefficient = 1;
					if (splitPlus[i].contains("^")){
						current.exponent = Integer.parseInt(splitPlus[i].substring(splitPlus[i].indexOf("^") + 1, splitPlus[i].length()));
						
					}
					current = current.next;
				}
				else {
					current.coefficient = Integer.parseInt(splitPlus[i].substring(0, splitPlus[i].indexOf("x")));
					if (splitPlus[i].contains("^")){
						current.exponent = Integer.parseInt(splitPlus[i].substring(splitPlus[i].indexOf("^") + 1, splitPlus[i].length()));
						
					}
					else {
						current.exponent = 1;
						
					}
					current = current.next;
				}
				
			}
			else {
				current.coefficient = Integer.parseInt(splitPlus[i]);
				current = current.next;
			}

		}
		//Get rid of anything with a coefficient of 0
		this.removeZeros();
		
	}
	
	public void printList(StringBuffer logger){
		String logText = "";
		Term current = new Term();
		current = head;
		System.out.print("Polynomial:");
		if (head == null){
			System.out.print(" EMPTY");
			
		}
		else {
			while (current != null){		
				if (current.hasVar){
					if (current.coefficient > 1){
						System.out.print(" " + current.coefficient + "x");
						logText += (" " + current.coefficient + "x");	
						
					}
					else {
						System.out.print(" x");
						logText += (" x");
					}
					if (current.exponent > 1){
						System.out.print("^" + current.exponent);
						logText += ("^" + current.exponent);
					}
					if (current.next != null){
						System.out.print(" +");
						logText += (" +");
					}
				}
				else {
					System.out.print(" " + current.coefficient);
					logText += (" " + current.coefficient);
					
				}
			current = current.next;
			}
			
			
		}
		logger.append("Result: " + logText);
		logger.append(System.lineSeparator());
		System.out.println();
	}
	
	public int calculateValue(int x){
		int total = 0;
		Term current = new Term();
		current = this.head;
		
		while (current != null){
			if (current.hasVar){
				total += (current.coefficient * (Math.pow(x, current.exponent)));
				
			}
			else {
				total += current.coefficient;
				
			}
			current = current.next;
		}
		
		return total;
	}
	
	public Polynomial addTwoPolynomials(Polynomial a, Polynomial b){ 
		Polynomial tempList = new Polynomial();
		Term currentA = new Term();
		Term currentB = new Term();
		Term addTerm = new Term();
		currentA = a.head;
		currentB = b.head;
		
		if (currentA == null){ // Polynomial a is empty
			tempList = b;
			
			return tempList;
		}
		else if (currentB == null){ // Polynomial b is empty
			tempList = a;
			
			return tempList;
		}
		else { //Both lists have at least 1 term in them
			while (currentA != null){
				while (currentB != null){
					if (currentA.hasVar && currentB.hasVar){
						if (currentA.exponent > currentB.exponent){
							tempList.addTermToList(currentA);
							currentA = currentA.next;
							
							
						}
						else if (currentA.exponent == currentB.exponent){
							addTerm.coefficient = (currentA.coefficient + currentB.coefficient);
							addTerm.exponent = currentA.exponent;
							addTerm.hasVar = true;
							tempList.addTermToList(addTerm);
							addTerm = new Term();
							currentA = currentA.next;
							currentB = currentB.next;
						}
						else {
							tempList.addTermToList(currentB);
							currentB = currentB.next;
							
						}
						
					}
					else if (currentA.hasVar && !(currentB.hasVar)){
						tempList.addTermToList(currentA);
						currentA = currentA.next;
						
					}
					else if (!(currentA.hasVar) && currentB.hasVar){
						tempList.addTermToList(currentB);
						currentB = currentB.next;
						
					}
					else {
						addTerm.coefficient = (currentA.coefficient + currentB.coefficient);
						addTerm.hasVar = false;
						tempList.addTermToList(addTerm);
						currentA = currentA.next;
						currentB = currentB.next;
						addTerm = new Term();
						
					}	
					if (currentA == null){
						while (currentB != null){
							tempList.addTermToList(currentB);
							currentB = currentB.next;
							
						}
						
					}
				}
				if (currentB == null){
					while (currentA != null){
						tempList.addTermToList(currentA);
						currentA = currentA.next;
						
					}
					
				}
				
				
			}				
		}
		return tempList;
	}
	
	public void removeZeros(){
		Term current = new Term();		
		current = head;
		while (current != null){
			if (head.coefficient == 0){
				head = head.next;
				if (head == null){
					
					break;
				}
				head.previous = null;
				current = head;
				
			}
			else {
				if (current.coefficient == 0){
					current.previous.next = current.next;
					current = current.next;
				}
				else {
					
					current = current.next;
				}
			}
			
		}
		
		
		
	}
	
	public void addTermToList(Term termToAdd){
		Term current = new Term();
		Term tempNode = new Term();
		tempNode.coefficient = termToAdd.coefficient;
		tempNode.exponent = termToAdd.exponent;
		tempNode.hasVar = termToAdd.hasVar;

		
		current = this.head;
		if (current == null){
			this.head = tempNode;
			
		}
		else {
			while (current.next != null){
				current = current.next;
			}
			tempNode.previous = current;
			current.next = tempNode;
			
		}
		
		
		
	}
	
	private class Term{
		int coefficient;
		int exponent;
		boolean hasVar;
		Term next;
		Term previous;
		
		public Term(){
			coefficient = 1;
			exponent = 1;
			hasVar = false;
			next = null;
			previous = null;
			
		}
		
		
	}
	
}