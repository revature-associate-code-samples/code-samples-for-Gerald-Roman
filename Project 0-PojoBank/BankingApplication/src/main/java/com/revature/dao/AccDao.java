package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Account;
import com.revature.util.ConnectionFactory;

import oracle.jdbc.internal.OracleTypes;

public class AccDao implements DAO<Account, Integer> {

	@Override
	public List<Account> findAll() {
		List<Account> account = new ArrayList<Account>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "{CALL GET_ALL_ACC (?)}";

			CallableStatement st = conn.prepareCall(query);
			st.registerOutParameter(1, OracleTypes.CURSOR);
			st.execute();
			ResultSet rs = (ResultSet) st.getObject(1);

			while (rs.next()) {
				Account temp = new Account();
				temp.setAccID(rs.getInt(1));
				temp.setAccType(rs.getInt(2));
				temp.setOwnerID(rs.getInt(3));
				temp.setBalance(rs.getDouble(4));
				account.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Account findById(Integer id) {
		Account a = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT ACC_ID FROM ACCOUNT_TB WHERE OWNER_ID = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				a = new Account();
				a.setAccID(rs.getInt(1));
				a.setAccType(rs.getInt(2));
				a.setBalance(rs.getDouble(3));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return a;
	}

	@Override
	public Account save(Account obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ACCOUNT_TB ( ACC_TYPE,OWNER_ID,BALANCE) VALUES(?,?,?)";
			String[] keyNames = { "ACC_ID" };

			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setInt(1, obj.getAccType());
			ps.setInt(2, obj.getOwnerID());
			ps.setDouble(3, obj.getBalance());

			int numRows = ps.executeUpdate();
			if (numRows == 1) {
				ResultSet pk = ps.getGeneratedKeys();
				while (pk.next()) {
					obj.setAccID(pk.getInt(1));
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Account update(Account obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "UPDATE ACCOUNT_TB SET BALANCE = ? WHERE ACC_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, obj.getBalance());
			ps.setInt(2, obj.getAccID());
			ps.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace(); // use a logger
		}
		return obj;
	}
}
