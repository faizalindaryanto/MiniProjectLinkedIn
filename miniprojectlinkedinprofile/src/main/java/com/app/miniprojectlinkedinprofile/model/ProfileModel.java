package com.app.miniprojectlinkedinprofile.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "profile")
@Data

public class ProfileModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int idProfile;
	private String nama;
	private String pendidikan;
	private int tahunLulus;
	private String noTelp;
	private int idSkill1;
	private int idSkill2;
	private int idSkill3;
	private int idGender;
	

}
