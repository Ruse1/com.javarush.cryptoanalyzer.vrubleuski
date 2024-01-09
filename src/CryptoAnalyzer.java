import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class CryptoAnalyzer {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CryptoAnalyzer.mainCryptoAnalyzer();
    }

    private static void mainCryptoAnalyzer() {
        String mode = "";
        while (true) {
            System.out.println(Message.MODE);
            mode = scanner.nextLine();
            if (mode.equalsIgnoreCase("exit")) {
                break;
            } else {
                CryptoAnalyzer cryptoAnalyzer = new CryptoAnalyzer();
                cryptoAnalyzer.toEncryptOrDecrypt(mode);
            }
        }
    }

    private void toEncryptOrDecrypt(String mode) {
        try {
            int number = Integer.parseInt(mode);     //F:\\Test1\\Text.txt
            if (number == 1) {                       //F:\\Test1\\Text1.txt
                String[] allData = enterData(number);
                if (checkPath(allData[0]) && checkPath(allData[1]) && checkKey(allData[2])) {
                    int key = Integer.parseInt(allData[2]);
                    Language language = checkLanguage(allData[3]);
                    Encryption encryption = new Encryption(allData[0], allData[1], key, language);
                    encryption.doEncrypt();
                    System.out.println(Message.ENCRYPTION_READY);
                }
            } else if (number == 2) {
                String[] allData = enterData(number);
                if (checkPath(allData[0]) && checkPath(allData[1]) && checkKey(allData[2])) {
                    int key = Integer.parseInt(allData[2]);
                    Language language = checkLanguage(allData[3]);
                    Decryption encryption = new Decryption(allData[0], allData[1], key, language);
                    encryption.doDecrypt();
                    System.out.println(Message.DECRYPTION_READY);
                }
            } else if (number == 3) {
                String[] allData = enterData(number);
                if (checkPath(allData[0]) && checkPath(allData[1])) {
                    Language language = checkLanguage(allData[3]);
                    BruteForce bruteForce = new BruteForce(allData[0], allData[1], language);
                    bruteForce.toDecryptionBruteForce();
                    System.out.println(Message.DECRYPTION_READY);
                }
            } else {
                System.out.println(Message.MISTAKE);
            }
        } catch (NumberFormatException e) {
            System.out.println(Message.ERROR);
        }
    }

    private boolean checkPath(String inputPath) {
        Path result = null;
        try {
            result = Path.of(inputPath);
        } catch (InvalidPathException e) {
            System.out.println(Message.NOT_EXIST);
            return false;
        }

        if (Files.exists(result) && Files.isRegularFile(result)) {
            if (inputPath.endsWith(".txt")) {
                return true;
            } else {
                String[] array = inputPath.split("\\.");
                System.out.printf(Message.WRONG_FORMAT, array[array.length - 1]);
                return false;
            }
        } else {
            System.out.println(Message.NOT_EXIST);
            return false;
        }
    }

    private String[] enterData(int mode) {
        String[] data = new String[4];
        if (mode == 3) {
            System.out.println(Message.WARN_BRUTE_FORCE);
        }
        System.out.println(mode == 1 ? Message.INPUT_PATH_ENCRYPTION : Message.INPUT_PATH_DECRYPTION);
        data[0] = scanner.nextLine();
        System.out.println(mode == 1 ? Message.OUTPUT_PATH_ENCRYPTION : Message.OUTPUT_PATH_DECRYPTION);
        data[1] = scanner.nextLine();
        if (mode != 3) {
            System.out.println(Message.KEY_ENCRYPTION);
            data[2] = scanner.nextLine();
        }
        System.out.println(Message.VOCABULARY);
        String language = scanner.nextLine();
        data[3] = language;
        return data;
    }

    private boolean checkKey(String key) {
        int result = 0;
        try {
            result = Integer.parseInt(key);
        } catch (NumberFormatException e) {
            System.out.println(Message.KEY_ERROR);
            return false;
        }
        return true;
    }

    private static Language checkLanguage(String language) {
        if (language.equalsIgnoreCase(Language.RUS.getName())) {
            System.out.println(Message.RUS);
            return Language.RUS;
        } else if (language.equalsIgnoreCase(Language.ENG.getName())) {
            System.out.println(Message.ENG);
            return Language.ENG;
        } else {
            System.out.println(Message.DEFAULT);
            return Language.DEFAULT;
        }
    }
}
