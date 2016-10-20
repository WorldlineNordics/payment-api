/*
*
* Copyright 2000-2015 Digital River, All Rights Reserved
* Created on March - 2015 by ppawar
* 
* Created library for "Open Invoice" payment method type.
*/
function getAddress(aBillingSocialSecurityNumber, aGetAddressURL, aIReqParam, aKlarnaPaymentMethodId){
	$("#loadingImageDiv").show();
	var resultAddress;
    $.ajax({
    				type: "GET",
    		        url: aGetAddressURL,
    		        async: false,   // asynchronous request? (synchronous requests are discouraged...)
    		        cache: false,   // with this, you can force the browser to not make cache of the retrieved data
    		        dataType: "json",  // jQuery will infer this, but you can set explicitly
    		        data: {
    		        	ssnNumber : aBillingSocialSecurityNumber,
    		        	ireq : aIReqParam,
    		        	pmId : aKlarnaPaymentMethodId
				    },
    		        error: function(httpRequest, textStatus, errorThrown) {
    		        	resultAddress = null;
    	            },
    	            success: function(data) {
    	            	resultAddress = data;
    	        	},
    	            complete: function(){
    	            	$("#loadingImageDiv").hide();
    	            }
    });
    return resultAddress;
}

function getPaymentPlan(aGetPaymentPlanURL, aIReqParam, aKlarnaPaymentMethodId){
	$("#loadingImageDiv").show();
	var resultPaymentPlan;
    $.ajax({
    				type: "GET",
    		        url: aGetPaymentPlanURL,
    		        async: false,   // asynchronous request? (synchronous requests are discouraged...)
    		        cache: false,   // with this, you can force the browser to not make cache of the retrieved data
    		        dataType: "json",  // jQuery will infer this, but you can set explicitly
    		        data: {
    		        	ireq : aIReqParam,
    		        	pmId : aKlarnaPaymentMethodId
				    },
    		        error: function(httpRequest, textStatus, errorThrown) {
    		        	resultPaymentPlan = null;
    	            },
    	            success: function(data) {
    	            	resultPaymentPlan = data;
    	        	},
    	            complete: function(){
    	            	$("#loadingImageDiv").hide();
    	            }
    });
    return resultPaymentPlan;
}

function isEmpty(aVar){
	if(aVar != ''){
		return false;
	}else{
		return true;
	}
}

function displayShippingAddress(aResultShippingAddress, aKlarnaShippingAddressHeading, aKlarnaSSNError,
		aKlarnaShippingAddressError, aBillingSocialSecurityNumber){
	$("#shippingAddressDiv").empty();
	$("#shippingAddressDivHidden").empty();
	
	// Assuming that user has already fetched the address by providing valid ssn number.
    // But after that if user try to fetch the address again so to take care of all scenarios I have removed
	// previous ssn msg, previous address and disabled confirm button.
    $('#ssnMsg').empty();
    disableConfirmButton();
	
	if(aResultShippingAddress != null){
		var statusCode = aResultShippingAddress.responseStatus.statusCode;
		if(statusCode == '0'){
			var aShippingInfo = aResultShippingAddress.shippingInfo;
			for(key in aShippingInfo) {
				var firstName = (aShippingInfo[key].hasOwnProperty('firstName')) ? aShippingInfo[key].firstName : '',
					lastName = (aShippingInfo[key].hasOwnProperty('lastName')) ? aShippingInfo[key].lastName : '',
				    city = aShippingInfo[key].city,
				    zipCode = aShippingInfo[key].zipCode,
				    streetName = aShippingInfo[key].streetName;
						
				$("#shippingAddressDiv").append('<label id="shippingAddrsesLabel"><input id="shippingAddressRadio" '+
				    'class='+key+' type="radio" name="shippingAddress"/><b>'+firstName+' ' +lastName+'</b>'+
				    '<div id="shippingAddressSpan">'+city+', '+streetName+', '+zipCode+'</div></label>');
			}
			$('#shippingAddressDiv').removeAttr('disabled');
		}else{
			// Provide ssn text box if merchant sends Invalid SSN number of the buyer in the request and sustain that invalid SSN in the text box.
			$("#ssnDiv").show();
			$("#ssnBox").attr("value", aBillingSocialSecurityNumber);
			// Displaying Error message for invalid SSN
			$('#ssnMsg').append('<label id="ssnMsgLabel">'+aKlarnaSSNError+'</label>');
			$('#shippingAddressDiv').attr('disabled', 'disabled');
		}
	}else{
		// Displaying Error message for connectivity issues
		$('#ssnMsg').append('<label id="ssnMsgLabel">'+aKlarnaShippingAddressError+'</label>');
		$('#shippingAddressDiv').attr('disabled', 'disabled');
	}
}

function passSelectedShippingAddressParameters(aIndex,aResultShippingAddress){
	$("#shippingAddressDivHidden").empty();
	var aShippingInfo = aResultShippingAddress.shippingInfo;
	    firstName = (aShippingInfo[aIndex].hasOwnProperty('firstName')) ? aShippingInfo[aIndex].firstName : '',
		lastName = (aShippingInfo[aIndex].hasOwnProperty('lastName')) ? aShippingInfo[aIndex].lastName : '',
	 	city = aShippingInfo[aIndex].city,
	 	zipCode = aShippingInfo[aIndex].zipCode,
	 	streetName = aShippingInfo[aIndex].streetName,
	 	countryCode = aShippingInfo[aIndex].countryCode;

    $("#shippingAddressDivHidden").append('<input type="hidden" name="firstName" value="'+firstName+'" />');
    $("#shippingAddressDivHidden").append('<input type="hidden" name="lastName" value="'+lastName+'" />');
    $("#shippingAddressDivHidden").append('<input type="hidden" name="city" value="'+city+'" />');
    $("#shippingAddressDivHidden").append('<input type="hidden" name="zipCode" value="'+zipCode+'" />');
    $("#shippingAddressDivHidden").append('<input type="hidden" name="streetName" value="'+streetName+'" />');
    $("#shippingAddressDivHidden").append('<input type="hidden" name="countryCode" value="'+countryCode+'" />');
}

function displayPaymentPlan(aResultPaymentPlans, aCurrencyCode, aMonth, aInvoice, aPartPaymentFixed, aPartPaymentFlexible,
		aMonths, aInvoiceRadioDescription, aKlarnaPaymentPlanHeading,
		aKlarnaPaymentPlanTotalCost, aKlarnaPaymentPlanAnnualPercentageRate, aKlarnaTC, aKlarnaForInvoice, aKlarnaForPartPaymentFixed, aKlarnaForPartPaymentFlexible, aImgPath){
	$("#paymentPlanDiv").empty();
	
	// Invoice plans
	$("#paymentPlanDiv").append('<div id="paymentPlanInvoiceWrapper"></div>');
	$("#paymentPlanInvoiceWrapper").append('<div id="paymentPlanInvoice"><div id="paymentPlanInvoiceLabel">'+
			'<label><img class="ccLogo" alt="klarna" src="'+aImgPath+'img/klarna.png" height="25" width="60" align="right"></label></div></div>');
	$("#paymentPlanInvoiceWrapper").append('<label id="paymentPlanLabel">'+
    		'<input id="paymentPlanRadio" class="invoice" type="radio" name="paymentPlanCode" value="-1" checked="checked"/>'+
    		'<b><font size="4">'+aInvoice+' : </font></b><span>'+aInvoiceRadioDescription+'</span></label><br/>');
    		$("#paymentPlanInvoiceWrapper").append('<div class="verticleSpace"><a href="#" id="klarnaTClabel">'+aKlarnaTC +'</a>'+'<label> '+aKlarnaForInvoice+'</label></div>');
	
		if(aResultPaymentPlans != null){
		var statusCode = aResultPaymentPlans.responseStatus.statusCode;
		if(statusCode == '0' && aResultPaymentPlans.paymentPlan.length > 0){
			// PartPayment-Flexible plans
			$("#paymentPlanDiv").append('<div id="paymentPlanPartPaymentWrapperFlexible"></div>');
			$("#paymentPlanPartPaymentWrapperFlexible").append('<div id="paymentPlanInvoiceLabel"><h2><b><font size="4">'+aPartPaymentFlexible+'</font></b><label><img class="ccLogo" alt="klarna" src="'+aImgPath+'img/klarna.png" height="25" width="60" align="right"></label></h2></div>');
			var aPaymentPlan = aResultPaymentPlans.paymentPlan;
			var count = 0;
			for(key in aPaymentPlan) {
			    var paymentPlanCode = aPaymentPlan[key].paymentPlanCode;
			     	monthlyCost = aPaymentPlan[key].monthlyCost,
			     	annualPercentageRate = aPaymentPlan[key].annualPercentageRate,
			     	numberOfPayments = aPaymentPlan[key].numberOfPayments,
			    
			     	interest = aPaymentPlan[key].interest,
			     	arrangementFee = aPaymentPlan[key].arrangementFee,
			     	surcharge = aPaymentPlan[key].surcharge,
			     	minAmount = aPaymentPlan[key].minAmount,
					aType = aPaymentPlan[key].type,
			     	paymentPlanDescription = ''+monthlyCost+' '+aCurrencyCode+'/'+aMonth+' '+numberOfPayments+' '+aMonths+'';	
					if(aType == 1){					
						 $("#paymentPlanPartPaymentWrapperFlexible").append('<label id="paymentPlanLabel">'+
			    		'<input id="paymentPlanRadio" class='+key+' type="radio" name="paymentPlanCode" value='+paymentPlanCode+'>'+
			    		'<b>'+paymentPlanDescription+'</b>'+
			    		'<div id="paymentPlanData" align="left">'+
			    		''+aKlarnaPaymentPlanAnnualPercentageRate+' '+annualPercentageRate+'%'+
			    		'</div></label><br/>');
			    		count++;
					}						   
			}
			if(count > 0){
				$("#paymentPlanPartPaymentWrapperFlexible").append('<div class="verticleSpace"> <a href="#" id="klarnaTClabelPPFlexible">'+aKlarnaTC +'</a>'+'<label> '+aKlarnaForPartPaymentFlexible+'</label></div>');
			}else{
				$("#paymentPlanPartPaymentWrapperFlexible").empty();
			}
			
			// PartPayment-Fixed plans
			$("#paymentPlanDiv").append('<div id="paymentPlanPartPaymentWrapper"></div>');
			$("#paymentPlanPartPaymentWrapper").append('<div id="paymentPlanInvoiceLabel"><h2><b><font size="4">'+aPartPaymentFixed+'</font></b><label><img class="ccLogo" alt="klarna" src="'+aImgPath+'img/klarna.png" height="25" width="60" align="right"></label></h2></div>');
			var aPaymentPlan = aResultPaymentPlans.paymentPlan;
			var count = 0;
			for(key in aPaymentPlan) {
			    var paymentPlanCode = aPaymentPlan[key].paymentPlanCode;
			     	totalCost = aPaymentPlan[key].totalCost,
			     	monthlyCost = aPaymentPlan[key].monthlyCost,
			     	annualPercentageRate = aPaymentPlan[key].annualPercentageRate,
			     	numberOfPayments = aPaymentPlan[key].numberOfPayments,
			    
			     	interest = aPaymentPlan[key].interest,
			     	arrangementFee = aPaymentPlan[key].arrangementFee,
			     	surcharge = aPaymentPlan[key].surcharge,
			     	minAmount = aPaymentPlan[key].minAmount,
					aType = aPaymentPlan[key].type,
			     	paymentPlanDescription = ''+monthlyCost+' '+aCurrencyCode+'/'+aMonth+' '+numberOfPayments+' '+aMonths+'';
					if(aType == 0){						
						 $("#paymentPlanPartPaymentWrapper").append('<label id="paymentPlanLabel">'+
			    		'<input id="paymentPlanRadio" class='+key+' type="radio" name="paymentPlanCode" value='+paymentPlanCode+'>'+
			    		'<b>'+paymentPlanDescription+'</b>'+
			    		'<div id="paymentPlanData" align="left">'+
			    		''+aKlarnaPaymentPlanTotalCost+' '+totalCost+' '+aCurrencyCode+'<br/>'+
			    		''+aKlarnaPaymentPlanAnnualPercentageRate+' '+annualPercentageRate+'%'+
			    		'</div></label><br/>');	
			    		count++;		    		
					}						   
			}
			if(count > 0){			
				$("#paymentPlanPartPaymentWrapper").append('<div class="verticleSpace"><a href="#" id="klarnaTClabelPP">'+aKlarnaTC +'</a>'+'<label> '+aKlarnaForPartPaymentFixed+'</label></div>');
		   }else{
		   		$("#paymentPlanPartPaymentWrapper").empty();
		   }	
		}
	}
}


function passSelectedPaymentPlan(aIndex, aResultPaymentPlans){
	$("#paymentPlanDivHidden").empty();
	if(aIndex == 'invoice'){
		$("#paymentPlanDivHidden").append('<input type="hidden" name="paymentPlanCode" value="-1" />');
	}else{
		var aPaymentPlan = aResultPaymentPlans.paymentPlan;
		 	paymentPlanCode = aPaymentPlan[aIndex].paymentPlanCode,
		 	totalCost = aPaymentPlan[aIndex].totalCost,
		 	monthlyCost = aPaymentPlan[aIndex].monthlyCost,
		 	annualPercentageRate = aPaymentPlan[aIndex].annualPercentageRate,
		 	numberOfPayments = aPaymentPlan[aIndex].numberOfPayments,
	    
		 	interest = aPaymentPlan[aIndex].interest,
		 	arrangementFee = aPaymentPlan[aIndex].arrangementFee,
		 	surcharge = aPaymentPlan[aIndex].surcharge,
		 	minAmount = aPaymentPlan[aIndex].minAmount;
	    
	    $("#paymentPlanDivHidden").append('<input type="hidden" name="paymentPlanCode" value="'+paymentPlanCode+'" />');
	    $("#paymentPlanDivHidden").append('<input type="hidden" name="totalCost" value="'+totalCost+'" />');
	    $("#paymentPlanDivHidden").append('<input type="hidden" name="monthlyCost" value="'+monthlyCost+'" />');
	    $("#paymentPlanDivHidden").append('<input type="hidden" name="annualPercentageRate" value="'+annualPercentageRate+'" />');
	    $("#paymentPlanDivHidden").append('<input type="hidden" name="numberOfPayments" value="'+numberOfPayments+'" />');
	    $("#paymentPlanDivHidden").append('<input type="hidden" name="interest" value="'+interest+'" />');
	    $("#paymentPlanDivHidden").append('<input type="hidden" name="arrangementFee" value="'+arrangementFee+'" />');
	    $("#paymentPlanDivHidden").append('<input type="hidden" name="surcharge" value="'+surcharge+'" />');
	    $("#paymentPlanDivHidden").append('<input type="hidden" name="minAmount" value="'+minAmount+'" />');
	}
	
}

function enableConfirmButton(){
	// Enabling "Confirm" button so that user can submit the request.
	$('#submitOI').removeAttr('disabled');
	$('#submitOI').css("cursor","pointer");
}

function disableConfirmButton(){
	// Disabling "Confirm" button so that user cannot submit the request.
	$('#submitOI').attr('disabled', 'disabled');
    $('#submitOI').css("cursor","default");
}

function isNumeric(aVar){
	var regex = /^[-+]?[0-9]+$/;
	if(aVar.match(regex)){
		return true;
	}else{
		return false;
	}
}

function isSSNValidationPassed(maxLength){
	var ssn = $("#ssnText").val(); 
	if(!isEmpty(ssn) && ssn.length == maxLength && isNumeric(ssn)){
		return true;
	}else{
		return false;
	}
}

function isSSNValidationPassedForFI(maxLength){
	var ssn = $("#ssnText").val(); 
	if(!isEmpty(ssn) && ssn.length == maxLength){
		return true;
	}else{
		return false;
	}
}

function isHouseNumValidationPassed(maxLength){
	var houseNum = $("#shippingHouseNumberText").val(); 
	if(!isEmpty(houseNum) && !(houseNum.length > maxLength)){
		return true;
	}else{
		return false;
	}
}

function isDOBValidationPassed(){
	var dob = $("#shippingBirthDateText").val();
	if(!isEmpty(dob) && dob.length == 8 && isNumeric(dob)){
		return true;
	}else{
		return false;
	}
}

function confirmBtnBehaviourForAT(){
	if(isDOBValidationPassed()){
		if(!isEmpty($("#shippingFirstNameText").val()) && !isEmpty($("#shippingLastNameText").val()) &&
		   !isEmpty($("#shippingGenderOption").val()) && !isEmpty($("#shippingBirthDateText").val()) &&
		   !isEmpty($("#shippingEmailIdText").val()) && !isEmpty($("#shippingStreetNameText").val()) &&
		   !isEmpty($("#shippingCityText").val()) && !isEmpty($("#shippingZipCodeText").val()) &&
		   !isEmpty($("#shippingCountryText").val()) ){
				if(!isEmpty($("#shippingPhoneNumberText").val()) || !isEmpty($("#shippingMobileNumberText").val())){
				  	enableConfirmButton(); 
				 }else{
				    disableConfirmButton();
				 }
		}else{
			disableConfirmButton();
		}
	}else{
		disableConfirmButton();
	}
}

function confirmBtnBehaviourForNO(){
	var ssnMaxLength = 11;
	if(!isEmpty($("#shippingFirstNameText").val()) && !isEmpty($("#shippingLastNameText").val()) &&
	   isSSNValidationPassed(ssnMaxLength) && !isEmpty($("#shippingEmailIdText").val()) 
	   && !isEmpty($("#shippingStreetNameText").val()) && !isEmpty($("#shippingCityText").val()) 
	   && !isEmpty($("#shippingZipCodeText").val()) && !isEmpty($("#shippingCountryText").val()) ){
	  		  if(!isEmpty($("#shippingPhoneNumberText").val()) || !isEmpty($("#shippingMobileNumberText").val())){
	  		   	   enableConfirmButton(); 
	  		  }else{
	  		   	   	disableConfirmButton();
	  		  }
	 }else{
		 disableConfirmButton();
	 }
}

function confirmBtnBehaviourForDE(){
	var houseNumberMaxLength = 10;
	if(!isEmpty($("#shippingFirstNameText").val()) && !isEmpty($("#shippingLastNameText").val()) &&
	   isDOBValidationPassed() && !isEmpty($("#shippingEmailIdText").val()) 
	   && !isEmpty($("#shippingStreetNameText").val()) && isHouseNumValidationPassed(houseNumberMaxLength)
	   && !isEmpty($("#shippingCityText").val()) && !isEmpty($("#shippingZipCodeText").val()) 
	   && !isEmpty($("#shippingCountryText").val()) ){
	  		  if(!isEmpty($("#shippingPhoneNumberText").val()) || !isEmpty($("#shippingMobileNumberText").val())){
	  		   	   enableConfirmButton(); 
	  		  }else{
	  		   	   	disableConfirmButton();
	  		  }
	 }else{
		 disableConfirmButton();
	 }
}

function confirmBtnBehaviourForDK(){
	var ssnMaxLength = "10";
	if(!isEmpty($("#shippingFirstNameText").val()) && !isEmpty($("#shippingLastNameText").val()) &&
	   isSSNValidationPassed(ssnMaxLength) &&
	   !isEmpty($("#shippingEmailIdText").val()) && !isEmpty($("#shippingStreetNameText").val()) &&
	   !isEmpty($("#shippingCityText").val()) && !isEmpty($("#shippingZipCodeText").val()) &&
	   !isEmpty($("#shippingCountryText").val()) ){
			if(!isEmpty($("#shippingPhoneNumberText").val()) || !isEmpty($("#shippingMobileNumberText").val())){
			  	enableConfirmButton(); 
			 }else{
			    disableConfirmButton();
			 }
	}else{
		disableConfirmButton();
	}
}

function confirmBtnBehaviourForFI(){
	var ssnMaxLength = "11";
	if(!isEmpty($("#shippingFirstNameText").val()) && !isEmpty($("#shippingLastNameText").val()) &&
	    isSSNValidationPassedForFI(ssnMaxLength) &&
	   !isEmpty($("#shippingEmailIdText").val()) && !isEmpty($("#shippingStreetNameText").val()) &&
	   !isEmpty($("#shippingCityText").val()) && !isEmpty($("#shippingZipCodeText").val()) &&
	   !isEmpty($("#shippingCountryText").val()) ){
			if(!isEmpty($("#shippingPhoneNumberText").val()) || !isEmpty($("#shippingMobileNumberText").val())){
			  	enableConfirmButton(); 
			 }else{
			    disableConfirmButton();
			 }
	}else{
		disableConfirmButton();
	}
}

function isHouseExtensionValidationPassed(maxLength){
	var houseExt = $("#shippingHouseExtensionText").val(); 
	if(houseExt.length <= maxLength){
		return true;
	}else{
		return false;
	}
}

function confirmBtnBehaviourForNL(){
	if(isDOBValidationPassed()){
		if(!isEmpty($("#shippingFirstNameText").val()) && !isEmpty($("#shippingLastNameText").val()) &&
		   !isEmpty($("#shippingGenderOption").val()) && !isEmpty($("#shippingBirthDateText").val()) &&
		   !isEmpty($("#shippingEmailIdText").val()) && !isEmpty($("#shippingStreetNameText").val()) &&
		   !isEmpty($("#shippingCityText").val()) && !isEmpty($("#shippingZipCodeText").val()) &&
		   !isEmpty($("#shippingCountryText").val()) && !isEmpty($("#shippingHouseNumberText").val())){
				if(!isEmpty($("#shippingPhoneNumberText").val()) || !isEmpty($("#shippingMobileNumberText").val())){
				  	enableConfirmButton(); 
				 }else{
				    disableConfirmButton();
				 }
		}else{
			disableConfirmButton();
		}
	}else{
		disableConfirmButton();
	}
}