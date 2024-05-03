package com.example.practice.project.repository;

import com.example.practice.project.entity.Company;
import com.example.practice.project.enums.CompanyStatus;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CustomRepository<Company, Long> {

    Page<Company> findAllByCreatedBy_IdAndCompanyStatusIn(Long userId, List<CompanyStatus> companyStatus, Pageable pageable);

    Page<Company> findAllByCreatedBy_Id(Long userId, Pageable pageable);

    @Query("SELECT c FROM Company c JOIN c.payments p WHERE p.status = 'PAID' AND c.createdBy.id = :userId")
    Page<Company> findPaidCompaniesByCreatedBy(@Param("userId") Long userId, Pageable pageable);

}
