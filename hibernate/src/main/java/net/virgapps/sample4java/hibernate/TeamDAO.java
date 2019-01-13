package net.virgapps.sample4java.hibernate;

import java.util.List;

import net.virgapps.sample4java.hibernate.model.Team;

public class TeamDAO implements DAO<Team, Long> {
	private static Controller<Team, Long> controller;
	
	public TeamDAO() {
		controller = new Controller<Team, Long>(Team.class);
	}
	
	@Override
	public Team persist(Team t) {
		return controller.persist(t);
	}

	@Override
	public Team find(Long id) {
		return controller.find(id);
	}

	@Override
	public List<Team> findAll() {
		return controller.findAll();
	}
}