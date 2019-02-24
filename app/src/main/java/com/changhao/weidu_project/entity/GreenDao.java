package com.changhao.weidu_project.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenDao {
    @Id(autoincrement = true)
    @Unique
    @Property(nameInDb = "commodityId")
    public long commodityId;
    public String commodityName;
    public String masterPic;
    public String price;
    public String saleNum;
    @Generated(hash = 1233672533)
    public GreenDao(long commodityId, String commodityName, String masterPic,
            String price, String saleNum) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.masterPic = masterPic;
        this.price = price;
        this.saleNum = saleNum;
    }
    @Generated(hash = 766040118)
    public GreenDao() {
    }
    public long getCommodityId() {
        return this.commodityId;
    }
    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityName() {
        return this.commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getMasterPic() {
        return this.masterPic;
    }
    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getSaleNum() {
        return this.saleNum;
    }
    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }


    
}
