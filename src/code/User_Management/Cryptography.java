package code.User_Management;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Handles the encryption of the Password
public class Cryptography 
{
	//Generates the Hashcode for a given String using SHA-256 Algorithm
   private static String generateHash(String StringtoEncrypt) throws NoSuchAlgorithmException
   {
	  
	   MessageDigest msgdigest=MessageDigest.getInstance("SHA-256");
	   msgdigest.update(StringtoEncrypt.getBytes());
	   String encryptedString = new String(msgdigest.digest());
	   return encryptedString;
	   
   }
   
   //Returns the Password Hash for the given Password
   public static String getPasswordHash(String Password) throws NoSuchAlgorithmException
   {
	   return generateHash(Password);
   }
}
