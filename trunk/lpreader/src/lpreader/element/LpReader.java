package lpreader.element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import lpreader.grammar.LpExtendLexer;
import lpreader.grammar.LpExtendParser;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.testng.Assert;
import org.testng.annotations.Test;




public class LpReader {
	
	private static CharStream stream;	
	public static Map<String, String> configMap;
	private static String[] configKeyList;
	private static Map<String , String> configKeyMap;


	public LpReader(String[] configKeyListInput){
		
		configKeyList = configKeyListInput;		
		configKeyMap = new HashMap<String , String>();
		
		for (String key: configKeyList) {
			configKeyMap.put(key.toLowerCase(), key);
		
		}

	}
	
	public static Map<String, String> newConfigMap(){
		
		return new LinkedHashMap<String, String>();
	}
	
	//@Test( groups = { "config" } )
	public static void parseFile(String inputFilePath) throws RecognitionException  {		

		//String inputFilePath = "D:\\cvwrsm\\trunk\\wrims2_scripting\\studies\\callite_svn47\\CONV\\Run\\CONV.config";
		try {
			stream = new ANTLRFileStream(inputFilePath, "UTF8");
			}
	    catch(Exception e) {
	         //e.printStackTrace();
	         System.out.println("Config file not found! "+ inputFilePath);
	         
	        }
		    
		LpExtendLexer lexer = new LpExtendLexer(stream);
		TokenStream tokenStream = new CommonTokenStream(lexer);		

		LpExtendParser parser = new LpExtendParser(tokenStream);
		
		
//		
//		parser.configKeyMap = ConfigReader.configKeyMap;	
//		parser.configFile();
//		
//		
//		configMap = parser.configMap;
		
		//return configMap;
		

	}	


}
	
