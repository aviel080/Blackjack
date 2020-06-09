package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	private static FileManager fileManager= null;
	
	private FileManager()	{
		
	}
	public static FileManager buildFileManager()
	{
		if (fileManager == null)
			fileManager = new FileManager();
		return fileManager;
	}
	
	public void write(Object object, String filename) throws IOException {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
			objectOutputStream.writeObject(object);
		}
	}
	
	public Object read(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
		if ((new File(filename)).exists() == false)
			return null;
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
			return objectInputStream.readObject();
		}
	}
	public <T> void Update(T object,String filename)
	{
		try{
		@SuppressWarnings("unchecked")
		List<T> objects = (ArrayList<T>) read(filename);
		if (objects == null)
			objects = new ArrayList<T>();
		objects.remove(object);//old
		objects.add(object);//new
		write(objects,filename);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}