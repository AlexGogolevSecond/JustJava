package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;

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

        int price = calculatePrice(quantity, 5);
        CheckBox checkbox1 = (CheckBox) findViewById(R.id.whipped_cream);
        checkCream = checkbox1.isChecked();
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.whipped_chocolate);
        checkChocolate = checkbox2.isChecked();
        String priceMessage = createOrderSummary(quantity, price, checkCream,checkChocolate);
        displayMessage(priceMessage);
    }
    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered and
     *  @param pricePerCup is the price position
     */
    private int calculatePrice(int quantity, int pricePerCup) {
        return quantity * pricePerCup;
    }

    /**
     *
     * @param quantity
     * @param price
     * @return text
     */
    private String createOrderSummary(int quantity, int price, boolean checkCream, boolean checkChocolate){
        String textMessage = "Name: Gogolev Alex";
        textMessage += "\nAdd whipped cream? "+checkCream;
        textMessage += "\nAdd Chocolate? "+checkChocolate;
        textMessage += "\nQuantity: "+quantity;
        textMessage +="\nTotal: $"+price;
        textMessage += "\nThank you!";
        return textMessage;

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
        quantity = quantity + 1;
        display(quantity);
    }
    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }
}