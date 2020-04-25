package New;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ShiftUpEncryption enc =new ShiftUpEncryption();
        DoubleEncryption DU = new DoubleEncryption(enc);

        FileEncryptor fi = new FileEncryptor(enc);

        fi.encryptFiles("C:\\Users\\2020\\encrypt\\amir.jpg","C:\\Users\\2020\\encrypt\\amir_encrypted.jpg","C:\\Users\\2020\\encrypt\\key.txt");
        fi.decryptFiles("C:\\Users\\2020\\encrypt\\amir_encrypted.jpg","C:\\Users\\2020\\encrypt\\amir_encrypted_decrypted.jpg");//,"C:\\Users\\2020\\encrypt\\key.txt");

    }
}
