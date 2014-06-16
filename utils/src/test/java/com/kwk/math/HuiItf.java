package com.kwk.math;


import java.util.List;

public interface HuiItf {
    List<DiscountInfo> getDiscountInfo(int shopId);

    String calculateDiscount(int shopId, String inputMoney);
}


class DiscountInfo {
    private int discountId;
    private String description;
}
