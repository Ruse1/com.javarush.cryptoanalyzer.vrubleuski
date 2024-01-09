import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class CryptoAnalyzer {
    private static final String MODE = "Выберите режим, где:\n1 - режим шифрования\n" +
            "2 - режим дешифрования\n3 - режим взлом \"Brute Force\"\nexit - для выхода";
    private static final String ERROR = "Вы ввели не число, попробуйте снова.";
    private static final String MISTAKE = "Такого режима не существует, попробуйте снова.";
    private static final String INPUT_PATH_DECRYPTION = "Введите путь файла формата - txt который требуется расшифровать";
    private static final String INPUT_PATH_ENCRYPTION = "Введите путь файла формата - txt который требуется зашифровать";
    private static final String OUTPUT_PATH_DECRYPTION = "Введите путь файла куда расшифровать данные";
    private static final String OUTPUT_PATH_ENCRYPTION = "Введите путь файла куда зашифровать данные";
    private static final String WRONG_FORMAT = "Неправильный формат файла %s, требуется txt\n";
    private static final String NOT_EXIST = "Файла не существует или указан неправильный путь!";
    private static final String KEY_ENCRYPTION = "Введите ключ шифрования";
    private static final String KEY_ERROR = "Вы ввели не число в поле ключа, попробуйте все заново";
    private static final String ENCRYPTION_READY = "Файл зашифрован!";
    private static final String DECRYPTION_READY = "Файл расшифрован!";
    private static final String VOCABULARY = "Выберите язык текста для шифрования. Напечатав:\n" +
            "Русский\n" + "Английский";
    private static final String RUS = "Выбран \"Русский язык\"";
    private static final String ENG = "Выбран \"Английский язык\"";
    private static final String DEFAULT = "Выбран язык по умолчанию т.к. указанного словаря не существует";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String mode = "";
        while (true) {
            System.out.println(MODE);
            mode = scanner.nextLine();
            if (mode.equalsIgnoreCase("exit")) {
                break;
            } else {
                toEncryptOrDecrypt(mode);
            }
        }
    }

    private static void toEncryptOrDecrypt(String mode) {
        try {
            int number = Integer.parseInt(mode);     //F:\\Test1\\Text.txt
            if (number == 1) {                       //F:\\Test1\\Text1.txt
                String[] allData = enterData(number);
                if (checkPath(allData[0]) && checkPath(allData[1]) && checkKey(allData[2])) {
                    int key = Integer.parseInt(allData[2]);
                    Language language = checkLanguage(allData[3]);
                    Encryption encryption = new Encryption(allData[0], allData[1], key, language);
                    encryption.doEncrypt();
                    System.out.println(ENCRYPTION_READY);
                }
            } else if (number == 2) {
                String[] allData = enterData(number);
                if (checkPath(allData[0]) && checkPath(allData[1]) && checkKey(allData[2])) {
                    int key = Integer.parseInt(allData[2]);
                    Language language = checkLanguage(allData[3]);
                    Decryption encryption = new Decryption(allData[0], allData[1], key, language);
                    encryption.doDecrypt();
                    System.out.println(DECRYPTION_READY);
                }
            } else if (number == 3) {
                String[] allData = enterData(number);
                if (checkPath(allData[0]) && checkPath(allData[1]) && checkKey(allData[2])) {
                    int key = Integer.parseInt(allData[2]);
                    Language language = checkLanguage(allData[3]);
                    BruteForce bruteForce = new BruteForce(allData[0],allData[1],language);
                    bruteForce.toDecryptionBruteForce();
                    System.out.println(DECRYPTION_READY);
                }
            } else {
                System.out.println(MISTAKE);
            }
        } catch (NumberFormatException e) {
            System.out.println(ERROR);
        }
    }

    private static boolean checkPath(String inputPath) {
        Path result = null;
        try{
            result = Path.of(inputPath);
        } catch (InvalidPathException e){
            System.out.println(NOT_EXIST);
        }

        if (Files.exists(result) && Files.isRegularFile(result)) {
            if (inputPath.endsWith(".txt")) {
                return true;
            } else {
                String[] array = inputPath.split("\\.");
                System.out.printf(WRONG_FORMAT, array[array.length - 1]);
                return false;
            }
        } else {
            System.out.println(NOT_EXIST);
            return false;
        }
    }

    private static String[] enterData(int mode) {
        String[] data = new String[4];
        System.out.println(mode == 1 ? INPUT_PATH_ENCRYPTION : INPUT_PATH_DECRYPTION);
        data[0] = scanner.nextLine();
        System.out.println(mode == 1 ? OUTPUT_PATH_ENCRYPTION : OUTPUT_PATH_DECRYPTION);
        data[1] = scanner.nextLine();
        System.out.println(KEY_ENCRYPTION);
        data[2] = scanner.nextLine();
        System.out.println(VOCABULARY);
        String language = scanner.nextLine();
        data[3] = language;
        return data;
    }

    private static boolean checkKey(String key) {
        int result = 0;
        try {
            result = Integer.parseInt(key);
        } catch (NumberFormatException e) {
            System.out.println(KEY_ERROR);
            return false;
        }
        return true;
    }

    private static Language checkLanguage(String language) {
        if (language.equalsIgnoreCase(Language.RUS.getName())) {
            System.out.println(RUS);
            return Language.RUS;
        } else if (language.equalsIgnoreCase(Language.ENG.getName())) {
            System.out.println(ENG);
            return Language.ENG;
        } else {
            System.out.println(DEFAULT);
            return Language.DEFAULT;
        }
    }
}
