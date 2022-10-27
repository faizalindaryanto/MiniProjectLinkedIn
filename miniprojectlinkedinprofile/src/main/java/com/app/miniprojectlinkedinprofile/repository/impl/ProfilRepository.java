package com.app.miniprojectlinkedinprofile.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.miniprojectlinkedinprofile.model.AllModel;
import com.app.miniprojectlinkedinprofile.model.ProfileModel;
import com.app.miniprojectlinkedinprofile.model.SkillModel;
import com.app.miniprojectlinkedinprofile.repository.IProfileRepository;

@Repository
public class ProfilRepository implements IProfileRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	// Insert Data
	@Override
	public ProfileModel insert(ProfileModel profileModel) {
		// TODO Auto-generated method stub
		String query = "insert into profile (id_gender, id_skill1, id_skill2, id_skill3, nama, no_telp, pendidikan, tahun_lulus) values (?,?,?,?,?,?,?,?)";

		jdbcTemplate.update(query, new Object[] {profileModel.getIdGender(), profileModel.getIdSkill1(),profileModel.getIdSkill2(),profileModel.getIdSkill3(),profileModel.getNama(),profileModel.getNoTelp(),profileModel.getPendidikan(),profileModel.getTahunLulus() });
		
		return profileModel;
	}
	
	// Insert Skill
	@Override
	public SkillModel insertSkill(SkillModel skillModel) {
		// TODO Auto-generated method stub
		String query = "insert into skill (nama_skill) values (?)";

		jdbcTemplate.update(query, new Object[] {skillModel.getNamaSkill() });
		
		return skillModel;
	}

	// Find Skill Berdasarkan Nama
	@Override
	public SkillModel findByNameSkill(String name) {
		// TODO Auto-generated method stub
		String query = "select * from skill m where m.nama_skill=? group by nama_skill";
		var result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(SkillModel.class), name);
		return result;
	}

	// Find All
	@Override
	public List<AllModel> findAll() {
		// TODO Auto-generated method stub
		String query ="select *, s.nama_skill as skill1, sk.nama_skill as skill2, ski.nama_skill as skill3 from profile1.profile p left join profile1.skill s on p.id_skill1 = s.id_skill left join profile1.skill sk on p.id_skill2 = sk.id_skill left join profile1.skill ski on p.id_skill3 = ski.id_skill join profile1.gender g on p.id_gender = g.id_gender";
		var result  =  jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AllModel.class));
		return result;
	}

	// Delete By ID
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		String query = "delete from profile p where p.id_profile = ?";
		jdbcTemplate.update(query, id);
		
	}
	
	// Find By ID
	@Override
	public ProfileModel findById(int id) {
		// TODO Auto-generated method stub
		String query = "select * from profile p where p.id_profile=?";
		var result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(ProfileModel.class), id);
		return result;
	}
	
	// Find Skill By ID
	@Override
	public SkillModel findByIdSkill(int id) {
		// TODO Auto-generated method stub
		String query = "select * from skill s where s.id_skill=?";
		var result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(SkillModel.class), id);
		return result;
	}
	
	
	// Update Profil
	@Override
	public ProfileModel update(ProfileModel profileModel) {
		// TODO Auto-generated method stub
		String query = "update profile p set p.id_gender=? , p.id_skill1 =?, p.id_skill2=?, p.id_skill3=?, p.nama=?, p.no_telp=?, p.pendidikan=?, p.tahun_lulus=? where p.id_profile=?";
		jdbcTemplate.update(query, new Object[] {profileModel.getIdGender(), profileModel.getIdSkill1(),profileModel.getIdSkill2(),profileModel.getIdSkill3(),profileModel.getNama(),profileModel.getNoTelp(),profileModel.getPendidikan(),profileModel.getTahunLulus(),profileModel.getIdProfile()});
		return profileModel;
	}

	// Filter
	@Override
	public List<AllModel> findAllFilter(int id) {
		// TODO Auto-generated method stub
		String query ="select *, s.nama_skill as skill1, sk.nama_skill as skill2, ski.nama_skill as skill3 from profile1.profile p left join profile1.skill s on p.id_skill1 = s.id_skill left join profile1.skill sk on p.id_skill2 = sk.id_skill left join profile1.skill ski on p.id_skill3 = ski.id_skill join profile1.gender g on p.id_gender = g.id_gender where p.id_skill1=? or p.id_skill2=? or p.id_skill3=?";
		var result  =  jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AllModel.class),id,id,id);
		return result;
	}

	// Filter Other
	@Override
	public List<AllModel> findAllFilterOther(int id) {
		// TODO Auto-generated method stub
		String query ="select *, s.nama_skill as skill1, sk.nama_skill as skill2, ski.nama_skill as skill3 from profile1.profile p left join profile1.skill s on p.id_skill1 = s.id_skill left join profile1.skill sk on p.id_skill2 = sk.id_skill left join profile1.skill ski on p.id_skill3 = ski.id_skill join profile1.gender g on p.id_gender = g.id_gender where p.id_skill1>=? or p.id_skill2>=? or p.id_skill3>=?";
		var result  =  jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AllModel.class),id,id,id);
		return result;
	}

	// Paginating
	@Override
	public List<AllModel> findPage(int numberOffset) {
		// TODO Auto-generated method stub
		String query ="select *, s.nama_skill as skill1, sk.nama_skill as skill2, ski.nama_skill as skill3 from profile1.profile p left join profile1.skill s on p.id_skill1 = s.id_skill left join profile1.skill sk on p.id_skill2 = sk.id_skill left join profile1.skill ski on p.id_skill3 = ski.id_skill join profile1.gender g on p.id_gender = g.id_gender limit 10 offset ?" ;
		var result  =  jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AllModel.class), numberOffset);
		return result;
	}




	


	

}
