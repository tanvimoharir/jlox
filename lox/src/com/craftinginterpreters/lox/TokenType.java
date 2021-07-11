package com.craftinginterpreters.lox;

enum TokenType {
	//Single character tokens
	LEFT_PARAN, RIGHT_PARAN, LEFT_BRACE, RIGHT_BRACE,
	COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,
	
	//ONE or two character tokens
	BANG, BANG_EQUAL,
	EQUAL, EQUAL_EQUAL,
	GREATER, GREATER_EQUAL,
	LESS, LESS_EQUAL,
	
	// Literals
	IDENTIFIER, STRING, NUMBER,
	
	//Keywords
	AND, CLASS, ELSE, FALSE, FUNE, FOR, IF, NIL, OR,
	PRINT, RETURN, SUPER, THIS, TRUE, VAR, WHILE,
	
	EOF,

}
