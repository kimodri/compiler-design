public class TestComments { public static void main(String[] args) { String code = "....."; Lexer.lex(code); for (String token : Lexer.tokens) { System.out.println(token); } } }  
