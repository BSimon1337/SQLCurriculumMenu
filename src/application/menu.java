package application;

import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.MemberDAO;
import dao.TeamDAO;
import entity.member;
import entity.team;

public class menu {
	
	private TeamDAO teamDao = new TeamDAO();
	private MemberDAO memberDao = new MemberDAO();
	private Scanner scanner = new Scanner(System.in);
	
	private List<String> options = Arrays.asList(
			"Display Teams", 
			"Display a Team", 
			"Create Team", 
			"Delete Team", 
			"Create Team Member", 
			"Delete Team Member");
	
	public void start() {
		String selection = "";
		
		do {
			printmenu();
			selection = scanner.nextLine();
			
			
			try {
				if (selection.equals("1")) {
					displayTeams();
				}else if (selection.equals("2")){
					displayTeam();
				}else if (selection.equals("3")){
					createTeam();
				}else if (selection.equals("4")){
					deleteTeam();
				}else if (selection.equals("5")){
					createMember();
				}else if (selection.equals("6")){
					deleteMember();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			System.out.println("Press enter to continue.....");
			scanner.nextLine();
			
		}while (!selection.equals("-1"));
	}

	private void deleteTeam() throws SQLException {
		System.out.print("Enter team Id to delete:");
		int id = Integer.parseInt(scanner.nextLine());
		teamDao.deleteTeamById(id);
		
	}

	private void createTeam() throws SQLException {
		System.out.print("Enter new team name:");
		String teamName = scanner.nextLine();
		teamDao.createNewTeam(teamName);
	}

	private void displayTeam() throws SQLException {
		System.out.print("Enter team id: ");
		int id = Integer.parseInt(scanner.nextLine());
		team team = teamDao.getTeamById(id);
		System.out.println(team.getTeamId() + ": " + team.getName());
		for (member member : team.getMembers()) {
			System.out.println("\tMemberId: " + member.getMemberId() + " - Name: " + member.getFirstName() + " " + member.getLastName());
		}
	}

	private void displayTeams() throws SQLException {
		List<team> teams = teamDao.getTeams();
		for (team team : teams) {
			System.out.println(team.getTeamId() + ": " + team.getName());
		}
		
	}

	private void printmenu() {
		System.out.println("Select and option:\n-----------------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ")" + options.get(i));
		}
		
	}
	
	private void createMember() throws SQLException {
		System.out.print("Enter first name of new member: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter last name of new member: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter team id of new member: ");
		int teamId = Integer.parseInt(scanner.nextLine());
		memberDao.createNewMember(firstName, lastName, teamId);
	}
	
	private void deleteMember() throws SQLException {
		System.out.println("Enter member id to delete:");
		int id = Integer.parseInt(scanner.nextLine());
		memberDao.deleteMemberById(id);
	}

}
