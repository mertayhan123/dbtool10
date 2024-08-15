package dbcopier;

import java.util.ArrayList;

public class DbInitialize {
	
	public static void printCredits()	{
		//String[] args3 = new String[] {"dbcopy","copy","ms","local","sl","c:\\","AdventureWorks2022","Adv2022","*","*","*","*"};
		System.out.println("--DbCopy Program: Opensource cross database copy program");
		System.out.println("--Copies database, database tables and other objects from one database to another");
		System.out.println("--can copy inside one database, on the same host, cross database, cross server, even cross product");
		System.out.println("--developed by BAUN (Balikesir University) students, under supervision of the company BT10 (bt10.com.tr)");
		System.out.println("--can be used for non-commercial and commercial purposes");
		System.out.println("--use at your own risk, no liabilities");
		System.out.println("--donations are welcome, 49$, all donations will be used for BAUN developer students");
	}	
	private static void printUsageError()	{
		//String[] args3 = new String[] {"dbcopy","copy","ms","local","sl","c:\\","AdventureWorks2022","Adv2022","*","*","*","*"};
		System.out.println("DbCopy Usage: dbcopy action prd1 prd2 url1 url2 db1 db2 schema1 schema2 tablename1 tablename2");
		System.out.println("action: copy or test");
		System.out.println("prd1, prd2: ms,pg,sl");
		System.out.println("url, url2:urls, local and * for local database server urls");
		System.out.println("db1, db2:database names");
		System.out.println("schema1,schema2:schema names, can be * for all");
		System.out.println("tablename1, tablename2: can be * for all");
	}	
	private static DbCopyArg getArg(String args[])	{
		//String[] args3 = new String[] {"dbcopy","copy","ms","local","sl","c:\\","AdventureWorks2022","Adv2022","*","*","*","*"};
		if (args.length<11)	{
			printUsageError();
    		return(null);
    	}
		int cnt=1;
		String action = args[cnt++];
		if ((!"copy".equals(action)) && (!"test".equals(action))) {System.out.println("action must be copy or test"); return(null);} 
		String prd1 = args[cnt++];
		if ((!"ms".equals(prd1)) && (!"pg".equals(prd1)) && (!"sl".equals(prd1))) {System.out.println("prd1 must be ms,pg or sl"); return(null);}
		String prd2 = args[cnt++];
		if ((!"ms".equals(prd2)) && (!"pg".equals(prd2)) && (!"sl".equals(prd2))) {System.out.println("prd2 must be ms,pg or sl"); return(null);}
		String url1 = args[cnt++];
		if ((url1==null) || url1.isBlank() || (url1.isEmpty())) {System.out.println("url1 must a be valid name, url1=\""+url1+"\""); return(null);}
		String url2 = args[cnt++];
		if ((url2==null) || url2.isBlank() || (url2.isEmpty())) {System.out.println("url2 must a be valid name, url2=\""+url2+"\""); return(null);}
		
		String db1 = args[cnt++];
		if ((db1==null) || db1.isBlank() || (db1.isEmpty())) {System.out.println("db1 must a be valid name, db1=\""+db1+"\""); return(null);}
		String db2 = args[cnt++];
		if ((db2==null) || db2.isBlank() || (db2.isEmpty())) {System.out.println("db2 must a be valid name, db2=\""+db2+"\""); return(null);}
		String schema1 = args[cnt++];
		if ((schema1==null) || schema1.isBlank() || (schema1.isEmpty())) {System.out.println("schema1 must a be valid name, schema1=\""+schema1+"\""); return(null);}
		String schema2 = args[cnt++];
		if ((schema2==null) || schema2.isBlank() || (schema2.isEmpty())) {System.out.println("schema2 must a be valid name, schema2=\""+schema2+"\""); return(null);}
		String tablename1 = args[cnt++];
		if ((tablename1==null) || tablename1.isBlank() || (tablename1.isEmpty())) {System.out.println("tablename1 must a be valid name, tablename1=\""+tablename1+"\""); return(null);}
		String tablename2 = args[cnt++];
		if ((tablename2==null) || tablename2.isBlank() || (tablename2.isEmpty())) {System.out.println("tablename2 must a be valid name, tablename2=\""+tablename2+"\""); return(null);}
		
		System.out.println("action:"+action+",prd1:"+prd1+",prd2:"+prd2+",url1:"+url1+",url2:"+url2+",db1:"+db1+",db2:"+db2+
				",schema1:"+schema1+",schema2:"+schema2+",tablename1:"+tablename1+",tablename2:"+tablename2);

		DbCopyArg arg = new DbCopyArg(action,prd1,prd2,url1,url2,db1,db2,schema1,schema2,tablename1,tablename2); 
		return(arg);
	} 
	private static void setConnsAndUrls(DbCopyArg arg)	{
		java.sql.Connection connMs = null;
    	java.sql.Connection connPg = null;
    	java.sql.Connection connSl = null;
		if ("ms".equals(arg.getPrd1())) 		{
			connMs = DbConnection.getMsConnection(arg.getUrl1()); 
			arg.setConn1(connMs);
		} else if ("pg".equals(arg.getPrd1())) 	{
			connPg = DbConnection.getPgConnection(arg.getUrl1()); 
			arg.setConn1(connPg);
		} else if ("sl".equals(arg.getPrd1())) 	{
			connSl = DbConnection.getSlConnection(arg.getUrl1(),"nocreate"); arg.setConn1(connSl);
			String normalizedAbsoluteFileName = DbConnection.getNormalizedAbsoluteFileName(arg.getUrl1());
			if (!arg.getUrl1().equals(normalizedAbsoluteFileName))	{
				arg.setUrl1(normalizedAbsoluteFileName);
			}
		}
		if ("ms".equals(arg.getPrd2()))		{
			if (("ms".equals(arg.getPrd1())) && (arg.getUrl1().equals(arg.getUrl2())))	{
				arg.setConn2(arg.getConn1());
			} else {
				connMs = DbConnection.getMsConnection(arg.getUrl2()); 
				arg.setConn2(connMs);
			}
		} else if ("pg".equals(arg.getPrd2())) {
			if (("pg".equals(arg.getPrd1())) && (arg.getUrl1().equals(arg.getUrl2()))) 	{
				arg.setConn2(arg.getConn1());
			} else {
				connPg = DbConnection.getPgConnection(arg.getUrl2()); 
				arg.setConn2(connPg);
			}  
		} else if ("sl".equals(arg.getPrd2())) {
			if (("sl".equals(arg.getPrd1())) && (arg.getUrl1().equals(arg.getUrl2())))	{
				arg.setConn2(arg.getConn1());
			} else {
				connSl = DbConnection.getSlConnection(arg.getUrl2(),"create"); arg.setConn2(connSl);
				String normalizedAbsoluteFileName = DbConnection.getNormalizedAbsoluteFileName(arg.getUrl2());
				if (!arg.getUrl2().equals(normalizedAbsoluteFileName))	{
					arg.setUrl2(normalizedAbsoluteFileName);
				}
			}
		}
    	if ((arg.getConn1()!=null) && (arg.getConn2()!=null) && (arg.getConn1().equals(arg.getConn2())))	{
    		arg.setConnSame(true);
    	}
	}
	private static boolean setDatabases(DbCopyArg arg)	{
		ArrayList<String> dbList = DbUtil.getListFromString(arg.getDb1());
		if (!checkSourceDb(arg))	{
    		System.out.println("Problem with the source database names");	return(false);	
    	}
     	if (!checkTargetDb(arg))	{
    		System.out.println("Problem with the target database names");	return(false);	
    	}
     	return(true);
	}
	private static boolean checkSourceDb(DbCopyArg arg)	{
		if ("ms".equals(arg.getPrd1())) 		{	
			ArrayList<String> dbList = DbCopySql.checkSourceDbMsList(arg,arg.getConn1(),arg.getDb1());
			if ((dbList==null) || (dbList.isEmpty()) || (dbList.size()>0)) return(false);
			String db1 = dbList.get(0);  
			if (db1==null){
				System.out.println("Problem with the source ms database "+arg.getDb1());	
				return(false);
			}
			if (!db1.equals(arg.getDb1()))	{
				System.out.println("The source ms database name changed from "+arg.getDb1()+"	to "+db1);
				arg.setDb1(db1);
			}
		} else if ("pg".equals(arg.getPrd1())) 	{	
			ArrayList<String> dbList = DbCopySql.checkSourceDbPgList(arg,arg.getConn1(),arg.getDb1()); 
			if ((dbList==null) || (dbList.isEmpty()) || (dbList.size()>0)) return(false);
			String db1 = dbList.get(0);  
			if (db1==null){
				System.out.println("Problem with the source pg database "+arg.getDb1());
				return(false);
			}
			if (!db1.equals(arg.getDb1()))	{
				System.out.println("The source pg database name changed from "+arg.getDb1()+"	to "+db1);
				arg.setDb1(db1);
			}
		} else if ("sl".equals(arg.getPrd1())) 	{
		}
		return(true);
	}
	private static boolean checkTargetDb(DbCopyArg arg)	{
		if ("ms".equals(arg.getPrd2())) 		{
			String db2 = DbCopySql.checkSourceDbMs(arg,arg.getConn2(),arg.getDb2()); 
			if (db2==null){
				System.out.println("No ms database with the name "+arg.getDb2());
				String createdDb = DbCopySql.createDbMs(arg,arg.getConn2(),arg.getDb2());
				if (createdDb!=null) {return(false);}
			}
			if (!db2.equals(arg.getDb2()))	{
				System.out.println("The target ms database name changed from "+arg.getDb2()+"	to "+db2);
				arg.setDb2(db2);
			}
			if (arg.getDb1().equals(arg.getDb2()))	{
				System.out.println("Source and target ms database are same, "+arg.getDb2()+"	,"+arg.getDb1());
				arg.setDbSame(true);
				return(true);
			}
			return(false);
		} else if ("pg".equals(arg.getPrd2())) 	{	
			String db2 = DbCopySql.checkSourceDbPg(arg,arg.getConn2(),arg.getDb2()); 
			if (db2==null){
				System.out.println("No pg database with the name "+arg.getDb2());
				String createdDb = DbCopySql.createDbPg(arg,arg.getConn2(),arg.getDb2());
				if (createdDb!=null) {return(false);}
			}
			if (!db2.equals(arg.getDb2()))	{
				System.out.println("The target pg database name changed from "+arg.getDb2()+"	to "+db2);
				arg.setDb2(db2);
			}
			if (arg.getDb1().equals(arg.getDb2()))	{
				System.out.println("Source and target pg database are same, "+arg.getDb2()+"	,"+arg.getDb1());
				arg.setDbSame(true);
				return(true);
			}
			return(false);
		} else if ("sl".equals(arg.getPrd2())) 	{
			if (arg.getDb1().equals(arg.getDb2()))	{
				System.out.println("Source and target sl database are same, "+arg.getDb2()+"	,"+arg.getDb1());
				arg.setDbSame(true);
				return(true);
			}
		}
		return(true);
	}
	private static boolean setSchemas(DbCopyArg arg)	{
		if (!checkSourceSchema(arg))	{	
			System.out.println("Problem with the source schema");	return(false);	
		}
		if (!checkTargetSchema(arg))	{	
			System.out.println("Problem with the target schema");	return(false);	
		}
		return(true);
	}	
	private static boolean checkSourceSchema(DbCopyArg arg)	{
		if ("ms".equals(arg.getPrd1())) 		{	
			ArrayList<String> schemaList = DbCopySql.checkSourceSchemaMsList(arg,arg.getConn1(),arg.getSchema1()); 
			if ((schemaList==null) || (schemaList.size()==0)){
				System.out.println("Schema problem with the source ms database "+arg.getDb1());	
				return(false);
			}
			arg.setSchemaList(schemaList);
		} else if ("pg".equals(arg.getPrd1())) 	{
			ArrayList<String> schemaList = DbCopySql.checkSourceSchemaPgList(arg,arg.getConn1(),arg.getSchema1()); 
			if ((schemaList==null) || (schemaList.size()==0)){
				System.out.println("Schema problem with the source pg database "+arg.getDb1());	
				return(false);
			}
			arg.setSchemaList(schemaList);
		} else if ("sl".equals(arg.getPrd1())) 	{
			//no schemas in sqlite
		}
		return(true);
	}
	private static boolean checkTargetSchema(DbCopyArg arg)	{
		if ("ms".equals(arg.getPrd1())) 		{	
			ArrayList<String> schemaList = DbCopySql.checkSourceSchemaMsList(arg,arg.getConn1(),arg.getSchema2()); 
			if ((schemaList==null) || (schemaList.size()==0)){
				//System.out.println("Schema problem with the target ms database "+arg.getDb1());
				System.out.println("No ms schema with the name "+arg.getSchema2());
				String createdSchema = DbCopySql.createSchemaMs(arg,arg.getConn2(),arg.getSchema2());
				if (createdSchema!=null) {return(false);}
				return(false);
			}
			arg.setSchemaList(schemaList);
		} else if ("pg".equals(arg.getPrd1())) 	{
			ArrayList<String> schemaList = DbCopySql.checkSourceSchemaPgList(arg,arg.getConn1(),arg.getSchema2()); 
			if ((schemaList==null) || (schemaList.size()==0)){
				//System.out.println("Schema problem with the target pg database "+arg.getDb1());	
				System.out.println("No pg schema with the name "+arg.getSchema2());
				String createdSchema = DbCopySql.createSchemaPg(arg,arg.getConn2(),arg.getSchema2());
				if (createdSchema!=null) {return(false);}
				return(false);
			}
			arg.setSchemaList(schemaList);
		} else if ("sl".equals(arg.getPrd1())) 	{
			//no schemas in sqlite
		}
		return(true);
	}
	private static boolean setTables(DbCopyArg arg)	{
	/*	if (!checkSourceSchema(arg))	{	
			System.out.println("Problem with the source schema");	return(false);	
		}
		if (!checkTargetSchema(arg))	{	
			System.out.println("Problem with the target schema");	return(false);	
		}
	*/	
		return(true);
	}	
	public static DbCopyArg initializeArg(String args[])	{

	   	// args copy prd1 prd2 db1 db2 schema1 schema2 tablename1 tablename2
		// prd1, prd2: ms,pg,sl db1, db2:database name schema1,schema2:schema names, can be * for all, tablename1, tablename2: can be * for all   
	    
    	DbCopyArg arg = getArg(args);
    	if (arg==null) return(arg);
    	setConnsAndUrls(arg);
    	if ((arg.getConn1()==null) || (arg.getConn2()==null)) {
    		System.out.println("Problem with the connections 1 and 2");
    		return(arg);
    	}
    	if ((arg.getUrl1()==null) || (arg.getUrl2()==null)) {
    		System.out.println("Problem with the URL's 1 and 2");
    		return(arg);
    	}
    	if (setDatabases(arg))	{return(arg);}
    	if (setSchemas(arg))	{return(arg);};
    	if (setTables(arg))	{return(arg);};
    	
    	return(arg);
	}
}
