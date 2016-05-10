package de.l3s.souza.url.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class URLUtils 
{
	private static ArrayList<String> stopwords;
	private static ArrayList<String> vowels;
	
	public void loadStopwords () throws IOException
	{
	   stopwords = new ArrayList<String>();
	   vowels = new ArrayList<String>();
	   vowels.add("a");
       vowels.add("e");
       vowels.add("i");
       vowels.add("o");
       vowels.add("u");
	   File file = new File("stopwords.txt");
	   String line;
	   FileReader fr = null;
	   
	   try {
			fr = new FileReader(file);
	   } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	   }
	    
	   BufferedReader br = new BufferedReader(fr);
	    
	   while ((line = br.readLine()) != null)
	   {
		   stopwords.add(line);
	   }
	}
	
	//Clean the URL, removing stopwords and other unused fields
	public String preProcess (String u) throws MalformedURLException
	{
		String path;
		String final_url = null;
		URL url = new URL (u);
		path = url.getPath();
		path =path.replaceAll("[^\\w\\s]", " ").replaceAll("_", " ");
		StringTokenizer token = new StringTokenizer (path);
		
		while (token.hasMoreElements())
     	{
			String str = token.nextToken();
		
			if (isValidToken(str))
			{		
				if (final_url != null)
					final_url = final_url + " " +str;
				else
					final_url = str;
			}
     	}
		
		if (final_url != null)
			return final_url;
		else
			return path;
	}
	
	public static boolean isValidToken (String str)
	{
		int position = 0;
		String currentChar;
		
		if (isNumber (str) || stopwords.contains(str) || str.length()==1)
			return false;
			while (position < str.length())
			{
				
				try {
					Integer.parseInt(String.valueOf(str.charAt(position)));
					return false;
				} catch (NumberFormatException ex) {
					
					currentChar = String.valueOf(str.charAt(position));
					
					if (vowels.contains(currentChar.toLowerCase()))
						return true;
				}
				position++;
			}
			return false;
	}
	
	public static boolean isNumber (String str)
	{
	
	    try{  
            Integer.parseInt(str);  
            return true;  
        }catch(Exception e){
        try {	
        	Float.parseFloat(str);
        	return true;
        }catch (Exception f) {
        	return false;
        }  
        }  
	
	}
	
}
