import java.io.*;

public class BruteForce extends Cryptography {

    public BruteForce(String readFilePath, String writeFilePath, Language language) {
        super(readFilePath, writeFilePath, language);
    }

    public void toDecryptionBruteForce() {
        alphabet = Vocabulary.checkVocabulary(language);
        StringBuilder stringBuilder = readData();
        StringBuilder[] allCombinations = getAllCombinations(stringBuilder);
        for (StringBuilder sb : allCombinations) {
            String[] strings = sb.toString().split(" ");
            int lengthWord = (sb.length() - strings.length) / strings.length;
            if (language == Language.RUS || language == Language.DEFAULT) {
                if (lengthWord > 3 && lengthWord < 9) {
                    writeToFile(sb);
                }
            } else if (language == Language.ENG) {
                if (lengthWord > 3 && lengthWord < 8) {
                    writeToFile(sb);
                }
            }

        }
    }

    private int shift(int index, int key) {
        key = Math.abs(key % alphabet.size());
        int result = index - key;
        if (result < 0) {
            return alphabet.size() + (result % alphabet.size());
        } else {
            return result;
        }
    }

    private StringBuilder readData() {
        StringBuilder result = new StringBuilder();
        try (FileReader fr = new FileReader(readFilePath);
             BufferedReader bf = new BufferedReader(fr)) {
            while (bf.ready()) {
                result.append(bf.readLine() + "\n");
            }
            result.deleteCharAt(result.lastIndexOf("\n"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private void writeToFile(StringBuilder text) {
        try (FileWriter wr = new FileWriter(writeFilePath, true)) {
            wr.write(String.valueOf(text));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private StringBuilder[] getAllCombinations(StringBuilder text) {
        StringBuilder[] result = new StringBuilder[alphabet.size()];
        for (int key = 0; key < alphabet.size(); key++) {
            result[key] = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char symbol = text.charAt(i);
                if (Character.isUpperCase(symbol)) {
                    symbol = Character.toLowerCase(symbol);
                    if (alphabet.contains(symbol)) {
                        int index = alphabet.indexOf(symbol);
                        int shift = shift(index, key);
                        result[key].append(Character.toUpperCase(alphabet.get(shift)));
                    } else {
                        result[key].append(Character.toUpperCase(symbol));
                    }
                } else {
                    if (alphabet.contains(symbol)) {
                        int index = alphabet.indexOf(symbol);
                        int shift = shift(index, key);
                        result[key].append(alphabet.get(shift));
                    } else {
                        result[key].append(symbol);
                    }
                }
            }
        }
        return result;
    }
}
