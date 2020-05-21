package view;

import java.util.Scanner;
import controller.*;

public class GameView {
	public static void mainScreen()
	{
		System.out.println("1 - Login");
		System.out.println("2 - Signup");
		System.out.println("Other - Exit");
		Scanner s = new Scanner(System.in);
		String select = s.nextLine();
		switch (select)
		{
		case "1":
			Controller.loginController();
			break;
		case "2":
			Controller.signupController();
			break;
	    default:
	    	s.close();
	    	System.exit(0); 
		}
	}
	public static void LoginScreen(StringBuilder username,StringBuilder password)
	{
		Scanner s = new Scanner(System.in);
		System.out.println("~Login~");
		System.out.println("Enter Username: ");
		username.append(s.nextLine());
		System.out.println("Enter Password: ");
		password.append(s.nextLine());
	}
	public static void SignUpScreen(StringBuilder username,StringBuilder password)
	{
		Scanner s = new Scanner(System.in);
		System.out.println("~SignUp~");
		System.out.println("Enter Username: ");
		username.append(s.nextLine());
		System.out.println("Enter Password: ");
		password.append(s.nextLine());
	}
}
