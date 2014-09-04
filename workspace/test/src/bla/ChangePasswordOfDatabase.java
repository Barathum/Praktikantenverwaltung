package bla;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.commons.io.FileUtils;

import praktikantenverwaltung.Cryptor;

public class ChangePasswordOfDatabase {
	private static Cryptor _crypt = new Cryptor();
	public static void main( String[] args ) {

        try {
        	_crypt.decryptFile( "db/PraktikantenDB.db", "1234" );
            _crypt.encryptFile( "db/PraktikantenDB.db", "Bacard1" );
            FileUtils.forceDelete(new File("db/PraktikantenDB.db.decrypted.db"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
