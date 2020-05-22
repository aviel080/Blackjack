package model;

import java.util.HashSet;
import java.util.Set;

public class LoginManager {
	private Set<User> users = new HashSet<User>();
	public User userLogin(String userName,String password) throws Exception
	{
		users = FileManager.read();
		User fileUser = userExists(userName);
		passwordMatch(fileUser, password);
		return fileUser;
	}
	private User userExists(String userName)throws Exception
	{
		User checkUser = new User(userName, "");
		if (!(users.contains(checkUser)))
			throw new Exception("Username Does Not Exists");
		for (User a : users)
		{
			if (a.equals(checkUser))
				return a;
		}
		throw new Exception("Somthing Went Wrong...");
	}
	private void passwordMatch(User user,String password)throws Exception
	{
		if (!(user.getPassword().equals(password)))
			throw new Exception("Invalid Password");
	}
}
