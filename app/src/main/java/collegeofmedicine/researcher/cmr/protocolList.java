package collegeofmedicine.researcher.cmr;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by Hissa on 11/08/17.
 */

public class protocolList extends Fragment {

    private ListView pListView;
    private MyProtocolAdapter mProtocolAdapter;
    ArrayList<Protocol> mProtocolArray=  new ArrayList<Protocol>();
    ProgressBar loaderView;

    //:get the whole database
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    //:get root "Events" from database
    DatabaseReference child = db.child("Protocols");


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.protocol_activity, container, false);



        ChildEventListener childEventListener = new ChildEventListener() {
            @Override

            public void onChildAdded(DataSnapshot ds, String previousChildName) {
                Protocol obj = new Protocol();

                if (ds.exists()) {

                    obj.setTitle(ds.child("name").getValue(String.class));
                    obj.setPath(ds.child("ProtocolFile").getValue(String.class));
                    mProtocolArray.add(obj);

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


        return view;
    }


    private void showEvents() {


        pListView = (ListView) getView().findViewById(R.id.protocolsList);
        loaderView = (ProgressBar) getView().findViewById(R.id.loading);

        mProtocolAdapter = new MyProtocolAdapter (getActivity().getApplicationContext(), R.layout.protocol_row, mProtocolArray);


        if (pListView != null) {
            pListView.setAdapter(mProtocolAdapter);
            loaderView.setVisibility(View.GONE);
        }



        //Animation
        //Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),  R.anim.push_up_in);
        //eqListView.startAnimation(animation);


        pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {

                Intent intent = new Intent(getActivity(), web.class);
                intent.putExtra("PDF", mProtocolArray.get(i).getPath());
                startActivity(intent);
                getActivity().finish();
            }

        });


    }


}
