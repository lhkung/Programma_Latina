import java.util.HashMap;
public class RomanNumber{
    private String roman = "";
    private int arabic = 0;

    public RomanNumber (String roman){
        this.roman = roman;
        this.arabic = romanToInt(roman);
    }

    public int romanToInt(String s) {
        HashMap<Character, Integer> rta = new HashMap<>();
        rta.put('I',1);
        rta.put('V',5);
        rta.put('X',10);
        rta.put('L',50);
        rta.put('C',100);
        rta.put('D',500);
        rta.put('M',1000);

        int arabic = 0;
        char previousKey = s.charAt(s.length() - 1);
        boolean minus = false;
        arabic += rta.get(previousKey);

        for (int i = s.length() - 2; i >= 0; i--){
            char currentKey = s.charAt(i);
            if (rta.get(currentKey) < rta.get(previousKey) || (minus == true && rta.get(currentKey) == rta.get(previousKey))){
                minus = true;
            } else {
                minus = false;
            }
            if (minus == true){
                arabic += -1 * rta.get(currentKey);
            } else{
                arabic += rta.get(currentKey);
            }
            previousKey = currentKey;
        }

        return arabic;
    }
    public static String intToRoman(int num) {
        String lessThanMille = "";
        int power = 1;
        String ans = "";
        int cur = 0;
        HashMap<Integer, String> atr = new HashMap<>();
        atr.put(0,"");
        atr.put(1,"I");
        atr.put(2,"II");
        atr.put(3,"III");
        atr.put(4,"VI");
        atr.put(5,"V");
        atr.put(6,"IV");
        atr.put(7,"IIV");
        atr.put(8,"IIIV");
        atr.put(9,"XI");
        atr.put(10,"X");
        atr.put(20,"XX");
        atr.put(30,"XXX");
        atr.put(40,"LX");
        atr.put(50,"L");
        atr.put(60,"XL");
        atr.put(70,"XXL");
        atr.put(80,"XXXL");
        atr.put(90,"CX");
        atr.put(100,"C");
        atr.put(200,"CC");
        atr.put(300,"CCC");
        atr.put(400,"DC");
        atr.put(500,"D");
        atr.put(600,"CD");
        atr.put(700,"CCD");
        atr.put(800,"CCCD");
        atr.put(900,"MC");

        while (num >= 1000){
            num -= 1000;
            ans += "M";
        }
        while (num > 0){
            cur = num % 10;
            lessThanMille += atr.get(power * cur);
            num /= 10;
            power *= 10;
        }
        for (int i = lessThanMille.length() - 1; i >= 0; i--){
            ans += lessThanMille.charAt(i);
        }

        return ans;
    }

    public int getArabic() {
        return arabic;
    }

    public String getRoman() {
        return roman;
    }

    public void setArabic (int n) {
        this.arabic = n;
        this.roman = intToRoman(n);
    }
}
