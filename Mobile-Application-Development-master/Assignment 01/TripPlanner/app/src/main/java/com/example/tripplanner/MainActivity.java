/*
    FILE            :   MainActivity.java
    PROJECT         :   PROG3150 (Mobile Application Development) - Assignment #01
    PROGRAMMERS     :   Kenan Dzindo, Chuhui Guo, Andrew Kang, Jayson Ovishek Biswas, Karson Lai
    FIRST VERSION   :   February 31st, 2020
    DESCRIPTION     :   This file is in charge of populating the List View items with the Hotel Names, Image, Price and Rating.
 */

package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/*
    NAME        : MainActivity
    PURPOSE     : The HotelInfo class has been created to populate the ListView with the Hotels.
 */
public class MainActivity extends AppCompatActivity {

    List<HotelInfo> hotelList;  // List HotelInfo Objects

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hotelList = new ArrayList<>();

        // setting/adding the Hotels
        // NOTE ADDING MORE HOTELS WITH DIFFERENT IMAGES MAKES THIS SHIT VERYYYYYYYYYYYY LAGGY
        hotelList.add(new HotelInfo("Hyatt", "$120.00", R.drawable.hyatt, 2));
        hotelList.add(new HotelInfo("IHG", "$100.00", R.drawable.ihg, 3));
        hotelList.add(new HotelInfo("Marriott", "$250.00", R.drawable.marriott, 5));
        hotelList.add(new HotelInfo("Hilton", "$175.00", R.drawable.hilton, 4));

        ListView lv = findViewById(R.id.listView);

        HotelAdapter adapter = new HotelAdapter(this, R.layout.hotel_list, hotelList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // set intents for next view when list selection is selected
                Intent intent = new Intent(MainActivity.this, HotelConfirmation.class);
                intent.putExtra("hotel_name", hotelList.get(position).getNameOfHotel());
                intent.putExtra("hotel_price", hotelList.get(position).getPriceOfHotel());
                intent.putExtra("hotel_rating", Double.toString(hotelList.get(position).getHotelRating()));
                intent.putExtra("hotel_image", hotelList.get(position).getHotelImage());
                startActivity(intent);
            }
        });
    }
}
