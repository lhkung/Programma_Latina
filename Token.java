public class Token {
    private String type;
    private String content;
    private int line;
    private String declension;


    public Token(String type, String content, int line){
        this.type = type;
        this.content = content;
        this.line = line;
        this.declension = null;
    }

    public Token(String type, String content, String declension, int line){
        this.type = type;
        this.content = content;
        this.line = line;
        this.declension = declension;
    }

    public String getType(){
        return this.type;
    }

    public String getContent(){
        return this.content;
    }
}
