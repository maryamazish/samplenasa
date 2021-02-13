package com.azish.nasa.presentation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.net.MalformedURLException;
import java.net.URL;

@Data
public class Photo {
	private long id;

	private long sol;

	private Camera camera;

	@JsonProperty("img_src")
	private URL imgSrc;

	@JsonProperty("earth_date")
	private String earthDate;

	private Rover rover;


	//public void setImgSrc(URL imgSrc) {
		//this.imgSrc = replaceHttpWithHttps(imgSrc);
	//}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", sol=" + sol + ", camera=" + camera
				+ ", imgSrc=" + imgSrc + ", earthDate=" + earthDate + ", rover="
				+ rover + "]";
	}

	private static URL replaceHttpWithHttps(URL url) {
		try {
			if (!url.toString().contains("https"))
				url = new URL(url.toString().replaceFirst("http", "https"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
}
