package code.User_Management;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import code.Exceptions.AuthenticationError;
import code.Exceptions.UserCreationError;
import code.General_Support.Shared_Connection;

//The User class defines provides functionality for LogIn and LogOut.
//This class represents a single user
//This class also exposes the Username and user authorizationlevel via Username() and AuthorizationLevel() methods
//This class "has an" Account object as a private member which facilitates all the user Account related functionality  
public class User {
   private Account a;
   
   //Public constructor initializes the associated account with Username and Password
   public User(String Username, String Password)
   {
	   a=new Account(Username,Password);
   }
   
   //This auhtenticates the user using verifyUserID() and verifyPassword() methods of the account object and
   //returns true on success
   public boolean login() throws AuthenticationError, SQLException, NoSuchAlgorithmException
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
   
   //This function logs the user out of the application by closing the shared connection
   public void logout() throws SQLException
   {
	   Shared_Connection.closeSharedConnection();
   }
   
   //Returns the Username using the Account object's getUsername() method
   public String Username()
   {
	   return a.getUsername();
   }
   
   //Returns the Authorization level using the Account object's getAuthorizationLevel() method
   public int AuthorizationLevel()
   {
	   return a.getAuthorizationLevel();
   }
   
   //This function creates a new user using the Account object's createAccount() method
   public void create(int AuthLevel) throws NoSuchAlgorithmException, SQLException, UserCreationError, Exception
   {
	   a.createAccount(AuthLevel);
   }
}
