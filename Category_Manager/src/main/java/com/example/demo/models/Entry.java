package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@JsonProperty("API")
	private String api;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("Auth")
	private String auth;

	@JsonProperty("HTTPS")
	private boolean https;

	@JsonProperty("CORS")
	private String cors;

	@JsonProperty("Link")
	private String link;

	@JsonProperty("Category")
	private String category;

}
