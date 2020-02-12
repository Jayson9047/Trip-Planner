/*
    FILE            :   HotelInfo.java
    PROJECT         :   PROG3150 (Mobile Application Development) - Assignment #01
    PROGRAMMERS     :   Kenan Dzindo, Chuhui Guo, Andrew Kang, Jayson Ovishek Biswas, Karson Lai
    FIRST VERSION   :   February 31st, 2020
    DESCRIPTION     :   This file contains the constructor and necessary getters to create the
                        HotelInfo Object.
 */

package com.example.tripplanner;

/*
    NAME        : HotelInfo
    PURPOSE     : The HotelInfo class has been created to represent a Hotel. It will store/hold
                  the Name, Price, Image and Rating of the Hotel.
 */
public class HotelInfo {
    private String nameOfHotel;
    private String priceOfHotel;
    private int hotelImage;
    private double hotelRating;

    // Constructor
    public HotelInfo(String nameOfHotel, String priceOfHotel, int hotelImage, double hotelRating) {
        this.nameOfHotel = nameOfHotel;
        this.priceOfHotel = priceOfHotel;
        this.hotelImage = hotelImage;
        this.hotelRating = hotelRating;
    }

    // getters
    public String getNameOfHotel() {
        return nameOfHotel;
    }

    public String getPriceOfHotel() {
        return priceOfHotel;
    }

    public int getHotelImage() {
        return hotelImage;
    }

    public double getHotelRating() {
        return hotelRating;
    }
}
