package com.cjon.book.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.book.dto.BookDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class BookDAO{

	public String select(BookDTO entity) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String result=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/library";
			String id="teddy";
			String pw="teddy";
			con=DriverManager.getConnection(url, id, pw);
			
			String sql="select bisbn, bimgurl, btitle, bauthor, bprice from book where btitle like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,  "%" + entity.getBtitle() + "%");
			rs=pstmt.executeQuery();
			
			JSONArray arr=new JSONArray();
			while(rs.next()){
				JSONObject obj=new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("imgurl", rs.getString("bimgurl"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
				obj.put("price", rs.getString("bprice"));
				arr.add(obj);
			}
			result=arr.toJSONString();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	

}
