import java.util.List;

public class Cryptography {
    protected String readFilePath;
    protected String writeFilePath;
    protected int key;
    protected Language language;
    protected List<Character> alphabet;

    public Cryptography(String readFilePath, String writeFilePath, int key, Language language) {
        this.readFilePath = readFilePath;
        this.writeFilePath = writeFilePath;
        this.key = key;
        this.language = language;
    }

    public Cryptography(String readFilePath, String writeFilePath, Language language) {
        this.readFilePath = readFilePath;
        this.writeFilePath = writeFilePath;
        this.language = language;
    }
}
