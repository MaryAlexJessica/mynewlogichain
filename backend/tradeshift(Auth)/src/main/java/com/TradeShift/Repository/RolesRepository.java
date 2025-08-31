package com.TradeShift.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TradeShift.Entity.Roles;


public interface RolesRepository extends JpaRepository<Roles, Integer> {

	Optional<Roles> findByRoleName(String roleName);
}
