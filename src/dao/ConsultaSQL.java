package dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import annotation.FieldName;

public class ConsultaSQL {
	
	private static final String INSERT_INTO = "INSERT INTO ";
	private static final String SELECT 		= "SELECT ";
	private static final String FROM 		= " FROM ";
	private static final String VALUES 		= " VALUES ";
	private static final String WHERE 		= " WHERE ";
	private static final String EQUALS 		= " = ";
	private static final String AND 		= " AND ";
	private static final String OR 			= " OR ";
	
	
	private StringBuilder builder;
	private ArrayList<Object> valores;
	private String [] campos;
	
	public ConsultaSQL() {
		builder = new StringBuilder();
		valores = new ArrayList<Object>();
		campos = new String[1];
	}
	
	public String [] getCampos(){
		return this.campos;
	}
	
	public ConsultaSQL insertInto(String tabela){
		builder
		.append(INSERT_INTO)		
		.append(tabela);
		
		return this;
	}
	
	public ConsultaSQL insertInto(Class tabela){
		builder
		.append(INSERT_INTO)		
		.append(tabela.getSimpleName().toLowerCase());
		
		return this;
	}
	
	public ConsultaSQL fields(String...campos){
		
		circundarComParenteses(campos);
		
		return this;
	}
	
	public ConsultaSQL fields(Field ... campos){
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
	
	public ConsultaSQL fields(List<Field> campos){
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
	
	
	public ConsultaSQL values(){
		builder.append(VALUES);
		builder.append("(");
		for(int i=0; i < valores.size();i++){
			builder.append("?");
			
			if(i != valores.size() - 1){
				builder.append(", ");
			}
		}
		builder.append(")");
		
		return this;
	}
	
	
	public ConsultaSQL values(String...valores){
		builder.append(VALUES);
		
		circundarComParenteses(valores);
		
		return this;
	}
	
	public String getSql(){		
		return builder.toString();
	}

	public ConsultaSQL where() {
		builder.append(WHERE);
		return this;
	}
	
	public ConsultaSQL equals(String campo, String valor){
		builder
		.append(campo)
		.append(EQUALS)
		.append(valor);
		
		return this;		
	}
	
	public ConsultaSQL and(){
		builder.append(AND);
		return this;
	}
	
	public ConsultaSQL or(){
		builder.append(OR);
		return this;
	}

	public void adicionarValor(Object valor) {
		valores.add(valor);
		
	}
	
	public List<Object> getValores(){
		return valores;
	}

	public ConsultaSQL select(String...campos) {
		this.campos = campos;
		
		builder.append(SELECT);
		
		for (int i=0; i<campos.length; i++) {
			builder.append(campos[i]);
			
			if(i != campos.length -1){
				builder.append(", ");
			}
		}				
		
		return this;
	}
	
	public ConsultaSQL from(String tabela){
		builder
		.append(FROM)
		.append(tabela);
		
		return this;
	}
	
	public ConsultaSQL concatenar(ConsultaSQL builder){
		this.builder.append(builder.getSql());
		
		for (Object valor : builder.getValores()) {
			adicionarValor(valor);
		}
		
		return this;
	}
	
	private ConsultaSQL circundarComParenteses(String...campos){
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
