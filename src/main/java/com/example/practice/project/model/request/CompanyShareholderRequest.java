package com.example.practice.project.model.request;

import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyShareholderRequest implements Serializable {

    @Valid
    @NotNull(message = "Company Payload cannot be empty")
    private CompanyAddRequest company;
    @Valid
    @NotNull(message = "Shareholder Payload cannot be empty")
    private List<ShareholderAddRequest> shareholder;

}
