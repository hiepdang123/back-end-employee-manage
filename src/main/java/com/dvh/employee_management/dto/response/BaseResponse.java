package com.dvh.employee_management.dto.response;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@Slf4j
public class BaseResponse {
    public boolean success=false;
    public ErrorResponse error;

    public static <T> ResponseEntity<?> successData(T data){
        BaseItemResponse<T> response=new BaseItemResponse<>();
        response.setSuccess(data);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<?> successListData(List<T> rows,Integer total){
        BaseListResponse<T> response=new BaseListResponse<>();
        response.setResult(rows,total);
//        log.info("res: {}",response);
        return ResponseEntity.ok(response);
    }

    public static <T> BaseResponse error(int code,String message,List<ErrorDetail> errors){
        BaseResponse response=new BaseResponse();
        response.setSuccess(false);
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setCode(code);
        errorResponse.setMessage(message);
        errorResponse.setErrors(errors);

        response.setError(errorResponse);

        return response;
    }

    public static <T> BaseResponse error(int code,String message){
        BaseResponse response=new BaseResponse();
        response.setSuccess(false);
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setCode(code);
        response.setError(errorResponse);

        return response;
    }
}
