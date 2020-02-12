/*
    FILE            :   HotelAdapter.java
    PROJECT         :   PROG3150 (Mobile Application Development) - Assignment #01
    PROGRAMMERS     :   Kenan Dzindo, Chuhui Guo, Andrew Kang, Jayson Ovishek Biswas, Karson Lai
    FIRST VERSION   :   February 31st, 2020
    DESCRIPTION     :   This file contains the necessary HotelAdapters functions to effectively
                        create views within the ListView.
 */

package com.example.tripplanner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;



/*
    NAME        : HotelAdapter
    PURPOSE     : The HotelAdapter class has been created to reuse Views effectively.
                  This reused View is the convertView. If this is null it means that
                  there is no recycled View and we have to create a new one, otherwise we
                  should use it to avoid creating a new.
 */
public class HotelAdapter extends ArrayAdapter<HotelInfo> {
    private Context context;
    private int resource;
    private List<HotelInfo> hotelList;

    // HotelAdapter Constructor
    public HotelAdapter(Context context, int resource, List<HotelInfo> hotelList) {
        super(context, resource, hotelList);
        this.context = context;
        this.resource = resource;
        this.hotelList = hotelList;
    }

    /*
     * FUNCTION     : getView
     * DESCRIPTION  : This function is in charge of reusing Views, when a View is scrolled so
     *                that it is no longer visible, it can be used for one of the new Views appearing.
     * PARAMETERS   : int position
     *                View convertView
     *                ViewGroup parent
     * RETURNS      : View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotelInfo list = hotelList.get(position);
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);

            holder.mHotel_image = convertView.findViewById(R.id.image_of_hotel);
            holder.mHotel_name = convertView.findViewById(R.id.name_of_hotel);
            holder.mHotel_price = convertView.findViewById(R.id.price_of_hotel);
            holder.mRating_bar = convertView.findViewById(R.id.rating_of_hotel);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.mHotel_name.setText(list.getNameOfHotel());
        holder.mRating_bar.setRating((float) list.getHotelRating());
        holder.mHotel_price.setText(list.getPriceOfHotel());
        holder.mHotel_image.setImageDrawable(context.getResources().getDrawable( list.getHotelImage()));

        return convertView;
    }

    static class ViewHolder {
        TextView mHotel_name;
        TextView mHotel_price;
        ImageView mHotel_image;
        RatingBar mRating_bar;
    }
}
