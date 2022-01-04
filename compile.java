public class compile {
    public static void main(String[] args){
        Lexer lexer = new Lexer();
        String fileName = args[0];
        lexer.lexLatina(fileName);
        lexer.printTokens();
    }
}
