package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int quantity = 2;
    boolean whippedCream=false;
    boolean chocalate=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


          displayQuantiy(quantity);
          String orderSummary=createOrderSummary(calculatePrice());
          displayMessage(orderSummary);
    }

    private String createOrderSummary(int price)
    {
        EditText name=(EditText)findViewById(R.id.customer_name_editText);


        String message="Name :"+name.getText();
        message=message+"\nQuantity "+quantity;
        message=message+"\nWhipped Cream "+whippedCream;
        message=message+"\nChocalate "+chocalate;
        message=message+"\nTotal Price $"+price;
        message=message+"\nThank You !!!";
        return message;

    }

    private int calculatePrice()
    {
        return quantity*5;
    }

    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantiy(quantity);

    }

    public void decrement(View view) {

        if(quantity>0)
            quantity--;
        else
        {
            quantity=0;
            Toast.makeText(this,"Item cannot be negative",Toast.LENGTH_SHORT).show();
        }
        displayQuantiy(quantity);

    }
    public void onCheckBoxItemClicked(View view)
    {

        CheckBox checkBox=(CheckBox)findViewById(R.id.notify_me_checkbox);
        if (checkBox.isChecked())
        {
            whippedCream=true;
        }
        else
            whippedCream=false;
    }
    public void onChocalateCheckBoxItemClicked(View view)
    {
        CheckBox checkBox=(CheckBox)findViewById(R.id.notify_chocalate_checkbox);
        if (checkBox.isChecked())
        {
            chocalate=true;
        }
        else
            chocalate=false;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantiy(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String variable)
    {
        TextView priceTextView=(TextView)findViewById(R.id.order_summary_text_view);
        priceTextView.setText(variable);
        Uri email=Uri.parse("mailto:");

        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(email);
              //  intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "coffeebean@coffee.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
                intent.putExtra(Intent.EXTRA_TEXT,variable);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }
}