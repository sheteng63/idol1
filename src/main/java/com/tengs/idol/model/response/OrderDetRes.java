package com.tengs.idol.model.response;

import com.tengs.idol.entity.Order;
import com.tengs.idol.entity.User;
import lombok.Data;

@Data
public class OrderDetRes {
    private Order order;
    private User applyUser;
    private User acceptUser;

}
