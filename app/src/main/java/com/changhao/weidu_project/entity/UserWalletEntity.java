package com.changhao.weidu_project.entity;

import java.util.List;

public class UserWalletEntity {

    /**
     * result : {"balance":99999999,"detailList":[]}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * balance : 99999999
         * detailList : []
         */

        private double balance;
        private List<?> detailList;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public List<?> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<?> detailList) {
            this.detailList = detailList;
        }
    }
}
