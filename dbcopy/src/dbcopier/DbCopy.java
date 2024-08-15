package dbcopier;

public class DbCopy {
	
	private static void copyDb(DbCopyArg arg)	{
		if (arg.getPrd1().equals(arg.getPrd2())) 		{
			DbCopierSameDb.copySameDb(arg);
		} else {
			DbCopierCrossDb.copyCrossDb(arg);
		}
	}
	public static void runCopy(String args[])	{	
		DbCopyArg arg = DbInitialize.initializeArg(args);
		copyDb(arg);
    	    	
    	if (arg.getConn1()!=null)	{DbConnection.closeConnection(arg.getConn1());	}
    	if (arg.getConn2()!=null)	{DbConnection.closeConnection(arg.getConn2());	}    	
	}
	public static void main(String args[])  {
		
		DbInitialize.printCredits();
		
		int argSize=13;
		String[] ms2ms = new String[] {"dbcopy","copy","ms","ms","local","local","AdventureWorks2022","AdventureWorks2022Copy","*","*","*","*"};
		String[] ms2sl = new String[] {"dbcopy","copy","ms","sl","local","C:\\app\\sqlite\\Adv2022.db","AdventureWorks2022","*","*","*","*","*"};
		String[] ms2pg = new String[] {"dbcopy","copy","ms","pg","local","local","AdventureWorks2022","Adv2022","*","*","*","*"};
    	
		String[] sl2ms = new String[] {"dbcopy","copy","sl","ms","C:\\app\\sqlite\\chinook.db","local","*","chinook","*","*","*","*","*"};
		String[] sl2pg = new String[] {"dbcopy","copy","sl","pg","C:\\app\\sqlite\\chinook.db","local","*","chinook","*","*","*","*","*"};
		String[] sl2sl = new String[] {"dbcopy","copy","sl","sl","C:\\app\\sqlite\\chinook.db","C:\\app\\sqlite\\chinook2.db","*","*","*","*","*","*","*"};
		
		String[] pg2pg = new String[] {"dbcopy","copy","pg","pg","local","local","Adv2022","Adv2022Copy","*","*","*","*","*"};
		String[] pg2sl = new String[] {"dbcopy","copy","pg","sl","local","C:\\app\\sqlite\\Adv2022.db","AdventureWorks2022","*","*","*","*","*"};
		String[] pg2ms = new String[] {"dbcopy","copy","pg","ms","local","local","Adv2022","AdventureWorks2022FromPg","*","*","*","*"};
    	
		//runCopy(ms2ms);
		//runCopy(ms2sl);
		//runCopy(ms2pg);
		
		//runCopy(sl2ms);
		//runCopy(sl2pg);
		//runCopy(sl2sl);
		
		//runCopy(pg2pg);
		//runCopy(pg2sl);
		//runCopy(pg2ms);
		
		String dbList = "'AdventureWorks2022','AdventureWorksDW2022','AdventureWorksLT2022'"; //.replace("'", "\"");
		String[] ms2ms2 = new String[] {"dbcopy","copy","ms","ms","local","local",dbList,"?","*","*","*","*"};
		runCopy(ms2ms2);
		
    }	
}
