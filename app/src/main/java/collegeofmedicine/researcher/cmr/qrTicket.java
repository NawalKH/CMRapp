package collegeofmedicine.researcher.cmr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class qrTicket extends AppCompatActivity {

    String key;
    String QRcode;
    String name;
    event event1;
    ImageButton QRimg;
    TextView person;
    TextView eventTitle;
    TextView location;
    TextView time;
    String dd;
    ProgressBar loading;
    Boolean found;


    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    DatabaseReference child = db.child("Tickets");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_ticket);


            event1 = (event) getIntent().getSerializableExtra("event");
            name = getIntent().getStringExtra("name");
            QRcode = getIntent().getStringExtra("Email");
            key = event1.getKey();


            person = (TextView) findViewById(R.id.name);
            eventTitle = (TextView) findViewById(R.id.title);
            location = (TextView) findViewById(R.id.location);
            time = (TextView) findViewById(R.id.Time);
            dd = "Start Time : " + event1.getStartDate() + " -- " + event1.getStartTime() + " \nEnd Time : " + event1.getEndDate() + " -- " + event1.getEndTime();
            loading = (ProgressBar) findViewById(R.id.loading3);

            QRimg = (ImageButton) findViewById(R.id.QRimg);

        generateQRcode();


    }

    public void generateQRcode() {

        /*
        child.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(key)) {

                    //Event is Exist
                    child.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {


                            //:if Qr code exist .. loop until qr is unique
                            if(snapshot.hasChild(QRcode){

                                // your'e already rigistred in this event
                                //show my ticket

                          }



                            // UNIQUE
                            */

                                person.setText(name);
                                eventTitle.setText(event1.getName());
                                location.setText(event1.getLocation());
                                time.setText(dd);

                                QRCodeWriter writer = new QRCodeWriter();
                                try {
                                    BitMatrix bitMatrix = writer.encode(QRcode, BarcodeFormat.QR_CODE, 512, 512);
                                    int width = bitMatrix.getWidth();
                                    int height = bitMatrix.getHeight();
                                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                                    for (int x = 0; x < width; x++) {
                                        for (int y = 0; y < height; y++) {
                                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                                        }
                                    }

                                    QRimg.setImageBitmap(bmp);
                                    loading.setVisibility(View.GONE);


                                } catch (WriterException e) {
                                    e.printStackTrace();
                                }


                                //Save the Ticket in database

        found = getIntent().getExtras().getBoolean("found");
        if(!found){
            DatabaseReference Ticket = db.child("Tickets").child(key).child(name);
            Ticket.setValue(QRcode);
        }

        /*


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) { }
                    });




                }
                else {
                    // TODO: handle the case where the data does not yet exist
                    //:Event dosen't exist
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        */

    }


    public void onBackPressed(){
        Intent intent = new Intent(qrTicket.this, eventInfo.class);
        intent.putExtra("event", event1);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        qrTicket.this.finish();
    }


}
