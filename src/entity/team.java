package entity;

import java.util.List;

public class team {
	
	private int teamId;
	private String name;
	private List<member> members;
	
	public team(int teamId, String name, List<member> members) {
		this.setTeamId(teamId);
		this.setName(name);
		this.setMembers(members);
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<member> getMembers() {
		return members;
	}

	public void setMembers(List<member> members) {
		this.members = members;
	}

}
