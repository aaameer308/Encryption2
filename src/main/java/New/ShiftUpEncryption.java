package New;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ShiftUpEncryption implements EncryptionAlgorithm {


    private  byte key;

    public ShiftUpEncryption()//byte key)
    {
        /* this.key=key; */
    }

    public byte getKey() {
        return this.key;
    }

    public void setKey(byte key) {
        this.key = key;
    }

    public void Encryption(byte[] data ) // Encryption Algorithm
    {
       //CreateKey();
        for (int i = 0; i < data.length; i++)
        {
            data[i] = (byte) ( data[i] +  this.key);

        }
    }

    public void Decryption(byte[] data) // decryption Algorithm
    {
        for (int i = 0; i < data.length; i++)
        {
            data[i] = (byte) ( data[i] -  this.key);
        }
    }

    public void CreateKey(String KeyFile)
    {
        int  key =0;

        try{  /// read the key from the file
            Scanner scanner = new Scanner(new File(KeyFile));
            scanner.hasNextInt();
            key = scanner.nextInt();

        }catch (FileNotFoundException e) {
            System.out.println("An error in CreateKeyInDoubleForShiftUp.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( " the key from the file is : " + key);
       this.key= (byte) key;


    }




}
