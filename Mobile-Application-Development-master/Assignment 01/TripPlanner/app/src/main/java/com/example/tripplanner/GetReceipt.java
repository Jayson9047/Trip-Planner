/*
    FILE            :   GetReceipt.java
    PROJECT         :   PROG3150 (Mobile Application Development) - Assignment #01
    PROGRAMMERS     :   Kenan Dzindo, Chuhui Guo, Andrew Kang, Jayson Ovishek Biswas, Karson Lai
    FIRST VERSION   :   February 31st, 2020
    DESCRIPTION     :   This file is in charge of showing the user their end receipt with their
                        desired Hotel.
 */

package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Date;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class GetReceipt extends AppCompatActivity {
    public static String hotelNameURL;

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
        setContentView(R.layout.activity_get_receipt);

        // variables
        int people;
        double total;
        double price;
        String priceStr;
        String hotName;
        Random rand;
        int upperBound = 100000000;
        int lowerBound = 1000000000;
        int conCode;
        String check_in;
        String check_out;
        SimpleDateFormat format;
        int days = 1;

        // get the data passed from last activity
        hotName = getIntent().getStringExtra("hotName");
        priceStr = getIntent().getStringExtra("hotPrice");
        people = getIntent().getIntExtra("people",0);
        check_in = getIntent().getStringExtra("check_in");
        check_out = getIntent().getStringExtra("check_out");

        // find the views with their respected id
        TextView tv =  findViewById(R.id.hotelName);
        TextView tv2 = findViewById(R.id.cost);
        TextView tv3 = findViewById(R.id.noPpl);
        TextView tv4 = findViewById(R.id.ttlAmt);
        TextView tv5 = findViewById(R.id.code);
        TextView tv6 = findViewById(R.id.days);
        TextView tv7 = findViewById(R.id.from);
        TextView tv8 = findViewById(R.id.to);

        // set all known values
        tv.setText(hotName);        //name
        tv2.setText(priceStr);      //price per person
        tv7.setText(check_in);      //check in date
        tv8.setText(check_out);     //check out date
        tv3.setText(String.format(Locale.getDefault(), "%d", people)); // how many people

        // calculate how many days the person booked
        format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1 = format.parse(check_in);
            Date date2 = format.parse(check_out);
            days = (int) TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        // get the total cost -- how many people and how many days booked
        priceStr = priceStr.replaceAll("[^\\d.]+", "");
        price = Float.parseFloat(priceStr);
        total = price * people * days;
        tv4.setText(String.format(Locale.getDefault(), "$%.2f%n", total));
        tv6.setText(String.format(Locale.getDefault(), "%d", days));

        // get a random number for confirmation code
        rand = new Random();
        conCode = rand.nextInt(upperBound) + lowerBound;
        tv5.setText(String.format(Locale.getDefault(), "%d", conCode));

        hotelNameURL = hotName;
    }

    /*
     * FUNCTION     : openWebsite
     * DESCRIPTION  : This function is in charge of sending the user to the correct corresponding
     *                Hotel website
     * PARAMETERS   : View view
     * RETURNS      : N/A
     */
    public void openWebsite(View view) {
        String URL = "https://ca.hotels.com/";
        if (hotelNameURL.equals("Hyatt")) {
            URL = "https://www.hyatt.com/";
        } else if (hotelNameURL.equals("Hyatt")) {
            URL = "https://www.ihg.com/hotels/us/en/reservation";
        } else if (hotelNameURL.equals("Marriott")) {
            URL = "https://www.marriott.com/default.mi";
        } else if (hotelNameURL.equals("Hilton")) {
            URL = "https://www.hilton.com/en/";
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(browserIntent);
    }
}
