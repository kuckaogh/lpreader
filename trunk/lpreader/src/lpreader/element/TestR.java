package lpreader.element;
/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
import lpreader.grammar.RLexer;
import lpreader.grammar.RParser;

import org.antlr.v4.runtime.*;

public class TestR {
	public static void main(String[] args) throws Exception {
		String fp="D:\\cvwrsm\\trunk\\lpreader\\src\\lpreader\\grammar\\t.R";
		ANTLRInputStream input = new ANTLRFileStream(fp);
		RLexer lexer = new RLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		RParser parser = new RParser(tokens);
		parser.setBuildParseTree(true);
		RuleContext tree = parser.prog();
		//tree.inspect(parser); // show in gui
		//tree.save(parser, "/tmp/R.ps"); // Generate postscript
		System.out.println(tree.toStringTree(parser));
	}
}
