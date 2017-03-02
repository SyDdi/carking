package com.car.mp.domain;

import java.io.Serializable;

/**
 * 第三方车系结果参数
 * 
 * @author wangjh7
 * @date 2016-08-21
 *
 */
public class ResultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String messge;
	private String newPriceW;//厂商指导价（万）
	private String newcarUpdTaxPrice;//新车实际成交价（含购置税）
	private String saleStatus;//停售/在售情况；1为在售，2为停售
	private String fileStatus;//停产/在产情况；1为在产，2为停产
	private String easyToSell;//易卖度；1为最易卖，…，5为最不易卖（详见易卖度基表）；1对应公众号的5颗星，…，5对应公众号的1颗星
	private String betterRetailPrice;//车况较好时的零售价（万）
	private String betterPersonalTradingPrice;//车况较好时的个人交易价（万）
	private String betterPurchasePrice;//车况较好时的收购价（万）
	private String retailPrice;//车况一般时的零售价（万）
	private String personalTradingPrice;//车况一般时的个人交易价（万）
	private String purchasePrice;//车况一般时的收购价（万）
	private String worseRetailPrice;//车况较差时的零售价（万）
	private String worsePersonalTradingPrice;//车况较差时的个人交易价（万）
	private String worsePurchasePrice;//车况较差时的收购价（万）

    private String curGrade;
    private String gradeRetailPrice;
    private String basePrice;//基准价（万）
    private int milePrice;
    private int colorPrice;
    private int zonePrice;
    private int betterPurchasePriceDifference;
    private int purchasePriceDifference;
    private int worsePurchasePriceDifference;

    public String getCurGrade() {
        return curGrade;
    }

    public String getGradeRetailPrice() {
        return gradeRetailPrice;
    }

    public void setGradeRetailPrice(String gradeRetailPrice) {
        this.gradeRetailPrice = gradeRetailPrice;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public void setCurGrade(String curGrade){
        this.curGrade=curGrade;
    }

    public void setgradeRetailPrice(String curGrade){
        this.curGrade=curGrade;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessge() {
		return messge;
	}

	public void setMessge(String messge) {
		this.messge = messge;
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

	public String getBetterRetailPrice() {
		return betterRetailPrice;
	}

	public void setBetterRetailPrice(String betterRetailPrice) {
		this.betterRetailPrice = betterRetailPrice;
	}

	public String getBetterPersonalTradingPrice() {
		return betterPersonalTradingPrice;
	}

	public void setBetterPersonalTradingPrice(String betterPersonalTradingPrice) {
		this.betterPersonalTradingPrice = betterPersonalTradingPrice;
	}

	public String getBetterPurchasePrice() {
		return betterPurchasePrice;
	}

	public void setBetterPurchasePrice(String betterPurchasePrice) {
		this.betterPurchasePrice = betterPurchasePrice;
	}

	public String getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}

	public String getPersonalTradingPrice() {
		return personalTradingPrice;
	}

	public void setPersonalTradingPrice(String personalTradingPrice) {
		this.personalTradingPrice = personalTradingPrice;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getWorseRetailPrice() {
		return worseRetailPrice;
	}

	public void setWorseRetailPrice(String worseRetailPrice) {
		this.worseRetailPrice = worseRetailPrice;
	}

	public String getWorsePersonalTradingPrice() {
		return worsePersonalTradingPrice;
	}

	public void setWorsePersonalTradingPrice(String worsePersonalTradingPrice) {
		this.worsePersonalTradingPrice = worsePersonalTradingPrice;
	}

	public String getWorsePurchasePrice() {
		return worsePurchasePrice;
	}

	public void setWorsePurchasePrice(String worsePurchasePrice) {
		this.worsePurchasePrice = worsePurchasePrice;
	}

    public int getMilePrice() {
        return milePrice;
    }

    public void setMilePrice(int milePrice) {
        this.milePrice = milePrice;
    }

    public int getColorPrice() {
        return colorPrice;
    }

    public void setColorPrice(int colorPrice) {
        this.colorPrice = colorPrice;
    }

    public int getZonePrice() {
        return zonePrice;
    }

    public void setZonePrice(int zonePrice) {
        this.zonePrice = zonePrice;
    }

    public int getBetterPurchasePriceDifference() {
        return betterPurchasePriceDifference;
    }

    public void setBetterPurchasePriceDifference(int betterPurchasePriceDifference) {
        this.betterPurchasePriceDifference = betterPurchasePriceDifference;
    }

    public int getPurchasePriceDifference() {
        return purchasePriceDifference;
    }

    public void setPurchasePriceDifference(int purchasePriceDifference) {
        this.purchasePriceDifference = purchasePriceDifference;
    }

    public int getWorsePurchasePriceDifference() {
        return worsePurchasePriceDifference;
    }

    public void setWorsePurchasePriceDifference(int worsePurchasePriceDifference) {
        this.worsePurchasePriceDifference = worsePurchasePriceDifference;
    }

	@Override
	public String toString() {
		return "ResultEntity [status=" + status + ", messge=" + messge + ", newPriceW=" + newPriceW
				+ ", newcarUpdTaxPrice=" + newcarUpdTaxPrice + ", saleStatus=" + saleStatus + ", fileStatus="
				+ fileStatus + ", easyToSell=" + easyToSell + ", betterRetailPrice=" + betterRetailPrice
				+ ", betterPersonalTradingPrice=" + betterPersonalTradingPrice + ", betterPurchasePrice="
				+ betterPurchasePrice + ", retailPrice=" + retailPrice + ", personalTradingPrice="
				+ personalTradingPrice + ", purchasePrice=" + purchasePrice + ", worseRetailPrice=" + worseRetailPrice
				+ ", worsePersonalTradingPrice=" + worsePersonalTradingPrice + ", worsePurchasePrice="
				+ worsePurchasePrice + ", curGrade=" + curGrade + ", gradeRetailPrice=" + gradeRetailPrice
				+ ", basePrice=" + basePrice + ", milePrice=" + milePrice + ", colorPrice=" + colorPrice
				+ ", zonePrice=" + zonePrice + ", betterPurchasePriceDifference=" + betterPurchasePriceDifference
				+ ", purchasePriceDifference=" + purchasePriceDifference + ", worsePurchasePriceDifference="
				+ worsePurchasePriceDifference + "]";
	}

/*	public String toString() {
		return "ResultEntity [status=" + status + ", messge=" + messge + ", purchasePrice=" + purchasePrice
				+ ", retailPrice=" + retailPrice + "]";
	}*/


}
