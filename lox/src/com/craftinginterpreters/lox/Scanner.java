package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.craftinginterpreters.lox.TokenType.*;

public class Scanner {
	private final String source; //raw source string
	private final List<Token> tokens = new ArrayList<>();
	
	private int start = 0;
	private int current = 0;
	private int line = 1;
	
	Scanner (String source) {
		this.source = source;
	}
	
	List<Token> scanTokens() {
		while (!isAtEnd()) {
			// We are at the beginning of the next lexeme
			start = current;
			scanToken();
		}
		
		tokens.add(new Token(EOF, "", null, line));
		return tokens;
	}
	
	private boolean isAtEnd() {
		return current >= source.length();
	}
	
	private char advance() {
		return source.charAt(current++);
	}
	
	private void addToken(TokenType type) {
		addToken(type, null);  //this is calling the overloaded method defined below
	}
	
	private void addToken(TokenType type, Object literal) {
		String text = source.substring(start, current);
		tokens.add(new Token(type,text,literal,line));
	}
	
	private void scanToken() {
		//Heart of scanner
		
		char c = advance();
		
		switch(c) {
		case '(' : addToken(LEFT_PAREN); break;
		case ')' : addToken(RIGHT_PAREN); break;
		case '{' : addToken(LEFT_BRACE); break;
		case '}' : addToken(RIGHT_BRACE); break;
		case ',' : addToken(COMMA); break;
		case '.' : addToken(DOT); break;
		case '-' : addToken(MINUS); break;
		case '+' : addToken(PLUS); break;
		case ';' : addToken(SEMICOLON); break;
		case '*' : addToken(STAR); break;
		case '!' :
			addToken(match('=') ? BANG_EQUAL : BANG); //we need to check next character
			break;
		case '=' :
			addToken(match('=') ? EQUAL_EQUAL : EQUAL);
			break;
		case '<' :
			addToken(match('=') ? LESS_EQUAL : LESS);
			break;
		case '>' :
			addToken(match('=') ? GREATER_EQUAL : GREATER);
			break;
		case '/' :
			if (match('/')) {
				//a comment goes till end of line
				while (peek() != '\n' && !isAtEnd()) advance();
			} else {
				addToken(SLASH);
			}
			break;
		case ' ' :
		case '\r' :
		case '\t' :
			//ignore whitespace
			break;
		case '\n' :
			line++;
			break;
		default:
			Lox.error(line, "Unexpected character.");
			break;
		}
	}
	
	private boolean match(char expected) {
		//this does lookahead
		if(isAtEnd()) return false;
		if (source.charAt(current) != expected) return false;
		
		current++;
		return true;
	}
	
	private char peek() {
		//this is like advance() but does not consume the character
		if (isAtEnd()) return '\0';
		return source.charAt(current);
	}
}
