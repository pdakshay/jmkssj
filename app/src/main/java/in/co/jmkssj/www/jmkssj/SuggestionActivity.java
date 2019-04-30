package in.co.jmkssj.www.jmkssj;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class SuggestionActivity extends AppCompatActivity {
    TextView title;
    ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_suggestion);

        //ToolBar
        // Title
        title = (TextView)findViewById(R.id.s_title);
        title.setText("Suggestion");
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/waltographUI.ttf");
        title.setTypeface(typeface);
        //Bcak Button
        btn_back = (ImageButton)findViewById(R.id.imageBack_btn);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        //End ToolBar

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
