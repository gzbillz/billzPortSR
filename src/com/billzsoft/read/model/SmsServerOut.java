package com.billzsoft.read.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @description 从金迪短信中获取，待发短信信息【smsserver_out】
 * @class SmsServerOut
 * @author YHZ
 * @date 2013-6-24
 */
@SuppressWarnings("serial")
public class SmsServerOut implements Serializable {

	private Long id; // 编号
	private String recipient; // 手机号
	private String text; // 短信内容
	private String status ; // 短信状态："U" :未发送, "Q" : 排队中, "S" : 已发送, "F" : 失败

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 手机号
	 * 
	 * @return
	 */
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 * 短信内容
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("recipient", this.recipient).append("text", this.text)
				.toString();
	}
}
