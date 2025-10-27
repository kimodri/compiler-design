public class Token {
    private String token;
    private String lexeme;

    public Token(String token, String lexeme){
        this.token = token;
        this.lexeme = lexeme;
    }

    @Override
    public String toString(){
        return token + ", " + lexeme;
    }
}
