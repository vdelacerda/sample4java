package net.virgapps.sample4java.hibernate;

import java.util.List;

import net.virgapps.sample4java.hibernate.model.Player;

public class PlayerDAO implements DAO<Player, Long> {
	private static Controller<Player, Long> controller;
	
	public PlayerDAO() {
		controller = new Controller<Player, Long>(Player.class);
	}
	
	@Override
	public Player persist(Player p) {
		return controller.persist(p);
	}

	@Override
	public Player find(Long id) {
		return controller.find(id);
	}

	@Override
	public List<Player> findAll() {
		return controller.findAll();
	}
}