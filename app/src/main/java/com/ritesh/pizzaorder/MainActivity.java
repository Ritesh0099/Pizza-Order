package com.ritesh.pizzaorder;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup sizeGroup;
    Spinner crustSpinner;
    CheckBox cheese, pepperoni, mushrooms, onions, sausage, bacon, ham, bellpeppers, olives, pineapple, spinach, tomatoes, chicken, jalapenos, anchovies;
    Button orderButton;
    TextView orderSummary;

    // Prices
    double priceSmall = 5.0, priceMedium = 7.0, priceLarge = 9.0;
    double priceThin = 2.0, priceThick = 2.5, priceStuffed = 3.0;
    double priceCheese = 1.0, pricePepperoni = 1.5, priceMushrooms = 1.2, priceOnions = 1.0, priceSausage = 1.7,
            priceBacon = 1.8, priceHam = 1.5, priceBellPeppers = 1.1, priceOlives = 1.0, pricePineapple = 1.2,
            priceSpinach = 1.0, priceTomatoes = 1.0, priceChicken = 2.0, priceJalapenos = 1.0, priceAnchovies = 2.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sizeGroup = findViewById(R.id.sizeGroup);
        crustSpinner = findViewById(R.id.crustSpinner);

        cheese = findViewById(R.id.cheese);
        pepperoni = findViewById(R.id.pepperoni);
        mushrooms = findViewById(R.id.mushrooms);
        onions = findViewById(R.id.onions);
        sausage = findViewById(R.id.sausage);
        bacon = findViewById(R.id.bacon);
        ham = findViewById(R.id.ham);
        bellpeppers = findViewById(R.id.bellpeppers);
        olives = findViewById(R.id.olives);
        pineapple = findViewById(R.id.pineapple);
        spinach = findViewById(R.id.spinach);
        tomatoes = findViewById(R.id.tomatoes);
        chicken = findViewById(R.id.chicken);
        jalapenos = findViewById(R.id.jalapenos);
        anchovies = findViewById(R.id.anchovies);

        orderButton = findViewById(R.id.orderButton);
        orderSummary = findViewById(R.id.orderSummary);

        // Set up the crust spinner
        String[] crusts = {"Thin", "Thick", "Stuffed"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, crusts);
        crustSpinner.setAdapter(adapter);

        orderButton.setOnClickListener(v -> {
            double totalCost = 0.0;
            StringBuilder summary = new StringBuilder();

            // Get selected size
            int selectedId = sizeGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedSize = findViewById(selectedId);
                String size = selectedSize.getText().toString();
                double sizeCost = 0.0;

                if (size.contains("Small")) sizeCost = priceSmall;
                else if (size.contains("Medium")) sizeCost = priceMedium;
                else if (size.contains("Large")) sizeCost = priceLarge;

                totalCost += sizeCost;
                summary.append("Size: ").append(size).append(" (₹").append(sizeCost).append(")\n");
            } else {
                summary.append("Size: Not selected\n");
            }

            // Get selected crust
            String crust = crustSpinner.getSelectedItem().toString();
            double crustCost = 0.0;
            switch (crust) {
                case "Thin": crustCost = priceThin; break;
                case "Thick": crustCost = priceThick; break;
                case "Stuffed": crustCost = priceStuffed; break;
            }
            totalCost += crustCost;
            summary.append("Crust: ").append(crust).append(" (₹").append(crustCost).append(")\n");

            // Toppings
            summary.append("Toppings:\n");

            if (addTopping(cheese, priceCheese, summary)) totalCost += priceCheese;
            if (addTopping(pepperoni, pricePepperoni, summary)) totalCost += pricePepperoni;
            if (addTopping(mushrooms, priceMushrooms, summary)) totalCost += priceMushrooms;
            if (addTopping(onions, priceOnions, summary)) totalCost += priceOnions;
            if (addTopping(sausage, priceSausage, summary)) totalCost += priceSausage;
            if (addTopping(bacon, priceBacon, summary)) totalCost += priceBacon;
            if (addTopping(ham, priceHam, summary)) totalCost += priceHam;
            if (addTopping(bellpeppers, priceBellPeppers, summary)) totalCost += priceBellPeppers;
            if (addTopping(olives, priceOlives, summary)) totalCost += priceOlives;
            if (addTopping(pineapple, pricePineapple, summary)) totalCost += pricePineapple;
            if (addTopping(spinach, priceSpinach, summary)) totalCost += priceSpinach;
            if (addTopping(tomatoes, priceTomatoes, summary)) totalCost += priceTomatoes;
            if (addTopping(chicken, priceChicken, summary)) totalCost += priceChicken;
            if (addTopping(jalapenos, priceJalapenos, summary)) totalCost += priceJalapenos;
            if (addTopping(anchovies, priceAnchovies, summary)) totalCost += priceAnchovies;

            // Total
            summary.append("\nTotal Price: ₹").append(String.format("%.2f", totalCost));

            orderSummary.setText(summary.toString());
        });
    }

    private boolean addTopping(CheckBox checkBox, double price, StringBuilder summary) {
        if (checkBox.isChecked()) {
            summary.append("- ").append(checkBox.getText().toString())
                    .append(" (₹").append(price).append(")\n");
            return true;
        }
        return false;
    }
}
