package dsm2reader.element;

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

import dsm2reader.grammar.DRLexer;
import dsm2reader.grammar.DRParser;


public class DSM2Reader {
	

		public static void main(String[] args) throws IOException {

			String fp="D:\\cvwrsm\\trunk\\dsm2_reader\\src\\dsm2reader\\grammar\\t.lp";
			ANTLRInputStream input = new ANTLRFileStream(fp);
			DRLexer lexer = new DRLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			DRParser parser = new DRParser(tokens);
			parser.setBuildParseTree(true);
			RuleContext tree = parser.prog();
			//tree.inspect(parser); // show in gui
			//tree.save(parser, "/tmp/R.ps"); // Generate postscript
			System.out.println(tree.toStringTree(parser));
			
			

		}

}
	
