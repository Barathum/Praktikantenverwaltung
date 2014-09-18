package praktikantenverwaltung;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHAtoTXTFile {
//	private static Cryptor _crypt = new Cryptor();
	
	public void signup(String password) {
        String hashedPassword = generateHash(password);
        keyToFile(hashedPassword);
    }
	public void keyToFile(String password){
	      try {
	          File keyFile = new File("db/keyfile.txt");
	          FileWriter keyStream = new FileWriter(keyFile);
	          keyStream.write(password.toString());
	          keyStream.close();
	      } catch (IOException e) {
	          System.err.println("Failure writing key to file");
	          e.printStackTrace();
	      }

	  }
	public Boolean login(String filepath, String password) {
        Boolean isAuthenticated = false;

        // remember to use the same SALT value use used while storing password
        // for the first time.
        String hashedPassword = generateHash(password);

        String storedPasswordHash = null;
		try {
			storedPasswordHash = new Scanner(new File(filepath)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			System.out.println("Datei fehlt!");
			e.printStackTrace();
		}
        if(hashedPassword.equals(storedPasswordHash)){
            isAuthenticated = true;
        }else{
            isAuthenticated = false;
        }
        return isAuthenticated;
    }
	public String getHash(String filepath){
		try {
			return new Scanner(new File(filepath)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	public String generateHash(String input) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
//            md.update(salt.getBytes());
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
