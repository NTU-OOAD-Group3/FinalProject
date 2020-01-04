package org.hotelsystem.control;

import org.hotelsystem.model.DBUtil;
import org.hotelsystem.model.Order;
import org.hotelsystem.view.ModifyUI;
import java.util.ArrayList;

public class ModifyControl {
    private ModifyUI modifyUI;
    private DBUtil dbutils = new DBUtil("140.112.21.82", "ooad", "ooad", "HOTEL");

    public void setUI(ModifyUI modifyUI){
        this.modifyUI = modifyUI;
    }
    public void setOrder(Order order){
        this.modifyUI.setOrder(order);
    }
    public Order getOrder(int orderID){
        System.out.println("Set InquireUI");
        ArrayList<Order> orders = dbutils.getOrders(orderID, -1, -1);
        if (orders.size()!=1) return null;
        return orders.get(0);
    }
}