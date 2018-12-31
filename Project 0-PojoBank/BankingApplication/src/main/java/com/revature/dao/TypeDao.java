package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.AccountType;
import com.revature.util.ConnectionFactory;

public class TypeDao implements DAO<AccountType, Integer> {

	@Override
	public List<AccountType> findAll() {
		List<AccountType> type = new ArrayList<AccountType>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT BALANCE FROM ACCOUNT_TB WHERE USERID = ?";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {

				AccountType temp = new AccountType();
				temp.setTypeID(rs.getInt(1));
				temp.setType(rs.getString(2));
				type.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

	@Override
	public AccountType findById(Integer id) {
		AccountType accT = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM ACC_TYPE_TB WHERE TYPE = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				accT = new AccountType();
				accT.setTypeID(rs.getInt(1));
				accT.setType(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accT;
	}

	@Override
	public AccountType save(AccountType obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);

			String sql = "INSERT INTO ACCOUNT_TB (TYPE) VALUES(?)";
			String[] keyNames = { "ACC_ID" };

			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setInt(1, obj.getTypeID());
			ps.setString(2, obj.getType());

			int numRows = ps.executeUpdate();
			if (numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while (pk.next()) {
					obj.setTypeID(pk.getInt(1));
					obj.setType(pk.getString(2));
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;

	}

	@Override
	public AccountType update(AccountType obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "UPDATE ACC_TYPE_TB SET TYPE = ? WHERE TYPEID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getType());

			ps.setInt(2, obj.getTypeID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}