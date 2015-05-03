package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Entity;
import annotation.Id;

public class GenericDao implements Dao<Entity>{
	
	private static Connection connection;
	private static GenericDao genericDao;
	private static DatabaseDefinitions definitions;
	
	private GenericDao() throws SQLException{		
		definitions= (definitions == null)? new DatabaseDefinitions() : definitions;
		connection = (connection == null)? ConnectionProvider.getConnection() : connection;		
	}
	
	public static GenericDao getDao() throws SQLException{			
			return (genericDao == null)? genericDao = new GenericDao() : genericDao;
	}

	public static void setDefinitions(DatabaseDefinitions definitions) {
		GenericDao.definitions = definitions;
	}

	@Override
	public int insert(Entity entity) throws IllegalArgumentException, IllegalAccessException, SQLException {
		SQLQueryDeprecated consulta = new SQLQueryDeprecated();		
		Field [] fields = entity.getClass().getDeclaredFields();
		List<Field> fieldsToInsert = new ArrayList<Field>();
		
		for(Field field : fields){
			field.setAccessible(true);			
			if((!isFieldEmpty(field, entity)) && field.getAnnotation(Id.class) == null){				
				fieldsToInsert.add(field);
				consulta.adicionarValor(field.get(entity));
			}			
		}
		
		consulta.insertInto(entity.getClass())
		.fields(fieldsToInsert)
		.values();
		
		System.out.println(consulta.getSql());
		
		PreparedStatement statement = ConnectionProvider.getConnection().prepareStatement(consulta.getSql(), PreparedStatement.RETURN_GENERATED_KEYS);
		
		int indiceParametro = 1;		
		for(Object valor : consulta.getValores()){
			statement.setObject(indiceParametro, valor);
			indiceParametro++;
		}
		
		statement.executeUpdate();
		ResultSet set = statement.getGeneratedKeys();
		set.next();
		return set.getInt(1);		
	}
	
	private boolean isFieldEmpty(Field field, Entity entity) throws IllegalArgumentException, IllegalAccessException{
		Class type = field.getType();
		Object value = field.get(entity);
		
		if(type == boolean.class && Boolean.FALSE.equals(value)){
			return true;
		}
		
		if(type.isPrimitive() && ((Number) value).doubleValue() == 0){
			return true;
		}
		
		if(!type.isPrimitive() && value == null){
			return true;
		}
		
		return false;		
	}

	@Override
	public int remove(Entity t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Entity t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Entity t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Entity find(QueryFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entity> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entity> query(SQLQueryDeprecated sql) {
		// TODO Auto-generated method stub
		return null;
	}	
}