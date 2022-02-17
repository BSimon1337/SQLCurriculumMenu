package dao;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.member;

public class MemberDAO {
	
	private Connection connection;
	private final String GET_MEMBERS_BY_TEAM_ID_QUERY = "SELECT * FROM members WHERE team_id = ?";
	private final String delete_members_by_team_id_query = "delete from members where team_id = ?";
	private final String create_new_member_query = "insert into members(first_name, last_name, team_id) values(?,?,?)";

	
	public MemberDAO() {
		connection = DBconnection.getConnection();
	}

	public List<member> getMembersByTeamId(int teamId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_MEMBERS_BY_TEAM_ID_QUERY);
		ps.setInt(1, teamId);
		ResultSet rs = ps.executeQuery();
		List<member> members = new ArrayList<member>();
		
		while (rs.next()) {
			members.add(new member(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		return members;
	}
	
	public void createNewMember(String firstName, String lastName, int teamId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(create_new_member_query);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setInt(3, teamId);
		ps.executeUpdate();	}
	
	public void deleteMembersByTeamId(int teamId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(delete_members_by_team_id_query);
		ps.setInt(1, teamId);
		ps.executeUpdate();
	}

}
 