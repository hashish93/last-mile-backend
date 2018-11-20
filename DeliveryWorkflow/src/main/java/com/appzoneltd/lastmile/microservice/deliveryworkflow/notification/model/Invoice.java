package com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model;

import java.io.Serializable;
import java.util.List;

/**
 * the Invoice Object that the client display it for approving payments
 * <p>
 * Created by Ahmed Adel on 1/1/2017.
 */

public class Invoice implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3498357238924882713L;
    protected String code;
    protected String date;
    protected String time;
    protected List<InvoiceService> services;
    protected String totalWithoutTaxes;
    protected String taxes;
    protected String totalWithTaxes;
    protected String paymentMethod;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalWithoutTaxes() {
        return totalWithoutTaxes;
    }

    public void setTotalWithoutTaxes(String totalWithoutTaxes) {
        this.totalWithoutTaxes = totalWithoutTaxes;
    }

    public String getTotalWithTaxes() {
        return totalWithTaxes;
    }

    public void setTotalWithTaxes(String totalWithTaxes) {
        this.totalWithTaxes = totalWithTaxes;
    }

    public List<InvoiceService> getServices() {
        return services;
    }

    public void setServices(List<InvoiceService> services) {
        this.services = services;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", services=" + services +
                ", totalWithoutTaxes='" + totalWithoutTaxes + '\'' +
                ", taxes='" + taxes + '\'' +
                ", totalWithTaxes='" + totalWithTaxes + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
