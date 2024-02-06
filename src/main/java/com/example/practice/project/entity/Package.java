package com.example.practice.project.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SQLDelete(sql = "UPDATE package SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Package extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String packageTitle;
    private Double packageTotalPrice;
    private Double packageFee;
    private Double stateFee;
    @Column(columnDefinition = "json")
    @JsonRawValue
    private String packageDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_type_id", referencedColumnName = "id", nullable = false)
    private CompanyType companyType;

}
