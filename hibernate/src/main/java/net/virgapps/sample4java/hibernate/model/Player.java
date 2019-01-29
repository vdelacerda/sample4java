package net.virgapps.sample4java.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity @Table(name="player")
public class Player {
	private Long id;
	private String firstName;
	private String lastName;
	private Team team;
	
	public Player() {}
	
	public Player(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id @GeneratedValue(generator="increment") @GenericGenerator(name="increment", strategy="increment")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="firstname", nullable=false, length=64)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="lastname", nullable=false, length=64)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	public Team getTeam() {
		return this.team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
}
