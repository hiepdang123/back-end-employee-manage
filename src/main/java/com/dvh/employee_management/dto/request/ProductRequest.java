package com.dvh.employee_management.dto.request;

import com.dvh.employee_management.constant.Constants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = Constants.ErrorMessageProductValidation.NAME_NOT_BLANK)
    private String name;
    @Min(value = 0,message = Constants.ErrorMessageProductValidation.AMOUNT_MIN)
    private String amount;
    @Positive(message=Constants.ErrorMessageProductValidation.PRICE_GREATE_THAN)
    private double price;
    @NotBlank(message = Constants.ErrorMessageProductValidation.NAME_NOT_BLANK)
    private String imgUrl;
}
