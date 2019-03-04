package com.changhao.weidu_project.entity;

import java.util.List;

public class AddressListEntity {
    public List<Result> result;
    public String message;
    public String status;

    public class Result {
        public String address;
        public long createTime;
        public int id;
        public String phone;
        public String realName;
        public int userId;
        public int whetherDefault;
        public int zipCode;
    }
}
