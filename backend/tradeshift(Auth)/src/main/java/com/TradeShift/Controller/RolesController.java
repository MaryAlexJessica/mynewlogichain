package com.TradeShift.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TradeShift.Entity.Roles;
import com.TradeShift.Repository.RolesRepository;

@RestController
@RequestMapping("/api/role")
public class RolesController {

	@Autowired
	private RolesRepository rolesRepository;
	
	@PostMapping
	public Roles roles(@RequestBody Roles roles) {
		return rolesRepository.save(roles);
	}
	
	@GetMapping
	public List<Roles> roles(){
		return rolesRepository.findAll();
	}
}
