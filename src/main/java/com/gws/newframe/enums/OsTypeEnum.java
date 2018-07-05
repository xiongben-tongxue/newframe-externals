
package com.gws.newframe.enums;

/**
 * 【操作系统枚举】
 *
 * @author wangdong
 *
 */
public enum OsTypeEnum {

	iOS(1, "iOS"),
	Android(2, "安卓"),
	ALLSYSTEM(3,"全平台"),
    ;

	private Integer code;
	private String message;

	private OsTypeEnum(Integer code, String message){
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	public static OsTypeEnum getEnum(Integer vCodeType) {
		for (OsTypeEnum osTypeEnum: OsTypeEnum.values()) {
			if (osTypeEnum.getCode().equals(vCodeType)) {
				return osTypeEnum;
			}
		}
		return null;
	}
	
	
}
