package collegeofmedicine.researcher.cmr;


import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import static android.content.ContentValues.TAG;

/**
 * Created by Hissa on 27/07/17.
 */

public class retrieveEvents {
    //:get the whole database
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    //:get root "Events" from database : it's like table "Events"
    DatabaseReference child = db.child("Events");

    ArrayList<event> mEventArray = new ArrayList<event>();



    public ArrayList<event> eventData(){
        //: attach Event Listener to "Events" .. when the data changes or loaded for first time.
        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //: handle the data in showEvents method .. P.S: you can handle it here without creating another method
                showEvents(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
        return mEventArray;
    }


    private void showEvents(DataSnapshot dataSnapshot) {
        //mEventArray.clear();
        //:loop over every child in Events to get its information.
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            event obj = new event();

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
        }
    }
}
