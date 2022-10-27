package com.app.miniprojectlinkedinprofile.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "skill")
@Data

public class SkillModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSkill;
	private String namaSkill;
}
