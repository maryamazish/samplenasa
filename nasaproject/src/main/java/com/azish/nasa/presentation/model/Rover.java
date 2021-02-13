package com.azish.nasa.presentation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Arrays;

@Data
public class Rover {
	private int id;
	private String name;
	@JsonProperty("landing_date")
	private String landingDate;

	@JsonProperty("launch_date")
	private String launchDate;

	private String status;

//	@JsonProperty("max_sol")
//	private long maxSol;
//
//	@JsonProperty("max_date")
//	private String maxDate;
//
//	@JsonProperty("total_photos")
//	private long totalPhotos;
//
//	private Camera[] cameras;

}
