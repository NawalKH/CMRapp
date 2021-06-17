package collegeofmedicine.researcher.cmr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by Nawal on 7/20/2017.
 */

public class paperlist extends AppCompatActivity {

    private ListView pListView;
    private MyPaperAdapter mPaperAdapter;
    ArrayList<paper> mPaperArray;
    ProgressBar loaderView;

    //:get the whole database
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    //:get root "Events" from database
    DatabaseReference child = db.child("papers");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paperlist);
        setTitle("Papers");



        loaderView = (ProgressBar) findViewById(R.id.loading);


        //: attach Event Listener to "Events" .. when the data changes or loaded for first time.
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
        mPaperArray = new ArrayList<paper>();

        //:loop over every child in Events to get its information.
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            paper obj = new paper();


            obj.setTitle(ds.child("title").getValue().toString());
            obj.setDescription(ds.child("description").getValue().toString());
            obj.setDate(ds.child("year").getValue().toString());
            obj.setAuthor(ds.child("author").getValue().toString());
            obj.setSourceTitle(ds.child("sourceTitle").getValue().toString());
            obj.setUrl(ds.child("sourceURL").getValue().toString());
            mPaperArray.add(obj);

        }



        pListView = (ListView) findViewById(R.id.listview2);
        //Context context = parent.getContext();
        mPaperAdapter = new MyPaperAdapter(this, R.layout.paper_row, mPaperArray);

        //Animation

        if (pListView != null) {
            pListView.setAdapter(mPaperAdapter);
            loaderView.setVisibility(View.GONE);
        }




        pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {

                Intent intent = new Intent(paperlist.this, paperInfo.class);
                intent.putExtra("paper",mPaperArray.get(i));
                startActivity(intent);
                finish();
            }

        });

    }

}
