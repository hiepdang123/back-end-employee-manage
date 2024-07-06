package com.dvh.employee_management.dto.request;

import com.dvh.employee_management.constant.Constants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = Constants.ErrorMessageEmployeeValidation.EMP_NO_NOT_BLANK)
    private String empNo;
    @NotBlank(message = Constants.ErrorMessageEmployeeValidation.EMP_NO_NOT_BLANK)
    private String password;
}
