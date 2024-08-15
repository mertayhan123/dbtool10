package dbcopier;

public class DbCopierCrossDb {
	private static void copyMs2Pg(DbCopyArg arg)	{
		//copy databases, schemas, tables from one to another, for the different database producer, ms to pg
	}
	private static void copyMs2Sl(DbCopyArg arg)	{
	}
	private static void copyPg2Ms(DbCopyArg arg)	{
	}
	private static void copyPg2Sl(DbCopyArg arg)	{
	}
	private static void copySl2Ms(DbCopyArg arg)	{
	}
	private static void copySl2Pg(DbCopyArg arg)	{
	}
	public static void copyCrossDb(DbCopyArg arg)	{
		if ("ms".equals(arg.getPrd1())) 		{
			if ("pg".equals(arg.getPrd2())) 	{
				copyMs2Pg(arg);
			} else if ("sl".equals(arg.getPrd2())) 	{
				copyMs2Sl(arg);
			}
		} else if ("pg".equals(arg.getPrd1())) 	{
			if ("ms".equals(arg.getPrd2())) 	{
				copyPg2Ms(arg);
			} else if ("sl".equals(arg.getPrd2())) 	{
				copyPg2Sl(arg);
			}
		} else if ("sl".equals(arg.getPrd1())) 	{
			if ("ms".equals(arg.getPrd2())) 	{
				copySl2Ms(arg);
			} else if ("pg".equals(arg.getPrd2())) 	{
				copySl2Pg(arg);
			}
		}
	}
}
