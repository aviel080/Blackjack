package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

public class FileManager {
	
	private static final String filename = "allUsers.dat";
		
	public static void write(Set<User> object) throws IOException {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
			objectOutputStream.writeObject(object);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Set<User> read() throws FileNotFoundException, IOException, ClassNotFoundException {		
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
			return (Set<User>) objectInputStream.readObject();
		}
	}
	public static void Update(User user)
	{
		try{
		Set<User> users = read();
		users.remove(user);//old
		users.add(user);//new
		write(users);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
}