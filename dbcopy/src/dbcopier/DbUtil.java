package dbcopier;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DbUtil {

	private static ArrayList<String> getTokens(String str)	{
		ArrayList<String> tokens = new ArrayList<String>();
	    StringTokenizer tokenizer = new StringTokenizer(str, ",");
	    while (tokenizer.hasMoreElements()) {
	    	tokens.add(tokenizer.nextToken());
	    }
	    return tokens;
	}
	public static ArrayList<String> getListFromString(String argStr)	{
		String str = argStr.replaceAll("\"", "");
		ArrayList<String> strList = getTokens(str);
	    for (int i=0; i<strList.size(); i++) {
	    	String ss =strList.get(i);
	    	String ss2 = ss.replaceAll("'", "");
	    	if (!ss.equals(strList))	{	
	    		strList.set(i, ss2);
	    	}
	    }
		return(strList);
	}
	public static void main(String args[])  {
		String testStr = "'AdventureWorks2022','AdventureWorksDW2022','AdventureWorksLT2022'"; //.replace("'", "\"");
		ArrayList<String> testList = getListFromString(testStr);
		System.out.println("testStr=<"+testStr+">");
		int i=0;
		for (String ss:testList)	{
			System.out.println("testStr["+(i++)+"] = <"+ss+">");
		}
    }
}
