package com.geekbrains.geek.market.repositories;


import com.geekbrains.geek.market.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
