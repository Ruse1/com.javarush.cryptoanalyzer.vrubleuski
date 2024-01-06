import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Decryption {
    private String readFilePath;
    private String writeFilePath;
    private int key;
    private Language language;
    private List<Character> alphabet;

    public Decryption(String readFilePath, String writeFilePath, int key) {
        this.readFilePath = readFilePath;
        this.writeFilePath = writeFilePath;
        this.key = key;
        this.language = Language.DEFAULT;
    }

    public Decryption(String readFilePath, String writeFilePath, int key, Language language) {
        this.readFilePath = readFilePath;
        this.writeFilePath = writeFilePath;
        this.key = key;
        this.language = language;
    }

    public void doDecrypt() {
        alphabet = Vocabulary.checkVocabulary(language);
        try (FileReader fr = new FileReader(readFilePath);
             FileWriter wr = new FileWriter(writeFilePath)) {
            while (fr.ready()) {
                int result = fr.read();
                char symbol = (char) result;
                if (Character.isUpperCase(symbol)) {
                    symbol = Character.toLowerCase(symbol);
                    if (alphabet.contains(symbol)) {
                        int index = alphabet.indexOf(symbol);
                        int shift = shift(index);
                        wr.write(Character.toUpperCase((Character) alphabet.get(shift)));
                    } else {
                        wr.write(Character.toUpperCase(symbol));
                    }
                } else {
                    if (alphabet.contains(symbol)) {
                        int index = alphabet.indexOf(symbol);
                        int shift = shift(index);
                        wr.write((char) alphabet.get(shift));
                    } else {
                        wr.write(symbol);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int shift(int index) {
        key = Math.abs(key);
        int result = index - key;
        if (result < 0) {
            return alphabet.size() + (result % alphabet.size());
        } else {
            return result;
        }
    }
}
