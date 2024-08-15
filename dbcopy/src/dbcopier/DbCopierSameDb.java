package dbcopier;

public class DbCopierSameDb {
	private static void copyMs2Ms(DbCopyArg arg)	{
		//copy databases, schemas, tables from one to another, for the same database producer, ms
		//copy database
		//copy schema
		//copy tables
		//copy other objects
	}
	private static void copyPg2Pg(DbCopyArg arg)	{
	}
	private static void copySl2Sl(DbCopyArg arg)	{
	}
	public static void copySameDb(DbCopyArg arg)	{
		if (("ms".equals(arg.getPrd1())) && ("ms".equals(arg.getPrd2()))) 		{
			copyMs2Ms(arg);
		} else if (("pg".equals(arg.getPrd1())) && ("pg".equals(arg.getPrd2()))) 	{
			copyPg2Pg(arg);
		} else if (("sl".equals(arg.getPrd1())) && ("sl".equals(arg.getPrd2()))) 	{
			copySl2Sl(arg);
		}
	}
}
