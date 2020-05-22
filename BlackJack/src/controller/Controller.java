package controller;

import model.*;
import view.GameView;

public class Controller {
	private static User user;
	public static void main(String[] args) 
	{
		GameView.mainScreen();
	}
	public int hitController()
	{
		return 0;
	}
	public void doubleController()
	{
		
	}
	public void holdController()
	{
		
	}
	public void splitController()
	{
		
	}
	public void surrenderController()
	{
		
	}
	public static void loginController()
	{
		StringBuilder username = new StringBuilder();
		StringBuilder password = new StringBuilder();
		GameView.LoginScreen(username,password);
		LoginManager sum = new LoginManager();
		try {
			user = sum.userLogin(username.toString(),password.toString());
			System.out.println("Login Succsefully");
			//toplay(user)
		}catch(Exception e){
			System.out.println(e.toString());
		}
		GameView.mainScreen();
	}
	public static void signupController()
	{
		StringBuilder username = new StringBuilder();
		StringBuilder password = new StringBuilder();
		GameView.SignUpScreen(username,password);
		SignUpManager sum = new SignUpManager();
		try {
			sum.signNewUser(username.toString(),password.toString());
			System.out.println("User Added Succsefully");
		}catch(Exception e){	
			System.out.println(e.toString());
		}
		GameView.mainScreen();
	}
	public void playController()
	{
		
	}
	public void depositController()
	{
		
	}
	public void withdrawController()
	{
		
	}
	public void statisticsController()
	{
		
	}
	
}
