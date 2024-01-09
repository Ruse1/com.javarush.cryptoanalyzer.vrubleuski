import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Decryption extends Cryptography {

    public Decryption(String readFilePath, String writeFilePath, int key, Language language) {
        super(readFilePath, writeFilePath, key, language);
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
        key = Math.abs(key % alphabet.size());
        int result = index - key;
        if (result < 0) {
            return alphabet.size() + (result % alphabet.size());
        } else {
            return result;
        }
    }
}
