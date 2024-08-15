package dbcopier;

import java.util.ArrayList;

public class DbCopyArg {
	
	public DbCopyArg(String action, String prd1, String prd2, String url1, String url2,String db1, String db2, 
			String schema1, String schema2,	String tablename1, String tablename2) {
		super();
		this.action = action;
		this.prd1 = prd1;
		this.prd2 = prd2;
		this.url1 = url1;
		this.url2 = url2;
		this.db1 = db1;
		this.db2 = db2;
		this.schema1 = schema1;
		this.schema2 = schema2;
		this.tablename1 = tablename1;
		this.tablename2 = tablename2;
		
		this.connSame = false; 
		this.dbSame = false;
		this.schemaSame = false;
		this.tablenameSame = false;
		
		this.databaseList = new ArrayList<String>();
		this.schemaList = new ArrayList<String>();
		this.tableList = new ArrayList<String>();
		
		this.databaseList2 = new ArrayList<String>();
		this.schemaList2 = new ArrayList<String>();
		this.tableList2 = new ArrayList<String>();
	}
	private String action;
	private	String prd1;
	private	String prd2;
	private	String url1;
	private	String url2;
	private	String db1;
	private	String db2;
	private	String schema1;
	private	String schema2;
	private	String tablename1;
	private	String tablename2;
	private java.sql.Connection conn1; //in
	private java.sql.Connection conn2; //out
	private boolean connSame; 
	private boolean dbSame;
	private boolean schemaSame;
	private boolean tablenameSame;
	
	private ArrayList<String> databaseList;
	private ArrayList<String> schemaList;
	private ArrayList<String> tableList;
	private ArrayList<String> databaseList2;
	private ArrayList<String> schemaList2;
	private ArrayList<String> tableList2;
	//private ArrayList<String> tableList;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getPrd1() {
		return prd1;
	}
	public void setPrd1(String prd1) {
		this.prd1 = prd1;
	}
	public String getPrd2() {
		return prd2;
	}
	public void setPrd2(String prd2) {
		this.prd2 = prd2;
	}
	public String getDb1() {
		return db1;
	}
	public void setDb1(String db1) {
		this.db1 = db1;
	}
	public String getDb2() {
		return db2;
	}
	public void setDb2(String db2) {
		this.db2 = db2;
	}
	public String getSchema1() {
		return schema1;
	}
	public void setSchema1(String schema1) {
		this.schema1 = schema1;
	}
	public String getSchema2() {
		return schema2;
	}
	public void setSchema2(String schema2) {
		this.schema2 = schema2;
	}
	public String getTablename1() {
		return tablename1;
	}
	public void setTablename1(String tablename1) {
		this.tablename1 = tablename1;
	}
	public String getTablename2() {
		return tablename2;
	}
	public void setTablename2(String tablename2) {
		this.tablename2 = tablename2;
	}
	public java.sql.Connection getConn1() {
		return conn1;
	}
	public void setConn1(java.sql.Connection conn1) {
		this.conn1 = conn1;
	}
	public java.sql.Connection getConn2() {
		return conn2;
	}
	public void setConn2(java.sql.Connection conn2) {
		this.conn2 = conn2;
	}
	public boolean isConnSame() {
		return connSame;
	}
	public void setConnSame(boolean connSame) {
		this.connSame = connSame;
	}
	public boolean isDbSame() {
		return dbSame;
	}
	public void setDbSame(boolean dbSame) {
		this.dbSame = dbSame;
	}
	public boolean isSchemaSame() {
		return schemaSame;
	}
	public void setSchemaSame(boolean schemaSame) {
		this.schemaSame = schemaSame;
	}
	public boolean isTablenameSame() {
		return tablenameSame;
	}
	public void setTablenameSame(boolean tablenameSame) {
		this.tablenameSame = tablenameSame;
	}
	public String getUrl1() {
		return url1;
	}
	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	public String getUrl2() {
		return url2;
	}
	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	public ArrayList<String> getDatabaseList() {
		return databaseList;
	}
	public void setDatabaseList(ArrayList<String> databaseList) {
		this.databaseList = databaseList;
	}
	public ArrayList<String> getSchemaList() {
		return schemaList;
	}
	public void setSchemaList(ArrayList<String> schemaList) {
		this.schemaList = schemaList;
	}
	public ArrayList<String> getTableList() {
		return tableList;
	}
	public void setTableList(ArrayList<String> tableList) {
		this.tableList = tableList;
	}
	public ArrayList<String> getDatabaseList2() {
		return databaseList2;
	}
	public void setDatabaseList2(ArrayList<String> databaseList2) {
		this.databaseList2 = databaseList2;
	}
	public ArrayList<String> getSchemaList2() {
		return schemaList2;
	}
	public void setSchemaList2(ArrayList<String> schemaList2) {
		this.schemaList2 = schemaList2;
	}
	public ArrayList<String> getTableList2() {
		return tableList2;
	}
	public void setTableList2(ArrayList<String> tableList2) {
		this.tableList2 = tableList2;
	}
	
}