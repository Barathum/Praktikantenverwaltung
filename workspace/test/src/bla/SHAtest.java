package bla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import praktikantenverwaltung.Cryptor;

public class SHAtest {
	private static Cryptor _crypt = new Cryptor();
  /**Record the key to a text file for testing:**/
  private static void keyToFile(String password){
	  
	  
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

    static Map<String, String> DB = new HashMap<String, String>();
    public static final String SALT = "";

    public static void main(String args[]) {
        SHAtest demo = new SHAtest();
        demo.signup("horst", "lol");
        keyToFile((String) DB.get("horst"));
        try {
			_crypt.encryptFile("db/keyfile.txt", "123", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // login should fail because of wrong password.
        if (demo.login("db/keyfile.txt" , "")){
            System.out.println("User login successfull.");
        }
        else{
            System.out.println("user login failed.");
        }
    }

    public void signup(String username, String password) {
        String saltedPassword = SALT +  password;
        String hashedPassword = generateHash(saltedPassword);
        DB.put(username, hashedPassword);
    }

    public Boolean login(String filepath, String password) {
        Boolean isAuthenticated = false;

        // remember to use the same SALT value use used while storing password
        // for the first time.
        String saltedPassword = SALT  + password;
        String hashedPassword = generateHash(saltedPassword);

        String storedPasswordHash = null;
		try {
			storedPasswordHash = new Scanner(new File(filepath)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(hashedPassword.equals(storedPasswordHash)){
            isAuthenticated = true;
        }else{
            isAuthenticated = false;
        }
        return isAuthenticated;
    }

    public static String generateHash(String input) {
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