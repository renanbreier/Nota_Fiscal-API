package com.github.renanbreier.nf.repository;

import com.github.renanbreier.nf.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
