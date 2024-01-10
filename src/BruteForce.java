import java.io.*;

public class BruteForce extends Cryptography {

    public BruteForce(String readFilePath, String writeFilePath, Language language) {
        super(readFilePath, writeFilePath, language);
    }

    public void doDecryptionBruteForce() {
        HelperClassEncryptionOrDecryptionOrBruteForce helper = new HelperClassEncryptionOrDecryptionOrBruteForce();
        helper.doDecryptionBruteForce(this.readFilePath, this.writeFilePath, this.language, "BruteForce");
    }
}
