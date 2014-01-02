
grammar CplexLP;


expr:   
       ('-'|'+') expr
    |   expr ('*'|'/') expr
    |   expr ('+'|'-') expr
    |   expr ('&'|'&&') expr
    |   '(' expr ')'
    |   ID
    |   STRING
    |   INT
    |   FLOAT
    ;


INT :   DIGIT+ [Ll]? ;

FLOAT:  DIGIT+ '.' DIGIT* [Ll]?
    |   DIGIT+ [Ll]?
    |   '.' DIGIT+ [Ll]?
    ;
fragment
DIGIT:  '0'..'9' ; 

COMPLEX
    :   INT 'i'
    |   FLOAT 'i'
    ;

STRING
    :   '"' ( ~[\\"] )*? '"'
    |   '\'' ( ~[\\'] )*? '\''
    ;


ID  :   '.' (LETTER|'_'|'.') (LETTER|DIGIT|'_'|'.')*
    |   LETTER (LETTER|DIGIT|'_'|'.')*
    ;

fragment LETTER  : [a-zA-Z] ;

USER_OP :   '%' .*? '%' ;

COMMENT :   '#' .*? '\r'? '\n' -> type(NL) ;

// Match both UNIX and Windows newlines
NL      :   '\r'? '\n' ;

WS      :   [ \t]+ -> skip ;
