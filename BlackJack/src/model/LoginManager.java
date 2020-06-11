package model;

import java.util.Set;

public class LoginManager {
	private static UserRepository userRepository = UserRepository.BuildUserRepository();
	public User userLogin(String userName,String password) throws Exception
	{
		User fileUser = userExists(userName);
		passwordMatch(fileUser, password);
		return fileUser;
	}
	private User userExists(String userName)throws Exception
	{
		Set<User> users = userRepository.getUsers();
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
