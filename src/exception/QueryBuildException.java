package exception;

public class QueryBuildException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public QueryBuildException(){
		
	}
	
	public QueryBuildException(String message){
		super(message);
	}
}
