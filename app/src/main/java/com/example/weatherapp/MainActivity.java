package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.input.InputManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.weatherapp.Models.WeatherData;
import com.example.weatherapp.Models.weather;
import com.example.weatherapp.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.weatherapp.Models.main;




public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        linearLayout=findViewById(R.id.mainLay);

        SimpleDateFormat format=new SimpleDateFormat("dd MMMM yyyy");
        String currentDate=format.format(new Date());

        binding.date.setText(currentDate);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                if(TextUtils.isEmpty(binding.searched.getText().toString())){
                    binding.searched.setText("Please Enter City");
                    return;
                }
                binding.city.setText(binding.searched.getText().toString());
                String city=binding.searched.getText().toString();
                fetchWeather(city);
            }
        });
    }

    public void hideKeyboard(){
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(linearLayout.getApplicationWindowToken(),0);
    }

    public void fetchWeather(String city){
        Retrofit retrofit=new Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceApi interfaceApi=retrofit.create(InterfaceApi.class);

        Call<WeatherData> call=interfaceApi.getData(city,"80409e07282e463da7a67d85222710b6","metric");
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(response.isSuccessful()){
                    WeatherData weatherData=response.body();
                    System.out.println(weatherData);
                    main to=weatherData.getMain();
                    binding.temp.setText(String.valueOf(to.getTemp())+"\u2103");
                    binding.min.setText(String.valueOf(to.getTemp_min()));
                    binding.max.setText(String.valueOf(to.getTemp_max()));
                    binding.pressure.setText(String.valueOf(to.getPressure()));
                    binding.humidity.setText(String.valueOf(to.getHumidity()));
                    binding.mainn.setText(String.valueOf(weatherData.getWeathers()));

//

                }else{
                    Toast.makeText(MainActivity.this, "gyhkujbm", Toast.LENGTH_SHORT).show();
//                    binding.city.setText(response.code());
                }


            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });
    }
}
//  80409e07282e463da7a67d85222710b6
//  https://api.openweathermap.org/data/2.5/weather?q=Nagpur&appid=80409e07282e463da7a67d85222710b6&units=metric
