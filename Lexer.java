import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private ArrayList<Token> tokens;
    private Set<Character> parenthesisQuotationsPunctuations;
    private Set<Character> arithmeticOperators;
    private Set<Character> comparisonOperators;

    public Lexer() {
        tokens = new ArrayList<>();
        parenthesisQuotationsPunctuations = new HashSet<>();
        parenthesisQuotationsPunctuations.add('(');
        parenthesisQuotationsPunctuations.add(')');
        parenthesisQuotationsPunctuations.add('[');
        parenthesisQuotationsPunctuations.add(']');
        parenthesisQuotationsPunctuations.add('{');
        parenthesisQuotationsPunctuations.add('}');
        parenthesisQuotationsPunctuations.add(',');
        parenthesisQuotationsPunctuations.add(';');
        parenthesisQuotationsPunctuations.add('\"');
        parenthesisQuotationsPunctuations.add('\'');

        arithmeticOperators = new HashSet<>();
        arithmeticOperators.add('+');
        arithmeticOperators.add('-');
        arithmeticOperators.add('*');
        arithmeticOperators.add('/');
        arithmeticOperators.add('%');
        arithmeticOperators.add('=');

        comparisonOperators = new HashSet<>();
        comparisonOperators.add('=');
        comparisonOperators.add('!');
        comparisonOperators.add('<');
        comparisonOperators.add('>');
    }

    public void createToken(String word, int line){
        String numRegex = "[0-9]+";
        Pattern numPattern = Pattern.compile(numRegex);
        Matcher matchNum = numPattern.matcher(word);

        String IDRegex = "_*[A-Za-z0-9_]+";
        Pattern IDPattern = Pattern.compile(IDRegex);
        Matcher matchID = IDPattern.matcher(word);

        String stringRegex = "\".*\"";
        Pattern stringPattern = Pattern.compile(stringRegex);
        Matcher matchString = stringPattern.matcher(word);
        String charRegex = "\'.\'";
        Pattern charPattern = Pattern.compile(charRegex);
        Matcher matchChar = charPattern.matcher(word);

        String type = "";
        String content = "";
        String declension = "";
        if (word.equals("scribe")){
            type = "SCRIBE";
        } else if (word.equals("numerus")){
            type = "NUMERUS";
            declension = "nominative";
        } else if (word.equals("numerorum")){
            type = "NUMERUS";
            declension = "genitive";
        } else if (word.equals("numerum")){
            type = "NUMERUS";
            declension = "accusative";
        } else if (word.equals("series")){
            type = "SERIES";
            declension = "nominative";
        } else if (word.equals("serienum")){
            type = "SERIES";
            declension = "genitive";
        } else if (word.equals("seriem")){
            type = "SERIES";
            declension = "accusative";
        } else if (word.equals("littera")){
            type = "LITTERA";
            declension = "nominative";
        } else if (word.equals("litterarum")){
            type = "LITTERA";
            declension = "genitive";
        } else if (word.equals("litteram")){
            type = "LITTERA";
            declension = "accusative";
        } else if (word.equals("punctum_fluctuans")){
            type = "PUNCTUM_FLUCTUANS";
            declension = "nominative";
        } else if (word.equals("punctorum_fluctuantium")){
            type = "PUNCTUM_FLUCTUANS";
            declension = "genitive";
        } else if (word.equals("booleanum")){
            type = "BOOLEANUM";
            declension = "nominative";
        } else if (word.equals("booleanorum")){
            type = "BOOLEANUM";
            declension = "genitive";
        } else if (word.equals("nihil")){
            type = "NIHIL";
        } else if (word.equals("si")){
            type = "SI";
        } else if (word.equals("alioqui")){
            type = "ALIOQUI";
        } else if (word.equals("dum")){
            type = "DUM";
        } else if (word.equals("fiat")){
            type = "FIAT";
        } else if (word.equals("falsum")){
            type = "FALSUM";
        } else if (word.equals("verum")){
            type = "VERUM";
        } else if (word.equals("per")){
            type = "PER";
        } else if (word.equals("inventarium")){
            type = "INVENTARIUM";
        } else if (word.equals("principalis")){
            type = "PRINCIPALIS";
        } else if (word.equals("redde")){
            type = "REDDE";
        } else if (word.equals("functio")){
            type = "FUNCTIO";
        } else if (word.equals("(")){
            type = "PARENTHESIS_CIRCULARIS_SINISTER";
        } else if (word.equals(")")){
            type = "PARENTHESIS_CIRCULARIS_DEXTER";
        } else if (word.equals(")")){
            type = "PARENTHESIS_CIRCULARIS_DEXTER";
        } else if (word.equals("[")){
            type = "PARENTHESIS_QUADRA_SINISTER";
        } else if (word.equals("]")){
            type = "PARENTHESIS_QUADRA_DEXTER";
        } else if (word.equals("{")){
            type = "PARENTHESIS_UNGULATA_SINISTER";
        } else if (word.equals("}")){
            type = "PARENTHESIS_UNGULATA_DEXTER";
        } else if (word.equals("\"")){
            type = "DOUBLE_QUOTATION";
        } else if (word.equals("\'")){
            type = "SINGLE_QUOTATION";
        } else if (word.equals("+")){
            type = "ADDITIO";
        } else if (word.equals("-")){
            type = "SUBTRACTIO";
        } else if (word.equals("*")){
            type = "MULTIPLICATIO";
        } else if (word.equals("/")){
            type = "DIVISIO";
        } else if (word.equals("%")){
            type = "MODULUS";
        } else if (word.equals("=")){
            type = "SIT";
        } else if (word.equals("!=")){
            type = "NON_AEQUALIS";
        } else if (word.equals("==")){
            type = "AEQUALIS";
        } else if (word.equals(">")){
            type = "MAIOR_QUAM";
        } else if (word.equals("<")){
            type = "MINOR_QUAM";
        } else if (word.equals("<=")){
            type = "MINOR_QUAM_AUT_AEQUAT";
        } else if (word.equals(">=")){
            type = "MAIOR_QUAM_AUT_AEQUAT";
        } else if (word.equals("+=")){
            type = "ADDERE_ASSIGNARE";
        } else if (word.equals("-=")){
            type = "SUBTRAHERE_ASSIGNARE";
        } else if (word.equals("*=")){
            type = "MULTIPLICARE_ASSIGNARE";
        } else if (word.equals("/=")){
            type = "DIVIDERE_ASSIGNARE";
        } else if (word.equals("%=")){
            type = "MODULO_ASSIGNARE";
        } else if (word.equals(";")){
            type = "SEMICOLON";
        } else if (matchNum.matches()){
            type = "NUMERUS_ARABICUS";
            content = word;
        } else if (matchID.matches()) {
            type = "ID";
            content = word;
        } else if (matchString.matches()){
            type = "STRING_LITERAL";
            content = word.substring(1, word.length() - 1);
        } else if (matchChar.matches()){
            type = "CHAR_LITERAL";
            content = word.substring(1, word.length() - 1);
        } else {
            System.out.printf(ErrorMessage.lexicalError, line);
            System.out.printf("Don't recognize: %s\n",word);
        }
        Token t = new Token(type, content, line);
        this.tokens.add(t);

        //don't forget the quotation of applicable
        if (type.equals("STRING_LITERAL")){
            createToken("\"", line);
        } else if (type.equals("CHAR_LITERAL")){
            createToken("\'", line);
        }
    }

    public ArrayList<Token> lexLatina(String fileName) {
        String word = "";
        char curr = 0;
        char next = 0;
        int line = 1;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            Scanner latinaScanner = new Scanner(fis).useDelimiter("");
            while (latinaScanner.hasNext()) {
                //if curr != 0, curr already holds next.
                if (curr == 0){
                    curr = latinaScanner.next().charAt(0);
                }

                //if curr is 0-9, enter number state
                if (curr >= 48 && curr <= 57){
                    while (curr >= 48 && curr <= 57 || curr == '.'){
                        word += curr; //number or letters
                        curr = latinaScanner.next().charAt(0);
                    }
                    createToken(word, line);
                    word = "";

                 // if curr is alphabet, enter string state
                } else if (curr >= 65 && curr <= 90 || curr == 95 || curr >= 97 && curr <= 122) {
                    while (curr >= 48 && curr <= 57 || curr >= 65 && curr <= 90 || curr == 95 || curr >= 97 && curr <= 122){
                        word += curr;
                        curr = latinaScanner.next().charAt(0);
                    }
                    createToken(word, line);
                    word = "";
                } else if (curr == ' '){
                    curr = 0;
                } else if (parenthesisQuotationsPunctuations.contains(curr)){
                    //deal with the punctuation
                    word += curr;
                    createToken(word, line);

                    //build string literal if quotation mark is seen
                    if (curr == '\"'){
                        curr = latinaScanner.next().charAt(0);
                        while (curr != '\"'){
                            word += curr;
                            curr = latinaScanner.next().charAt(0);
                        }
                        word += curr;
                        createToken(word, line);
                    } else if (curr == '\''){
                        curr = latinaScanner.next().charAt(0);
                        word += curr;
                        curr = latinaScanner.next().charAt(0);
                        word += curr;
                        createToken(word, line);
                    }
                    word = "";

                    curr = 0;

                } else if (arithmeticOperators.contains(curr) || comparisonOperators.contains(curr)){
                    word += curr; //treat the curr
                    next = latinaScanner.next().charAt(0);
                    //if next char is also operator, grab it too, else reset.
                    if (arithmeticOperators.contains(next) || comparisonOperators.contains(next)){
                        word += next;
                        createToken(word, line);
                        word = "";
                        curr = 0;
                    } else {
                        createToken(word, line);
                        curr = next;
                        word = "";
                    }
                } else if (curr == '\n'){
                    line++;
                    curr = 0;
                }
            }
        } catch (IOException e) {
            System.out.println("file not found.");
            return null;
        }
        return tokens;
    }

    public void printTokens(){
        for (int i = 0; i < tokens.size(); i++){
            System.out.printf("%d. type: %-40s %s\n",i + 1, tokens.get(i).getType(), "content: " + tokens.get(i).getContent());
        }
    }

    public static void main(String[] args){
        Lexer test = new Lexer();
        String fileName = args[0];
        test.lexLatina("salve_munde.txt");
        test.printTokens();
    }
}
