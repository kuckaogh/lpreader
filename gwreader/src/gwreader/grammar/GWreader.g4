
grammar GWreader;
@header {
package gwreader.grammar;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
}

gwnode : nd factor list+ EOF;
nd:  INT NOTE;
factor:  number NOTE;

list: INT number number;


number :  
        ( INT | FLOAT )
       ;


INT :   Digit+  ;

FLOAT:  Digit+ '.' Digit* 
    |   Digit+ 
    |   '.' Digit+
    ;

fragment
Digit:  '0'..'9' ; 


// Keywords


COMMENT_C : {getCharPositionInLine()==0}? 'C' ~[\r\n]*  ->skip;

NOTE
    :   '/' ~[\r\n]* 
    ;

WS  :  [ \t\r\n]+ ->skip
    ;
    
 //Ignore : .+?;   
  