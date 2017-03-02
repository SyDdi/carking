package com.car.domain;

import javax.persistence.*;

@Table(name = "t_car_model_ext")
public class ModelExt extends IdEntity {
    /**
     * 车型ID
     */
    @Id
    @Column(name = "model_id")
    private Integer modelId;

    /**
     * 基本参数
     */
    @Column(name = "base_info")
    private String baseInfo;

    /**
     * 车身
     */
    @Column(name = "body_info")
    private String bodyInfo;

    /**
     * 发动机
     */
    @Column(name = "engine_info")
    private String engineInfo;

    /**
     * 变速箱
     */
    @Column(name = "gearbox_info")
    private String gearboxInfo;

    /**
     * 地盘转向
     */
    @Column(name = "chassis_steering")
    private String chassisSteering;

    /**
     * 车轮制动
     */
    @Column(name = "wheel_brake")
    private String wheelBrake;

    /**
     * 安全装备
     */
    @Column(name = "safety_info")
    private String safetyInfo;

    /**
     * 操控配置
     */
    @Column(name = "control_info")
    private String controlInfo;

    /**
     * 外部配置
     */
    @Column(name = "external_info")
    private String externalInfo;

    /**
     * 内部配置
     */
    @Column(name = "internal_info")
    private String internalInfo;

    /**
     * 座椅配置
     */
    @Column(name = "seat_info")
    private String seatInfo;

    /**
     * 多媒体配置
     */
    @Column(name = "multimedia_info")
    private String multimediaInfo;

    /**
     * 灯光配置
     */
    @Column(name = "lighting_info")
    private String lightingInfo;

    /**
     * 玻璃/后视镜
     */
    @Column(name = "glass_info")
    private String glassInfo;

    /**
     * 空调/冰箱
     */
    @Column(name = "air_conditioner_info")
    private String airConditionerInfo;

    /**
     * 高科技配置
     */
    @Column(name = "high_tech_info")
    private String highTechInfo;

    /**
     * 获取车型ID
     *
     * @return model_id - 车型ID
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * 设置车型ID
     *
     * @param modelId 车型ID
     */
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    /**
     * 获取基本参数
     *
     * @return base_info - 基本参数
     */
    public String getBaseInfo() {
        return baseInfo;
    }

    /**
     * 设置基本参数
     *
     * @param baseInfo 基本参数
     */
    public void setBaseInfo(String baseInfo) {
        this.baseInfo = baseInfo == null ? null : baseInfo.trim();
    }

    /**
     * 获取车身
     *
     * @return body_info - 车身
     */
    public String getBodyInfo() {
        return bodyInfo;
    }

    /**
     * 设置车身
     *
     * @param bodyInfo 车身
     */
    public void setBodyInfo(String bodyInfo) {
        this.bodyInfo = bodyInfo == null ? null : bodyInfo.trim();
    }

    /**
     * 获取发动机
     *
     * @return engine_info - 发动机
     */
    public String getEngineInfo() {
        return engineInfo;
    }

    /**
     * 设置发动机
     *
     * @param engineInfo 发动机
     */
    public void setEngineInfo(String engineInfo) {
        this.engineInfo = engineInfo == null ? null : engineInfo.trim();
    }

    /**
     * 获取变速箱
     *
     * @return gearbox_info - 变速箱
     */
    public String getGearboxInfo() {
        return gearboxInfo;
    }

    /**
     * 设置变速箱
     *
     * @param gearboxInfo 变速箱
     */
    public void setGearboxInfo(String gearboxInfo) {
        this.gearboxInfo = gearboxInfo == null ? null : gearboxInfo.trim();
    }

    /**
     * 获取地盘转向
     *
     * @return chassis_steering - 地盘转向
     */
    public String getChassisSteering() {
        return chassisSteering;
    }

    /**
     * 设置地盘转向
     *
     * @param chassisSteering 地盘转向
     */
    public void setChassisSteering(String chassisSteering) {
        this.chassisSteering = chassisSteering == null ? null : chassisSteering.trim();
    }

    /**
     * 获取车轮制动
     *
     * @return wheel_brake - 车轮制动
     */
    public String getWheelBrake() {
        return wheelBrake;
    }

    /**
     * 设置车轮制动
     *
     * @param wheelBrake 车轮制动
     */
    public void setWheelBrake(String wheelBrake) {
        this.wheelBrake = wheelBrake == null ? null : wheelBrake.trim();
    }

    /**
     * 获取安全装备
     *
     * @return safety_info - 安全装备
     */
    public String getSafetyInfo() {
        return safetyInfo;
    }

    /**
     * 设置安全装备
     *
     * @param safetyInfo 安全装备
     */
    public void setSafetyInfo(String safetyInfo) {
        this.safetyInfo = safetyInfo == null ? null : safetyInfo.trim();
    }

    /**
     * 获取操控配置
     *
     * @return control_info - 操控配置
     */
    public String getControlInfo() {
        return controlInfo;
    }

    /**
     * 设置操控配置
     *
     * @param controlInfo 操控配置
     */
    public void setControlInfo(String controlInfo) {
        this.controlInfo = controlInfo == null ? null : controlInfo.trim();
    }

    /**
     * 获取外部配置
     *
     * @return external_info - 外部配置
     */
    public String getExternalInfo() {
        return externalInfo;
    }

    /**
     * 设置外部配置
     *
     * @param externalInfo 外部配置
     */
    public void setExternalInfo(String externalInfo) {
        this.externalInfo = externalInfo == null ? null : externalInfo.trim();
    }

    /**
     * 获取内部配置
     *
     * @return internal_info - 内部配置
     */
    public String getInternalInfo() {
        return internalInfo;
    }

    /**
     * 设置内部配置
     *
     * @param internalInfo 内部配置
     */
    public void setInternalInfo(String internalInfo) {
        this.internalInfo = internalInfo == null ? null : internalInfo.trim();
    }

    /**
     * 获取座椅配置
     *
     * @return seat_info - 座椅配置
     */
    public String getSeatInfo() {
        return seatInfo;
    }

    /**
     * 设置座椅配置
     *
     * @param seatInfo 座椅配置
     */
    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo == null ? null : seatInfo.trim();
    }

    /**
     * 获取多媒体配置
     *
     * @return multimedia_info - 多媒体配置
     */
    public String getMultimediaInfo() {
        return multimediaInfo;
    }

    /**
     * 设置多媒体配置
     *
     * @param multimediaInfo 多媒体配置
     */
    public void setMultimediaInfo(String multimediaInfo) {
        this.multimediaInfo = multimediaInfo == null ? null : multimediaInfo.trim();
    }

    /**
     * 获取灯光配置
     *
     * @return lighting_info - 灯光配置
     */
    public String getLightingInfo() {
        return lightingInfo;
    }

    /**
     * 设置灯光配置
     *
     * @param lightingInfo 灯光配置
     */
    public void setLightingInfo(String lightingInfo) {
        this.lightingInfo = lightingInfo == null ? null : lightingInfo.trim();
    }

    /**
     * 获取玻璃/后视镜
     *
     * @return glass_info - 玻璃/后视镜
     */
    public String getGlassInfo() {
        return glassInfo;
    }

    /**
     * 设置玻璃/后视镜
     *
     * @param glassInfo 玻璃/后视镜
     */
    public void setGlassInfo(String glassInfo) {
        this.glassInfo = glassInfo == null ? null : glassInfo.trim();
    }

    /**
     * 获取空调/冰箱
     *
     * @return air_conditioner_info - 空调/冰箱
     */
    public String getAirConditionerInfo() {
        return airConditionerInfo;
    }

    /**
     * 设置空调/冰箱
     *
     * @param airConditionerInfo 空调/冰箱
     */
    public void setAirConditionerInfo(String airConditionerInfo) {
        this.airConditionerInfo = airConditionerInfo == null ? null : airConditionerInfo.trim();
    }

    /**
     * 获取高科技配置
     *
     * @return high_tech_info - 高科技配置
     */
    public String getHighTechInfo() {
        return highTechInfo;
    }

    /**
     * 设置高科技配置
     *
     * @param highTechInfo 高科技配置
     */
    public void setHighTechInfo(String highTechInfo) {
        this.highTechInfo = highTechInfo == null ? null : highTechInfo.trim();
    }
}