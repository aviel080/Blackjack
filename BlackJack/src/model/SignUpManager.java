package model;

import java.util.ArrayList;
import java.util.List;

import model.User.UserBuilder;

public class SignUpManager {
	private FileManager fileManager = FileManager.buildFileManager();
	
	public void signNewUser(String userName,String password) throws Exception
	{
		userNameValidation(userName);
		passwordValidation(password);
		userNameAvailable(userName);
		UserBuilder newUser = new UserBuilder();
		newUser.userName(userName);
		newUser.password(password);
		User user =	newUser.build();
		fileManager.Update(user,"allUsers.dat");
	    fileManager.Update(new Statistic(user.getuserId()), "statistics.dat");
	}	
	private void userNameValidation(String userName)throws Exception
	{
		if (userName.length() < 3)
			throw new Exception("userName length less than 3 letters");
		if (userName.length() > 10)
			throw new Exception("userName length more than 10 letters");
	}
	
	private void passwordValidation(String password)throws Exception
	{
		if (password.length() < 3)
			throw new Exception("password length less than 3 letters");
		if (password.length() > 10)
			throw new Exception("password length more than 10 letters");
	}
	
	private void userNameAvailable(String userName)throws Exception
	{
		@SuppressWarnings("unchecked")
		List<User> users = (ArrayList<User>)fileManager.read("allUsers.dat");
		if (users == null)
			users = new ArrayList<User>();
		for (User a : users)
		{
			if (a.getUserName().toUpperCase().equals(userName.toUpperCase()))
				throw new Exception("Username Already Exists");
		}
	}
}
