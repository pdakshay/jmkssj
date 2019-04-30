package in.co.jmkssj.www.jmkssj;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import in.co.jmkssj.www.jmkssj.Model.Volunteers_Details_C;

public class MemberShip extends AppCompatActivity implements View.OnClickListener {
    EditText selectDate;
    private int mYear, mMonth, mDay;
    TextView title;
    ImageButton btn_back;
    EditText editTextname, editTextemail, editTextphone, editTextaddress,editTextdate, dob;
    RadioGroup radioGroup;
    RadioButton male, female;
    String name, email, phone, address,formfillingdate, dateOfBirth, gender = "";
    Button submit;
    TextView txtview;
    DatePickerDialog datePickerDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String phoneNumber;
    ProgressDialog progressDoalog;
    RelativeLayout relativeLayout;
    RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_member_ship);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Volunteers");

        //ToolBar
        // Title
        title = (TextView) findViewById(R.id.r_title);
        title.setText("Ragistration Form");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/waltographUI.ttf");
        title.setTypeface(typeface);
        //Bcak Button
        btn_back = (ImageButton) findViewById(R.id.imageBack_btn);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        //End ToolBar

        //DataBaseReference
        //EditText Initilization
        editTextname = (EditText) findViewById(R.id.memberName);
        editTextemail = (EditText) findViewById(R.id.memberEmail);
        editTextphone = (EditText) findViewById(R.id.memberPhone);
        editTextaddress = (EditText) findViewById(R.id.memberAddress);
        editTextdate = (EditText) findViewById(R.id.formDate);
        dob = (EditText) findViewById(R.id.Birthday);
        //ButtonEvent
        dob.setOnClickListener(this);
        submit = (Button) findViewById(R.id.btn_register);


        //Calender Auto Date&Time
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String date_str = df.format(cal.getTime());
        editTextdate.setCursorVisible(false);
        editTextdate.setInputType(InputType.TYPE_NULL);
        editTextdate.setFocusableInTouchMode(false);
        editTextdate.setText(date_str);
        genderRadioGroup=(RadioGroup)findViewById(R.id.gender_radiogroup);
        //RadioButton
        //  male = (RadioButton)findViewById(R.id.male);
        // male = (RadioButton)findViewById(R.id.female);

        //RadioGroupMaleFemale
/*        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.male:
                        gender = "Male";
                        break;
                    case R.id.female:
                        gender = "Female";
                        break;
                }
                /*int selectedId = radioGroup.getCheckedRadioButtonId();
                if (i == R.id.male) {
                    gender = "Male";
                } else if (i == R.id.female) {
                    gender = "Female";
                }}
        }) */
        //Ragistration Button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioButton selectedRadioButton=(RadioButton)findViewById(genderRadioGroup.getCheckedRadioButtonId());
                String gender=selectedRadioButton==null ? "":selectedRadioButton.getText().toString();
                if (gender.isEmpty()){
                    Toast.makeText(MemberShip.this, "Please select Gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                name = editTextname.getText().toString().trim();
                if (name.isEmpty()) {
                    editTextname.setError("Please Enter Your Name");
                    editTextname.requestFocus();
                    return;
                }
                email = editTextemail.getText().toString().trim();
                if (email.isEmpty()) {
                    editTextemail.setError("Please Enter Email Id");
                    editTextemail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextemail.setError("Please Enter  Valid  Email Address");
                    editTextemail.requestFocus();
                    return;
                }
                phone = editTextphone.getText().toString().trim();
                if (phone.isEmpty()) {
                    editTextphone.setError("Please Enter  Mobile Number");
                    editTextphone.requestFocus();
                    return;
                }
                if (!Patterns.PHONE.matcher(phone).matches()) {
                    editTextphone.setError("Please Enter 10 Digits Mobile Number");
                    editTextphone.requestFocus();
                    return;
                }
                address = editTextaddress.getText().toString().trim();
                if (address.isEmpty()) {
                    editTextaddress.setError("Please Enter Your Address");
                    editTextaddress.requestFocus();
                    return;
                }
                formfillingdate = editTextdate.getText().toString().trim();

                dateOfBirth = dob.getText().toString().trim();

                if (dateOfBirth.isEmpty()) {
                    dob.setError("Please Enter Your Date Of Birth");
                    dob.requestFocus();
                    return;
                }


                progressDoalog = new ProgressDialog(MemberShip.this);
                progressDoalog.show();
                progressDoalog.setMessage("Data Will be inserted Please wait...");

                Volunteers_Details_C volunteers_details_c = new Volunteers_Details_C(name, email, phone, address,formfillingdate, gender, dateOfBirth);
                databaseReference.child("+91" + phone).setValue(volunteers_details_c).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                dataSnapshot.getValue(Boolean.parseBoolean(name));
                                progressDoalog.hide();
                                openDialog();
                            }

                            private void openDialog() {
                                WelcomeDialog welcomeDialog = new WelcomeDialog();
                                welcomeDialog.show(getSupportFragmentManager()," ");
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        if (view == dob) {
            dob.setShowSoftInputOnFocus(false);
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == btn_back) {
            onBackPressed();

        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}