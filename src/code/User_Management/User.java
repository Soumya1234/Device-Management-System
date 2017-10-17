package code.User_Management;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import code.Exceptions.AuthenticationError;
import code.Exceptions.UserCreationError;
import code.General_Support.Shared_Connection;

public class User {
   private Account a;
   
   public User(String Username, String Password)
   {
	   a=new Account(Username,Password);
   }
   
   public boolean login() throws AuthenticationError, SQLException
   {
	   if(a.verifyUserID())
	   {
		   if(a.verifyPassword())
		   {
			   Active_User.setUser(this);
			   return true;
		   }
		   else
		   {
			   throw new AuthenticationError("User could not be authenticated");
		   }
	   }
	   else
	   {
		   throw new AuthenticationError("User does not exist");
	   }
   }
   
   public void logout() throws SQLException
   {
	   Shared_Connection.closeSharedConnection();
   }
   
   public String Username()
   {
	   return a.getUsername();
   }
   
   public int AuthorizationLevel()
   {
	   return a.getAuthorizationLevel();
   }
   
   public void create(int AuthLevel) throws NoSuchAlgorithmException, SQLException, UserCreationError
   {
	   a.createAccount(AuthLevel);
   }
}
