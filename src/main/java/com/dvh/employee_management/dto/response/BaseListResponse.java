package com.dvh.employee_management.dto.response;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class BaseListResponse<T> extends BaseResponse {
    private DataList data;

    public void setResult(Collection<T> rows, Integer total) {
        if (rows != null) {
            super.setSuccess(true);
            data = new DataList();
            data.setItems(rows);
            data.setTotal(total);
        }
    }
}
