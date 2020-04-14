package net.growdev.footballclub.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAllTeam{

	@SerializedName("teams")
	private List<TeamsItem> teams;

	public List<TeamsItem> getTeams(){
		return teams;
	}
}