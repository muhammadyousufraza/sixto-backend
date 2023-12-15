package com.example.practice.project.repository;

import com.example.practice.project.entity.Company;
import com.example.practice.project.entity.Package;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CustomRepository<Company, Long> {


}
