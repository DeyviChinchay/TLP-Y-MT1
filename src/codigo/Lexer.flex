
package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens

digit		= [0-9]
number		= {digit}+
letter		= [a-zA-Z]
Identificador	= {letter}+
whitespace	= [ \r,\n,\t]+
line            = .*\n


%{
    public String lexeme;
    public int lineno = 1;
%}

%%

then {lexeme=yytext(); return THEN;}
if {lexeme=yytext(); return IF;}
else {lexeme=yytext(); return ELSE;}
repeat {lexeme=yytext(); return REPEAT;}
until {lexeme=yytext(); return UNTIL;}
read {lexeme=yytext(); return READ;}
write {lexeme=yytext(); return WRITE;}
:= {lexeme=yytext(); return ASSIGN;}
"//".* {/*Ignore*/}
"=" {lexeme=yytext(); return EQ;}
"<" {lexeme=yytext(); return LT;}
"+" {lexeme=yytext(); return PLUS;}
"-" {lexeme=yytext(); return MINUS;}
"*" {lexeme=yytext(); return TIMES;}
"/" {lexeme=yytext(); return OVER;}
"(" {lexeme=yytext(); return LPAREN;}
")" {lexeme=yytext(); return RPAREN;}
"{" {lexeme=yytext(); return COMM;}
";" {lexeme=yytext(); return SEMI;}
"EOF" {lexeme=yytext(); return EOF;}
{whitespace} {lexeme=yytext(); return WHITESPACE;} 
{letter}({letter}|{digit})* {lexeme=yytext(); return Identificador;}
("(-"{digit}+")")|{digit}+ {lexeme=yytext(); return Numero;}
. {return ERROR;}