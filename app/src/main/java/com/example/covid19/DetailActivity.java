package com.example.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;
    TextView Country,Cases,Recovered,Critical,Active,TodayCases,TotalDeaths,TodayDeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of" + AffectedCountries.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        Country=findViewById(R.id.tvCountry);
        Cases=findViewById(R.id.tvCases);
        Recovered=findViewById(R.id.tvRecorved);
        Critical=findViewById(R.id.tvCritical);
       Active=findViewById(R.id.tvActive);
        TodayCases=findViewById(R.id.tvTodayCases);
       TotalDeaths=findViewById(R.id.tvDeaths);
        TodayDeaths=findViewById(R.id.tvTodayDeaths);


        Country.setText(AffectedCountries.countryModelList.get(positionCountry).getCountry());
        Cases.setText(AffectedCountries.countryModelList.get(positionCountry).getCases());
        Recovered.setText(AffectedCountries.countryModelList.get(positionCountry).getRecovered());
       Critical.setText(AffectedCountries.countryModelList.get(positionCountry).getCritical());
        Active.setText(AffectedCountries.countryModelList.get(positionCountry).getActive());
        TodayCases.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayCases());
       TotalDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getDeaths());
       TodayDeaths.setText(AffectedCountries.countryModelList.get(positionCountry).getTodayDeaths());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
