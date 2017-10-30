package com.mobanker.auth.barrier;

import lombok.Data;

/**
 * @类说明:银行卡验证请求参数
 * @类名:RecognizeBankCardResponse.java
 * @作者:xiaoshijie
 * @创建日期:2016年9月7日
 */
@Data
public class RecognizeBankCardResponse {
	/** 银行卡号 **/
	private String cardNumber;
	/** 有效时间 **/
	private String validate;
	/** 银行卡类型 **/
	private String type;
	/** 属于哪种银行 **/
	private String issuer;

}
