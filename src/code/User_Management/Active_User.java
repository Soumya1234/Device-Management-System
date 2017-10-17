//This class contains the data about the user logged into the Application
package code.User_Management;
import java.sql.*;

import code.General_Support.Shared_Connection;

public class Active_User {
	
  private static User Active_User;
 
  
  /**
   * Sets the User_ID of the Active User Logged in to the Application 
   * @param ID
   */
  public static void setUser(User a)
  {
	  Active_User=a;
  }
  
  public static String getUserName()
  {
	  return Active_User.Username();
  }
  /**
   * Will be used for determining Permission Level for each Method in Business Logic
   * @return Permission Level as integer
   */
  public static int getPermissionLevel()
  {   /***
	  try{
		  Connection con=Shared_Connection.getSharedConnection();
		  String query_permission_level="SELECT PERMISSION_LEVEL FROM USER_DATA WHERE USER_ID=?";
		  PreparedStatement pst=con.prepareStatement(query_permission_level);
		  //pst.setInt(1, Active_User_ID);
		  ResultSet rst= pst.executeQuery();
		  rst.next();
		  Active_User_Permission_Level=rst.getInt("PERMISSION_LEVEL");
	  }
	  catch (SQLException e)
	  {
		  e.printStackTrace();
	  }
	  ***/
	  return Active_User.AuthorizationLevel();
  }
  
 
}
