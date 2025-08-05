package com.trigon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {

	private String paymentMethod;
	private String 	paymentStatus;
	private String paymentId;
	private String rozarPayPaymentLinkId;
	private String rozarPayPaymentLinkReferenceId;
	private String rozarPayPaymentStatus;
	private String rozarPayPaymentId;
}
