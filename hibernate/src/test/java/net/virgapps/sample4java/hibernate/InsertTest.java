package net.virgapps.sample4java.hibernate;

import java.util.List;

import org.junit.Assert;

import junit.framework.TestCase;
import net.virgapps.sample4java.hibernate.model.Player;
import net.virgapps.sample4java.hibernate.model.Team;

public class InsertTest extends TestCase {
	private TeamDAO teamDAO;
	private PlayerDAO playerDAO;
	
	@Override
	protected void setUp() throws Exception {
		teamDAO = new TeamDAO();
		playerDAO = new PlayerDAO();
	}
	
	public void testInsertTeam() {
		Team knicks = new Team("New-York", "Knicks");
		Team lakers = new Team("Los Angeles", "Lakers");
		
		knicks.addPlayer(new Player("LeBron", "James"));
		knicks.addPlayer(new Player("Rajon", "Rondo"));
		
		knicks.addPlayer(new Player("Kristaps", "Porzingis"));
		knicks.addPlayer(new Player("Franck", "Ntilikina"));
		
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
		
		List<Player> players = playerDAO.findAll();
		for (Player player: players) {
			System.out.println(player.getFirstName() + " " + player.getLastName() + " plays for " + player.getTeam().getName());
		}
		
		Assert.assertEquals(teams.size(), 2);
	}
}
