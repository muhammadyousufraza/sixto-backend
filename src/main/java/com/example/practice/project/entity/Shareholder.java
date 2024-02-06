package com.example.practice.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@SQLDelete(sql = "UPDATE shareholder SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Shareholder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String nationality;
    private String passportNumber;
    private String occupation;
    private String maritalStatus;
    private String homeAddress;
    private Double sharePercentage;
    private boolean isShareHolder;
    private boolean isLegalRepresentative;
    private boolean isManager;

    @OneToOne
    private Company company;

}
