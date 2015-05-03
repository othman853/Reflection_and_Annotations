package dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import annotation.FieldName;

public class SQLQueryDeprecated {
	
	private static final String INSERT_INTO = "INSERT INTO ";
	private static final String SELECT 		= "SELECT ";
	private static final String FROM 		= " FROM ";
	private static final String VALUES 		= " VALUES ";
	private static final String WHERE 		= " WHERE ";
	private static final String EQUALS 		= " = ";
	private static final String AND 		= " AND ";
	private static final String OR 			= " OR ";
	
	
	private StringBuilder builder;
	private ArrayList<Object> parameterValues;
	private String [] fields;
	private SQLQueryFilter filter;
	
	public SQLQueryDeprecated() {
		builder = new StringBuilder();
		parameterValues = new ArrayList<Object>();
		fields = new String[1];
	}
	
	public String [] getCampos(){
		return this.fields;
	}
	
	public SQLQueryDeprecated insertInto(String tabela){
		builder
		.append(INSERT_INTO)		
		.append(tabela);
		
		return this;
	}
	
	public SQLQueryDeprecated insertInto(Class tabela){
		builder
		.append(INSERT_INTO)		
		.append(tabela.getSimpleName().toLowerCase());
		
		return this;
	}
	
	public SQLQueryDeprecated fields(String...campos){
		
		circundarComParenteses(campos);
		
		return this;
	}
	
	public SQLQueryDeprecated fields(Field ... campos){
		builder.append("(");
		
		for (int i = 0; i < campos.length; i++) {
			builder.append(campos[i].getName().toLowerCase());
			
			if(i != campos.length -2 ){
				builder.append(", ");
			}			
		}
		
		builder.append(")");
		
		return this;
	}
	
	public SQLQueryDeprecated fields(List<Field> campos){
		builder.append("(");
		
		for (int i = 0; i < campos.size(); i++) {
			Field campo = campos.get(i); 
			if(campo.getAnnotation(FieldName.class) != null){
				String nome = ((FieldName) campo.getAnnotation(FieldName.class)).value();
				builder.append(nome);
			}else{
				builder.append(campo.getName().toLowerCase());
			}			
			
			if(i != campos.size() -1 ){
				builder.append(", ");
			}			
		}
		
		builder.append(")");
		
		return this;
	}
	
	
	public SQLQueryDeprecated values(){
		builder.append(VALUES);
		builder.append("(");
		for(int i=0; i < parameterValues.size();i++){
			builder.append("?");
			
			if(i != parameterValues.size() - 1){
				builder.append(", ");
			}
		}
		builder.append(")");
		
		return this;
	}
	
	
	public SQLQueryDeprecated values(String...valores){
		builder.append(VALUES);
		
		circundarComParenteses(valores);
		
		return this;
	}
	
	public String getSql(){		
		return builder.toString();
	}

	public SQLQueryDeprecated where() {
		builder.append(WHERE);
		return this;
	}
	
	public SQLQueryDeprecated equals(String campo, String valor){
		builder
		.append(campo)
		.append(EQUALS)
		.append(valor);
		
		return this;		
	}
	
	public SQLQueryDeprecated and(){
		builder.append(AND);
		return this;
	}
	
	public SQLQueryDeprecated or(){
		builder.append(OR);
		return this;
	}

	public void adicionarValor(Object valor) {
		parameterValues.add(valor);
		
	}
	
	public List<Object> getValores(){
		return parameterValues;
	}

	public SQLQueryDeprecated select(String...campos) {
		this.fields = campos;
		
		builder.append(SELECT);
		
		for (int i=0; i<campos.length; i++) {
			builder.append(campos[i]);
			
			if(i != campos.length -1){
				builder.append(", ");
			}
		}				
		
		return this;
	}
	
	public SQLQueryDeprecated from(String tabela){
		builder
		.append(FROM)
		.append(tabela);
		
		return this;
	}
	
	public SQLQueryDeprecated concatenar(SQLQueryDeprecated builder){
		this.builder.append(builder.getSql());
		
		for (Object valor : builder.getValores()) {
			adicionarValor(valor);
		}
		
		return this;
	}
	
	private SQLQueryDeprecated circundarComParenteses(String...campos){
		builder.append("(");
		
		for (int i = 0; i < campos.length; i++) {
			builder.append(campos[i]);
			
			if(i != campos.length -1){
				builder.append(", ");
			}			
		}		
		builder.append(")");
		
		return this;
	}

}
