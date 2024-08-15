package dbcopier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * 
 * */
public class DbCopySql {
	
	public static String checkSourceDbMs(DbCopyArg arg,java.sql.Connection connMs,String dbName)	{
		String sqlStr = "SELECT name FROM master.sys.databases WHERE upper(name) = upper(?)"; //? = AdventureWorks2022
		String name = null;
		try { 
			PreparedStatement ps = connMs.prepareStatement(sqlStr); 
			ps.setString(1,dbName);
			ResultSet rs = ps.executeQuery(); 
			while ( rs.next() ) {
				name = rs.getString("name");
			} //for
			rs.close();
			ps.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		return(name);
	}
	public static String checkSourceDbPg(DbCopyArg arg,java.sql.Connection connPg,String dbName)	{
		String sqlStr = "SELECT datname FROM pg_database WHERE upper(datname) = upper(?)"; //? = AdventureWorks2022
		String datname = null;
		try { 
			PreparedStatement ps = connPg.prepareStatement(sqlStr); 
			ps.setString(1,dbName);
			ResultSet rs = ps.executeQuery(); 
			while ( rs.next() ) {
				datname = rs.getString("datname");
			} //for
			rs.close();
			ps.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		return(datname);
	}
	public static ArrayList<String> checkSourceDbMsList(DbCopyArg arg,java.sql.Connection connMs,String dbName)	{
		ArrayList<String> dbList = new ArrayList<String>();
		String sqlStr = "SELECT name FROM master.sys.databases WHERE upper(name) = upper(?)"; //? = AdventureWorks2022
		String name = null;
		try { 
			PreparedStatement ps = connMs.prepareStatement(sqlStr); 
			ps.setString(1,dbName);
			ResultSet rs = ps.executeQuery(); 
			while ( rs.next() ) {
				name = rs.getString("name");
				if (name!=null)	dbList.add(name);
			} //for
			rs.close();
			ps.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		return(dbList);
	}
	public static ArrayList<String> checkSourceDbPgList(DbCopyArg arg,java.sql.Connection connPg,String dbName)	{
		ArrayList<String> dbList = new ArrayList<String>();
		String sqlStr = "SELECT datname FROM pg_database WHERE upper(datname) = upper(?)"; //? = AdventureWorks2022
		String datname = null;
		try { 
			PreparedStatement ps = connPg.prepareStatement(sqlStr); 
			ps.setString(1,dbName);
			ResultSet rs = ps.executeQuery(); 
			while ( rs.next() ) {
				datname = rs.getString("datname");
				if (datname!=null)	dbList.add(datname);
			} //for
			rs.close();
			ps.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		return(dbList);
	}
	public static ArrayList<String> checkSourceSchemaMsList(DbCopyArg arg,java.sql.Connection connMs,String schemaName)	{
		ArrayList<String> schemaList = new ArrayList<String>();
		String sqlStr = "SELECT s.name FROM sys.schemas s " +
						" inner join sys.sysusers u on u.uid = s.principal_id "+
						" where u.name='dbo'"+
						" order by s.name ";
		try { 
			PreparedStatement ps = connMs.prepareStatement(sqlStr); 
			ps.setString(1,schemaName);
			ResultSet rs = ps.executeQuery(); 
			while ( rs.next() ) {
				String name = rs.getString("name");
				if (name!=null)	schemaList.add(name);
			} //for
			rs.close();
			ps.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		return(schemaList);
	}
	public static ArrayList<String> checkSourceSchemaPgList(DbCopyArg arg,java.sql.Connection connPg,String schemaName)	{
		ArrayList<String> schemaList = new ArrayList<String>();
		String sqlStr = " SELECT schema_name, schema_owner " +
					 	" FROM information_schema.schemata " +
						" order by schema_name ";
		try { 
			PreparedStatement ps = connPg.prepareStatement(sqlStr); 
			ps.setString(1,schemaName);
			ResultSet rs = ps.executeQuery(); 
			while ( rs.next() ) {
				String name = rs.getString("schema_name");
				if (name!=null)	schemaList.add(name);
			} //for
			rs.close();
			ps.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		return(schemaList);
	}
	
	public static boolean checkSourceDbSl(DbCopyArg arg,String dbName)	{
		return(true);
	}
	public static String createDbMs(DbCopyArg arg,java.sql.Connection connMs,String dbName)	{
		//create ms database
		String sqlStr = "CREATE DATABASE "+dbName; //? = AdventureWorks2022
		try { 
			Statement stmt = connMs.createStatement(); 
			int cntCreated = stmt.executeUpdate(sqlStr); 
			stmt.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		String datname = checkSourceDbMs(arg,connMs,dbName);
		return(datname);
	}
	public static String createDbPg(DbCopyArg arg,java.sql.Connection connPg,String dbName)	{
		//create pg database
		String sqlStr = "CREATE DATABASE "+dbName; //? = AdventureWorks2022
		try { 
			Statement stmt = connPg.createStatement(); 
			int cntCreated = stmt.executeUpdate(sqlStr); 
			stmt.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		String datname = checkSourceDbPg(arg,connPg,dbName);
		return(datname);
	}
	public static String createSchemaMs(DbCopyArg arg,java.sql.Connection connMs,String schemaName)	{
		//create ms database
		String sqlStr = "CREATE SCHEMA "+schemaName; 
		try { 
			Statement stmt = connMs.createStatement(); 
			int cntCreated = stmt.executeUpdate(sqlStr); 
			stmt.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		String datname = schemaName; // checkSourceSchemaMs(arg,connMs,schemaName);
		return(datname);
	}
	public static String createSchemaPg(DbCopyArg arg,java.sql.Connection connPg,String schemaName)	{
		//create pg database
		String sqlStr = "CREATE SCHEMA "+schemaName; //? = AdventureWorks2022
		try { 
			Statement stmt = connPg.createStatement(); 
			int cntCreated = stmt.executeUpdate(sqlStr); 
			stmt.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		String scmName = schemaName; //checkSourceSchemaPg(arg,connPg,schemaName);
		return(scmName);
	}
	public static ArrayList<String> checkSourceTableMsList(DbCopyArg arg,java.sql.Connection connMs,String dbName,String schemaName,String tableName)	{
		ArrayList<String> tableList = new ArrayList<String>();
		String sqlStr = "SELECT t.name FROM "+dbName+".sys.table t " +
						" inner join "+dbName+".sys.schemas s on t.schema_id = s.schema_id "+
						" where s.name='"+schemaName+"'"+
						" order by t.name ";
		try { 
			PreparedStatement ps = connMs.prepareStatement(sqlStr); 
			ps.setString(1,tableName);
			ResultSet rs = ps.executeQuery(); 
			while ( rs.next() ) {
				String name = rs.getString("name");
				if (name!=null)	tableList.add(name);
			} //for
			rs.close();
			ps.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		return(tableList);
	}
	public static ArrayList<String> checkSourceTablePgList(DbCopyArg arg,java.sql.Connection connPg,String dbName,String schemaName,String tableName)	{
		ArrayList<String> tableList = new ArrayList<String>();
		String sqlStr = " SELECT tablename " +
					 	" FROM pg_catalog.pg_tables " +
					 	" where schemaname='"+schemaName+"'"+
						" order by tablename ";
		try { 
			PreparedStatement ps = connPg.prepareStatement(sqlStr); 
			ps.setString(1,tableName);
			ResultSet rs = ps.executeQuery(); 
			while ( rs.next() ) {
				String name = rs.getString("tablename");
				if (name!=null)	tableList.add(name);
			} //for
			rs.close();
			ps.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		return(tableList);
	}
	//SELECT name FROM sqlite_master WHERE type='table';
	public static ArrayList<String> checkSourceTableSlList(DbCopyArg arg,java.sql.Connection connSl,String tableName)	{
		ArrayList<String> tableList = new ArrayList<String>();
		String sqlStr = " SELECT name " +
					 	" FROM sqlite_master " +
					 	" where type='table' and name='"+tableName+"'"+
						" order by name ";
		try { 
			PreparedStatement ps = connSl.prepareStatement(sqlStr); 
			ps.setString(1,tableName);
			ResultSet rs = ps.executeQuery(); 
			while ( rs.next() ) {
				String name = rs.getString("name");
				if (name!=null)	tableList.add(name);
			} //for
			rs.close();
			ps.close();
		} catch (SQLException ex) { 
			ex.printStackTrace(); 
		} 
		return(tableList);
	}
	
}
