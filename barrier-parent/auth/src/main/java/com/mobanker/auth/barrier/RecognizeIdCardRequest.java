package com.mobanker.auth.barrier;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mobanker.auth.barrier.MultipartFormDataFile;

/**
 * @类说明:身份证请求参数
 * @类名:RecognizeIdCardRequest.java
 * @作者:xiaoshijie
 * @创建日期:2016年9月7日
 */
@Data
@EqualsAndHashCode
public class RecognizeIdCardRequest {
	/** 用户 **/
	private String user;
	/** 密码 **/
	private String pass;
	/** 编码 **/
	private String encoding;
	/** 是否返回头像,1-返回，0-不返回 **/
	private String headPortrait;
	/** 是否返回切边图,1-返回，0-不返回 **/
	private String cropImage;
	/** 使用先切边图后识别,1-使用，0-不使用 **/
	private String recognizeMode;
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
