package com.app.miniprojectlinkedinprofile.repository;

import java.util.List;



import com.app.miniprojectlinkedinprofile.model.AllModel;
import com.app.miniprojectlinkedinprofile.model.ProfileModel;
import com.app.miniprojectlinkedinprofile.model.SkillModel;

public interface IProfileRepository {
	
	public ProfileModel insert(ProfileModel profileModel);
	public SkillModel insertSkill(SkillModel skillModel);
	public SkillModel findByNameSkill(String name);
	public List<AllModel> findAll();
	public void deleteById(int id);
	public ProfileModel findById(int id);
	public SkillModel findByIdSkill(int id);
	public ProfileModel update(ProfileModel profileModel);
	public List<AllModel> findAllFilter(int id);
	public List<AllModel> findAllFilterOther(int id);
	public List<AllModel> findPage(int numberOffset);




}
