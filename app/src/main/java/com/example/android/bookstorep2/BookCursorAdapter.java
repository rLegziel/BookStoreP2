package com.example.android.bookstorep2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookstorep2.data.BookContract;
import com.example.android.bookstorep2.data.BookContract.BookEntry;

/**
 * Created by roix on 8/24/18.
 */

public class BookCursorAdapter extends CursorAdapter {
    public BookCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView nameTV = view.findViewById(R.id.name);
        TextView priceTV = view.findViewById(R.id.price_int);
        final TextView quantityTV = view.findViewById(R.id.quantity_int);
        final Button buyButton = view.findViewById(R.id.button_buy);

        //        params for the columns
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.BOOK_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.BOOK_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.BOOK_QUANTITY);

        //      params for the attributes to change
        String bookName = cursor.getString(nameColumnIndex);
        int bookPrice = cursor.getInt(priceColumnIndex);
        final int bookQuantity = cursor.getInt(quantityColumnIndex);

        //      Updated attributes in the views
        nameTV.setText(bookName);
        priceTV.setText(Integer.toString(bookPrice));
        quantityTV.setText(Integer.toString(bookQuantity));


        //       buy button
        buyButton.setText(R.string.buy_button);

/// reduces the quantity by 1 one pressed, shows a toast at 0.
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedQuantity = Integer.parseInt(quantityTV.getText().toString().trim());
                if (updatedQuantity > 0) {
                    quantityTV.setText(String.valueOf(updatedQuantity - 1));
                }
                if (updatedQuantity == 0) {
                    buyButton.setEnabled(false);
                    Toast.makeText(context, "there are 0 units in stock", Toast.LENGTH_SHORT).show();
                }
                ContentValues values = new ContentValues();
                values.put(BookContract.BookEntry.BOOK_QUANTITY, updatedQuantity);
            }
        });
    }
}