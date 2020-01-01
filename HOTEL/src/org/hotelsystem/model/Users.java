package org.hotelsystem.model;
import java.util.*;
public class Users {
    private ArrayList<User> userlist;
    
    public Users(){//constructor for test
        userlist = new ArrayList<User>();
        User user0 = new User(01,1,"John","PDASDW");
        User user1 = new User(01,1,"Tom","PDASDW");
        userlist.add(user0);
        userlist.add(user1);
    }
    //public Users(){
        //link DB
    //}
    public ArrayList<User> getUsers(){
        return userlist;
    }
    public void syncUsers(){
        //TODO
    }

}