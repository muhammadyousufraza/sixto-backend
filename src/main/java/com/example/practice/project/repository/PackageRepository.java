package com.example.practice.project.repository;

import com.example.practice.project.entity.Package;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends CustomRepository<Package, Long> {

    List<Package> findByCompanyTypeId(Long companyTypeId);

}
