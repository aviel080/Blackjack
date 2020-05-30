package model;

import java.util.Set;

public class SignUpManager {

	public static void signNewUser(String userName,String password) throws Exception
	{
		userNameValidation(userName);
		passwordValidation(password);
		userNameAvailable(userName);
		User newUser = new User(userName,password);
		FileManager.Update(newUser);
	}	
	private static void userNameValidation(String userName)throws Exception
	{
		if (userName.length() < 3)
			throw new Exception("userName length less than 3 letters");
		if (userName.length() > 10)
			throw new Exception("userName length more than 10 letters");
	}
	
	private static void passwordValidation(String password)throws Exception
	{
		if (password.length() < 3)
			throw new Exception("password length less than 3 letters");
		if (password.length() > 10)
			throw new Exception("password length more than 10 letters");
	}
	
	private static void userNameAvailable(String userName)throws Exception
	{
	    Set<User> users = FileManager.read();
		for (User a : users)
		{
			if (a.getUserName().toUpperCase().equals(userName.toUpperCase()))
				throw new Exception("Username Already Exists");
		}
	}
}
