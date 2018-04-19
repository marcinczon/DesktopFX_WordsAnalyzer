package source;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.sql.*;

public class ExportDataBase
{
	private Connection connection;
	private Statement statement;
	private DatabaseMetaData databaseMetaData;
	private ResultSet resultSet;

	private String tableName = "";
	private int increaseCounter = 0;

	// ****************************
	// Referencje
	// ****************************

	private Filter filter;
	private Map<String, Data> dataMap;

	public boolean Connect()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "mixer");
			statement = connection.createStatement();
			databaseMetaData = connection.getMetaData();
			System.out.println("Connected");
			return true;

		} catch (ClassNotFoundException e)
		{
			System.err.println(e.toString());
			return false;
		} catch (SQLException e)
		{
			System.err.println(e.toString());
			return false;
		}
	}

	public void createTable(String tableName)
	{
		this.tableName = tableName;

		if (connection != null)
		{
			try
			{
				resultSet = databaseMetaData.getTables(null, null, this.tableName, null);

				if (resultSet.next())
				{
					System.out.println("Table already exist");
				} else
				{
					System.out.println("Creating table " + this.tableName + "...");
					statement.executeUpdate("CREATE TABLE " + tableName + " (COUNTER INT, SOURCE VARCHAR2(40) ,TRANSLATED VARCHAR2(40) )");
					System.out.println("Table created " + this.tableName);

				}

			} catch (SQLException e)
			{
				System.err.println("Table aLready exist: " + tableName);
				// System.err.println(e.toString());
			}

		} else
		{
			System.out.println("Already Connected");
		}
	}

	public boolean isExist(String source)
	{
		try
		{
			resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE SOURCE = '" + source + "'");
			if (resultSet.next())
			{
				increaseCounter = resultSet.getInt("COUNTER");
				// System.out.println(sourcee + " " + increaseCounter);
				return true;
			} else
			{
				return false;
			}
		} catch (SQLException e)
		{
			System.err.println(e.toString());
			return false;
		}
	}

	public boolean updateRecord(int counter, String source)
	{
		try
		{
			String querry = String.format("UPDATE " + tableName + " SET COUNTER=%d WHERE SOURCE='%s' ", increaseCounter + counter, source);
			statement.executeUpdate(querry);

			return true;
		} catch (SQLException e)
		{
			e.getMessage();
			return false;
		}
	}

	public boolean addRecord(int counter, String source, String translate)
	{
		try
		{
			String querry = String.format("INSERT INTO " + tableName + " (COUNTER, SOURCE, TRANSLATED) VALUES (%d, '%s', '%s')", counter, source, translate);
			statement.executeUpdate(querry);

			return true;
		} catch (SQLException e)
		{
			e.getMessage();
			return false;
		}

	}

	public void saveTable()
	{
		System.out.println();
		for (Data data : dataMap.values())
		{
			if (!data.getSource().equals(null) && !data.getSource().equals("") && filter.isInFilterRange(data))
			{
				if (isExist(data.getSource()))
				{
					updateRecord(data.getCounter(), data.getSource());
					System.out.println("UP DATE: " + data.getSource());
				} else
				{
					addRecord(data.getCounter(), data.getSource(), data.getTranslate());
					System.out.println("CREATED: " + data.getSource());
				}
			}
		}
	}

	public void setReferenceDataMap(Map<String, Data> dataMap)
	{
		this.dataMap = dataMap;
	}

	public void setRefrenceFilter(Filter filter)
	{
		this.filter = filter;
	}

}