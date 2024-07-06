package com.dvh.employee_management.dto.request;

import com.dvh.employee_management.constant.Constants;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    @NotBlank(message = Constants.ErrorMessageEmployeeValidation.EMP_NO_NOT_BLANK)
    private String empNo;
    @NotBlank(message = Constants.ErrorMessageEmployeeValidation.EMP_NAME_NOT_BLANK)
    private String empName;
    @NotBlank(message = Constants.ErrorMessageEmployeeValidation.POSITON_NOT_BLANK)
    private String position;
    @NotBlank(message = Constants.ErrorMessageEmployeeValidation.EMP_NO_NOT_BLANK)
    private String password;
}
