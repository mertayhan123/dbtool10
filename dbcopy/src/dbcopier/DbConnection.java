package dbcopier;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	
	public static java.sql.Connection getMsConnection(String url) {
		String dbURL = null;
		//url can be "local" or "*" for localhost server
		//another long and valid url can be defined also
		if (("local".equals(url)) || ("*".equals(url)))	{
            dbURL = "jdbc:sqlserver://localhost;databaseName=AFACANDW;integratedSecurity=true; "
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30";
		} else {
			//check url
		}
        Connection conn = null;
 
        try {
            	
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
            	System.out.println("\nConnected to the Microsoft Sql server successfully.");
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            } else {
            	System.out.println("No Database Connection ");
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return(conn);
    }
	public static java.sql.Connection getPgConnection(String url) {
		Properties properties = new Properties();
		String dbURL = null;
		//url can be "local" or "*" for localhost server
		//another long and valid url can be defined also
		if (("local".equals(url)) || ("*".equals(url)))	{
			dbURL = "jdbc:postgresql://localhost/db10";
            properties.setProperty("user", "postgres");
            properties.setProperty("password", "hdncTT56__");
            properties.setProperty("currentSchema", "schema10");
            //connectionParams = ';currentSchema=my_schema';
		} else {
			//check url
		}
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbURL, properties); //url+"&" + "currentSchema=schema10"
            System.out.println("\nConnected to the PostgreSQL server successfully.");
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());                
            } else {
            	System.out.println("No Database Connection ");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return(conn);
	 }
	public static java.sql.Connection getSlConnection(String dbUrl,String createOrNot) {
        Connection conn = null;
        if (dbUrl==null) return(conn);
        if (("nocreate".equals(createOrNot)) && (!fileExists(dbUrl)))	{
        	System.out.println("\nFile name does not exist " + dbUrl);
        	return(null);
        }    
        try {
           Class.forName("org.sqlite.JDBC");
           conn = DriverManager.getConnection("jdbc:sqlite:"+dbUrl);
           
           if (conn != null) {
               DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
               System.out.println("Driver name: " + dm.getDriverName());
               System.out.println("Driver version: " + dm.getDriverVersion());
               System.out.println("Product name: " + dm.getDatabaseProductName());
               System.out.println("Product version: " + dm.getDatabaseProductVersion());
           } else {
           	System.out.println("No Database Connection ");
           }
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Sqlite database successfully opened");
        return(conn);
    }
	private static boolean fileExists(String fileName)	{
		File f = new File(fileName); //"F:\\program1.txt");
        if (f.exists())	{
        	String normalizedName = f.getAbsolutePath();
            System.out.print("File Exists, fileName="+fileName+", normalizedName"+normalizedName);
            if (!fileName.equals(normalizedName)) {System.out.println("	,normalized name is different");}
            return(true);
        } else	{
            // Print message if it does not exists
            System.out.println("File "+fileName+" Does not Exists");
            return(false);
		}
	}
	public static String getNormalizedAbsoluteFileName(String fileName)	{
		File f = new File(fileName); //"F:\\program1.txt");
        if (f.exists())	{
        	String normalizedName = f.getAbsolutePath();
            System.out.print("File Exists, fileName="+fileName+", normalizedName"+normalizedName);
            if (!fileName.equals(normalizedName)) {System.out.println("	,normalized name is different");}
            return(normalizedName);
        } else	{
            // Print message if it does not exists
            System.out.println("Does not Exists");
            return(null);
		}
	}
    public static void closeConnection(java.sql.Connection conn)	{
    	try {
    		conn.close();
    	} catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }    
}
