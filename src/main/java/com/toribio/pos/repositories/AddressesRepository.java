package com.toribio.pos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.toribio.pos.models.Addresses;

@Repository
public interface AddressesRepository extends CrudRepository<Addresses, Long>{
    List<Addresses> findAll();
}