package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;// 管理员
    }

    public interface Cart {
        int CHECKED = 1;//选中
        int UN_CHECKED = 0;
        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }
    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");

    }

    public enum ProductStatusEnum {
        ON_SALE(1, "在线"),
        on_sale(2, "在线"),
        ;
        private int code;
        private String value;

        ProductStatusEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public enum OrderStatusEnum {
        NO_PAY(1, "未付款"),
        PAYED(2, "已付款"),
        ;
        private int code;
        private String value;

        OrderStatusEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public enum PayPlatformEnum {
        ALI_PAY(1, "支付宝"),
        ;
        private int code;
        private String value;

        PayPlatformEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    public enum PaymentTypeEnum {
        ONLINE_PAY(1, "在线支付"),
        ;
        private int code;
        private String value;

        PaymentTypeEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }
}
