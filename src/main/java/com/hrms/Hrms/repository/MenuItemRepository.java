package com.hrms.Hrms.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.Hrms.model.MenuItem;

@Repository@Transactional
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

	
}
