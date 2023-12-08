package com.example.practice.project.entity;

import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
//import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    private boolean isDeleted;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
