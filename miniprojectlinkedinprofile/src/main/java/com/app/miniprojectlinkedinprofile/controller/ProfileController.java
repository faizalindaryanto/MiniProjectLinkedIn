package com.app.miniprojectlinkedinprofile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.miniprojectlinkedinprofile.model.AllModel;
import com.app.miniprojectlinkedinprofile.model.ProfileModel;
import com.app.miniprojectlinkedinprofile.model.SkillModel;
import com.app.miniprojectlinkedinprofile.service.IProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	IProfileService profileService;

	// Path Home
	@RequestMapping("/home")
	public String home(/* Model model */) {
		/*
		 * List<AllModel> profileList = profileService.findAll(); int totalPage =
		 * profileList.size() / 10; model.addAttribute("totalPage", totalPage);
		 */
		return "/profile/home";
	}
	
	// Path Modal Add
	@RequestMapping("/add")
	public String add() {
		return "/profile/add";
	}

	// Insert Data
	@RequestMapping("/create")
	public String create(HttpServletRequest request) {

		String nama = request.getParameter("nama");
		String pendidikan = request.getParameter("pendidikan");
		int tahunLulus = Integer.valueOf(request.getParameter("tahunLulus"));
		int idGender = Integer.valueOf(request.getParameter("idGender"));
		String noTelp = request.getParameter("noTelp");
		String idSkill8 = request.getParameter("idSkill8");

		int[] idSkill = new int[3];
		int j = 0;
		for (int i = 1; i <= 7; i++) {
			if (request.getParameter("idSkill" + String.valueOf(i)) != null) {
				idSkill[j] = Integer.valueOf(request.getParameter("idSkill" + String.valueOf(i)));
				j++;
			}
		}

		String idSkillO = "";
		if (!request.getParameter("other").equals("")) {
			idSkillO = request.getParameter("other");
		}
		
		ProfileModel profileModel = new ProfileModel();
		SkillModel skillModel = new SkillModel();

		profileModel.setNama(nama);
		profileModel.setPendidikan(pendidikan);
		profileModel.setTahunLulus(tahunLulus);
		profileModel.setIdGender(idGender);
		profileModel.setNoTelp(noTelp);
		profileModel.setIdSkill1(idSkill[0]);
		profileModel.setIdSkill2(idSkill[1]);

		if (!idSkillO.equals("") && idSkill8 != null) {
			skillModel.setNamaSkill(idSkillO);
			profileService.insertSkill(skillModel);
			SkillModel skillModel2 = profileService.findByNameSkill(idSkillO);

			if (idSkill[0] == 0) {
				profileModel.setIdSkill1(skillModel2.getIdSkill());
			} else if (idSkill[1] == 0) {
				profileModel.setIdSkill2(skillModel2.getIdSkill());
			} else {
				profileModel.setIdSkill3(skillModel2.getIdSkill());
			}
		} else {
			profileModel.setIdSkill3(idSkill[2]);

		}
		profileService.insert(profileModel);
		return "/profile/home";
	}

	// Get All Data
	@RequestMapping("/list")
	public String listprofile(Model model) {
		List<AllModel> profileList = profileService.findAll();
		model.addAttribute("bungkusanData", profileList);
		return "/profile/list";
	}

	// Modal Remove
	@RequestMapping("/remove")
	public String remove(HttpServletRequest request, Model model) {
		int id = Integer.valueOf(request.getParameter("id"));
		ProfileModel profileModel = profileService.findById(id);
		model.addAttribute("bingkisanDelete", profileModel);
		return "/profile/remove";
	}

	// Delete Data
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request) {
		int id = Integer.valueOf(request.getParameter("id"));
		profileService.deleteById(id);
		return "/profile/home";
	}

	// Modal Edit
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, Model model) {
		int id = Integer.valueOf(request.getParameter("id"));
		ProfileModel profileModel = profileService.findById(id);

		if (profileModel.getIdSkill1() >= 8) {
			SkillModel skillModel = profileService.findByIdSkill(profileModel.getIdSkill1());
			model.addAttribute("bingkisanEditSkill", skillModel);
		} else if (profileModel.getIdSkill2() >= 8) {
			SkillModel skillModel = profileService.findByIdSkill(profileModel.getIdSkill2());
			model.addAttribute("bingkisanEditSkill", skillModel);
		} else if (profileModel.getIdSkill3() >= 8) {
			SkillModel skillModel = profileService.findByIdSkill(profileModel.getIdSkill3());
			model.addAttribute("bingkisanEditSkill", skillModel);
		}

		model.addAttribute("bingkisanEdit", profileModel);
		return "/profile/edit";
	}

	// Update Data
	@RequestMapping("/update")
	public String update(HttpServletRequest request) {

		int idProfile = Integer.valueOf(request.getParameter("idProfile"));
		String nama = request.getParameter("nama");
		String pendidikan = request.getParameter("pendidikan");
		int tahunLulus = Integer.valueOf(request.getParameter("tahunLulus"));

		int idGender = Integer.valueOf(request.getParameter("idGender"));
		String noTelp = request.getParameter("noTelp");
		String idSkill8 = request.getParameter("idSkill8");

		int[] idSkill = new int[3];
		int j = 0;
		for (int i = 1; i <= 7; i++) {

			if (request.getParameter("idSkill" + String.valueOf(i)) != null) {
				idSkill[j] = Integer.valueOf(request.getParameter("idSkill" + String.valueOf(i)));
				j++;
			}
		}

		String idSkillO = "";
		if (!request.getParameter("other").equals("")) {
			idSkillO = request.getParameter("other");
		}

		ProfileModel profileModel = new ProfileModel();
		SkillModel skillModel = new SkillModel();

		profileModel.setIdProfile(idProfile);
		profileModel.setNama(nama);
		profileModel.setPendidikan(pendidikan);
		profileModel.setTahunLulus(tahunLulus);
		profileModel.setIdGender(idGender);
		profileModel.setNoTelp(noTelp);
		profileModel.setIdSkill1(idSkill[0]);
		profileModel.setIdSkill2(idSkill[1]);

		ProfileModel profileModel1 = profileService.findById(idProfile);

		String namaSkillAwal = "";
		if (profileModel1.getIdSkill1() >= 8) {
			SkillModel skillModel1 = profileService.findByIdSkill(profileModel1.getIdSkill1());
			namaSkillAwal = skillModel1.getNamaSkill();
		} else if (profileModel1.getIdSkill2() >= 8) {
			SkillModel skillModel1 = profileService.findByIdSkill(profileModel1.getIdSkill2());
			namaSkillAwal = skillModel1.getNamaSkill();
		} else if (profileModel1.getIdSkill3() >= 8) {
			SkillModel skillModel1 = profileService.findByIdSkill(profileModel1.getIdSkill3());
			namaSkillAwal = skillModel1.getNamaSkill();
		}

		if (idSkillO.equals("") || idSkill8 == null) {
			profileModel.setIdSkill3(idSkill[2]);
		} else if (!idSkillO.equals(namaSkillAwal)) {
			skillModel.setNamaSkill(idSkillO);
			profileService.insertSkill(skillModel);
			SkillModel skillModel2 = profileService.findByNameSkill(idSkillO);
			if (idSkill[0] == 0) {
				profileModel.setIdSkill1(skillModel2.getIdSkill());
			} else if (idSkill[1] == 0) {
				profileModel.setIdSkill2(skillModel2.getIdSkill());
			} else {
				profileModel.setIdSkill3(skillModel2.getIdSkill());
			}

		} else if (idSkillO.equals(namaSkillAwal)) {
			SkillModel skillModel2 = profileService.findByNameSkill(idSkillO);
			if (idSkill[0] == 0) {
				profileModel.setIdSkill1(skillModel2.getIdSkill());
			} else if (idSkill[1] == 0) {
				profileModel.setIdSkill2(skillModel2.getIdSkill());
			} else {
				profileModel.setIdSkill3(skillModel2.getIdSkill());
			}
		}

		profileService.update(profileModel);
		return "/profile/home";
	}

	// Filter Berdasarkan Skill
	@RequestMapping("/filter")
	public String filter(int id, Model model) {

		if (id == 0) {
			List<AllModel> profileList = profileService.findAll();
			model.addAttribute("bungkuslahData", profileList);
		} else if (id >= 8) {
			List<AllModel> profileList = profileService.findAllFilterOther(id);
			model.addAttribute("bungkuslahData", profileList);
		} else {
			List<AllModel> profileList = profileService.findAllFilter(id);
			model.addAttribute("bungkuslahData", profileList);
		}
		return "/profile/filter";
	}

	// Paginating
	@RequestMapping("/page")
	public String listPage(Model model, HttpServletRequest request) {

		int numberOffset = Integer.valueOf(request.getParameter("numberOffset"));

		numberOffset = numberOffset * 10;
		List<AllModel> profileList = profileService.findPage(numberOffset);
		model.addAttribute("bungkusanData", profileList);

		return "/profile/list";
	}

}
