package com.example.practice.project.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto implements Serializable {

    private Long id;
    @Lob
    private byte[] image;
    private String name;
}
