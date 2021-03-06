package org.hotelsystem.control;

import org.hotelsystem.model.AvailableHotel;
import org.hotelsystem.model.Hotel;
import org.hotelsystem.model.User;
import org.hotelsystem.model.Order;
import org.hotelsystem.model.DBUtil;
import org.hotelsystem.model.Review;
import org.hotelsystem.view.SearchUI;

import java.util.*;
import java.awt.Image;
import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class SearchControl {
    private ArrayList<Hotel> hotels = new ArrayList<Hotel>(0);
    private SearchUI searchUI;
    private MainControl mainControl;
    private ArrayList<AvailableHotel> availableHotel = new ArrayList<AvailableHotel>();
    private ArrayList<Review> review = new ArrayList<Review>();
    private Random ran = new Random();
    private DBUtil dbutils;
    private int resultsPage = 0, resultsTotalPage = 0;
    private int reviewPage = 0, reviewTotalPage = 0;
    private int reviewHotelID = 0;
    private int searchPeople, searchRoom;
    private int checkin, checkout;
    private int imageIdx;
    private int searchNight;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");  
    //
	public SearchControl(MainControl mainControl, DBUtil dbutils){
        this.mainControl = mainControl;
        this.dbutils = dbutils;
    }

    public void setUI(SearchUI searchUI){
        this.searchUI = searchUI;
    }

    public void searchAvailableHotel(String locality, int checkin, int checkout, int room, int people){
        this.hotels = dbutils.getHotels(locality, checkin, checkout);
        ArrayList<AvailableHotel> tmp = new ArrayList<AvailableHotel>(0);
        System.out.printf("get %d hotels\n", this.hotels.size());
       
        this.searchRoom = room;
        this.searchPeople = people;
        for( int i=0; i<this.hotels.size(); ++i){
            AvailableHotel temp = hotels.get(i).getAvailableHotel(people, room);
            if( temp.getRoomCombination().size() > 0 ){
                ArrayList<Integer> roomCombPrice = temp.getCombinationPrice();
                temp.setCombinationPrice(roomCombPrice);
                tmp.add(temp);
            }
        }
        Collections.sort(tmp);
        this.checkin = checkin;
        this.checkout = checkout;
        this.availableHotel = tmp;
        this.resultsTotalPage = (tmp.size() - 1) / 10; 
        try{
            long diffInMillies = this.dateFormatter.parse(Integer.toString(checkout)).getTime() - this.dateFormatter.parse(Integer.toString(checkin)).getTime();
            this.searchNight = (int)TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS); 
        }
        catch(Exception e){
            
        }
        this.searchUI.refreshFilter();
        this.setSearchResults(new ArrayList<AvailableHotel>(this.availableHotel.subList(0, Math.min(10, this.availableHotel.size()))), 0, (tmp.size() -1) / 10);
    }

    public void applySearchFilter(int lower, int upper, int star, int priceOrder){
        ArrayList<AvailableHotel> sortedAvailableHotel = new ArrayList<AvailableHotel>();
        Collections.sort(availableHotel);
        
        for(int i=0;i<availableHotel.size();i++){
            if(availableHotel.get(i).getMinPrice() >= lower && availableHotel.get(i).getMinPrice() <= upper && (availableHotel.get(i).getHotelStar() == star || star == 0)){
                sortedAvailableHotel.add(availableHotel.get(i));
            }
        }
            
        if( priceOrder == 1)
            Collections.reverse(sortedAvailableHotel);
        this.resultsPage = 0;
        this.resultsTotalPage = (sortedAvailableHotel.size() - 1) / 10;
        this.setSearchResults(sortedAvailableHotel, this.resultsPage, this.resultsTotalPage);
    }


    public void setSearchResults(ArrayList<AvailableHotel> availableHotel, int page, int totalPage){
        ArrayList<ImageIcon> imageIcons = new ArrayList<ImageIcon>(0);
        try{
            for(int i=0; i<availableHotel.size(); ++i){
                this.imageIdx = this.ran.nextInt(87);
                ImageIcon icon = new ImageIcon(String.format("resources/%d.jpg", this.imageIdx));
                imageIcons.add(icon);
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        this.searchUI.setAvailableHotels(availableHotel, page, totalPage, imageIcons);
    }
    
    public boolean insertOrder(Order order, int singleNum, int doubleNum, int quadNum){
        if( this.dbutils.insertOrder(order, singleNum, doubleNum, quadNum) ){
            order.setPrice(order.getPrice() * this.searchNight);
            return true;
        }
        return false;
    }

    public void setSearchResultsPage(int direction){
        ArrayList<AvailableHotel> newAvailableHotel;
        if( this.resultsPage + direction > this.resultsTotalPage || this.resultsPage + direction < 0 )
            System.out.println("Already the final page or the first page.");
        else{
            this.resultsPage += direction;
            newAvailableHotel = new ArrayList<AvailableHotel>(this.availableHotel.subList(this.resultsPage * 10, Math.min(this.resultsPage * 10 + 10, this.availableHotel.size())));
            this.setSearchResults(newAvailableHotel , this.resultsPage, this.resultsTotalPage);
        }
    }

    public void setReviewPage(int direction){
        ArrayList<Review> newReview;
        if( this.reviewPage + direction > this.reviewTotalPage || this.reviewPage + direction < 0 )
            System.out.println("Already the final page or the first page.");
        else{
            this.reviewPage += direction;
            newReview = new ArrayList<Review>(this.review.subList(this.reviewPage * 10, Math.min(this.reviewPage * 10 + 10, this.review.size())));
            this.showReview(this.reviewHotelID, "Hotel Reviews", newReview, this.reviewPage, this.reviewTotalPage);
        }
    }

    public void showReview(int hotelID, String dialogName, ArrayList<Review> review, int page, int totalPage){
        this.reviewHotelID = hotelID;
        this.searchUI.showReview(hotelID, dialogName, review, page, totalPage);
    }

    public ArrayList<String> getLocality(){
        return dbutils.getLocality();
    }

    public int getSearchPeople(){
        return this.searchPeople;
    }

    public int getSearchRoom(){
        return this.searchRoom;
    }


    public int getCheckin(){
        return this.checkin;
    }

    public int getCheckout(){
        return this.checkout;
    }

    public User getUser(){
        return this.mainControl.getCurrentUser();
    }

    public int getSearchNight(){ return this.searchNight; }
    public Image getBackGroundImage(){
        return this.mainControl.getBackGroundImage();
    }

    public void getHotelReviews(int hotelID){
        ArrayList<Review> ans = dbutils.getHotelReviews(hotelID);
        this.reviewPage = 0;
        this.reviewTotalPage = (ans.size() - 1) / 10;
        this.review = ans;
        this.showReview(hotelID, "Reviews", ans, this.reviewPage, this.reviewTotalPage);
    }
}