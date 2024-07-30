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

    interface ErrorMessageProductValidation{
        String NAME_NOT_BLANK="Tên sản phẩm không được để trống";
        String PRICE_GREATE_THAN="Giá phải lớn hơn 0";
        String AMOUNT_MIN = "Số lượng phải lớn hơn hoặc bằng 0";
        String IMG_NOT_BLANK="Ảnh không được để trống";
        String NOT_FIND_PRODUCT_BY_ID="Không tìm thấy sản phẩm có ID là: ";
    }
}
