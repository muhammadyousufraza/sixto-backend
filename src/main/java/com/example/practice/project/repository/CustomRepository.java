package com.example.practice.project.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface CustomRepository<T extends Object, I extends Serializable> extends JpaRepository<T,I> {
    List<T> findAllByIdIn(List<I> ids);
}
