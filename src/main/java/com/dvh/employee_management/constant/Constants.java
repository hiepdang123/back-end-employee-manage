package com.dvh.employee_management.constant;

public interface Constants {
    interface ErrorMessageEmployeeValidation{
        String EMP_NO_NOT_BLANK="EmpNo không được để trống";
        String EMP_NAME_NOT_BLANK="EmpName không được để trống";
        String POSITON_NOT_BLANK="Position không được để trống";
        String PASSWORD_NOT_BLANK="Position không được để trống";
        String NOT_FIND_EMPLOYEE_BY_EMPNO="Không tìm thấy employee có empNo là: ";
        String EXIST_EMPLOYEE="Tồn tại employee có empNo là: ";
    }
}
