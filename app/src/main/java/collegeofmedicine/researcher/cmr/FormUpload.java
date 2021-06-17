package collegeofmedicine.researcher.cmr;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FormUpload extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    List<Uri> files = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_upload);
    }

    public void pickFile(View view) {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("*/*");

        startActivityForResult(intent, READ_REQUEST_CODE);

        }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {


        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                String s = uri.toString();

                TextView selectedF = (TextView) findViewById(R.id.selected);

                if(files!=null) {
                    files.clear();
                    selectedF.setText("");
                }

                File file = new File(s);
                files.add(uri);
                selectedF.append(file.getName()+"\n");

            }
        }
    }


    public void sendMessage(View view) {

        //Getting Subject and message from user's input
        EditText fmessage = (EditText) findViewById(R.id.f_message);
        EditText fTitle = (EditText) findViewById(R.id.f_title);
        String message,title;
        title = fTitle.getText().toString();

        if(fmessage.getText().toString().equals(""))
            message = "Sent from College Of Medicine Researcher App";

        else
            message =  fmessage.getText().toString();

        // setting up dialog box for the form title (it's required)
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this,R.style.MyDialogTheme);
        dlgAlert.setTitle("Form Submission");
        dlgAlert.setMessage("Please enter the form title");
        dlgAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });

        final ShareCompat.IntentBuilder intentBuilder = ShareCompat.IntentBuilder.from(this)
                .setType("text/html")
                .setChooserTitle("Choose application to send email")
                .addEmailTo("cmrc.ksu@gmail.com")
                .addEmailCc("cmrc.ksu@gmail.com")
                .setSubject(title)
                .setHtmlText(message);
        if (files != null) {
            for (Uri fileUri : files) {
                intentBuilder.addStream(fileUri); // add files attachment
            }
        }

        if (this.getPackageManager().resolveActivity(intentBuilder.getIntent(), 0) != null) {
            final Intent intent = intentBuilder.createChooserIntent();
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            if(!title.equals(""))
                this.startActivityForResult(intent, 0);
            else
                dlgAlert.show();
        }

    }

}
