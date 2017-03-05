package lab2;
import common.Listing;
import common.OpenFile;
import lab2.autogen.Parser;
import lab2.autogen.Yylex;

public class bup {
   public static void main(String args[]) throws Exception {
      if (args.length < 1) throw new Error("Usage:  java bup file-or-ULR");

      new Listing(System.out);
      Parser ah = new Parser(new Yylex(new OpenFile(args[0])));
      ah.parse();
   }
}
