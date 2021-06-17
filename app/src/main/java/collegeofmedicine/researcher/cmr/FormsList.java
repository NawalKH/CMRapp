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


public class FormsList extends Fragment {

    private ListView fListView;
    private MyFormAdapter mFormAdapter;
    ArrayList<Form> mFormArray=  new ArrayList<Form>();
    ProgressBar loaderView;

    //:get the whole database
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    //:get root "Events" from database
    DatabaseReference child = db.child("Forms");



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.form_activity, container, false);



        final View button = view.findViewById(R.id.s_btn);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                /* DO SOMETHING UPON THE CLICK */
                Intent intent = new Intent(getActivity(),FormUpload.class);
                        startActivity(intent);
                    }
                }
        );



        ChildEventListener childEventListener = new ChildEventListener() {
            @Override


            public void onChildAdded(DataSnapshot ds, String previousChildName) {
                Form obj = new Form();

                if (ds.exists()) {

                    obj.setTitle(ds.child("name").getValue(String.class));
                    obj.setPath(ds.child("FormFile").getValue(String.class));
                    mFormArray.add(obj);

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


        fListView = (ListView) getView().findViewById(R.id.listview_f);
         loaderView = (ProgressBar) getView().findViewById(R.id.loading2);

        mFormAdapter = new MyFormAdapter (getActivity().getApplicationContext(), R.layout.form_row, mFormArray);


        if (fListView != null) {
            fListView.setAdapter(mFormAdapter);
            loaderView.setVisibility(View.GONE);
        }



        //Animation
        //Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),  R.anim.push_up_in);
        //eqListView.startAnimation(animation);


        fListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {

                Intent intent = new Intent(getActivity(), web.class);

               intent.putExtra("PDF", mFormArray.get(i).getPath());
                startActivity(intent);
                getActivity().finish();
            }

        });


    }


}
