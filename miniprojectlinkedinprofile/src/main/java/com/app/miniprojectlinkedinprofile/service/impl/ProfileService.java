package com.app.miniprojectlinkedinprofile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.app.miniprojectlinkedinprofile.model.AllModel;
import com.app.miniprojectlinkedinprofile.model.ProfileModel;
import com.app.miniprojectlinkedinprofile.model.SkillModel;
import com.app.miniprojectlinkedinprofile.repository.IProfileRepository;
import com.app.miniprojectlinkedinprofile.service.IProfileService;

@Service
public class ProfileService implements IProfileService {
	
	@Autowired
	IProfileRepository profileRepository;

	@Override
	public ProfileModel insert(ProfileModel profileModel) {
		// TODO Auto-generated method stub
		
		
		return profileRepository.insert(profileModel);
	}

	@Override
	public SkillModel insertSkill(SkillModel skillModel) {
		// TODO Auto-generated method stub
		return profileRepository.insertSkill(skillModel);
	}

	@Override
	public SkillModel findByNameSkill(String name) {
		// TODO Auto-generated method stub
		
		var result = profileRepository.findByNameSkill(name);
		return result;
	}

	@Override
	public List<AllModel> findAll() {
		// TODO Auto-generated method stub
		var result = profileRepository.findAll();
		return result;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		profileRepository.deleteById(id);
		
	}

	@Override
	public ProfileModel findById(int id) {
		// TODO Auto-generated method stub
		
		var result = profileRepository.findById(id);
		return result;
	}

	@Override
	public SkillModel findByIdSkill(int id) {
		// TODO Auto-generated method stub
		
		var result = profileRepository.findByIdSkill(id);
		return result;
	}

	@Override
	public ProfileModel update(ProfileModel profileModel) {
		// TODO Auto-generated method stub
		return profileRepository.update(profileModel);
	}

	@Override
	public List<AllModel> findAllFilter(int id) {
		// TODO Auto-generated method stub
		var result = profileRepository.findAllFilter(id);
		return result;
	}

	@Override
	public List<AllModel> findAllFilterOther(int id) {
		// TODO Auto-generated method stub
		
		var result = profileRepository.findAllFilterOther(id);
		return result;
	}

	@Override
	public List<AllModel> findPage(int numberOffset) {
		// TODO Auto-generated method stub
		var result = profileRepository.findPage(numberOffset);
		return result;
	}





}
