package com.example.practice.project.repository;

import com.example.practice.project.entity.Company;
import com.example.practice.project.enums.CompanyStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CustomRepository<Company, Long> {

    Page<Company> findAllByCreatedBy_IdAndCompanyStatusIn(Long userId, List<CompanyStatus> companyStatus, Pageable pageable);

}
