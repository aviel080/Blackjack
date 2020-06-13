package model;

import java.util.Set;

public class UserRepository {
	private static UserRepository userRepository;
	private FileManager<User> fileManager;
	private Set<User> users;
	@SuppressWarnings("unchecked")
	private UserRepository()
	{
		fileManager = new FileManager<User>("allUsers.dat");
		try {
		users = (Set<User>) fileManager.read();
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public static UserRepository BuildUserRepository()
	{	
		if (userRepository == null)
			userRepository = new UserRepository();
		return userRepository;
	}
	public void Update(User user)
	{
		try{
		users.remove(user);//old
		users.add(user);//new
		fileManager.write(users);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	public int getSize()
	{
		return users.size();
	}
	public Set<User> getUsers()
	{
		return users;
	}
	public User getUser(String userName) 
	{
		for (User a : users)
		{
			if (a.getUserName().equals(userName))
				return a;
		}
		return null;
	}
}
