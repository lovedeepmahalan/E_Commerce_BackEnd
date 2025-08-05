package com.trigon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trigon.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

	
}
  