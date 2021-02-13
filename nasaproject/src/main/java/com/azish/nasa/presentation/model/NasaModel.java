package com.azish.nasa.presentation.model;



import lombok.Data;

import java.util.Date;

@Data
public class NasaModel {
private Integer sol ;
private Date earth_date;
private String camera;
private Integer page;
private String api_key;
}
