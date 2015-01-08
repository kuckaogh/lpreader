package lpreader.element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import lpreader.grammar.CplexLpLexer;
import lpreader.grammar.CplexLpParser;


public class CplexLpReader {
	

		public static void main(String[] args) throws IOException {

			String fp="D:\\cvwrsm\\trunk\\wreader\\src\\lpreader\\grammar\\t.lp";
			ANTLRInputStream input = new ANTLRFileStream(fp);
			CplexLpLexer lexer = new CplexLpLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			CplexLpParser parser = new CplexLpParser(tokens);
			parser.setBuildParseTree(true);
			RuleContext tree = parser.prog();
			//tree.inspect(parser); // show in gui
			//tree.save(parser, "/tmp/R.ps"); // Generate postscript
			System.out.println(tree.toStringTree(parser));
			
			

		}

}
	
