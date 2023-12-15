package com.example.practice.project.entity;


import com.example.practice.project.enums.CompanyType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE company SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String secondName;
    private String thirdName;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    private String streetAddress;
    private String detailAddress;
    private String city;
    private String state;
    private String code;

    @OneToOne
    private Package aPackage;

    @ManyToOne
    private User createdBy;

}