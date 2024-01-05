import java.awt.*;

public class CryptoAnalyzer {
    public static void main(String[] args) {
        Encryption encryption = new Encryption("F:\\Test1\\Text.txt","F:\\Test1\\1.txt", 3);
        encryption.doEncrypt();
        Decryption decryption = new Decryption("F:\\Test1\\1.txt","F:\\Test1\\2.txt",3);
        decryption.doDecrypt();

    }
}
