package com.sep29th.rest.webservices.restfultwebservices.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {
	private int id;
	@Size(min = 2, message = "Username must have more than 2 characters")
	private String name;
	@Past(message = "Birth Date have to in the past")
	private LocalDate birthDate;

	@JsonCreator
	public User(@JsonProperty("name") String name, @JsonProperty("birthDate") LocalDate birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

	public User(int id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
}
