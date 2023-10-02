package com.example.log3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText emailInput;
    private EditText dobInput;
    private String selectedOption;

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
        {
            LocalDate d = LocalDate.now();
            int year = d.getYear();
            int month = d.getMonthValue();
            int day = d.getDayOfMonth();
            return new DatePickerDialog(getActivity(), this, year, --month, day);}
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day){
            LocalDate dob = LocalDate.of(year, ++month, day);
            ((MainActivity)getActivity()).updateDate(dob);
        }
    }
    public  void updateDate(LocalDate date){
         dobInput = findViewById(R.id.dob_input);
        dobInput.setText(date.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize views
        nameInput = findViewById(R.id.name_input);
        emailInput = findViewById(R.id.email_input);
        dobInput = findViewById(R.id.dob_input);
        // Set date of birth
        dobInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        // set radiobutton
        RadioGroup radioGroup;
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            selectedOption = radioButton.getText().toString();
//            // Display the selected option
            Toast.makeText(this, "Image selection ", Toast.LENGTH_SHORT).show();
        });
        // Save button click handler
        Button saveButton = findViewById(R.id.save_btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDetails();
            }
        });

        // View details button click handler
        Button viewDetailsButton = findViewById(R.id.details_btn);
        viewDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveDetails() {
        if(nameInput.getText().toString().isEmpty()||dobInput.getText().toString().isEmpty()||emailInput.getText().toString().isEmpty()||selectedOption.isEmpty()){
            errorMessage("You need to fill the required fields");
        }
        else {
            displayNextAlert(nameInput.getText().toString(), dobInput.getText().toString(),emailInput.getText().toString(),selectedOption);
            MyDatabaseHelper db= new MyDatabaseHelper(this);
            db.addContact(nameInput.getText().toString().trim(),
                        dobInput.getText().toString().trim(),
                        emailInput.getText().toString().trim(),
                    Integer.parseInt(selectedOption.trim()));
        }
    }
    public void errorMessage(String message){
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }
    public void displayNextAlert(String name, String dobInput, String emailInput, String selectedOption){
        new AlertDialog.Builder(this)
                .setTitle("Add success")
                .setMessage(
                        "Name: "+name + "\n" +
                                "Email: "+ emailInput + "\n" +
                                "Date of birth: "+dobInput + "\n" +
                                "Image: "+ selectedOption
                )
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }
}