package com.example.practice.project.entity;

import com.example.practice.project.enums.CompanyStatus;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    private String companyType;

    private String streetAddress;
    private String detailAddress;
    private String city;
    private String state;
    private String code;

    @Enumerated(EnumType.STRING)
    private CompanyStatus companyStatus;

    @OneToOne
    private Package aPackage;

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Payment> payments;

}
