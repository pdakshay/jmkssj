package in.co.jmkssj.www.jmkssj;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements  BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener  {
        Toolbar toolbar;
        TextView textView,appTitle;
        SliderLayout sliderLayout;
        HashMap<String, Integer> HashMapForURL;
        RelativeLayout viewSuggestion,viewDonation,viewMembership,viewMap,viewFounder,viewOurTeam;
        Boolean doubleBackToExitPressedOnce = false;


    @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        appTitle = (TextView)findViewById(R.id.app_title);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/waltographUI.ttf");
        appTitle.setTypeface(typeface);

        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        viewSuggestion = (RelativeLayout)findViewById(R.id.viewSuggestion);
        viewDonation = (RelativeLayout)findViewById(R.id.viewDonation);
        viewMembership = (RelativeLayout)findViewById(R.id.viewMembership);
        viewMap = (RelativeLayout)findViewById(R.id.viewMap);
        viewFounder = (RelativeLayout)findViewById(R.id.viewFounder);

        viewOurTeam = (RelativeLayout)findViewById(R.id.viewOurTeam);

        viewSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SuggestionActivity.class));
                finish();
            }
        });
        viewDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Donation.class));
                finish();
            }
        });
        viewMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MemberShip.class));
                finish();
            }
        });
        viewFounder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Founder.class));
                finish();
            }
        });
        viewOurTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getApplicationContext(),OurTeam.class));
                finish();
            }
        });
        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = 25.457701;
                double longitude = 78.582071;
                String label = "Munna Lal Dharamshala(Samiti Office) ";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent mapintent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(mapintent);
                finish();
            }
        });

        AddImagesUrl();
        for (String name : HashMapForURL.keySet()) {
            TextSliderView textSliderView = new TextSliderView(MainActivity.this);
            textSliderView.setPicasso(Picasso.get());
            textSliderView.description(name)
  .image(HashMapForURL.get(name))
                  .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(MainActivity.this);

    }
//Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            Toast.makeText(MainActivity.this, "Share clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.login) {
            Toast.makeText(MainActivity.this, "Login clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//End Menu

//Slider
    @Override
    protected void onStop() {

        sliderLayout.startAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        sliderLayout.startAutoCycle();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImagesUrl(){
        HashMapForURL = new HashMap<String, Integer>();
        HashMapForURL.put(".", R.drawable.s1);
        HashMapForURL.put("..", R.drawable.s2);
        HashMapForURL.put("...", R.drawable.s3);
        HashMapForURL.put("....", R.drawable.s4);
        }
//End Slider


    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please Clicked BACK  again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        },2000);
    }
}


