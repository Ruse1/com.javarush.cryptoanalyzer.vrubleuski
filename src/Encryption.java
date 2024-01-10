
public class Encryption extends Cryptography {

    public Encryption(String readFilePath, String writeFilePath, int key, Language language) {
        super(readFilePath, writeFilePath, key, language);
    }

    public void doEncrypt() {
        HelperClassEncryptionOrDecryptionOrBruteForce helper = new HelperClassEncryptionOrDecryptionOrBruteForce();
        helper.doEncryptionOrDecryption(this.readFilePath, this.writeFilePath, this.key, this.language, "Encryption");
    }

}


