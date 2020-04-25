package New;

import java.util.Random;

public class ShiftMultiplyEncryption extends ShiftUpEncryption {


    private ShiftMultiplyEncryption EA;
    private  byte key;
    private  byte key_inverse;
    static int formod=256; // help fining the inverse key



   // public ShiftMultiplyEncryption()//byte key)
   // {}

    public ShiftMultiplyEncryption getEA() {
        return EA;
    }

    public void setEA(ShiftMultiplyEncryption EA) {
        this.EA = EA;
    }

    public byte getKey() {
        return key;
    }

    public void setKey(byte key) {
        this.key = key;
    }

    public byte getKey_inverse() {
        return key_inverse;
    }

    public void setKey_inverse(byte key_inverse) {
        this.key_inverse = key_inverse;
    }

    public void Encryption(byte[] data) {

        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (data[i] * this.key);
        }
    }

    public void Decryption(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = ((byte) ((data[i] * this.key_inverse)));
        }
    }

    public void CreateKeyAndInverse()// check if the key have inverse
    {

        int rand_int1=0;
        Random rand = new Random();
        int key= rand.nextInt(10);
        int temp= modInverse(key);

        while(temp==1)  // have no inverse number
        {
            key= rand.nextInt(10);
            temp= modInverse(key);

        }

        System.out.println("key is  " + key  );
        System.out.println(" inverse key is " + temp );
        this.key=(byte ) key;
        this.key_inverse= (byte) temp;

    }

    public int modInverse(int key)
    {
            int m=formod;
            int a =  key;
            a= a % m;
            for (int x = 1; x < m; x++)
                if ((a * x) % m == 1)
                    return x;
            return 1;
    }






}
