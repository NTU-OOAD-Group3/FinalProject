package org.hotelsystem.control;

import org.hotelsystem.model.AvailableHotel;
import org.hotelsystem.model.Hotel;
import org.hotelsystem.model.DBUtil;
import org.hotelsystem.model.HotelReview;
import org.hotelsystem.view.SearchUI;

import java.util.ArrayList;

public class SearchControl {
    private ArrayList<Hotel> hotels = new ArrayList<Hotel>(0);
    private SearchUI searchUI;
    private ArrayList<AvailableHotel> availableHotel = new ArrayList<AvailableHotel>();
    private ArrayList<HotelReview> hotelReview = new ArrayList<HotelReview>();
    //
    private DBUtil dbutils = new DBUtil("140.112.21.82", "ooad", "ooad", "HOTEL");
    private int resultsPage = 0, resultsTotalPage = 0;
    private int reviewPage = 0, reviewTotalPage = 0;
    private int reviewHotelID = 0;
    private int searchPeople, searchRoom, searchNight;
    //
	public SearchControl(){
    }

    public void setUI(SearchUI searchUI){
        this.searchUI = searchUI;
    }

    public void searchAvailableHotel(String locality, int checkin, int checkout, int room, int people){
        this.hotels = dbutils.getHotels(locality, checkin, checkout);
        ArrayList<AvailableHotel> tmp = new ArrayList<AvailableHotel>(0);
        System.out.printf("get %d hotels\n", this.hotels.size());
        this.searchNight = checkout - checkin;
        this.searchRoom = room;
        this.searchPeople = people;
        for( int i=0; i<this.hotels.size(); ++i){
            AvailableHotel temp = hotels.get(i).getAvailableHotel(people, room);
            if( temp.getRoomCombination().size() > 0 )
                tmp.add(temp);
        }
        this.availableHotel = tmp;
        this.resultsTotalPage = (tmp.size() - 1) / 10;  
        this.setSearchResults(this.availableHotel, 0, (tmp.size() -1) / 10);
    }

    public void setSearchResults(ArrayList<AvailableHotel> availableHotel, int page, int totalPage){
        this.searchUI.setAvailableHotels(availableHotel, page, totalPage);
    }
    
    public void setSearchResultsPage(int direction){
        ArrayList<AvailableHotel> newAvailableHotel;
        if(direction > 0){
            if( this.resultsPage == this.resultsTotalPage ){
                System.out.println("Already the final page.");
            }
            else{
                this.resultsPage += 1;
                newAvailableHotel = new ArrayList<AvailableHotel>(this.availableHotel.subList(this.resultsPage * 10, Math.min(this.resultsPage * 10 + 10, this.availableHotel.size())));
                this.setSearchResults(newAvailableHotel , this.resultsPage, this.resultsTotalPage);
            }
        }
        else{
            if( this.resultsPage == 0 ){
                System.out.println("Already the first page.");
            }
            else{
                this.resultsPage -= 1;
                newAvailableHotel = new ArrayList<AvailableHotel>(this.availableHotel.subList(this.resultsPage * 10, Math.min(this.resultsPage * 10 + 10, this.availableHotel.size())));
                this.setSearchResults(newAvailableHotel , this.resultsPage, this.resultsTotalPage);
            }
        }
    }

    public void setReviewPage(int direction){
        ArrayList<HotelReview> newHotelReview;
        if(direction > 0){
            if( this.reviewPage == this.reviewTotalPage ){
                System.out.println("Already the final page.");

            }
            else{
                this.reviewPage += 1;
                newHotelReview = new ArrayList<HotelReview>(this.hotelReview.subList(this.reviewPage * 10, Math.min(this.reviewPage * 10 + 10, this.hotelReview.size())));
                this.showReview(this.reviewHotelID, "Hotel Reviews", newHotelReview, this.reviewPage, this.reviewTotalPage);
            }
        }
        else{
            if( this.reviewPage == 0 ){
                System.out.println("Already the first page.");
            }
            else{
                this.reviewPage -= 1;
                newHotelReview = new ArrayList<HotelReview>(this.hotelReview.subList(this.reviewPage * 10, Math.min(this.reviewPage * 10 + 10, this.hotelReview.size())));
                this.showReview(this.reviewHotelID, "Hotel Reviews", newHotelReview, this.reviewPage, this.reviewTotalPage);
            }
        }
    }

    public void showReview(int hotelID, String dialogName, ArrayList<HotelReview> hotelReview, int page, int totalPage){
        this.reviewHotelID = hotelID;
        this.searchUI.showReview(hotelID, dialogName, hotelReview, page, totalPage);
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

    public int getSearchNight(){
        return this.searchNight;
    }

    public void getHotelReviews(int hotelID){
        ArrayList<HotelReview> ans = dbutils.getHotelReviews(hotelID);
        this.reviewPage = 0;
        this.reviewTotalPage = (ans.size() - 1) / 10;
        this.hotelReview = ans;
        this.showReview(hotelID, "Hotel Reviews", ans, this.reviewPage, this.reviewTotalPage);
    }
}