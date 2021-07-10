package com.makemagic.school.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class HouseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String mascot;
	private String school;
	private List<String> colors  = new ArrayList<String>();;
	private String headOfHouse;
	private String founder;
	private String name;
	private String houseGhost;
	private List<String> values  = new ArrayList<String>();

}
