package com.example.hp.databasetutorial;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {
    public DatabaseHelper myDb;
    String MobilePattern = "[0-9]{10}";
    EditText editname,editemail,editnumber,editpwd,editcpwd;
    Button btnAddData,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editname = (EditText)findViewById(R.id.editText_name);
        editemail = (EditText)findViewById(R.id.editText_email);
        editnumber = (EditText)findViewById(R.id.editText_number);
        editpwd = (EditText)findViewById(R.id.editText_pwd);
        editcpwd=(EditText)findViewById(R.id.editText_cpwd);
        btnAddData = (Button)findViewById(R.id.btnAdddata);
        login=(Button)findViewById(R.id.login);
        btnAddData.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v)
                {

                    if(isValidEmaillId(editemail.getText().toString().trim()) && editpwd.getText().toString().length()>=6 && isValidPassword(editpwd.getText().toString()) && editnumber.getText().toString().matches(MobilePattern)){
                        //Toast.makeText(getApplicationContext(), "Valid Email Address.", Toast.LENGTH_SHORT).show();
                        AddData();
                    }
                    else if(editpwd.getText().toString().length()<6)
                    {
                        editpwd.setError("Enter password having length more than 6 characters");
                    }
                    else if(isValidPassword(editpwd.getText().toString())==false)
                    {
                        editpwd.setError("at least 1 Alphabet,Number & Special Character Require");
                    }
                    else if(editnumber.getText().toString().matches(MobilePattern)==false)
                    {
                         editnumber.setError("Number Should Contain 10 digits.");
                    }
                    else if(isValidEmaillId(editemail.getText().toString().trim())==false){
                         editemail.setError("InValid Email Address.");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "InValid Enteries... ", Toast.LENGTH_LONG).show();
                    }

                }
        }
        );
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                editname.setText("");editemail.setText("");editnumber.setText("");editpwd.setText("");editcpwd.setText("");
                Intent i=new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        }
        );
        //login.setOnClickListener(new View.OnClickListener(){
          //  public void onClick(View v)
            //{
                //editname.setText("");editemail.setText("");editnumber.setText("");editpwd.setText("");editcpwd.setText("");
               // Intent i=new Intent(MainActivity.this,Login.class);
                //startActivity(i);
            //}
        //});
    }

    private boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public  void AddData() {
          String s1=editemail.getText().toString();
          String s2=editpwd.getText().toString();
          String s3=editcpwd.getText().toString();
          if(s1.equals("")||s2.equals("")||s3.equals(""))
              Toast.makeText(MainActivity.this,"None of the field should be empty",Toast.LENGTH_LONG).show();
          else {
              if(s2.equals(s3)) {
                  boolean isInserted = myDb.insertData(editname.getText().toString(),
                                                        editemail.getText().toString(),
                                            editnumber.getText().toString(),
                                            editpwd.getText().toString()
                  );
                  if (isInserted == true)
                      Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                  else
                      Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();

              }
              else
              {
                  Toast.makeText(MainActivity.this,"Password don't match",Toast.LENGTH_LONG).show();
              }

          }
    }
}