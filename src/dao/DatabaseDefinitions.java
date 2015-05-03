package dao;

public class DatabaseDefinitions {
	
	public static final int NAME_CASE_LOWER = 11;
	public static final int NAME_CASE_UPPER = 12;
	public static final int NAME_CASE_CAPITALIZE = 13;
	
	private static int nameCase;
	
	public DatabaseDefinitions(){
		nameCase = NAME_CASE_LOWER;
	}	

	public static int getNameCase() {
		return nameCase;
	}

	public static void setNameCase(int nameCase) {
		DatabaseDefinitions.nameCase = nameCase;
	}
}