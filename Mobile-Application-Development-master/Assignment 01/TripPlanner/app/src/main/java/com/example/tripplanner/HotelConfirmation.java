/*
    FILE            :   HotelConfirmation.java
    PROJECT         :   PROG3150 (Mobile Application Development) - Assignment #01
    PROGRAMMERS     :   Kenan Dzindo, Chuhui Guo, Andrew Kang, Jayson Ovishek Biswas, Karson Lai
    FIRST VERSION   :   February 31st, 2020
    DESCRIPTION     :   This file contains the necessary functions to create the confirmation screen
                        and to switch to the second screen if the user confirms their hotel choice.
 */
package com.example.tripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/*
    NAME        : HotelConfirmation
    PURPOSE     : The HotelConfirmation class has been created to represent the confirmation
                  screen/view when a user selects a Hotel.
 */
public class HotelConfirmation extends AppCompatActivity {
    // variable decelerations
    Intent next;
    Intent previous;
    TextView nameOfHotel;
    TextView priceOfHotel;
    TextView descOfHotel;
    String priceInt;
    int imageOfHotelInt;
    ImageView imageOfHotel;
    String nameOfHotelStr;
    String descOfHotelStr;
    float hotelRatingFlo;
    RatingBar hotelRating;
    Button confirmationHotelBtn;

    /*
     * FUNCTION     : onCreate
     * DESCRIPTION  : This function is in charge of recreating and retrieving information about
     *                the hotel and presenting ir inside the confirmation screen.
     * PARAMETERS   : Bundle savedInstanceState
     * RETURNS      : N/A
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_confirmation);

        // find the views with their respected id
        confirmationHotelBtn = findViewById(R.id.hotel_confirm_btn);
        nameOfHotel = findViewById(R.id.name_of_hotel);
        priceOfHotel = findViewById(R.id.price_of_hotel);
        imageOfHotel = findViewById(R.id.image_of_hotel);
        hotelRating = findViewById(R.id.rating_of_hotel);
        descOfHotel = findViewById(R.id.dec_of_hotel);

        // request information from main component
        previous = getIntent();
        nameOfHotelStr = previous.getStringExtra("hotel_name");
        setTitle("Information About " + nameOfHotelStr);    // set the title for the new layout/view
        priceInt = previous.getStringExtra("hotel_price");
        hotelRatingFlo = Float.parseFloat(previous.getStringExtra("hotel_rating"));
        imageOfHotelInt = previous.getIntExtra("hotel_image",this.getResources().getIdentifier("ic_launcher", "drawable", this.getPackageName()));

        // info about each hotel
        if (nameOfHotelStr.equals("Hyatt")) {
            descOfHotelStr = "Discover the excitement of Downtown Toronto’s Entertainment District, steps from the business and " +
                    "financial district and all that makes the city a vibrant destination. " +
                    "With easy access to the Metro Toronto Convention Centre, you can also explore the " +
                    "CN Tower, Rogers Centre, Royal Ontario Museum, and Princess of Wales Theatre. " +
                    "Enjoy the fashionable shopping and dining scene within walking distance of our hotel. " +
                    "At the Hyatt Regency, you are immersed in the creative urban energy of Toronto.";
        } else if (nameOfHotelStr.equals("IHG")) {
            descOfHotelStr = "InterContinental Hotels Group plc, informally InterContinental Hotels " +
                    "or IHG, is a British multinational hospitality company headquartered in Denham, Buckinghamshire, England." +
                    "IHG has about 842,749 guest rooms and 5,656 hotels across nearly 100 countries.";
        } else if (nameOfHotelStr.equals("Marriott")) {
            descOfHotelStr = "Marriott Hotels & Resorts is Marriott International's flagship brand of full-service hotels and resorts. " +
                    "The company, based in Bethesda, Maryland, is repeatedly included on the Forbes list of best companies to work for, " +
                    "and it was voted the fourth best company to work for in the UK by The Times in 2009.[2]"+
                    "As of December 31, 2018, there were 567 hotels and resorts with 201,366 rooms operating under the brand";
        } else if (nameOfHotelStr.equals("Hilton")) {
            descOfHotelStr = "Hilton Hotels & Resorts[3] (formerly known as Hilton Hotels) is a global " +
                    "brand of full-service hotels and resorts and the flagship brand of American multinational hospitality company Hilton.";
        }

        descOfHotel.setText(descOfHotelStr);

        // set the elements with the retrieved data
        nameOfHotel.setText(nameOfHotelStr);
        imageOfHotel.setImageDrawable(this.getResources().getDrawable(imageOfHotelInt));
        priceOfHotel.setText(String.valueOf(priceInt));
        hotelRating.setRating(hotelRatingFlo);

        confirmationHotelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next = new Intent(HotelConfirmation.this, StayInfoActivity.class);
                next.putExtra("hotel_name", nameOfHotelStr);
                next.putExtra("hotel_price", priceInt);
                next.putExtra("imageInt", imageOfHotelInt);
                startActivity(next);
            }
        });
    }
}
