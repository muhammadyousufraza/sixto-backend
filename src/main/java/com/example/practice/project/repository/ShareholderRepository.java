package com.example.practice.project.repository;

import com.example.practice.project.entity.Shareholder;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareholderRepository extends CustomRepository<Shareholder, Long> {

    List<Shareholder> findByCompany(Long companyId);

}
