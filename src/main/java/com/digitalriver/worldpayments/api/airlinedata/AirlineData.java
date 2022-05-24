package com.digitalriver.worldpayments.api.airlinedata;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AirlineData {

    public String ticketNumber;
    public String travelAgencyCode;
    public String travelAgencyName;
    public String airlineProcessIdentifier;
    public String airlineInvoiceNumber;
    public String passengerName;
    public String documentType;
    public String iataNumericCode;
    public String ticketingCarrierName;
    public String ticketIssueAddress;
    public Date ticketIssueDate;
    public Integer numberInParty;
    public Boolean electronicTicketIndicator;
    public Boolean conjunctionTicketIndicator;
    public String conjunctionTicketNumber;
    public Boolean restrictedTicket;
    public BigDecimal totalFare;
    public BigDecimal totalFee;	
    public String exchangeTicketNumber;
    public BigDecimal exchangeTicketAmount;
    public String controlId;
    public Integer numberOfAirSegments;
    public String pnr;
    public List<AirlineLeg> airlineLegs;
    public String computerizedReservationSystem;
    
    
    public AirlineData() {
    	//Default-constructor
	}
    
    
	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getTravelAgencyCode() {
		return travelAgencyCode;
	}

	public void setTravelAgencyCode(String travelAgencyCode) {
		this.travelAgencyCode = travelAgencyCode;
	}

	public String getTravelAgencyName() {
		return travelAgencyName;
	}

	public void setTravelAgencyName(String travelAgencyName) {
		this.travelAgencyName = travelAgencyName;
	}

	public String getAirlineProcessIdentifier() {
		return airlineProcessIdentifier;
	}

	public void setAirlineProcessIdentifier(String airlineProcessIdentifier) {
		this.airlineProcessIdentifier = airlineProcessIdentifier;
	}

	public String getAirlineInvoiceNumber() {
		return airlineInvoiceNumber;
	}

	public void setAirlineInvoiceNumber(String airlineInvoiceNumber) {
		this.airlineInvoiceNumber = airlineInvoiceNumber;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getIataNumericCode() {
		return iataNumericCode;
	}

	public void setIataNumericCode(String iataNumericCode) {
		this.iataNumericCode = iataNumericCode;
	}

	public String getTicketingCarrierName() {
		return ticketingCarrierName;
	}

	public void setTicketingCarrierName(String ticketingCarrierName) {
		this.ticketingCarrierName = ticketingCarrierName;
	}

	public String getTicketIssueAddress() {
		return ticketIssueAddress;
	}

	public void setTicketIssueAddress(String ticketIssueAddress) {
		this.ticketIssueAddress = ticketIssueAddress;
	}

	public Date getTicketIssueDate() {
		return ticketIssueDate;
	}

	public void setTicketIssueDate(Date ticketIssueDate) {
		this.ticketIssueDate = ticketIssueDate;
	}

	public Integer getNumberInParty() {
		return numberInParty;
	}

	public void setNumberInParty(Integer numberInParty) {
		this.numberInParty = numberInParty;
	}

	public Boolean getElectronicTicketIndicator() {
		return electronicTicketIndicator;
	}

	public void setElectronicTicketIndicator(Boolean electronicTicketIndicator) {
		this.electronicTicketIndicator = electronicTicketIndicator;
	}

	public Boolean getConjunctionTicketIndicator() {
		return conjunctionTicketIndicator;
	}

	public void setConjunctionTicketIndicator(Boolean conjunctionTicketIndicator) {
		this.conjunctionTicketIndicator = conjunctionTicketIndicator;
	}

	public String getConjunctionTicketNumber() {
		return conjunctionTicketNumber;
	}

	public void setConjunctionTicketNumber(String conjunctionTicketNumber) {
		this.conjunctionTicketNumber = conjunctionTicketNumber;
	}

	public Boolean isRestrictedTicket() {
		return restrictedTicket;
	}

	public void setRestrictedTicket(Boolean restrictedTicket) {
		this.restrictedTicket = restrictedTicket;
	}

	public BigDecimal getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(BigDecimal totalFare) {
		this.totalFare = totalFare;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getExchangeTicketNumber() {
		return exchangeTicketNumber;
	}

	public void setExchangeTicketNumber(String exchangeTicketNumber) {
		this.exchangeTicketNumber = exchangeTicketNumber;
	}

	public BigDecimal getExchangeTicketAmount() {
		return exchangeTicketAmount;
	}

	public void setExchangeTicketAmount(BigDecimal exchangeTicketAmount) {
		this.exchangeTicketAmount = exchangeTicketAmount;
	}

	public String getControlId() {
		return controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}

	public Integer getNumberOfAirSegments() {
		return numberOfAirSegments;
	}

	public void setNumberOfAirSegments(Integer numberOfAirSegments) {
		this.numberOfAirSegments = numberOfAirSegments;
	}

	public String getComputerizedReservationSystem() {
		return computerizedReservationSystem;
	}

	public void setComputerizedReservationSystem(String computerizedReservationSystem) {
		this.computerizedReservationSystem = computerizedReservationSystem;
	}
	
	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public List<AirlineLeg> getAirlineLegs() {
		return airlineLegs;
	}

	public void setAirlineLegs(List<AirlineLeg> airlineLegs) {
		this.airlineLegs = airlineLegs;
	}


	@Override
	public String toString() {
		return "AirlineData [ticketNumber=" + ticketNumber + ", travelAgencyCode=" + travelAgencyCode
				+ ", travelAgencyName=" + travelAgencyName + ", airlineProcessIdentifier=" + airlineProcessIdentifier
				+ ", airlineInvoiceNumber=" + airlineInvoiceNumber + ", passengerName=" + passengerName
				+ ", documentType=" + documentType + ", iataNumericCode=" + iataNumericCode + ", ticketingCarrierName="
				+ ticketingCarrierName + ", ticketIssueAddress=" + ticketIssueAddress + ", ticketIssueDate="
				+ ticketIssueDate + ", numberInParty=" + numberInParty + ", electronicTicketIndicator="
				+ electronicTicketIndicator + ", conjunctionTicketIndicator=" + conjunctionTicketIndicator
				+ ", conjunctionTicketNumber=" + conjunctionTicketNumber + ", restrictedTicket=" + restrictedTicket
				+ ", totalFare=" + totalFare + ", totalFee=" + totalFee + ", exchangeTicketNumber="
				+ exchangeTicketNumber + ", exchangeTicketAmount=" + exchangeTicketAmount + ", controlId=" + controlId
				+ ", numberOfAirSegments=" + numberOfAirSegments + ", pnr=" + pnr + ", airlineLegs=" + airlineLegs
				+ ", computerizedReservationSystem=" + computerizedReservationSystem + "]";
	}


}
