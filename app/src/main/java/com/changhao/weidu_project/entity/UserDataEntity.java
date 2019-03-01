package com.changhao.weidu_project.entity;

public class UserDataEntity {

    /**
     * result : {"createTime":1550781226000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-02-27/20190227140709.JPG","nickName":"FC_6r79f","password":"eWLPHopE945d2ivttHaQTQ==","phone":"13717830790","sex":1,"userId":831}
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
         * createTime : 1550781226000
         * headPic : http://172.17.8.100/images/small/head_pic/2019-02-27/20190227140709.JPG
         * nickName : FC_6r79f
         * password : eWLPHopE945d2ivttHaQTQ==
         * phone : 13717830790
         * sex : 1
         * userId : 831
         */

        private long createTime;
        private String headPic;
        private String nickName;
        private String password;
        private String phone;
        private int sex;
        private int userId;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
