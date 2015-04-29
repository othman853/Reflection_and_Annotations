package app;

import java.sql.SQLException;

import model.Car;
import model.Person;
import dao.GenericDao;

public class Main {
	
	public static void main(String[] args) {		
		
		GenericDao dao = new GenericDao();		
		try {
			dao.insert(new Person(1, "Annotation", 42));
			dao.insert(new Car(1,"Ka", "Ford", "Red"));
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
