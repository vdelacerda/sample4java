package net.virgapps.sample4java.hibernate.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity @Table(name="team")
public class Team {
	private Long id;
	private String city;
	private String name;
	private Collection<Player> players = new HashSet<Player>();
	
	public Team() {}
	
	public Team(String city, String name) {
		this.city = city;
		this.name = name;
	}
	
	@Id @GeneratedValue(generator="increment") @GenericGenerator(name="increment", strategy="increment")
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="city", nullable=false, length=64)
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="name", nullable=false, length=64)
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade= {CascadeType.ALL})
	public Collection<Player> getPlayers() {
		return this.players;
	}
	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}
}
