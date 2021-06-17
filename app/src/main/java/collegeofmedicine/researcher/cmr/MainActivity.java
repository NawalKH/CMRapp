package collegeofmedicine.researcher.cmr;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public Button events;
    public Button facilities;
    public Button grants;
    public Button paper;
    public Button cal;
    public Button protocols;



    public void init()
    {

        events= (Button)findViewById(R.id.events);
        facilities= (Button)findViewById(R.id.facilities);
        grants= (Button)findViewById(R.id.grants);
        paper=(Button)findViewById(R.id.paper);
        cal= (Button)findViewById(R.id.cal);
        protocols=(Button)findViewById(R.id.survey);







        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventsActivity.class);
                startActivity(intent);

            }
        });




        facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, facilitiesActivity.class);
                startActivity(intent);

            }
        });



        grants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(MainActivity.this, grantsActivity.class);
                startActivity(g);
            }
        });


        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, paperlist.class);
                startActivity(intent);

            }
        });



        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calc = new Intent(MainActivity.this, SampleSizeCalculater.class);
                startActivity(calc);
            }
        });


        protocols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent calc = new Intent(MainActivity.this, TappedPnF.class);
                startActivity(calc);

            }
        });

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");

        init();


    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.about:
            Intent aboutActivity = new Intent(MainActivity.this, About.class);
            startActivity(aboutActivity);
            return (true);
    }
        return(super.onOptionsItemSelected(item));
    }

    */


    //********* LINKS PAGE
    public void showLinks(View view) {
        Intent link = new Intent(MainActivity.this, Links.class);
        startActivity(link);
    }

    public void showContact(View view) {
        Intent contact = new Intent(MainActivity.this, contact.class);
        startActivity(contact);
    }

    public void showAbout(View view) {
        Intent about = new Intent(MainActivity.this, About.class);
        startActivity(about);
    }


}