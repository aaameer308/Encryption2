package New;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class DoubleEncryption extends ShiftUpEncryption{

    private  byte key1;
    private  byte key2;
    private  byte inv_key1;
    private  byte inv_key2;
    static int formod=256;  // help fining the inverse key
    private ShiftUpEncryption ENCALG;// the algorithm  that will run

    public  DoubleEncryption(ShiftUpEncryption EA)
    {
        this.ENCALG=EA;

    }

    public ShiftUpEncryption getENCALG() {
        return ENCALG;
    }

    public void setENCALG(ShiftUpEncryption ENCALG) {
        this.ENCALG = ENCALG;
    }

    public byte getInv_key1() {
        return inv_key1;
    }

    public byte getInv_key2() {
        return inv_key2;
    }

    public byte getKey1() {
        return key1;
    }

    public byte getKey2() {
        return key2;
    }

    public void setInv_key1(byte inv_key1) {
        this.inv_key1 = inv_key1;
    }

    public void setInv_key2(byte inv_key2) {
        this.inv_key2 = inv_key2;
    }

    @Override
    public void setKey(byte key) {
        this.key1=key;
    }

    public void setKey1(byte key1) {
        this.key1 = key1;
    }

    public void setKey2(byte key2) {
        this.key2 = key2;
    }

    public void Encryption(byte[] data) {

        if (this.ENCALG instanceof ShiftMultiplyEncryption) {
            ((ShiftMultiplyEncryption) this.ENCALG).setKey(this.key1);  // save the  key
            ((ShiftMultiplyEncryption) this.ENCALG).Encryption(data); /// encryption

            ((ShiftMultiplyEncryption) this.ENCALG).setKey(this.key2);  // save the key
            ((ShiftMultiplyEncryption) this.ENCALG).Encryption(data);  // encryption 2

        }
        else
            if (this.ENCALG instanceof ShiftUpEncryption)
            {
                ((ShiftUpEncryption) this.ENCALG).setKey(this.key1);//  save the  key
                ((ShiftUpEncryption) this.ENCALG).Encryption(data); /// encryption

                ((ShiftUpEncryption) this.ENCALG).setKey(this.key2);  // save the key
                ((ShiftUpEncryption) this.ENCALG).Encryption(data); /// encryption
            }

    }

    public void Decryption(byte[] data) {
        if (this.ENCALG instanceof ShiftMultiplyEncryption) {
            ((ShiftMultiplyEncryption) this.ENCALG).setKey_inverse(this.inv_key1);   //  save the inv  key
           // System.out.println("the inv_key1  is " +  inv_key1);
            ((ShiftMultiplyEncryption) this.ENCALG).Decryption(data); // / decryption

            ((ShiftMultiplyEncryption) this.ENCALG).setKey_inverse(this.inv_key2);  //  save the inv  key
          //  System.out.println("the inv_key2  is " +  inv_key2);
            ((ShiftMultiplyEncryption) this.ENCALG).Decryption(data); // / decryption
        }
        else
        if (this.ENCALG instanceof ShiftUpEncryption)
        {
            ((ShiftUpEncryption) this.ENCALG).setKey(key2); //  save the  key
            ((ShiftUpEncryption) this.ENCALG).Decryption(data); // / decryption
            ((ShiftUpEncryption) this.ENCALG).setKey(key1);  //  save the  key
            ((ShiftUpEncryption) this.ENCALG).Decryption(data); // / decryption
        }


    }

    public byte[] CreateKeyInDoubleForShiftUp(String KeyFile)
    {

        byte  key[] = new  byte[3];
        int temp[] = new  int[3];
        int i=0;

        try{  /// read the key from the file
            Scanner scanner = new Scanner(new File(KeyFile));
            while(scanner.hasNextInt())
            {
                temp[i]=scanner.nextInt();
                key[i++]  = (byte)temp[i];

            }

            System.out.println( " the key from the file is : " + temp[0]);
            System.out.println( " the key from the file is : " + temp[1]);

        }catch (FileNotFoundException e) {
            System.out.println("An error in CreateKeyInDoubleForShiftUp.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  key;

    }

    public byte[] CreateKeyInDoubleForShiftMultiplyUp()
    {

        int rand_int1=0;
        Random rand = new Random();
        int key= rand.nextInt(10);
        int temp= modInverse(key);
        byte[] finalKeyAndInverse=new byte[2];

        while(temp==1)  // have no inverse number
        {
            key= rand.nextInt(6);
            temp= modInverse(key);

        }

        System.out.println("key is  " + key    );
        System.out.println(" inverse key is " + temp );
        finalKeyAndInverse[0]=(byte ) key;
        finalKeyAndInverse[1]=(byte ) temp;
        return finalKeyAndInverse;


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
