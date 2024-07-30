package com.dvh.employee_management.exception;

import com.dvh.employee_management.dto.response.BaseResponse;
import com.dvh.employee_management.dto.response.ErrorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalHandleException {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleException(Exception e){
        log.error("Exception: {}",e);
        return BaseResponse.error(1,e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleException(HttpMessageNotReadableException e){
        log.error("Exception: {}",e);
        return BaseResponse.error(2,e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleException(MethodArgumentNotValidException e){
        log.error("Exception: {}",e);
        List<FieldError> listErrors=e.getBindingResult().getFieldErrors();
        List<ErrorDetail> errorDetails=new ArrayList<>();
        for (FieldError error:listErrors){
            ErrorDetail errorDetail=new ErrorDetail();
            errorDetail.setId(error.getField());
            errorDetail.setMessage(error.getDefaultMessage());
            errorDetails.add(errorDetail);
        }
        return BaseResponse.error(2,"Dữ liệu/ Tham số không hợp lệ",errorDetails);
    }

    @ExceptionHandler(EmployeeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleEmployeeException(EmployeeException e){
        log.error("Exception: {}",e);
        return BaseResponse.error(3,e.getMessage());
    }

    @ExceptionHandler(ProductException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleProductException(ProductException e){
        log.error("Exception: {}",e);
        return BaseResponse.error(3,e.getMessage());
    }

    @ExceptionHandler(FileException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleFileException(FileException e){
        log.error("Exception: {}",e);
        return BaseResponse.error(3,e.getMessage());
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleDataNotFoundException(DataNotFoundException e){
        log.error("Exception: {}",e);
        return BaseResponse.error(4,e.getMessage());
    }
}
