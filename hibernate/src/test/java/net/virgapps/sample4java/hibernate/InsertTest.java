package net.virgapps.sample4java.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import junit.framework.TestCase;
import net.virgapps.sample4java.hibernate.model.Player;
import net.virgapps.sample4java.hibernate.model.Team;

public class InsertTest extends TestCase {
	private TeamDAO teamDAO;
	
	@Override
	protected void setUp() throws Exception {
		teamDAO = new TeamDAO();
	}
	
	public void testInsertTeam() {
		Team knicks = new Team("New-York", "Knicks");
		Team lakers = new Team("Los Angeles", "Lakers");
		
		List<Player> lakersPlayers = new ArrayList<Player>();
		lakersPlayers.add(new Player("LeBron", "James"));
		lakersPlayers.add(new Player("Rajon", "Rondo"));
		lakers.setPlayers(lakersPlayers);
		
		List<Player> knicksPlayers = new ArrayList<Player>();
		knicksPlayers.add(new Player("Kristaps", "Porzingis"));
		knicksPlayers.add(new Player("Franck", "Ntilikina"));
		knicks.setPlayers(knicksPlayers);
		
		teamDAO.persist(knicks);
		teamDAO.persist(lakers);
		
		System.out.println("Rosters :");
		List<Team> teams = teamDAO.findAll();
		for (Team team: teams) {
			System.out.println("[" + team.getCity() + " " + team.getName() + "]");
			for (Player player: team.getPlayers()) {
				System.out.println(player.getFirstName() + " " + player.getLastName());
			}
		}
		
		Assert.assertEquals(teams.size(), 2);
	}
}
