package org.hotelsystem.control;

import org.hotelsystem.model.DBUtil;
import org.hotelsystem.model.Order;
import org.hotelsystem.view.ModifyUI;
import java.util.ArrayList;

public class ModifyControl{
    private ModifyUI modifyUI;
    private MainControl mainControl;
    private DBUtil dbutils = new DBUtil("140.112.21.82", "ooad", "ooad", "HOTEL");
    ModifyControl(MainControl mainControl){
        this.mainControl = mainControl;
    }

    public void setUI(ModifyUI modifyUI){
        this.modifyUI = modifyUI;
    }
    public void setOrder(Order order){
        this.modifyUI.setOrder(order);
    }
    public Order getOrder(int orderID){
        System.out.println("get Order");
        ArrayList<Order> orders = dbutils.getOrders(orderID, -1, -1);
        if (orders.size()!=1) return null;
        System.out.println(orders.get(0).toString());
        return orders.get(0);
    }
    public boolean modifyOrder(Order order, int newSN, int newDN, int newQN, int newCheckin, int newCheckout){
        return dbutils.modifyOrder(order, newSN, newDN, newQN, newCheckin, newCheckout);
    }
}