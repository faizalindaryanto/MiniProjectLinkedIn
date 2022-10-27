package com.app.miniprojectlinkedinprofile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.miniprojectlinkedinprofile.model.AllModel;
import com.app.miniprojectlinkedinprofile.model.ProfileModel;
import com.app.miniprojectlinkedinprofile.model.SkillModel;
import com.app.miniprojectlinkedinprofile.service.IProfileService;

@RestController
@RequestMapping("/api")
public class HomeController {

	@Autowired
	IProfileService profileService;

	// Insert Data
	@PostMapping("/insert")
	public ProfileModel insert(@RequestBody ProfileModel profileModel) {
		// TODO Auto-generated method stub
		return profileService.insert(profileModel);
	}

	// Insert Skill
	@PostMapping("/insertskill")
	public SkillModel insertSkill(@RequestBody SkillModel skillModel) {
		return profileService.insertSkill(skillModel);
	}

	// Get Skill Berdasarkan Nama
	@GetMapping("/getByName/{name}")
	public SkillModel getByName(@PathVariable String name) {
		var result = profileService.findByNameSkill(name);
		return result;
	}

	// Get All
	@GetMapping("/getall")
	public List<AllModel> getAll() {
		var result = profileService.findAll();
		return result;
	}

	// Delete Berdasarkan ID
	@DeleteMapping("/delete{id}")
	public ResponseEntity<String> deleteProfile(@PathVariable int id) {
		profileService.deleteById(id);
		return ResponseEntity.status(HttpStatus.GONE).body("Data Sudah Terhapus");
	}

	// Get Berdasarkan ID
	@GetMapping("/getById/{id}")
	public ProfileModel getById(@PathVariable int id) {
		var result = profileService.findById(id);
		return result;
	}

	// Get Skill Berdasarkan ID
	@GetMapping("/getByIdSkill/{id}")
	public SkillModel getByIdSkill(@PathVariable int id) {
		var result = profileService.findByIdSkill(id);
		return result;
	}

	// Paginating
	@GetMapping("/findpage/{numberOffset}")
	public List<AllModel> findPage(@PathVariable int numberOffset) {
		var result = profileService.findPage(numberOffset);
		return result;
	}

}
