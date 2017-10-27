package com.base.xiaopa.base;

/**
 * Created by Satellite Wu on 2017/10/8.
 */
public class ShopCar {
     private    int SChead;
     private   String SCname;
     private   String SCinformation;
     private String SCprice;
     private String SCnumber;

ShopCar(){}
    public ShopCar(int head, String name, String information, String price, String number)
    {
        SChead=head;
        SCname=name;
        SCinformation=information;
        SCprice=price;
        SCnumber=number;
    }




    public int getSChead() {
        return SChead;
    }

    public String getSCinformation() {
        return SCinformation;
    }

    public String getSCname() {
        return SCname;
    }

    public String getSCnumber() {
        return SCnumber;
    }

    public String getSCprice() {
        return SCprice;
    }

    public void setSChead(int SChead) {
        this.SChead = SChead;
    }

    public void setSCinformation(String SCinformation) {
        this.SCinformation = SCinformation;
    }

    public void setSCname(String SCname) {
        this.SCname = SCname;
    }

    public void setSCnumber(String SCnumber) {
        this.SCnumber = SCnumber;
    }

    public void setSCprice(String SCprice) {
        this.SCprice = SCprice;
    }
}

