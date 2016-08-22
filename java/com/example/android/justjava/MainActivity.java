package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        boolean checkCream, checkChocolate = false;

        CheckBox checkbox1 = (CheckBox) findViewById(R.id.whipped_cream);
        checkCream = checkbox1.isChecked();
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.whipped_chocolate);
        checkChocolate = checkbox2.isChecked();

        int price = calculatePrice(quantity, 5, checkCream, checkChocolate);

        EditText nameText = (EditText) findViewById(R.id.name);
        String name = nameText.getText().toString();

        //String priceMessage = createOrderSummary(quantity, price, checkCream,checkChocolate, name);
        createOrderSummary(quantity, price, checkCream,checkChocolate, name);
        //Log.v("MainActivity","Name = "+name);

        //displayMessage(priceMessage);
    }
    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered and
     *  @param pricePerCup is the price position
     */
    private int calculatePrice(int quantity, int pricePerCup, boolean checkCream, boolean checkChocolate) {
        int cream;
        int chocolate;
        if (checkCream==true){
            cream = 1;
        } else {cream = 0;}
        if (checkChocolate==true){
            chocolate=2;
        } else {chocolate=0;}

        return quantity * (pricePerCup+cream+chocolate);
    }

    /**
     *
     * @param quantity
     * @param price
     * @return text
     */
    private void createOrderSummary(int quantity, int price, boolean checkCream, boolean checkChocolate, String name){
        String textMessage = "Name: "+ name;
        textMessage += "\nAdd whipped cream? "+checkCream;
        textMessage += "\nAdd Chocolate? "+checkChocolate;
        textMessage += "\nQuantity: "+quantity;
        textMessage +="\nTotal: $"+price;
        textMessage += "\nThank you!";

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6,-122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
       // i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for "+ name);//тема
        i.putExtra(Intent.EXTRA_TEXT   , textMessage);//текст
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

       // return textMessage;

    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    public void increment(View view) {
        if (quantity==100){
            Toast.makeText(this,"You cannot have more than 100 cups coffes",Toast.LENGTH_SHORT).show();
            return;}
        quantity = quantity + 1;

        display(quantity);
    }
    public void decrement(View view) {
        if (quantity==1){
            Toast.makeText(this,"You cannot have less than 1 cups coffes",Toast.LENGTH_SHORT).show();
            return;}
        quantity = quantity - 1;

        display(quantity);
    }
}