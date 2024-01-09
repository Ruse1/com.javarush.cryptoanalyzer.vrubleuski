import java.io.*;
import java.util.List;

public class BruteForce {
    private String readFilePath;
    private String writeFilePath;
    private Language language;
    private List<Character> alphabet;

    public BruteForce(String readFilePath, String writeFilePath) {
        this.readFilePath = readFilePath;
        this.writeFilePath = writeFilePath;
        this.language = Language.DEFAULT;
    }

    public BruteForce(String readFilePath, String writeFilePath, Language language) {
        this.readFilePath = readFilePath;
        this.writeFilePath = writeFilePath;
        this.language = language;
    }

    public void toDecryptionBruteForce() {
        alphabet = Vocabulary.checkVocabulary(language);
        StringBuilder stringBuilder = readData();
        StringBuilder[] allCombinations = getAllCombinations(stringBuilder);
        for (StringBuilder sb : allCombinations) {
            String[] strings = sb.toString().split(" ");
            int lengthWord = (sb.length() - strings.length) / strings.length;
            System.out.println(lengthWord);
            if (lengthWord > 3 && lengthWord < 9) {
                System.out.println(sb);
                System.out.println("*".repeat(20));
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
//                            wr.write(Character.toUpperCase(alphabet.get(shift)));
                        result[key].append(Character.toUpperCase(alphabet.get(shift)));
                    } else {
//                            wr.write(Character.toUpperCase(symbol));
                        result[key].append(Character.toUpperCase(symbol));
                    }
                } else {
                    if (alphabet.contains(symbol)) {
                        int index = alphabet.indexOf(symbol);
                        int shift = shift(index, key);
//                            wr.write(alphabet.get(shift));
                        result[key].append(alphabet.get(shift));
                    } else {
//                            wr.write(symbol);
                        result[key].append(symbol);
                    }
                }
            }
        }
        return result;
    }
}
/*
  try (FileReader fr = new FileReader(readFilePath)) {
                for (int key = 2; key < alphabet.size(); key++) {
                    wr.write("Текст№" + key);
                    while (fr.ready()) {
                        int result = fr.read();
                        char symbol = (char) result;
                        System.out.println(key);
                        if (Character.isUpperCase(symbol)) {
                            symbol = Character.toLowerCase(symbol);
                            if (alphabet.contains(symbol)) {
                                int index = alphabet.indexOf(symbol);
                                int shift = shift(index, key);
                                wr.write(Character.toUpperCase((Character) alphabet.get(shift)));
                            } else {
                                wr.write(Character.toUpperCase(symbol));
                            }
                        } else {
                            if (alphabet.contains(symbol)) {
                                int index = alphabet.indexOf(symbol);
                                int shift = shift(index, key);
                                wr.write((char) alphabet.get(shift));
                            } else {
                                wr.write(symbol);
                            }
                        }
                    }
                }
 */
