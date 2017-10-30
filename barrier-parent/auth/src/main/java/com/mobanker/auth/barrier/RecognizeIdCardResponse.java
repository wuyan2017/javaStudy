package com.mobanker.auth.barrier;

import lombok.Data;

/**
 * @类说明:身份证银行卡验证
 * @类名:RecognizeIdCardResponse.java
 * @作者:xiaoshijie
 * @创建日期:2016年9月7日
 */
@Data
public class RecognizeIdCardResponse {
	/** 身份证类型 **/
	private String type;
	/** 有效时间 **/
	private String validity;
	/** 身份证切边照片 **/
	private String croppedImage;
	/** 是否返回头像 **/
	private String headPortrait;
	/** 发证机构 **/
	private String issueAuthority;
	/** 地址 **/
	private String address;
	/** 生日 **/
	private String birthday;
	/** 身份证号码 **/
	private String idNumber;
	/** 名字 **/
	private String name;
	/** 籍贯 **/
	private String people;
	/** 性别 **/
	private String sex;

}
