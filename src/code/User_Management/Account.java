package code.User_Management;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import code.Exceptions.UserCreationError;
import code.General_Support.Shared_Connection;

//This class manages all the User Account related functionality like Authentication, Creation of New Account
class Account {
    private String Username;
    private String Password;
    private int AuthorizationLevel=3;
    
    //Constructor for the Account class: initializes the Username and Password
    public Account(String username, String password)
    {
    	Username=username;
    	Password=password;
    }
 
    //This function verifies the existence of the Username from the User Database
    public boolean verifyUserID() throws SQLException
    {
    	Connection con=Shared_Connection.getSharedConnection();
		String query_authenticate="SELECT * FROM USER_DATA WHERE USERNAME=?";
		PreparedStatement pst=con.prepareStatement(query_authenticate);
		pst.setString(1, Username);
		ResultSet rst=pst.executeQuery();
		if(rst.next())
		{
			return true;
		}
    	return false;
    }
    
    //This function verifies the encrypted Password from the User Database
    public boolean verifyPassword() throws SQLException, NoSuchAlgorithmException
    {
    	Connection con=Shared_Connection.getSharedConnection();
		String query_authenticate="SELECT * FROM USER_DATA WHERE USERNAME=?";
		PreparedStatement pst=con.prepareStatement(query_authenticate);
		pst.setString(1, Username);
		ResultSet rst=pst.executeQuery();
		rst.next();
		System.out.println(Password);
		System.out.println(rst.getString("Login_Password"));
	    if(Password.equals(rst.getString("Login_Password")))
	    {
		   return true;
	    }
    	return false;
    }
    
    //This function returns the AuthorizationLevel of the User from the User Database
    public int getAuthorizationLevel()
    {
    	//Code to obtain AuthorizationLevel
    	return AuthorizationLevel;
    }
   
    //This function returns the Username associated with the User 
    public String getUsername() 
    {
    	return Username;
    }
    
    //This function creates a new Account in the User Database
    public void createAccount(int a) throws SQLException, UserCreationError, NoSuchAlgorithmException, Exception
    {
    	if(verifyUserID()) //Checking if the given Username already exists in the user database
    	{
    		throw new UserCreationError("Username already exists");
    	}
    	else //Creates the user only if the username does not already exists in the User Database
    	{
    		AuthorizationLevel=a;
    		Connection con=Shared_Connection.getSharedConnection();
			int Last_User_ID=0;
            Statement myStat=null;
            myStat=con.createStatement();
            ResultSet myRest=myStat.executeQuery("SELECT * FROM USER_DATA ORDER BY USER_ID DESC LIMIT 1");
            if(myRest.next())
            {
               Last_User_ID=myRest.getInt("USER_ID");
            }
            int New_User_ID=Last_User_ID+1;
            String PasswordHash=Cryptography.getPasswordHash(Password);
			String create_user_query="INSERT INTO USER_DATA VALUES(?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(create_user_query);
			pst.setInt(1,New_User_ID);
			pst.setString(2, Username);
			pst.setString(3, Password);
			pst.setInt(4, AuthorizationLevel);
			pst.execute();
            
    	}
    }
}
