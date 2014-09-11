package bla;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.commons.io.FileUtils;

import praktikantenverwaltung.Cryptor;
import praktikantenverwaltung.SHAtoTXTFile;

public class ChangePasswordOfDatabase {
	private static Cryptor _crypt = new Cryptor();
	private static SHAtoTXTFile _sha = new SHAtoTXTFile();
	public static void main( String[] args ) {
		try {
			_crypt.decryptFile("db/keyfile.txt", "123", true);
		String oldpw = _sha.getHash("db/keyfile.txt.decrypted.txt");
		String newpw = "yolo";
        	_crypt.decryptFile( "db/PraktikantenDB.db", oldpw  , false);
            _crypt.encryptFile( "db/PraktikantenDB.db", _sha.generateHash(newpw) , false);
            _sha.keyToFile(_sha.generateHash(newpw));
	          try {
				_crypt.encryptFile( "db/keyfile.txt", "123" , true);
				
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            FileUtils.forceDelete(new File("db/PraktikantenDB.db.decrypted.db"));
            FileUtils.forceDelete(new File("db/keyfile.txt.decrypted.txt"));
            FileUtils.forceDelete(new File("db/keyfile.txt"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
