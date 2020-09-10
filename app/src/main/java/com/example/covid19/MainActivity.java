package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView Cases,Recovered,Critical,Active,TodayCases,TotalDeaths,TodayDeaths;
    TextView AffectedCountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cases=findViewById(R.id.tvCases);
      Recovered=findViewById(R.id.tvRecovered);
      Critical=findViewById(R.id.tvCritical);
       Active=findViewById(R.id.tvActive);
       TodayCases=findViewById(R.id.tvTodayCases);
        TotalDeaths=findViewById(R.id.tvTotalDeaths);
        TodayDeaths=findViewById(R.id.tvTodayDeaths);
       AffectedCountries=findViewById(R.id.tvAffectedCountries);


        simpleArcLoader=findViewById(R.id.loader);
        scrollView=findViewById(R.id.scrollStats);
        pieChart=findViewById(R.id.piechart);

        fetchData();
    }

    private void fetchData() {


        String url="https://corona.lmao.ninja/v2/all";
        simpleArcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response.toString());

                    Cases.setText(jsonObject.getString("cases"));
                    Recovered.setText(jsonObject.getString("recovered"));
                    Critical.setText(jsonObject.getString("critical"));
                    Active.setText(jsonObject.getString("active"));
                   TodayCases.setText(jsonObject.getString("todayDeaths"));
                   TotalDeaths.setText(jsonObject.getString("deaths"));
                   TodayDeaths.setText(jsonObject.getString("todayDeaths"));
                   AffectedCountries.setText(jsonObject.getString("affectedCountries"));



                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(Cases.getText().toString()), Color.parseColor("#E2C208")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(Recovered.getText().toString()), Color.parseColor("#52C709")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(TotalDeaths.getText().toString()), Color.parseColor("#DF0C0C")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(Active.getText().toString()), Color.parseColor("#14DAC8")));
                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);







                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);




    }







    public void goTrackCountries(View view) {

      startActivity(new Intent(getApplicationContext(),AffectedCountries.class));

    }
}
