package com.digitalriver.worldpayments.api.airlinedata;

import java.math.BigDecimal;
import java.util.Date;

public class AirlineLeg {
	
    public String segmentNumber;
    public String departureAirport;
    public String departureLocationCode;
    public Date departureDate;
    public String destinationAirport;
    public String arrivalLocationCode;
    public Date arrivalDate;
    public String carrierCode;
    public String fareBasisCode;
    public String classOfTravel;
    public String stopoverCode;
    public String flightNumber;
    public BigDecimal fare;
    public String originalTicketNumber;
    public BigDecimal departTax;
    public Integer timeToDeparture; 
	
	
	public AirlineLeg() {
		//Default-constructor
	}
	
	
	public String getSegmentNumber() {
		return segmentNumber;
	}

	public void setSegmentNumber(String segmentNumber) {
		this.segmentNumber = segmentNumber;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getDepartureLocationCode() {
		return departureLocationCode;
	}

	public void setDepartureLocationCode(String departureLocationCode) {
		this.departureLocationCode = departureLocationCode;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public String getArrivalLocationCode() {
		return arrivalLocationCode;
	}

	public void setArrivalLocationCode(String arrivalLocationCode) {
		this.arrivalLocationCode = arrivalLocationCode;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getFareBasisCode() {
		return fareBasisCode;
	}

	public void setFareBasisCode(String fareBasisCode) {
		this.fareBasisCode = fareBasisCode;
	}

	public String getClassOfTravel() {
		return classOfTravel;
	}

	public void setClassOfTravel(String classOfTravel) {
		this.classOfTravel = classOfTravel;
	}

	public String getStopoverCode() {
		return stopoverCode;
	}

	public void setStopoverCode(String stopoverCode) {
		this.stopoverCode = stopoverCode;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public BigDecimal getFare() {
		return fare;
	}

	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}

	public String getOriginalTicketNumber() {
		return originalTicketNumber;
	}

	public void setOriginalTicketNumber(String originalTicketNumber) {
		this.originalTicketNumber = originalTicketNumber;
	}

	public BigDecimal getDepartTax() {
		return departTax;
	}

	public void setDepartTax(BigDecimal departTax) {
		this.departTax = departTax;
	}

	public Integer getTimeToDeparture() {
		return timeToDeparture;
	}

	public void setTimeToDeparture(Integer timeToDeparture) {
		this.timeToDeparture = timeToDeparture;
	}


	@Override
	public String toString() {
		return "AirlineLeg [segmentNumber=" + segmentNumber + ", departureAirport=" + departureAirport
				+ ", departureLocationCode=" + departureLocationCode + ", departureDate=" + departureDate
				+ ", destinationAirport=" + destinationAirport + ", arrivalLocationCode=" + arrivalLocationCode
				+ ", arrivalDate=" + arrivalDate + ", carrierCode=" + carrierCode + ", fareBasisCode=" + fareBasisCode
				+ ", classOfTravel=" + classOfTravel + ", stopoverCode=" + stopoverCode + ", flightNumber="
				+ flightNumber + ", fare=" + fare + ", originalTicketNumber=" + originalTicketNumber + ", departTax="
				+ departTax + ", timeToDeparture=" + timeToDeparture + "]";
	}

}
