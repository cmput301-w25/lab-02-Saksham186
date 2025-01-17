package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityNameInput;
    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        cityNameInput = findViewById(R.id.city_name_input);
        Button addCityButton = findViewById(R.id.add_city_button);
        Button deleteCityButton = findViewById(R.id.delete_city_button);


        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin",
                "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));


        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);


        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
            Toast.makeText(this, selectedCity + " selected", Toast.LENGTH_SHORT).show();
        });


        addCityButton.setOnClickListener(v -> {
            String newCity = cityNameInput.getText().toString().trim();
            if (!newCity.isEmpty() && !dataList.contains(newCity)) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                cityNameInput.setText(""); // Clear input
                Toast.makeText(this, newCity + " added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "City name is empty or already exists!", Toast.LENGTH_SHORT).show();
            }
        });


        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != null && dataList.contains(selectedCity)) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                selectedCity = null; // Clear selection
                Toast.makeText(this, "City deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No city selected to delete!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
