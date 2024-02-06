package com.example.practice.project.repository;

import com.example.practice.project.entity.File;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CustomRepository<File, Long> {
}
