package controller;

import model.LoginManager;
import model.SignUpManager;
import model.User;

public class RegisterContoller {
	private static RegisterContoller registerContoller = null;
	private LoginManager loginManager;
	private SignUpManager signUpManager;
	private User user;
	private RegisterContoller() {
		loginManager= new LoginManager();
		signUpManager= new SignUpManager();
	}
	public static RegisterContoller BuildRegisterContoller()
	{
		if (registerContoller == null)
			registerContoller = new RegisterContoller();
		return registerContoller;
	}
	public User loginController(String username,String password)throws Exception
	{
		user = loginManager.userLogin(username, password);
		return user;
	}
	public void signupController(String username,String password)throws Exception
	{
		signUpManager.signNewUser(username,password);
	}
}
