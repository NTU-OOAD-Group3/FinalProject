package org.hotelsystem.control;

import org.hotelsystem.model.DBUtil;
import org.hotelsystem.model.Order;
import org.hotelsystem.view.ModifyUI;
import java.util.ArrayList;
import org.hotelsystem.model.User;

public class ModifyControl{
    private ModifyUI modifyUI;
    private MainControl mainControl;
    private DBUtil dbutil;
    public ModifyControl(MainControl mainControl, DBUtil dbutil){
        this.mainControl = mainControl;
        this.dbutil = dbutil;
    }

    public void setUI(ModifyUI modifyUI){
        this.modifyUI = modifyUI;
    }

    public void setOrder(Order order){
        this.modifyUI.setOrder(order);
    }

    public Order getOrder(int orderID){
        System.out.println("get Order");
        ArrayList<Order> orders = dbutil.getOrders(orderID, -1, -1);
        if (orders.size()!=1) return null;
        System.out.println(orders.get(0).toString());
        return orders.get(0);
    }

    public boolean modifyOrder(Order order, int newSN, int newDN, int newQN, int newCheckin, int newCheckout){
        boolean success = dbutil.modifyOrder(order, newSN, newDN, newQN, newCheckin, newCheckout);
        if (success) this.mainControl.getInquireControl().refreshUI();
        return success;
    }

    public void setUser(User user){
        this.modifyUI.setUser(user);
    }
}