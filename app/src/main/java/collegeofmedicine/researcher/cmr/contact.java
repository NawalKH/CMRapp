package collegeofmedicine.researcher.cmr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }


    public void SendEmail(View view)

    {
        if(view.getId() == R.id.tvNumber3)
        {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            emailIntent.setType("vnd.android.cursor.item/email");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"cmrc.ksu@gmail.com"});
            startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
        }

        if(view.getId() == R.id.tvNumber4)
        {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            emailIntent.setType("vnd.android.cursor.item/email");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"salehrc@ksu.edu.sa"});
            startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
        }
    }

    public void goToweb(View view)
    {
        Intent intent = new Intent(contact.this, web.class);
        intent.putExtra("EXTRA_SESSION_ID", "http://www.ksucmrc.com");
        startActivity(intent);
    }
}
