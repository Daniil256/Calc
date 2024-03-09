import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение");

        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        System.out.println("Ответ "+calc(expression));
    }
    public static String calc(String expression) throws Exception{
        int num1 = 0;
        int num2 = 0;
        String oper = null;
        String result;
        String [] nums = new String[0];
        int arabian;
        boolean isArabish = false;

        if(expression.matches("\\d{1,2}[+\\-*/]\\d{1,2}")){
            nums = expression.split("[+\\-*/]");
            num1 = Integer.parseInt(nums[0]);
            num2 = Integer.parseInt(nums[1]);
            oper = expression.replaceAll("[0-9]","");
            isArabish = true;
        }
        else if(expression.matches("[IVX]{1,4}[+\\-*/][IVX]{1,4}")){
            nums = expression.split("[+\\-*/]");

            if(Roman.isRoman(nums[0]) && Roman.isRoman(nums[1])){
                num1 = Roman.convertToArabian(nums[0]);
                num2 = Roman.convertToArabian(nums[1]);
                oper = expression.replaceAll("[IVX]","");

            }else{
                throw new Exception("Ошибка в написании римских чисел");
            }
        }
        else {
            throw new Exception("Должно быть два положительных только арабских или только римских числа и один знак: сложения, вычитания, деления или умножения");
        }

        if(num1>10||num2>10){
            throw new Exception("Числа должны быть от 1 до 10");
        }
        
        arabian = calc(num1, num2, oper);

        if(!isArabish) {
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Roman.convertToRoman(arabian);
        }else{
            result = String.valueOf(arabian);
        }
        return result;
    }
    static int calc(int a, int b, String oper){
        return switch (oper){
            case "+" -> a+b;
            case "-" -> a-b;
            case "*" -> a*b;
            default ->  a/b;
        };
    }
    static class Roman {
       static String[] romans = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
       public static boolean isRoman(String str){
           for (String roman : romans) {
               if (str.equals(roman)) {
                   return true;
               }
           }
            return false;
        }
        public static int convertToArabian(String roman){
            for (int i = 0; i < romans.length; i++) {
                if(roman.equals(romans[i])){
                    return i+1;
                }
            }
            return 0;
        }
    public static String convertToRoman(int arabian){
        return romans[arabian-1];
    }
}
}
