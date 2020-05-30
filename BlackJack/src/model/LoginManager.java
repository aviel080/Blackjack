package model;

import java.util.Set;

public class LoginManager {
	public static User userLogin(String userName,String password) throws Exception
	{
		User fileUser = userExists(userName);
		passwordMatch(fileUser, password);
		return fileUser;
	}
	private static User userExists(String userName)throws Exception
	{
		Set<User> users = FileManager.read();
		for (User a : users)
		{
			if (a.getUserName().equals(userName))
				return a;
		}
		throw new Exception("Username Does Not Exists");
	}
	private static void passwordMatch(User user,String password)throws Exception
	{
		if (!(user.getPassword().equals(password)))
			throw new Exception("Invalid Password");
	}
}
