public class Tokenizer {
    private String token;
    private String lexeme;

    public Tokenizer(String token, String lexeme){
        this.token = token;
        this.lexeme = lexeme;
    }

    @Override
    public String toString(){
        return token + ", " + lexeme;
    }
}
