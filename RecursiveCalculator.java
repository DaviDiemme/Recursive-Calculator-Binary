/*
AUTHOR Davide De Marco
Recursive parser and calculator for a simple language
that allows expressions that add binary digits:

<EXP> =  + <DIGIT><EXP> | S<EXP> | <DIGIT>
*/

import java.util.*;


class RecursiveCalculator
{
  public static void main (String[] param)
  {
    String in = input("Please give me an expression");
    int answer = evalEXP(in);
    
    print("The answer is " + answer);
    
    System.exit(0);
  }
  
  /* Recursively evaluates expressions */ 
  public static int evalEXP (String in)
  {
    int result;
    
    // Use the length as a simple way to decide whether its an addition or a digit
    int length = in.length();
    if (length == 1)
        result = DIGIT(in);
    else
        result = ADD(in);

    return result;
  }
 
  public static int DIGIT (String in)
  {
    int result = 0; 
    if ((in.equals("A")) || (in.equals("a")))
    {return result = 10;}
    else if ((in.equals("B")) || (in.equals("b")))
    {return result = 11;}
    else if ((in.equals("C")) || (in.equals("c")))
    {return result = 12;}
    else if ((in.equals("D")) || (in.equals("d")))
    {return result = 13;}
    else if ((in.equals("E")) || (in.equals("e")))
    {return result = 14;}
    else if ((in.equals("F")) || (in.equals("f")))
    {return result = 15;}
    else
    {return result = Integer.parseInt(in);}
  }
  
  /* Recursively evaluates ADD expressions */ 
  public static int ADD(String in)
  {
    String first;
    String rest;
    String second;
    String end;
    int result = 0;
    int length = in.length();
    
    // Split off the first two tokens as they are fixed
    if (length<=3)
   { 
      first = nextToken(in);
      rest = restTokens(in);
      second = nextToken(rest);
      end = in.substring(0);
    
      // Follow the structure of the definition DIGIT + EXP
      arg1 = evalDIGIT(first);  		// DIGIT
      checkOPERATOR(second);  		// +
      arg2 = evalEXP(end); 			// EXP

      result = arg1 + arg2;
   }

    else
   {
      first = nextToken(in);
      rest = restTokens(in);
      second = nextToken(rest);
      end = restTokens(rest);
   }
    
    if ((first.equals("S"))||(first.equals("s")) && (second.length()==1))
   {
      int i = DIGIT(second);
      while (i != 0)
      {
         result = result + i;
         i--;
      }
   }
    else if ((first.equals("S"))||(first.equals("s")))
   { 
      result = ADD(rest); 
   }
    else if (first.equals("+"))
   { 
      result = ADD(second) + ADD(end); 
   }
    else 
   { 
      result = DIGIT(first); 
   }

    return result;
  }

  /* This just checks if the operator is the right symbol so nothing to return
  */
  public static void checkOPERATOR (String in)
  {
     if (! (in.equals("+"))) quit();
  }

  /* A digit is either 0 or 1. Anything else is a mistake.
  */
  public static int evalDIGIT (String in)
  {
    int result = -1;
    
    if (in.equals("0")) result = 0;
    else if (in.equals("1"))  result = 1;
    else quit(); // Something wrong with the input so quit

    return result;
  }
  
  /* Get a String from the keyboard
   */
   public static String input(String message)
   {
      Scanner scanner = new Scanner(System.in);
      System.out.println(message);
      return (scanner.nextLine());
   }
 
   /* General print
   */
   public static void print(String message)
   {
      System.out.println(message);
   }
   
   /* FIrst Character of a string as a string
   */
   public static String nextToken(String in)
   {
      return in.substring(0,1);
   }
  
    /* All but first Character of a string as a string
   */
   public static String restTokens(String in)
   {
      return in.substring(1);
   }
  

 // Something wrong with the input
  public static void quit ()
  {
        print("What you typed isn't an expression in this language");
        System.exit(0);
   }

}