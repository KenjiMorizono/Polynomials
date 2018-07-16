# Polynomials
This program does not have an interface and runs from the command line

Limitations:
  1.	Polynomials are stored in a linked list of Term objects (two classes: Polynomial and Term).  Term objects have two data fields: coefficient and exponent, both integers; all exponents will be positive.  Polynomial will have some methods that create linked list functionality, but not all list operations are needed; include only those used by this program.  Do not use a separate linked list class.
  2.	The list will be non-circular.  Make a decision on whether to use a doubly-linked list and/or a tail reference.  Explain your decisions in the cover letter; why are your choices good for the polynomial project?
  3.	Basic polynomial functions: read a polynomial from the user, read a polynomial from a file, add two polynomials (output to screen and file), evaluate a polynomial (output to screen and file).  Evaluate takes a value for x (the variable), then calculates the polynomial’s value.
  4.	Input / output.  The program will work interactively (good for testing during development), and with files.  All input (user and file) is guaranteed to be correct.  
  5.	Input files.  There will be two final input files, with the same format.  The first is test data from you, and will be four add operations and four evaluate operations, in any order.  The second file will be from me (posted on Moodle), with an unknown number of add and evaluate operations.  I’ll do additional testing on submitted projects.
  6.	Each operation takes three lines in the file: 
    a.	Add has the word “add” (line #1), then the two polynomials in normal form (one per line).  
    b.	Evaluate has the word “evaluate” (line #1), followed by the polynomial (in normal form) to evaluate (line #2), followed by the x value (line #3).  
  7.	Output log file.  This file will record the operations you used for final testing (taken from the input file) and the results.  It starts with a heading, which includes your name.  Then, for each operation, it gives the operation (add or evaluate), the polynomial(s) used, the x value (for evaluate), and the result (new polynomial for the add operation, final value for evaluate).  Put a blank line between each operation’s data.  Be sure to check this file to validate your answers.  
  8.	Methods in the polynomial class.
    a.	add two polynomials: some terms may be missing, polynomials may be different lengths
    b.	read polynomial from file: prompt user for filename; assumptions: the file exists and is readable, the polynomials are correctly formatted
    c.	read polynomial from user, assumption: perfect user 
    d.	print polynomial – coefficients of 1 do not print, terms with a zero coefficient do not print
    e.	evaluate polynomial – x values will be integers
  9.	Polynomial syntax, normal form.
    a.	polynomials are ordered from high-order term to low-order term
    b.	coefficients of one are not displayed
        x^2  not  1x^2
    c.	terms with zero coefficients are not stored or displayed 
        0x^3 
    d.	superscripts – use carets (^)
    e.	white space – exactly one space between terms and plus signs (input and output), no spaces in terms
    i.	example: 25x^3 + 6x + 2
  10.	Further specifications.  
    a.	main is in a separate driver class
    b.	coefficients are positive integers
    c.	exponents are positive integers
    d.	no subtraction in the polynomial
  11.	Development.  Use incremental development: first create a design for program structure, then create a stubbed form of the program.  Continue by creating and testing one operation at a time.  Comment out the stubs once the methods are implemented.
