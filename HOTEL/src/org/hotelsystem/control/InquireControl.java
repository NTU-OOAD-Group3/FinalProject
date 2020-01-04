package org.hotelsystem.control;

import org.hotelsystem.model.DBUtil;
import org.hotelsystem.model.Order;
import org.hotelsystem.model.User;
import org.hotelsystem.model.Review;
import org.hotelsystem.view.InquireUI;
import java.util.ArrayList;


public class InquireControl {

    private InquireUI inquireUI;
    private DBUtil dbutils;
    private User user;
    private MainControl mainControl;

    InquireControl(MainControl mainControl, DBUtil dbutils){
        this.mainControl = mainControl;
        this.dbutils = dbutils;
        //mock data
        this.user = new User(0,0,"NO cool","");
    }

    public void setUI(InquireUI inquireUI){
        this.inquireUI = inquireUI;
    }

    public void switchToModify(Order order){
        this.mainControl.getModifyControl().setOrder(order);
        this.mainControl.switchPane(2);
        System.out.println("Switch to modify");
    }
    public void setOrders(){
        //this.inquireUI.refreshUI(dbutils.getOrders(-1, this.user.getUserID(), -1), this.user);
        System.out.println("Set InquireUI");
        //mock data
        ArrayList<Order> orders = new ArrayList<Order>();
        for (int i=0;i<5;i++){
            ArrayList<Integer> a= new ArrayList<Integer>();
            a.add(12);
            a.add(15);
            orders.add(new Order(i, 0, 0, a, 19980112, 20200118, 666*i));
        }
        this.inquireUI.refreshUI(orders, this.user);
    }

    public void setReview(Order order, String rating, String comment){
        int star = Integer.valueOf(rating.substring(0,1));
        System.out.println(star);
        Review review = new Review(order.getOrderID(), order.getHotelID(), order.getUserID(), star, comment);
        this.dbutils.insertReview(review);
    }
}