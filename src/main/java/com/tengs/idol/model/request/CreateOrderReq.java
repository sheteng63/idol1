package com.tengs.idol.model.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateOrderReq {
    private String date;
    private ArrayList place;
}
