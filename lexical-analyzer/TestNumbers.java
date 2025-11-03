public class TestNumbers { public static void main(String[] args) { Lexer.lex("x 42 3.14 100"); for (String token : Lexer.tokens) { System.out.println(token); } } }  
