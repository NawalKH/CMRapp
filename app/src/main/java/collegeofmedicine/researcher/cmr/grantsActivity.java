package collegeofmedicine.researcher.cmr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class grantsActivity extends AppCompatActivity {


    private ListView pListView;
    private MyGrantAdapter mGrantAdapter;
    ArrayList<grant> mGrantArray;
    ProgressBar loaderView;

    //:get the whole database
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    //:get root "Events" from database
    DatabaseReference child = db.child("Grants");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_grants);
        setTitle("Grants");



        //loaderView = (ProgressBar) findViewById(R.id.loading);



        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                showEvents(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void showEvents(DataSnapshot dataSnapshot) {
        mGrantArray = new ArrayList<grant>();

        //:loop over every child in Events to get its information.
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            grant obj = new grant();

            obj.setName(ds.child("grantTitle").getValue().toString());
            obj.setSdate(ds.child("startDate").getValue().toString());
            obj.setEdate(ds.child("endDate").getValue().toString());
            obj.setUrl(ds.child("URL").getValue().toString());
            mGrantArray.add(obj);
        }



        pListView = (ListView) findViewById(R.id.granslist);
        //Context context = parent.getContext();
        mGrantAdapter = new MyGrantAdapter(this, R.layout.grant_row, mGrantArray);

        //Animation

        if (pListView != null) {
            pListView.setAdapter(mGrantAdapter);
            //loaderView.setVisibility(View.GONE);
        }




        pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {

                Intent intent = new Intent(grantsActivity.this, web.class);
                intent.putExtra("EXTRA_SESSION_ID",mGrantArray.get(i).getUrl());
                startActivity(intent);
                finish();
            }

        });


    }




    public void grantProvider(View view)
    {
        Intent intent = new Intent(grantsActivity.this, grantProvider.class);
        startActivity(intent);
        finish();
    }



}
