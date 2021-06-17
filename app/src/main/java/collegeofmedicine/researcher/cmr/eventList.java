package collegeofmedicine.researcher.cmr;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Nawal on 7/20/2017.
 */

public class eventList extends Fragment {

    private ListView eListView;
    private MyEventAdapter mEventAdapter;
    ArrayList<event> mEventArray=  new ArrayList<event>();
    ProgressBar loaderView;

    //:get the whole database
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    //:get root "Events" from database
    DatabaseReference child = db.child("Events");



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {



        //: attach Event Listener to "Events" .. when the data changes or loaded for first time.


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override


            public void onChildAdded(DataSnapshot ds, String previousChildName) {
                    event obj = new event();

                if (ds.exists()) {

                    obj.setName(ds.child("name").getValue(String.class));
                    obj.setDescrip(ds.child("description").getValue(String.class));
                    obj.setStartDate(ds.child("startDate").getValue(String.class));
                    obj.setEndDate(ds.child("endDate").getValue(String.class));
                    obj.setStartTime(ds.child("startTime").getValue(String.class));
                    obj.setEndTime(ds.child("EndTime").getValue(String.class));
                    obj.setLocation(ds.child("location").getValue(String.class));
                    obj.setnDate(ds.child("dateNotefication").getValue(String.class));
                    obj.setPic(ds.child("img").getValue(String.class));
                    obj.setOrgEmail(ds.child("emailOrg").getValue(String.class));
                    obj.setKey(ds.getKey());
                    mEventArray.add(obj);

                    //sorting the list by date
                    //Collections.sort(mEventArray);

                    showEvents();
                }

                // A new comment has been added, add it to the displayed list
                //Comment comment = dataSnapshot.getValue(Comment.class);
                // ...
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                //Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so displayed the changed comment.
                //Comment newComment = dataSnapshot.getValue(Comment.class);
                //String commentKey = dataSnapshot.getKey();

                // ...
            }


            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                //String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {


                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
               //Comment movedComment = dataSnapshot.getValue(Comment.class);
                //String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                //Toast.makeText(mContext, "Failed to load comments.",
                        //Toast.LENGTH_SHORT).show();
            }


        };

        child.addChildEventListener(childEventListener);



        /*
        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                showEvents(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        */
        return inflater.inflate(R.layout.eventlist,container,false);
    }


    private void showEvents() {

        eListView = (ListView) getView().findViewById(R.id.listview1);
        loaderView = (ProgressBar) getView().findViewById(R.id.loading2);

        mEventAdapter = new MyEventAdapter(getActivity().getApplicationContext(), R.layout.row, mEventArray);


        if (eListView != null) {
            eListView.setAdapter(mEventAdapter);
            loaderView.setVisibility(View.GONE);
        }



        //Animation
        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),  R.anim.push_up_in);
        eListView.startAnimation(animation);




        eListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {



                if(mEventArray.get(i).getName().equals("CITI Program"))
                {
                    Intent intent = new Intent(getActivity(), web.class);
                    intent.putExtra("PDF", mEventArray.get(i).getLocation());
                    startActivity(intent);
                    getActivity().finish();

                }
                else {
                    Intent intent = new Intent(getActivity(), eventInfo.class);
                    intent.putExtra("event", mEventArray.get(i));
                    startActivity(intent);
                    getActivity().finish();
                }
            }

        });


    }

}
