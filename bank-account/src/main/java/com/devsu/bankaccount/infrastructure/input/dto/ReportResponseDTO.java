package com.devsu.bankaccount.infrastructure.input.dto;

import com.devsu.bankaccount.application.dto.AccountReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {

        private Integer customerId;
        private List<AccountReport> accounts;

}
