package com.mobanker.auth.barrier.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author guanliming
 *
 */
@Data
@EqualsAndHashCode
public class FakeEntity {
	private Integer id;
	private String code;
	private String response;
	private Integer delayMillisecond;
	private Integer callback;
	private String callbackUrl;
	private String callbackStr;
}
