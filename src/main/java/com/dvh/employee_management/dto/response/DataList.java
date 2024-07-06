package com.dvh.employee_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DataList<T> {
    private Integer total;
    private T items;
}
