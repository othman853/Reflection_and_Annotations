package app;

import java.sql.SQLException;

import model.Car;
import model.Person;
import dao.GenericDao;

public class Main {
	
	public static void main(String[] args) {		
		
				
		try {
			
			GenericDao dao = GenericDao.getDao();
			
			dao.insert(new Person(1, "Yasser", 20));
			dao.insert(new Car(1,"A7", "Audi", "White"));
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
