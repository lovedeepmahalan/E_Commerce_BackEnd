package com.trigon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentLinkResponse {

	private String Payment_Link_Id;
	private String Payment_Link_Url;
}
