package code.User_Management;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import code.Exceptions.UserCreationError;
import code.General_Support.Shared_Connection;

class Account {
    private String Username;
    private String Password;
    private int AuthorizationLevel=3;
    
    public Account(String username, String password)
    {
    	Username=username;
    	Password=password;
    }
    
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
    
    public boolean verifyPassword() throws SQLException
    {
    	Connection con=Shared_Connection.getSharedConnection();
		String query_authenticate="SELECT * FROM USER_DATA WHERE USERNAME=?";
		PreparedStatement pst=con.prepareStatement(query_authenticate);
		pst.setString(1, Username);
		ResultSet rst=pst.executeQuery();
		rst.next();
	    if(Password.equals(rst.getString("Login_Password")))
	    {
		   return true;
	    }
    	return false;
    }
    
    public int getAuthorizationLevel()
    {
    	//Code to obtain AuthorizationLevel
    	return AuthorizationLevel;
    }
    
    public void setAuthorizationLevel(int a)
    {
    	AuthorizationLevel=a;
    }
    
    public String getUsername() 
    {
    	return Username;
    }
 
    public void createAccount(int a) throws SQLException, UserCreationError, NoSuchAlgorithmException
    {
    	if(verifyUserID())
    	{
    		throw new UserCreationError("Username already exists");
    	}
    	else
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
			pst.setString(3, PasswordHash);
			pst.setInt(4, AuthorizationLevel);
			pst.execute();
            
    	}
    }
}
