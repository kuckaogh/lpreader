grammar LpExtend;
//import CommonLexer;

options {
  language = Java;
  output=AST;
  ASTLabelType=CommonTree;
}

@header {
  package lpreader.grammar;
  import java.util.Map;
  import java.util.HashMap;
  import java.util.LinkedHashMap;
  import java.util.Arrays;
  import java.util.HashSet;
  import java.util.Set;
}
@lexer::header {
  package lpreader.grammar;
}

@members {
      //public CommonTree commonTree;
      public Map<String, String> configMap = new LinkedHashMap<String, String>();
      public Map<String, ArrayList<String>> transferMap = new LinkedHashMap<String, ArrayList<String>>();
      //public ArrayList<String> varList = new ArrayList<String>();
      public ArrayList<String> t = new ArrayList<String>();

      public Map<String , String> configKeyMap; // this is input from caller
}


cplexFile :

maximize
subjectTo
bounds
general
End
;

maximize : Maximize expName? valueExp;
subjectTo : SubjectTo constraintGroup;
bounds : Bounds boundsExp+;
general : General var+;


valueExp :  sign? term (sign term)*;
constraintGroup : (expName constraintExp)+ ;
constraintExp : valueExp  relationSign valueExp ;
boundsExp : ( lb var ub ) |  ( lb var ) | ( var ub ) ;

lb: bound '<=' ;
ub: '<=' bound ;

bound : sign? (number | Inf) ;
relationSign : '=' | '>=' | '<=' ;
expName : ID ':' ;

term : (number? var) | number ;

var : ID ;



integer : INT ;
real : INT '.' INT ;
number : integer | real ;
//complex : INT?  ( ID | '.' | '-' | '\"'  )+ ;
sign : '+' | '-' ;





SL_COMMENT : '\\' ~('\r'|'\n')*  {$channel=HIDDEN;} ;


INT : Digit+ ;

Maximize : 'Maximize' ;
SubjectTo : 'Subject To' ;
Bounds : 'Bounds' ;
General : 'General' | 'Generals' ;


Inf : 'inf' ;
Begin  : 'Begin' | 'begin' | 'BEGIN'  ;
End    : 'End' | 'end' | 'END'    ;
Config : 'Config' | 'config' | 'CONFIG' ;

ID : Letter ( Letter | Digit | '_' )*;


fragment Letter : 'a'..'z' | 'A'..'Z';

fragment Digit : '0'..'9';



WS : (' ' | '\t' ) {$channel=HIDDEN;};



ENDLINE : ( '\n' | '\r' ) {$channel=HIDDEN;};
