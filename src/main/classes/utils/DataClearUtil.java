package main.classes.utils;

public class DataClearUtil {

	public static String hexToString(String hex)
	{
		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();
	 
		  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
		  for( int i=0; i<hex.length()-1; i+=2 ){
	 
		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal;
		      try
		      {
		    	  decimal = Integer.parseInt(output, 16);
		      }
		      catch (NumberFormatException e)
		      {
		    	  return hex;
		      }
		      //convert the decimal to character
		      sb.append((char)decimal);
	 
		      temp.append(decimal);
		  }
	 
		  return sb.toString();
		
		
	}
	
	public static String stringToHex(String str)
	{
		return str;
		
	}
	
	public static String cleanHTMLTags(String text)
	{
		return text;
		
	}
}
