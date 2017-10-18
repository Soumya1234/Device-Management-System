
package code.User_Management;
import java.sql.*;

import code.General_Support.Shared_Connection;

//This class contains the data about the user logged into the Application
public class Active_User {
	
  private static User active_user;
 
  //Sets the user as Active User
  public static void setUser(User a)
  {
	  active_user=a;
  }
  
  //Returns the username of the Active User
  public static String getUserName()
  {
	  return active_user.Username();
  }
 
  //Returns the Authorization Level of the Active user
  //Used for granting necessary access to different features of the Application
  public static int getAuthorizationLevel()
  {   
	  return active_user.AuthorizationLevel();
  }
  
 
}
