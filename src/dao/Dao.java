package dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	
	public int insert(T t) throws IllegalArgumentException, IllegalAccessException, SQLException;
	public int remove(T t);
	public int update(T t);
	public int delete(T t);
	public T find(QueryFilter filter);
	public List<T> list();
	public List<T> query(SQLQueryDeprecated sql);
	
}