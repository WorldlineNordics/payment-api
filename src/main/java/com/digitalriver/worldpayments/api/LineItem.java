package com.digitalriver.worldpayments.api;

import com.digitalriver.worldpayments.api.utils.Parameter;

public class LineItem {

    @Parameter(shortName = "LIC", required = true)
    Double amount;

    @Parameter(shortName = "LIB")
    String description;

    @Parameter(shortName = "LIA", required = true, maxLength = 16)
    String id;

    @Parameter(shortName = "LID", required = true)
    Integer quantity;

    @Parameter(shortName = "LIE")
    Double taxAmount;

    @Parameter(shortName = "LIF")
    Double taxRate;

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public Double getTaxRate() {
        return taxRate;
    }
}
