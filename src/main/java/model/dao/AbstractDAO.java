package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Centralizes shared database operations
 * @author remideboer, gerke de boer
 *
 */
public class AbstractDAO {

	public static DBaccess db = new DBaccess();

	public AbstractDAO(DBaccess databaseaccess) {
		db = databaseaccess;
		db.openConnection();
	}


	/**
	 * Builds a prepared Statement from the sql string. A DAO should used this to
	 * fill the parameters.
	 * 
	 * @param sql,
	 *            the SQl query
	 */
	protected PreparedStatement getStatement(String sql) throws SQLException {
		return db.getConnection().prepareStatement(sql);
	}

	/**
	 * Executes a prepared statement without result. Used for insert, update and
	 * delete statements.
	 * 
	 * @param ps,
	 *            the prepared statement filled by a DAO
	 */
	protected void executeManipulatePreparedStatement(PreparedStatement ps) throws SQLException {
		ps.executeUpdate();
	}

	/**
	 * Executes a prepared statement with result. Used for select statements.
	 * 
	 * @param ps,
	 *            the prepared statement filled by a DAO
	 */
	protected ResultSet executeSelectPreparedStatement(PreparedStatement ps) throws SQLException {
		return ps.executeQuery();
	}

	/**
	 * Executes a prepared statement with result to get a generated key.
	 * 
	 * @param sql,
	 *            the SQL query
	 */
	protected PreparedStatement getStatementWithKey(String sql) throws SQLException {
		return db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	}

	protected int executeInsertPreparedStatement(PreparedStatement ps) throws SQLException {
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int key = 0;
		while (rs.next()) {
			key = rs.getInt(1);
		}
		return key;
	}

}
