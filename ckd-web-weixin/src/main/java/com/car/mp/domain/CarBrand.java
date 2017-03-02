package com.car.mp.domain;

public class CarBrand {
	
	private Integer id;
	private String brand_name_cn;
    private String brand_name_en;
    private String first_letter;
    private Integer brand_id;
    private Integer father_brand_id;
    private Integer brand_level;
    private Integer pic_id;
    private Integer hot_flag;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand_name_cn() {
		return brand_name_cn;
	}
	public void setBrand_name_cn(String brand_name_cn) {
		this.brand_name_cn = brand_name_cn;
	}
	public String getBrand_name_en() {
		return brand_name_en;
	}
	public void setBrand_name_en(String brand_name_en) {
		this.brand_name_en = brand_name_en;
	}
	public String getFirst_letter() {
		return first_letter;
	}
	public void setFirst_letter(String first_letter) {
		this.first_letter = first_letter;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public Integer getFather_brand_id() {
		return father_brand_id;
	}
	public void setFather_brand_id(Integer father_brand_id) {
		this.father_brand_id = father_brand_id;
	}
	public Integer getBrand_level() {
		return brand_level;
	}
	public void setBrand_level(Integer brand_level) {
		this.brand_level = brand_level;
	}
	public Integer getPic_id() {
		return pic_id;
	}
	public void setPic_id(Integer pic_id) {
		this.pic_id = pic_id;
	}
	public Integer getHot_flag() {
		return hot_flag;
	}
	public void setHot_flag(Integer hot_flag) {
		this.hot_flag = hot_flag;
	}
}
