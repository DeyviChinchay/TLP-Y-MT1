

package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol

L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+

%{
    private Symbol Symbol(int Type , Object value){
        return new Symbol(type , yyline , yycolum)
%}
%%

int {lexeme=yytext(); return Int;}
if {lexeme=yytext(); return If;}
else {lexeme=yytext(); return Else;}
while {lexeme=yytext(); return While;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {lexeme=yytext() return Igual;}
"+" {lexeme=yytext() return Suma;}
"-" {lexeme=yytext() return Resta;}
"*" {lexeme=yytext() return Multiplicacion;}
"/" {lexeme=yytext() return Division;}
"(" return {lexeme=yytext(); return Parentesis_a;}
")" return {lexeme=yytext(); return Parentesis_c;}
"{" return {lexeme=yytext(); return Llave_a;} 
"}" return {lexeme=yytext(); return Llave_c;}
"main" return {lexeme=yytext(); return Main;}
";" return {lexeme=yytext(); return P_coma;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {return ERROR;}