package eg.edu.alexu.csd.datastructure.hangman;

import java.util.Scanner;

public class game {
	public static void main (String []args) {
		Scanner p =new Scanner(System.in);
		Hangman g = new Hangman();
		String[]words=new String[10000];
		g.setDictionary(words);
		int max=10;
		char c;
		String intial_word=null;
	    String secret_word=null;
        secret_word=g.selectRandomSecretWord();
	    int wrong=g.currentWrong;
	   
	     while (wrong < max) {
	    	System.out.println("enter character!!");
	    	c=p.next().charAt(0);
	    	
	 try {
	    	intial_word=g.guess(c);}
	 catch(Exception r) {
		 System.out.println("exception");
	 }
	 System.out.println("The word:"+ intial_word );
	  if(g.currentWrong ==max) {
		   System.out.println("you lose");
		   break;
	   }  
	   if (intial_word == secret_word) {
	    		System.out.println("you won!!\nthe word is"+secret_word);
	    		break;
	   }
	    }

		}

}
