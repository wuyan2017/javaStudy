package com.mobanker.auth.barrier.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 客户签名验证
 * 
 * @author songxiangwei
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class MerchantKey{

	private String id;

	private String merchantId;// 商户ID

	private String clientPrivateKey;// 客户私钥

	private String clientPublicKey;// 客户公钥

	private String serverPrivateKey;// 服务私钥

	private String serverPublicKey;// 服务公钥
	
	private String desKey;//DES 密钥

}
