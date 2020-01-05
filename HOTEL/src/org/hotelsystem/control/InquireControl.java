package org.hotelsystem.control;

import org.hotelsystem.model.DBUtil;
import org.hotelsystem.model.Order;
import org.hotelsystem.model.User;
import org.hotelsystem.model.Review;
import org.hotelsystem.view.InquireUI;


public class InquireControl {

    private InquireUI inquireUI;
    private DBUtil dbutils;
    private MainControl mainControl;
    private User user = null;

    InquireControl(MainControl mainControl, DBUtil dbutils){
        this.mainControl = mainControl;
        this.dbutils = dbutils;
    }

    public void setUI(InquireUI inquireUI){
        this.inquireUI = inquireUI;
    }

    public void switchToModify(Order order){
        this.mainControl.getModifyControl().setOrder(order);
        this.mainControl.switchPane(2);
        System.out.println("Switch to modify");
    }

    public void refreshUI(User user){
        this.user = user;
        this.inquireUI.refreshUI(dbutils.getOrders(-1, user.getUserID(), -1), user);
    }

    public void refreshUI(){
        this.inquireUI.refreshUI(dbutils.getOrders(-1, this.user.getUserID(), -1), this.user);
    }

    public void setReview(Order order, String rating, String comment){
        int star = Integer.valueOf(rating.substring(0,1));
        System.out.println(star);
        Review review = new Review(order.getOrderID(), order.getHotelID(), order.getUserID(), star, comment);
        this.dbutils.insertReview(review);
    }

    public Review getReview(Order order){
        return this.dbutils.getOrderReview(order.getOrderID());
    }
}