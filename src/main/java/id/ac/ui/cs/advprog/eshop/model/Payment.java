package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import enums.OrderStatus;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.paymentData = paymentData;

        if (method.equals("VOUCHER_CODE")) {
            String voucherCode = paymentData.get("voucherCode");
            if(isPaymentDataByVoucherCodeFormatValid(paymentData) && isVoucherCodeValid(voucherCode)){
                this.setMethod("VOUCHER_CODE");
                this.status = PaymentStatus.SUCCESS.getValue();
                this.order = order;
            }else{
                this.status = PaymentStatus.REJECTED.getValue();
            }

        }else if(method.equals("CASH_ON_DELIVERY")){
            String address = paymentData.get("address");
            String deliveryFee = paymentData.get("deliveryFee");
            if(isPaymentDataByCashOnDeliveryFormatValid(paymentData) && isAdressAndDeliveryFeeValid(address, deliveryFee)){
                this.setMethod("CASH_ON_DELIVERY");
                this.status = PaymentStatus.SUCCESS.getValue();
                this.order = order;
            }else{
                this.status = PaymentStatus.REJECTED.getValue();
            }
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void setStatus(String status) {
        if (status.equals("SUCCESS") || status.equals("REJECTED")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    static public boolean isPaymentDataByCashOnDeliveryFormatValid(Map<String, String> paymentData){
        return paymentData.size() == 2 && paymentData.containsKey("address") && paymentData.containsKey("deliveryFee");
    }

    static public boolean isAdressAndDeliveryFeeValid(String address, String deliveryFee){
        if(isAdressNotNull(address) && isDeliveryFeeNotNull(deliveryFee)){
            return true;
        }else{
            return false;
        }
    }

    static public boolean isAdressNotNull(String address){
        return address != null;
    }

    static public boolean isDeliveryFeeNotNull(String deliveryFee){
        return deliveryFee != null;
    }

    static public boolean isPaymentDataByVoucherCodeFormatValid(Map<String, String> paymentData){
        return paymentData.size() == 1 && paymentData.containsKey("voucherCode");
    }

    static public boolean isVoucherCodeValid(String voucherCode){
       if(isVoucherCodeStartedWithESHOP(voucherCode) && isVoucherCode16Length(voucherCode) && isNumCharInVoucherCode8length(voucherCode)){
           return true;
       }else{
           return false;
       }

    }

    static public boolean isVoucherCodeStartedWithESHOP(String voucherCode){
        if(voucherCode.startsWith("ESHOP")){
            return true;
        }else{
            return false;
        }
    }

    static public boolean isVoucherCode16Length(String voucherCode){
        return voucherCode.length() == 16;
    }

    static public boolean isNumCharInVoucherCode8length(String voucherCode){
        int numCharCount  = 0;
        for(char c : voucherCode.toCharArray()){
            if(Character.isDigit(c)){
                numCharCount++;
            }
        }
        return numCharCount == 8;
    }
}
