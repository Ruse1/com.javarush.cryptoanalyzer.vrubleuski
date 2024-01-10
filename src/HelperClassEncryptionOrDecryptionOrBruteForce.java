import java.io.*;
import java.util.List;

public class HelperClassEncryptionOrDecryptionOrBruteForce {
    public void doEncryptionOrDecryption(String readFilePath, String writeFilePath, int key, Language language, String mode) {
        List<Character> alphabet = Vocabulary.getVocabulary(language);
        try (FileReader fr = new FileReader(readFilePath);
             FileWriter wr = new FileWriter(writeFilePath)) {
            while (fr.ready()) {
                int result = fr.read();
                char symbol = (char) result;
                findAndWriteSymbols(wr, key, alphabet, symbol, mode);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDecryptionBruteForce(String readFilePath, String writeFilePath, Language language, String mode) {
        List<Character> alphabet = Vocabulary.getVocabulary(language);
        StringBuilder text = readTxtFile(readFilePath);
        StringBuilder[] allCombinations = getAllDecryptionCombinations(text, alphabet, mode);
        writeToFileTheBestCombination(writeFilePath, allCombinations, language);

    }

    private void findAndWriteSymbols(FileWriter wr, int key, List<Character> alphabet, char symbol, String mode) throws IOException {
        if (Character.isUpperCase(symbol)) {
            symbol = Character.toLowerCase(symbol);
            if (alphabet.contains(symbol)) {
                int index = alphabet.indexOf(symbol);
                int shift = generalShift(index, key, alphabet, mode);
                wr.write(Character.toUpperCase(alphabet.get(shift)));
            } else {
                wr.write(Character.toUpperCase(symbol));
            }
        } else {
            if (alphabet.contains(symbol)) {
                int index = alphabet.indexOf(symbol);
                int shift = generalShift(index, key, alphabet, mode);
                wr.write(alphabet.get(shift));
            } else {
                wr.write(symbol);
            }
        }
    }

    private int encryptionShift(int index, int key, List<Character> alphabet) {
        key = Math.abs(key);
        int result = index + key;
        if (result >= alphabet.size()) {
            return result % alphabet.size();
        } else {
            System.out.println(result);
            return result;
        }
    }

    private int decryptionShift(int index, int key, List<Character> alphabet) {
        key = Math.abs(key % alphabet.size());
        int result = index - key;
        if (result < 0) {
            return alphabet.size() + (result % alphabet.size());
        } else {
            return result;
        }
    }


    private int generalShift(int index, int key, List<Character> alphabet, String mode) {
        if (mode.equals("Encryption")) {
            return encryptionShift(index, key, alphabet);
        } else if (mode.equals("Decryption")) {
            return decryptionShift(index, key, alphabet);
        } else if (mode.equals("BruteForce")) {
            return decryptionShift(index, key, alphabet);
        } else {
            return 0;
        }
    }

    private StringBuilder readTxtFile(String readFilePath) {
        StringBuilder result = new StringBuilder();
        try (FileReader fr = new FileReader(readFilePath);
             BufferedReader bf = new BufferedReader(fr)) {
            while (bf.ready()) {
                result.append(bf.readLine()).append("\n");
            }
            result.deleteCharAt(result.lastIndexOf("\n"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private StringBuilder[] getAllDecryptionCombinations(StringBuilder text, List<Character> alphabet, String mode) {
        StringBuilder[] result = new StringBuilder[alphabet.size()];
        for (int key = 0; key < alphabet.size(); key++) {
            result[key] = getDecryptionCombination(text, key, alphabet, mode);

        }
        return result;
    }

    private StringBuilder getDecryptionCombination(StringBuilder text, int key, List<Character> alphabet, String mode) {
        StringBuilder combination = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (Character.isUpperCase(symbol)) {
                symbol = Character.toLowerCase(symbol);
                if (alphabet.contains(symbol)) {
                    int index = alphabet.indexOf(symbol);
                    int shift = generalShift(index, key, alphabet, mode);
                    combination.append(Character.toUpperCase(alphabet.get(shift)));
                } else {
                    combination.append(Character.toUpperCase(symbol));
                }
            } else {
                if (alphabet.contains(symbol)) {
                    int index = alphabet.indexOf(symbol);
                    int shift = generalShift(index, key, alphabet, mode);
                    combination.append(alphabet.get(shift));
                } else {
                    combination.append(symbol);
                }
            }
        }
        return combination;
    }

    private void writeToFileTheBestCombination(String writeFilePath, StringBuilder[] decryptionCombinations, Language language) {
        try (FileWriter wr = new FileWriter(writeFilePath, true)) {
            for (StringBuilder txt : decryptionCombinations) {
                String[] words = txt.toString().split(" ");
                int averageLengthWord = (txt.length() - words.length) / words.length;
                if (language == Language.RUS || language == Language.DEFAULT) {
                    if (averageLengthWord >= 3 && averageLengthWord < 9) {
                        wr.write(String.valueOf(txt));
                    }
                } else if (language == Language.ENG) {
                    if (averageLengthWord >= 3 && averageLengthWord < 8) {
                        wr.write(String.valueOf(txt));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
