package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.team;

public class TeamDAO {
	
	private Connection connection;
	private MemberDAO memberDao;
	private final String GET_TEAMS_QUERY = "SELECT * FROM teams";
	private final String GET_TEAM_BY_ID_QUERY = "SELECT * FROM teams WHERE id = ?";
	private final String create_new_team_query = "insert into teams(name) values(?)";
	private final String delete_team_by_id_query = "delete from teams where id = ?"; 
	
	public TeamDAO() {
		connection = DBconnection.getConnection();
		memberDao = new MemberDAO();
	}
	
	public List<team> getTeams() throws SQLException{
		ResultSet rs = connection.prepareStatement(GET_TEAMS_QUERY).executeQuery();
		List<team> teams = new ArrayList<team>();
		
		while (rs.next()) {
			teams.add(populateTeam(rs.getInt(1), rs.getString(2)));
		}
		
		return teams;
	}
	
	public team getTeamById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TEAM_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateTeam(rs.getInt(1), rs.getString(2));
	}
	
	public void createNewTeam(String teamName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(create_new_team_query);
		ps.setString(1, teamName);
		ps.executeUpdate();
	}
	
	public void deleteTeamById(int id) throws SQLException {
		memberDao.deleteMembersByTeamId(id);
		PreparedStatement ps = connection.prepareStatement(delete_team_by_id_query);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	private team populateTeam(int id, String name) throws SQLException {
		return new team(id, name, memberDao.getMembersByTeamId(id));
	}
	

}
