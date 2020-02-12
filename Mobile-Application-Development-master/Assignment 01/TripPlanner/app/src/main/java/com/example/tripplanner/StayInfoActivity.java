/*
    FILE            :   StayInfoActivity.java
    PROJECT         :   PROG3150 (Mobile Application Development) - Assignment #01
    PROGRAMMERS     :   Kenan Dzindo, Chuhui Guo, Andrew Kang, Jayson Ovishek Biswas, Karson Lai
    FIRST VERSION   :   February 31st, 2020
    DESCRIPTION     :   This file is in charge of allowing the user to select the amount of days
                        they're going to be staying at the hotel for. It will also allow them to
                        select the amount of people joining them if any.
 */

package com.example.tripplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.tripplanner.R.layout.activity_stay_info;

/*
    NAME        : StayInfoActivity
    PURPOSE     : The StayInfoActivity class has been created to let user choose dates for their
 *                check-in and check-out, and select the number of people for the stay
 */
public class StayInfoActivity extends AppCompatActivity {
    ImageView hotelImage;
    Button stayInfo;
    Intent Next;
    String check_in;
    String check_out;
    NumberPicker peoplePicker;
    DatePicker checkIn;
    DatePicker checkOut;
    Intent Prev;
    int people;
    String hotel;
    String price;
    int hotelImageInt;
    /*
     * FUNCTION     : onCreate
     * DESCRIPTION  : This function is in charge of initialize the screen for user to enter the information
     *                of their stay.
     * PARAMETERS   : Bundle savedInstanceState
     * RETURNS      : N/A
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_stay_info);

        // The previous screen
        Prev = getIntent();

        // find the views with their respected id
        hotelImage = findViewById(R.id.image_of_hotel);
        stayInfo = findViewById(R.id.stay_info_btn);
        peoplePicker = findViewById(R.id.number_of_people);
        checkIn = findViewById(R.id.check_in_date);
        checkOut = findViewById(R.id.check_out_date);

        // get the data passed from last activity
        hotel = Prev.getStringExtra("hotel_name");
        price = Prev.getStringExtra("hotel_price");
        hotelImageInt = Prev.getIntExtra("imageInt", 0);

        // Initialize the components
        hotelImage.setImageDrawable(this.getResources().getDrawable(hotelImageInt));
        peoplePicker.setMinValue(1);
        peoplePicker.setMaxValue(12);

        // An alert dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your check-in date should be earlier than check-out date!");
        builder.setPositiveButton(
                "Got it!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // Listener for the click event
        stayInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check-out date should be later than check-in
                if (checkIn.getYear() >= checkOut.getYear() &&
                        checkIn.getMonth() >= checkOut.getMonth() &&
                        checkIn.getDayOfMonth() >= checkOut.getDayOfMonth()) {
                    AlertDialog alert = builder.create();
                    alert.show();
                    return;
                }

                // format the date by reading value from DatePicker
                check_in = checkIn.getDayOfMonth() + "/" + checkIn.getMonth() + "/" + checkIn.getYear();
                check_out = checkOut.getDayOfMonth() + "/" + checkOut.getMonth() + "/" + checkOut.getYear();
                // get the number of people
                people = peoplePicker.getValue();
                // next activity
                Next = new Intent(StayInfoActivity.this, GetReceipt.class);
                // parameters to be passed to next activity
                Next.putExtra("hotName", hotel);
                Next.putExtra("hotPrice", price);
                Next.putExtra("imageInt", hotelImageInt);
                Next.putExtra("check_in", check_in);
                Next.putExtra("check_out", check_out);
                Next.putExtra("people", people);
                // start next activity
                startActivity(Next);
            }
        });

    }
}