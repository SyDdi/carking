package com.car.domain;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "t_vehicle_pic")
public class Picture extends IdEntity implements Comparable<Picture>{
    /**
     * 车源id
     */
    @Column(name = "vehicle_id")
    private Long vehicleId;

    /**
     * 图片路径
     */
    private String img;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;
    
    /**
     * 1:左前2：右后3：仪表盘特写4：仪表盘全景5：后排座椅6：前排座椅
     */
    private Integer type;

    private Short status;

    /**
     * 获取车源id
     *
     * @return vehicle_id - 车源id
     */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**
     * 设置车源id
     *
     * @param vehicleId 车源id
     */
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 获取图片路径
     *
     * @return img - 图片路径
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置图片路径
     *
     * @param img 图片路径
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return status
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Short status) {
        this.status = status;
    }

	@Override
	public int compareTo(Picture o) {
		return this.getType()>o.getType()?1:-1;
	}
}