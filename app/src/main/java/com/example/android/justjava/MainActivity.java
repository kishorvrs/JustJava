package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static java.lang.System.exit;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void increment(View view) {
       // int numberOfCoffees=3;
       /* numberOfCoffees=4;
        numberOfCoffees=5;
        numberOfCoffees=6;
        */if (numberOfCoffees == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        numberOfCoffees=numberOfCoffees+1;
        display(numberOfCoffees);

    }
    public void decrement(View view) {
        //int numberOfCoffees=4;
       /* numberOfCoffees=2;
        numberOfCoffees=1;
        */
        if (numberOfCoffees == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        numberOfCoffees=numberOfCoffees-1;
        display(numberOfCoffees);

    }

    /**
     * This method is called when the order button is clicked.
     */
   /** public void submitOrder(View view) {
        int numberOfCoffees=2;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees*5);
        //display(2);
       // displayPrice(2*5);
    }
    */
   public void submitOrder(View view){
      // String priceMessage="Free";
       EditText editName=(EditText)findViewById(R.id.edit_name);
       String name=editName.getText().toString();
       CheckBox whippedCreamCheckbox=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
       boolean haswhippedCream=whippedCreamCheckbox.isChecked();
      // Log.v("MainActivity","Has Whipped cream:"+ haswhippedCream);
       CheckBox chocolateCheckbox=(CheckBox)findViewById(R.id.chocolate_checkbox);
       boolean hasChocolateCheckbox=chocolateCheckbox.isChecked();
       int price=calculatePrice(haswhippedCream,hasChocolateCheckbox);
       String priceMessage=createOrderSummary(price,haswhippedCream,hasChocolateCheckbox,name);
       Intent intent = new Intent(Intent.ACTION_SENDTO);
       intent.setData(Uri.parse("mailto:")); // only email apps should handle this
       intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for " + name);
       intent.putExtra(Intent.EXTRA_TEXT,  priceMessage);
       if (intent.resolveActivity(getPackageManager()) != null) {
           startActivity(intent);
       }

   }
   private int calculatePrice(boolean addWhippedCream,boolean addChocolate){
       int basePrice=5;
       if(addWhippedCream){
           basePrice=basePrice+1;
       }
         if(addChocolate){
           basePrice=basePrice+2;
       }

           return numberOfCoffees * basePrice;

   }
   private String createOrderSummary(int price,boolean addwhippedCream,boolean addChocolate,String name){
       String priceMessage="Name: " + name;
       priceMessage += "\nAdd whiped cream? " + addwhippedCream;
       priceMessage += "\nAdd chocolate? " + addChocolate;
       priceMessage += "\nQuantity:"+numberOfCoffees;
       priceMessage += "\nTotal: $" + price;
       priceMessage += "\nThank you";
       return priceMessage;
   }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



}