package New;

public interface EncryptionAlgorithm {


    //public byte getKey();

    public void setKey(byte key);

    public void Encryption(byte[] data );

    public void Decryption(byte[] data );

}
