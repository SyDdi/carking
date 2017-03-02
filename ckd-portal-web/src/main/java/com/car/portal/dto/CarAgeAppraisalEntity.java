package com.car.portal.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 根据车辆的信息，得到一段时间内的车辆的价格走势
 */
public class CarAgeAppraisalEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String message;
	private String newPriceW;//厂商指导价（万）
	private String newcarUpdTaxPrice;//新车实际成交价（含购置税）
	private String saleStatus;//停售/在售情况；1为在售，2为停售
	private String fileStatus;//停产/在产情况；1为在产，2为停产
	private String easyToSell;//易卖度；1为最易卖，…，5为最不易卖（详见易卖度基表）；1对应公众号的5颗星，…，5对应公众号的1颗星
    private List<PriceItem> priceList;


    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNewPriceW() {
		return newPriceW;
	}

	public void setNewPriceW(String newPriceW) {
		this.newPriceW = newPriceW;
	}

	public String getNewcarUpdTaxPrice() {
		return newcarUpdTaxPrice;
	}

	public void setNewcarUpdTaxPrice(String newcarUpdTaxPrice) {
		this.newcarUpdTaxPrice = newcarUpdTaxPrice;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getEasyToSell() {
		return easyToSell;
	}

	public void setEasyToSell(String easyToSell) {
		this.easyToSell = easyToSell;
	}


    public List<PriceItem> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PriceItem> priceList) {
        this.priceList = priceList;
    }

    @Override
    public String toString() {
        return "CarAgeAppraisalEntity{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", newPriceW='" + newPriceW + '\'' +
                ", newcarUpdTaxPrice='" + newcarUpdTaxPrice + '\'' +
                ", saleStatus='" + saleStatus + '\'' +
                ", fileStatus='" + fileStatus + '\'' +
                ", easyToSell='" + easyToSell + '\'' +
                ", priceList=" + priceList +
                '}';
    }
}
