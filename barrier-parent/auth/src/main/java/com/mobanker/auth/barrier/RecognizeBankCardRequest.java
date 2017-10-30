package com.mobanker.auth.barrier;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mobanker.auth.barrier.MultipartFormDataFile;

/**
 * @类说明:银行卡(信用卡)OCR请求参数
 * @类名:RecognizeBankCardRequest.java
 * @作者:xiaoshijie
 * @创建日期:2016年9月7日
 */
@Data
@EqualsAndHashCode
public class RecognizeBankCardRequest {
	/** 用户 **/
	private String user;
	/** 密码 **/
	private String pass;
	/** 文件名 **/
	private String fileName;
	/** 阿里云位置 **/
	private String url;
	/** 测试使用 **/
	private boolean test;
	/** 文件参数 **/
	private MultipartFormDataFile file;
	/** 流水号 **/
	private String billNo;
}
