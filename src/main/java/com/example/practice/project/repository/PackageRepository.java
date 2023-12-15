package com.example.practice.project.repository;

import com.example.practice.project.entity.Package;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends CustomRepository<Package, Long> {


}
