package com.app.miniprojectlinkedinprofile.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "gender")
@Data

public class JenisKelaminModel {
	
	@Id
	private int idGender;
	private String namaGender;
}
