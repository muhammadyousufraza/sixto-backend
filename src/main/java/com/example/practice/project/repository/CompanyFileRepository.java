package com.example.practice.project.repository;

import com.example.practice.project.entity.CompanyFiles;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyFileRepository extends CustomRepository<CompanyFiles, Long> {

    List<CompanyFiles> findByCompanyId(Long companyId);

}
