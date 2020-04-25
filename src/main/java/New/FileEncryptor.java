package New;

import java.io.*;

public class FileEncryptor {

    private ShiftUpEncryption EA;

    public FileEncryptor(ShiftUpEncryption EA)
    {
        this.EA = EA;
    }// End Constructor


    public void encryptFiles(String OrginalFilePath,String OutPutFilePath,String KeyPath)
    {
        InputStream is = null;
        OutputStream os = null;
        byte[] buffer = new byte[1024];
        int length=0;
        try {
            File ToRead = new File(OrginalFilePath);
            is = new FileInputStream(ToRead);

            ////// create and save the key
            if (this.EA instanceof ShiftMultiplyEncryption)
            {
                ((ShiftMultiplyEncryption) this.EA).CreateKeyAndInverse();
            }
            else if (this.EA instanceof DoubleEncryption) {

                if(((DoubleEncryption) this.EA).getENCALG()instanceof ShiftMultiplyEncryption )// we are in double and the ShiftMultiplyEncryption will work
                {
                    byte[] key=((DoubleEncryption) this.EA).CreateKeyInDoubleForShiftMultiplyUp();
                    ((DoubleEncryption) this.EA).setKey1(key[0]);
                    ((DoubleEncryption) this.EA).setInv_key1(key[1]);
                    key=((DoubleEncryption) this.EA).CreateKeyInDoubleForShiftMultiplyUp();
                    ((DoubleEncryption) this.EA).setKey2(key[0]);
                    ((DoubleEncryption) this.EA).setInv_key2(key[1]);

                }
                else if(((DoubleEncryption) this.EA).getENCALG()instanceof ShiftUpEncryption ) // we are in double and the ShiftUpEncryption will work
                {
                    byte key[]= ((DoubleEncryption) this.EA).CreateKeyInDoubleForShiftUp(KeyPath); // creating a key
                    ((DoubleEncryption) this.EA).setKey1(key[0]);  // save the key
                    //key= ((DoubleEncryption) this.EA).CreateKeyInDoubleForShiftUp(KeyPath); //create sec key
                    ((DoubleEncryption) this.EA).setKey2(key[1]); // save the second key
                }
            } else {
                ((ShiftUpEncryption) this.EA).CreateKey(KeyPath);
            }

            /// create the encrypted file
            File ToWrite = new File(OutPutFilePath);
            os = new FileOutputStream(ToWrite);

            while ((length = is.read(buffer)) > 0)
            {
                EA.Encryption(buffer);
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error in encryptFiles.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error in encryptFiles.");
            e.printStackTrace();
        }
    }


    public void decryptFiles(String OrginalFilePath,String OutPutFilePath)//String KeyPath)
    {
        InputStream is = null;
        OutputStream os = null;

        byte[] buffer = new byte[1024];
        int length=0;
        try {
            File ToRead = new File(OrginalFilePath);
            is = new FileInputStream(ToRead);

            /// create the decrypted file
            File ToWrite = new File(OutPutFilePath);
            os = new FileOutputStream(ToWrite);

            while ((length = is.read(buffer)) > 0)
            {
                EA.Decryption(buffer);
                os.write(buffer, 0, length);
            }

            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
