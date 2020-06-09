package model;

import java.util.ArrayList;
import java.util.List;

public class LoginManager {
	private FileManager fileManager = FileManager.buildFileManager();
	public User userLogin(String userName,String password) throws Exception
	{
		User fileUser = userExists(userName);
		passwordMatch(fileUser, password);
		return fileUser;
	}
	private User userExists(String userName)throws Exception
	{
		@SuppressWarnings("unchecked")
		List<User> users = (ArrayList<User>)fileManager.read("allUsers.dat");
		for (User a : users)
		{
			if (a.getUserName().equals(userName))
				return a;
		}
		throw new Exception("Username Does Not Exists");
	}
	private void passwordMatch(User user,String password)throws Exception
	{
		if (!(user.getPassword().equals(password)))
			throw new Exception("Invalid Password");
	}
}
