package exam1.autogen;
import common.Listing;
import java_cup.runtime.*;


%%
%cup
%public

%line
%char
%eofval{
  return makeSymbol(sym.EOF);
%eofval}



%{
  private Symbol makeSymbol(int nSymType) {
    return new Symbol(nSymType, yychar, yychar + yytext().length() - 1);
  }

  private Symbol makeSymbol(int nSymType, Object secondarg) {
    return new Symbol(nSymType, yychar, yychar + yytext().length() - 1,
                        secondarg);
  }
%}


ALPHA=[A-Za-z]
DIGIT=[0-9]
BLANKS=[\ \t\b\015]
NEWLINE=[\n]

%%
"(*"([^)*]|")"|"*""*"*[^)*])*"*""*"*")"
{
	Listing.get().echo(yytext());
}
{NEWLINE}
{
	Listing.get().NewLine(1);
}
{BLANKS}+  
{ 
	Listing.get().echo(yytext());
}
(<!--.*-->)
{ 
	Listing.get().echo(yytext());
}
((<a.*>(.|\n)*.*<\/a>))
{
	Listing.get().echo(yytext());
	return(makeSymbol(sym.link, yytext()));	
}
(<PRE>(.|\n)*<\/PRE>)
{
	Listing.get().echo(yytext());
}

"<"
{

	Listing.get().echo(yytext());
	return(makeSymbol(sym.ltag_o, yytext()));
              
}
">"
{ 
	Listing.get().echo(yytext());
	return(makeSymbol(sym.rtag, yytext()));
              
}
"</"
{ 
	Listing.get().echo(yytext());
	return(makeSymbol(sym.ltag_c, yytext()));
              
}
{ALPHA}+{DIGIT}*\"*\?*\'*\[*\]*\-*
{
Listing.get().echo(yytext());
return (makeSymbol(sym.tag, yytext())); 
}

[\w ]*[0-9:.,()\"\?\'\[\]\-]*
{

Listing.get().echo(yytext());
return (makeSymbol(sym.text, yytext())); 
}

{DIGIT}({DIGIT})* 
{

	Listing.get().echo(yytext());
	return(
          makeSymbol(sym.number, new Integer(Integer.parseInt(yytext())))
              );
}
.
{

	Listing.get().echo(yytext());
	Listing.get().oops("bad input");
}
