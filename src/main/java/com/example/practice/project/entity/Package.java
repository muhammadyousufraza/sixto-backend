package com.example.practice.project.entity;


import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.management.openmbean.ArrayType;
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
}
