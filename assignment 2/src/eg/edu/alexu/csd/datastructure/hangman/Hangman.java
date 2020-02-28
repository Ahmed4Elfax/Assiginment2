package eg.edu.alexu.csd.datastructure.hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Hangman implements IHangman {
	
	String []keys;
public	static int max_wrongs;
public	static int w;
public  static String secret;
private String back;
private char[] displayer;
public static int  currentWrong;
final char alphapet []= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
final char alphapet2 []= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	public String[] ReadFromFile(String[] words) {
		FileReader fr;
		try {
			fr = new FileReader("dictionary.txt");
			BufferedReader in = new BufferedReader(fr);
			try {
				String instring = null;
				int i=0;
				while ((instring = in.readLine()) != null) {
				words [i]=instring;
				i++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return words;
	}

	@Override
	public void setDictionary(String[] words) {
		words = ReadFromFile(words);
		keys=words;
		}
	

	@Override
	public String selectRandomSecretWord() {
		
		Random r=new Random();
		int y=0 ;
		for (String l :keys) {
			y++;
			if (l==null) {break;}
		}
	    w=r.nextInt(y);
	    secret = keys[w];
	   displayer = new char[secret.length()];
        for (int j = 0; j < secret.length(); j++) {
            displayer[j] = '-';
        }
	  
	   return secret;
		
	}
	public int check(char c) {
		int x=0;
		for (int i =0;i<26;i++) {
			if(c==alphapet[i]) {
				x=1;
				break;
			}
		}
		for (int i =0;i<26;i++) {
			if(c==alphapet2[i]) {
				x=1;
				break;
			}
		}
		return x;
	} 

	@Override
	public String guess(char c) throws Exception {
		back = new String(displayer);
		int x =check(c);
		if ( x==0 ) {
			throw new Exception("buggy word");
		}
		else {
		if (c == ' ') {
			
			return back;	
		}
		else {
			int j, flag = 0;

	        for (j = 0; j < secret.length(); j++) {
	            if (secret.charAt(j) == Character.toLowerCase(c)
	              || secret.charAt(j) == Character.toUpperCase(c)) {
	                displayer[j] = secret.charAt(j);
	                flag = 1; }
	               }
	        if (flag == 0) {
                currentWrong++;
            }
	        back = new String(displayer);
			return back;
		}}
	}
	public void gaming () {
		Scanner s =new Scanner(System.in);
		String[]words=new String[10000];
		setDictionary(words);
		int max=10;
		char c;
		String intial_word=" ";
	    String secret_word=null;
		
	     secret_word=selectRandomSecretWord();
		setMaxWrongGuesses(10);
	    while (currentWrong < max) {
	    	System.out.println("enter character!!");
	    	c=s.next().charAt(0);
	    	 try {
	    	intial_word=guess(c);}
	 catch(Exception r) {
		 System.out.println("exception");
	 }
	 System.out.println("The word:"+ intial_word );
	   
	    	
	   if(currentWrong ==max) {
		   System.out.println("you lose");
	   }
	   if (intial_word == secret_word) {
	    		System.out.println("you won!!\nthe word is"+secret_word);
	    		break;
	   }
	}}

	@Override
	public void setMaxWrongGuesses(int max) {
		max_wrongs=max;
	}

}
