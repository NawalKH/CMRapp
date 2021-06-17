package collegeofmedicine.researcher.cmr;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ticketForm extends AppCompatActivity {

    String name;
    String Email;
    event event1;
    String key;
    ValueEventListener g;
    ValueEventListener f;

    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    DatabaseReference child = db.child("Tickets");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_form);

        event1 = (event) getIntent().getSerializableExtra("event");
        key= event1.getKey();

        //TextView title = (TextView) findViewById(R.id.eventTitle);
        //title.setText(event1.getName());

        Button button = (Button) findViewById(R.id.getTicket);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v){

                EditText name1 = (EditText) findViewById(R.id.name);
                EditText email1 = (EditText) findViewById(R.id.Email);

                name = name1.getText().toString();
                Email = email1.getText().toString();

                //Retrieve the Event tree
                final Query query = child.orderByChild(key);

                 f = query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        /*if (snapshot.hasChild(key)) {
*/

                        //Retrieve the Email node
                        final Query query2 = db.child("Tickets").child(key).orderByValue().equalTo(Email);
                            //child.child(key)
                         g = query2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {

                                    if(snapshot.exists())
                                    {
                                        for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                                            name = childSnapshot.getKey();
                                        }

                                        //name = snapshot.getValue(String.class);

                                     /*

                                    boolean found = false;
                                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                        if (Email.equals(childSnapshot.getValue(String.class))) {
                                            found = true;
                                            name = childSnapshot.getKey();
                                        }
                                    }

                                    if(found)
                                    */


                     AlertDialog.Builder builder1 = new AlertDialog.Builder(ticketForm.this,R.style.MyDialogTheme);
                     builder1.setMessage("You Already have a Ticket");
                     builder1.setCancelable(true);
                     builder1.setNeutralButton("Show My Ticket", new DialogInterface.OnClickListener() {

                                                    public void onClick(DialogInterface dialog, int id) {
                                                        Intent intent = new Intent(ticketForm.this, qrTicket.class);
                                                        intent.putExtra("event", event1);
                                                        intent.putExtra("name", name);
                                                        intent.putExtra("Email", Email);
                                                        intent.putExtra("found",true);
                                                        query2.removeEventListener(g);
                                                        query.removeEventListener(f);
                                                        startActivity(intent);
                                                        dialog.dismiss();
                                                        ticketForm.this.finish();


                                                    }
                                                });

                                        AlertDialog alert11 = builder1.create();
                                        alert11.show();

                                        }
                                        else {

                                            Intent intent = new Intent(ticketForm.this, qrTicket.class);
                                            intent.putExtra("event", event1);
                                            intent.putExtra("name", name);
                                            intent.putExtra("Email", Email);
                                            intent.putExtra("found",false);
                                            query2.removeEventListener(g);
                                            query.removeEventListener(f);
                                            startActivity(intent);
                                            ticketForm.this.finish();

                                        }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });


                        // else {
                            //:Event dosen't exist
                        //}
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                sendNotification();

            }
        });


    }


    /**  setting up a notification, getting the date from database (admin sets the date) **/

    public void sendNotification() {

        String message1, title1;
        message1 = "You have an event \""+event1.getName()+"\" on "+event1.getStartDate()+" "+event1.getStartTime();
        title1 = "Event Reminder";
        Intent intent = new Intent(this , NotifyService.class);
        intent.putExtra("title", title1);
        intent.putExtra("message",message1);

        PendingIntent pendingIntent  = PendingIntent.getService(this, 0, intent, 0);

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        //Admin decides when the notification shows, (only the date, time will always be 12AM)
        calendar.setTime(event1.getN_date());

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC, calendar.getTimeInMillis() , pendingIntent);
        Log.d("ME", "Alarm started");

    }



}



