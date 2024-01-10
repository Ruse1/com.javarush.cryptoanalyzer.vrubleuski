
public class Decryption extends Cryptography {

    public Decryption(String readFilePath, String writeFilePath, int key, Language language) {
        super(readFilePath, writeFilePath, key, language);
    }

    public void doDecrypt() {
        HelperClassEncryptionOrDecryptionOrBruteForce helper = new HelperClassEncryptionOrDecryptionOrBruteForce();
        helper.doEncryptionOrDecryption(this.readFilePath, this.writeFilePath, this.key, this.language, "Decryption");
    }
 }
