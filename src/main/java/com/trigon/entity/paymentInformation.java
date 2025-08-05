package com.trigon.entity;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;

@Embeddable
public class paymentInformation {

	
	
	public paymentInformation(String cardHolderName, String cardNumber, LocalDate expirationDate, String cvv) {
		super();
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}
	private String cardHolderName;
	private String cardNumber;
	private LocalDate expirationDate;
	private String cvv;
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	@Override
	public String toString() {
		return "paymentInformation [cardHolderName=" + cardHolderName + ", cardNumber=" + cardNumber
				+ ", expirationDate=" + expirationDate + ", cvv=" + cvv + "]";
	}
	
	
}
