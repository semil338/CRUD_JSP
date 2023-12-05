package com.jsp.servlet;

import java.util.*;
import java.sql.*;

public class EmpDao {

	public static Connection getConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yt_demo", "semil", "semil612");
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
		return con;
	}

	public static int save(Emp e) throws Exception {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into user420(name,password,email,country) values (?,?,?,?)");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return status;
	}

	public static int update(Emp e) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("update user420 set name=?,password=?,email=?,country=? where id=?");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());
			ps.setInt(5, e.getId());

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from user420 where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public static Emp getEmployeeById(int id) {
		Emp e = new Emp();

		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user420 where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt(5));
				e.setName(rs.getString(1));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(2));
				e.setCountry(rs.getString(4));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return e;
	}

	public static List<Emp> getAllEmployees() {
		List<Emp> list = new ArrayList<Emp>();

		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user420");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Emp e = new Emp();
				e.setId(rs.getInt(5));
				e.setName(rs.getString(1));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(2));
				e.setCountry(rs.getString(4));
				list.add(e);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
