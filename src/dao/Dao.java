package dao;

import java.sql.SQLException;

public interface Dao<T> {
	
	public int insert(T t) throws IllegalArgumentException, IllegalAccessException, SQLException;
	public int remove(T t);
	public int update(T t);
	public int delete(T t);
	

}
