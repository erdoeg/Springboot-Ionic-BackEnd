package com.springproject.vmagri.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.vmagri.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
