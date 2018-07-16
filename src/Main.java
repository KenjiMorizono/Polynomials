import java.util.Scanner;
import java.io.*;
/* Doubly Linked List with no tail reference to represent a given polynomial
 * Kenji Morizono MSU Denver Fall 2017 CS 2050
 * 
 */

public class Main {
	private static Scanner userInput = new Scanner(System.in);
	private static StringBuffer logToFileUserIn = new StringBuffer();
	private static StringBuffer logToFileFileIn = new StringBuffer();
	private static final String outputHeading = "Kenji Morizono MSU Denver Fall 2017 CS 2050\n";
	private static final String outputFileNameUser = "userInLog.txt";
	private static final String outputFileNameFile = "fileInLog.txt";
	
	public static void exit(){
		try{
			BufferedWriter userInFileOut =
			new BufferedWriter(new FileWriter(new File(outputFileNameUser)));
			userInFileOut.write(logToFileUserIn.toString());
			userInFileOut.flush();
			userInFileOut.close();
			
		}
		catch (IOException IOError){	
			IOError.printStackTrace();
			
		}
		try {
			BufferedWriter fileInFileOut = 
			new BufferedWriter(new FileWriter(new File(outputFileNameFile)));
			fileInFileOut.write(logToFileFileIn.toString());
			fileInFileOut.flush();
			fileInFileOut.close();
			
		}
		catch (IOException IOError){
			IOError.printStackTrace();
			
		}
		
		userInput.close();
		
	}
	
	public static void userPolynomialMenu(){
		int userChoice = 0;
		int userIntInput = 0;
		boolean validInput = false;
		Polynomial poly1 = new Polynomial();
		
		poly1.userInToList(userInput, logToFileUserIn);
		do {
			logToFileUserIn.append("Original ");
			System.out.print("Original ");
			poly1.printList(logToFileUserIn);
			System.out.println("User Polynomial Menu");
			System.out.println("1: Input another polynomial and add the two polynomials");
			System.out.println("2: Input a value for x and evaluate the expression");
			System.out.println("0: Exit");
			System.out.println();
			System.out.println("Please note that operations here will not modify your original polynomial");
			System.out.println("Please choose an option: ");
			do {
				if (userInput.hasNextInt()){
					userChoice = userInput.nextInt();
					if (userChoice <= 2 && userChoice >= 0){
						validInput = true;
					}
					userInput.nextLine();
				}
				else {
					System.out.println("Please enter a valid input");
					validInput = false;
					userInput.nextLine();
				}
				
				
			} while (!validInput);
			logToFileUserIn.append("User choice for user polynomial menu: " + userChoice);
			logToFileUserIn.append(System.lineSeparator());
			
			switch (userChoice){
			case 0: // Exit user polynomial Menu
				
				break;
			case 1: // Input another polynomial from user and add the current one
				Polynomial poly2 = new Polynomial();
				Polynomial addResult = new Polynomial();
				
				poly2.userInToList(userInput, logToFileUserIn);
				addResult = addResult.addTwoPolynomials(poly1, poly2);
				System.out.print("Resulting ");
				addResult.printList(logToFileUserIn);
				System.out.println();
				break;
			case 2: // Evaluate polynomial given user input x
				int result = 0;
				do {
					System.out.println("Please enter a value for x to be evaluated");
					if (userInput.hasNextInt()){
						userIntInput = userInput.nextInt();
						userInput.nextLine();
						validInput = true;
					}
					else {
						System.out.println("Please enter a valid input");
						validInput = false;
						userInput.nextLine();
						
					}
					
					
				} while (!validInput);
				
				result = poly1.calculateValue(userIntInput);
				System.out.println("Result when x = " + userIntInput + ": " + result);
				break;
			
			}
			
			
		} while (userChoice != 0);
		
	}
	
	public static void filePolynomial(){
		Polynomial filePoly1 = new Polynomial();
		Polynomial filePoly2 = new Polynomial();
		Polynomial addResult = new Polynomial();
		String userFileName = "";
		String fileInstruction = "";
		int fileXVal = 0;
		int evalResult = 0;
		
		System.out.println("Creating polynomial from file input...");
		System.out.println("Please input the file name");
		userFileName = userInput.nextLine();
		logToFileFileIn.append("User file name: " + userFileName);
		logToFileFileIn.append(System.lineSeparator());
		
		try {
			BufferedReader parseFile = new BufferedReader(new FileReader(userFileName));
			fileInstruction = parseFile.readLine();
			while (fileInstruction != null){
				logToFileFileIn.append("Read Instruction: " + fileInstruction);
				logToFileFileIn.append(System.lineSeparator());
				if (fileInstruction.contains("add")){
					fileInstruction = parseFile.readLine();
					logToFileFileIn.append("Read Polynomial 1: " + fileInstruction);
					logToFileFileIn.append(System.lineSeparator());
					
					filePoly1.fileInstructionToList(fileInstruction);
					filePoly1.printList(logToFileFileIn);
					
					fileInstruction = parseFile.readLine();
					logToFileFileIn.append("Read Polynomial 2: " + fileInstruction);
					logToFileFileIn.append(System.lineSeparator());
					
					filePoly2.fileInstructionToList(fileInstruction);
					filePoly2.printList(logToFileFileIn);
					logToFileFileIn.append(System.lineSeparator());
					
					addResult = addResult.addTwoPolynomials(filePoly1, filePoly2);
					logToFileFileIn.append("Polynomial Add ");
					addResult.printList(logToFileFileIn);
					
				}
				else if (fileInstruction.contains("evaluate")){
					fileInstruction = parseFile.readLine();
					logToFileFileIn.append("Read Polynomial: " + fileInstruction);
					logToFileFileIn.append(System.lineSeparator());
					
					filePoly1.fileInstructionToList(fileInstruction);
					filePoly1.printList(logToFileFileIn);
					
					fileXVal = Integer.parseInt(parseFile.readLine());
					logToFileFileIn.append("Read X value: " + fileXVal);
					logToFileFileIn.append(System.lineSeparator());
					logToFileFileIn.append(System.lineSeparator());
					
					evalResult = filePoly1.calculateValue(fileXVal);
					logToFileFileIn.append("Calculation Result: " + evalResult);
					logToFileFileIn.append(System.lineSeparator());
					
				}
				filePoly1 = new Polynomial();
				filePoly2 = new Polynomial();
				fileInstruction = parseFile.readLine();
				logToFileFileIn.append(System.lineSeparator());
			}
			
		}
		catch (FileNotFoundException noFile){
			noFile.printStackTrace();
			
		}
		catch (IOException IOError){
			IOError.printStackTrace();
			
		}
		
	}
	
	public static void mainMenu(){
		int userChoice = 0;
		boolean validInput = false;
		do {
			System.out.println("-MENU-");
			System.out.println("1: Create polynomial from user input");
			System.out.println("2: Create polynomial from file");
			System.out.println("0: Exit");
			System.out.println();
			System.out.println("Please choose an option: ");
			do {
				if (userInput.hasNextInt()){
					userChoice = userInput.nextInt();
					if (userChoice <= 2 && userChoice >= 0){
						validInput = true;
					}
					userInput.nextLine();
				}
				else {
					System.out.println("Please enter a valid input");
					validInput = false;
					userInput.nextLine();
				}
				
				
			} while (!validInput);
			logToFileUserIn.append("User choice for main menu: " + userChoice);
			logToFileUserIn.append(System.lineSeparator());
			
			switch(userChoice){
			case 0: // Exit the program after writing to log
				exit();
				break;
				
			case 1: // Create polynomial from user input
				userPolynomialMenu();
				
				break;
				
			case 2: //Create polynomial from file input
				filePolynomial();
				System.out.println("Done.");
				exit();
				userChoice = 0;
				break;
			
			}
			
		} while (userChoice != 0);
		
	}
	
	public static void main(String[] args) {
		logToFileUserIn.append(outputHeading);
		logToFileUserIn.append(System.lineSeparator());
		logToFileFileIn.append(outputHeading);
		logToFileFileIn.append(System.lineSeparator());
		
		mainMenu();
		
	}

}
