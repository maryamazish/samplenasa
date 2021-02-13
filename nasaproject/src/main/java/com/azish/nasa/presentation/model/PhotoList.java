package com.azish.nasa.presentation.model;

import lombok.Data;

import java.util.Arrays;

@Data
public class PhotoList {

	private Photo[] photos;

	@Override
	public String toString() {
		return "PhotoList [photos=" + Arrays.toString(photos) + "]";
	}
}
