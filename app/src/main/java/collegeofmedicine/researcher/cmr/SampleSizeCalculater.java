package collegeofmedicine.researcher.cmr;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import 	android.content.Context;

import java.text.NumberFormat;
import java.util.Locale;

public class SampleSizeCalculater extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    EditText a;
    EditText b;
    Spinner dropdown;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);
        setTitle("Sample Size Calculator");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dropdown = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SampleSizeCalculater.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.conflvl));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
        }
        return super.onOptionsItemSelected(item);
    }


    //:Hide the Keyboard when user clicks away
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }




    public double confidenceLevel = -1;
    public double ConfidenceLevel(int l){
        if (l==0)
            return 1.28;
        if (l==1)
            return 1.44;
        if (l==2)
            return 1.65;
        if (l==3)
            return 1.96;
        if (l==4)
            return 2.58;
        return -1;
    }


    public void OnButtonClick(View v) {
        if (v.getId() == R.id.calc) {
            a = (EditText) findViewById(R.id.editText2);
            String aa = a.getText().toString();
            b = (EditText) findViewById(R.id.editText3);
            String bb = b.getText().toString();


            if (aa.equals("") || bb.equals("") || aa.equals(null) || bb.equals(null)) {
                Snackbar.make(v, "Fill all the fields", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show();
            } else {
                double pop = Double.parseDouble(aa);
                double err = Double.parseDouble(bb);


                if (pop <= 0 || err <= 0 || confidenceLevel <= 0) {
                    Snackbar.make(v, "Error", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } else
                    Calculation(pop, confidenceLevel, err);
            }

        }


    }

    public void Calculation(double pop, double conf, double err){
        double n = 0.25*Math.pow((conf/(err/100)), 2);
        double ans = Math.ceil((pop*n)/(n+pop-1));
        String st = nFormate(ans);
        TextView result = (TextView)findViewById(R.id.result);
        //String str = String.format("%.0f",ans);
        result.setText(st);

    }


    public static String nFormate(double d) {
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(10);
        String st= nf.format(d);
        return st;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        confidenceLevel = ConfidenceLevel(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Another interface callback
    }





}
