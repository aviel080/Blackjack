package bj;

public class SignUpManager {
	
	public void signNewUser(String userName,String password) throws Exception
	{
		userNameValidation(userName);
		passwordValidation(password);
		userNameAvailable(userName);
		User newUser = new User (userName,password);
		//Write to file;	
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
		throw new Exception("null ID");
	}

}
