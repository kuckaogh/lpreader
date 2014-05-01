package gwreader.element;

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

import gwreader.grammar.GWreaderLexer;
import gwreader.grammar.GWreaderParser;




public class GWreader {
	

		public static void main(String[] args) throws IOException {

			String fp="CVnode_simple.dat";
			ANTLRInputStream input = new ANTLRFileStream(fp);
			GWreaderLexer lexer = new GWreaderLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			GWreaderParser parser = new GWreaderParser(tokens);
			parser.setBuildParseTree(true);
			RuleContext tree = parser.gwnode();
			//tree.inspect(parser); // show in gui
			//tree.save(parser, "/tmp/R.ps"); // Generate postscript
			System.out.println(tree.toStringTree(parser));
			
			System.out.println(parser.numberOfPoints);
			System.out.println(parser.ratio);
			

		}

}
	
