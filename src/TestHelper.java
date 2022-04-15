import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class TestHelper {
    private TestHelper() {
    }

    private static long RndLong(long lLeftLimit, long lRightLimit) {
        /*
            Генерация случайного числа типа long в заданных границах
        */
        long lGenerateLong = lLeftLimit + (long) (Math.random() * (lRightLimit - lLeftLimit));
        return lGenerateLong;
    }

    public static void GetRndLong() {
        /*
            Генерация случайного числа типа long в максимальном диапазоне
        */
        long lLeftLimit = Long.MIN_VALUE, lRightLimit = Long.MAX_VALUE;
        long lResult = RndLong(lLeftLimit, lRightLimit);
        System.out.println("Случайное число типа long: " + Long.toString(lResult));
    }

    private static Date RndDate(long lLeftLimit, long lRightLimit) {
        /*
            Генерация случайной даты в заданных границах
        */
        Date dDate = new Date();
        dDate.setTime(RndLong(0L, lRightLimit));
        return dDate;
    }

    public static void GetRndDate() {
        /*
            Генерация случайной даты от 1970 до текущего момента
        */
        Date dDate = new Date();
        long lRightLimit = dDate.getTime();
        long lLeftLimit = 0L; // 01/01/1970, 00:00
        dDate = RndDate(lLeftLimit, lRightLimit);
        System.out.println(dDate.toString());
    }

    private static String DateFormating(Date dDate, String sFormat) {
        /*
            преобразует дату в выбраный формат
        */
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(sFormat);
        return formatForDateNow.format(dDate);
    }

    public static void SetDateFormat() {
        /*
            Осуществляет выбор формата даты/времни из предложеных вариантов
        */
        Scanner input = new Scanner(System.in);
        Date dDate = new Date();
        String sFormat = "";

        System.out.println("Текущее время: " + dDate.toString());
        System.out.println();
        System.out.println("Выбирите формат даты/времени:");
        System.out.println();
        System.out.println("1. Дата: <День недели> <yyyy.mm.dd> и время <hh:mm:ss> (24-часовой формат)");
        System.out.println("2. Дата: <День недели> <yyyy.mm.dd> и время <hh:mm:ss> (12-часовой формат)");
        System.out.println("3. Дата: <dd.mm.yyyy>");
        System.out.println("4. время <hh:mm:ss> (24-часовой формат)");
        switch (input.nextInt()) {
            case 1:
                sFormat = "'Дата: ' E yyyy.MM.dd 'и время' HH:mm:ss";
                break;
            case 2:
                sFormat = "'Дата: ' E yyyy.MM.dd 'и время' hh:mm:ss a";
                break;
            case 3:
                sFormat = "'Дата: ' dd.MM.yyyy";
                break;
            case 4:
                sFormat = "'Время' HH:mm:ss";
                break;
            default:
                System.out.println("Формат не выбран");
        }
        System.out.println(DateFormating(dDate, sFormat));
    }

    private static char RndChar() {
        /*
            Случайный символ из маленьких английских букв
        */
        Random random = new Random();
        char cSymbol = (char) (random.nextInt(26) + 'a');
        return cSymbol;
    }

    private static String RndString(int iSizeString, int iMaxSizeWord) {
        /*
            Генерация строки состоящей из iSizeString шт. слов, сформированных из iMaxSizeWord шт.
            сучайных маленьких английских букв.
            Каждое слово начинается с заглавной буквы и разделено пробелом.
        */
        String sString = "", sTemp;
        int iSize;

        for (int i = 0; i < iSizeString; i++) { //цикл на количество слов
            sTemp = "";
            iSize = (int) RndLong(1, iMaxSizeWord); // генерация длинны слова
            for (int j = 0; j < iSize; j++) { //цикл на формирование слова
                sTemp = sTemp + RndChar();
                if (j == 0) {             //формирование первой буквы слова как заглавной
                    sTemp = sTemp.toUpperCase();
                }
            }
            sString = sString + " " + sTemp; // формирование строки
        }
        return sString;
    }

    public static void GetRndString() {
         /*
            Генерация строки состоящей из 3 слов, сформированных из максимум 15 сучайных маленьких английских букв.
         */
        int iSizeString = 3, iMaxSizeWord = 15;
        System.out.println("Строка из трех слов: " + RndString(iSizeString, iMaxSizeWord));
    }

    private static Double StringToDouble(String sNumber) {
        /*
            Преобразует строку в число типа Double
        */
        return Double.valueOf(sNumber);
    }

    public static void GetDouble() {
        Scanner input = new Scanner(System.in);
        String sNumber;
        Double dNumber;

        System.out.println("Введите число: ");
        sNumber = input.nextLine();
        // проверка, что введено число
        if ((sNumber.replaceAll("[0123456789.,]", "").equals("")) && (sNumber.length() - sNumber.replaceAll("[.,]", "").length() <= 1)) {
            dNumber = StringToDouble(sNumber);
            System.out.println(dNumber);
        } else {
            System.out.println("Введено некорректное число");
            System.out.println("Было введено: " + sNumber);
        }

    }

    private static HashMap fileToHashMap(File fileName) throws IOException {
        /*
            Преобразование файла в HashMap
        */
        HashMap hm = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        System.out.println("Данные файла:");
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            String[] parts = line.split("::", 2);
            String key = parts[0];
            String value = parts[1];
            hm.put(key, value);
        }
        return hm;
    }

    public static void selectFile() throws IOException {
        /*
            Осуществляет выбор файла для чтения и вызов функции преобразования его в HashMap
        */
        Scanner input = new Scanner(System.in);

        System.out.println("Введите путь к файлу");
        System.out.println("Текущщая папка:");
        System.out.println(System.getProperty("user.dir"));
        File fileName = new File(input.nextLine());
        HashMap hm = fileToHashMap(fileName);
        System.out.println("Преобразованный файл:" + '\n' + hm);
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean bFlag = true;

        while (bFlag) {
            System.out.println();
            System.out.println("Выберите одно из следующих действий (введите число):");
            System.out.println();
            System.out.println("0. Выход из программы");
            System.out.println("1. Сформировать случайную дату от 1970 до текущего года.");
            System.out.println("2. Преобразование формата даты");
            System.out.println("3. Сформировать случайное число типа long.");
            System.out.println("4. Сформировать случайную строку (три слова c больших букв разумной длины через пробел).");
            System.out.println("5. Преобразовать выбранный файл в HashMap");
            System.out.println("6. Преобразовать строку в Double");

            switch (input.nextInt()) {
                case 1:
                    GetRndDate();
                    break;
                case 2:
                    SetDateFormat();
                    break;
                case 3:
                    GetRndLong();
                    break;
                case 4:
                    GetRndString();
                    break;
                case 5:
                    selectFile();
                    break;
                case 6:
                    GetDouble();
                    break;
                default:
                    bFlag = false;
            }

        }
    }
     /*public static long Rand(){
        Random random = new Random();
        System.out.println("Случайное число типа long: ");
        System.out.println(random.nextLong());
        long lResult = random.nextLong();
                return lResult;
    }*/

}
