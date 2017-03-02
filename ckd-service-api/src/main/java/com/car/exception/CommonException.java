package com.car.exception;

/**
 * 系统通用异常
 */
public class CommonException extends Exception {
	private static final long serialVersionUID = -1848618491499044704L;

	private String module;
	private String code;
	private String description;


    public CommonException(String message) {
        super(message);
    }

	public CommonException(String module, String code, String message) {
		super(message);
		this.module = module;
		this.code = code;
	}

	public CommonException(String module, String code, String message, String description) {
		super(message);
		this.module = module;
		this.code = code;
		this.description = description;
	}

	/**
	 * 产生异常的模块
	 * 
	 * @return
	 */
	public String getModule() {
		return module;
	}

	/**
	 * 错误码
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 用户可读描述信息
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName());
		sb.append(": [");
		sb.append(module);
		sb.append("] - ");
		sb.append(code);
		sb.append(" - ");
		sb.append(getMessage());
		if (getDescription() != null) {
			sb.append(" - ");
			sb.append(getDescription());
		}
		return sb.toString();
	}
}
