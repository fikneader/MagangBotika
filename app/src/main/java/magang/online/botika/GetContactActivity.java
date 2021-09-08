package magang.online.botika;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import jagerfield.mobilecontactslibrary.Contact.Contact;
import jagerfield.mobilecontactslibrary.ImportContacts;
import jagerfield.mobilecontactslibrary.ImportContactsAsync;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class GetContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_contact);

        new ImportContactsAsync(this, contactList -> {
            //Your code here
            // !contactList.get(i).getDisplaydName().equals(contactList.get(i-1).getDisplaydName())
            for (int i = 0; i < contactList.size();i++){
                if (contactList.get(i)!=null){
                    for (int y = 0; y < contactList.get(i).getNumbers().size();y++){
                        if (y>0){
                            if (!contactList.get(i).getNumbers().get(y).getNormalizedNumber().isEmpty()){
                                if (!contactList.get(i).getNumbers().get(y).getNormalizedNumber().equals(contactList.get(i).getNumbers().get(y-1).getNormalizedNumber())){
                                    if (!contactList.get(i).getNumbers().get(y).getNormalizedNumber().equals(" ")){
                                        Log.d("BotikaDebug",contactList.get(i).getDisplaydName() + " - number : " + contactList.get(i).getNumbers().get(y).getNormalizedNumber());
                                    }
                                }
                            }
                        } else if (!contactList.get(i).getDisplaydName().equals(contactList.get(i-1).getDisplaydName())) {
                            if (!contactList.get(i).getNumbers().get(y).getNormalizedNumber().isEmpty()) {
                                Log.d("BotikaDebug", contactList.get(i).getDisplaydName() + " - number : " + contactList.get(i).getNumbers().get(y).getNormalizedNumber());
                            }
                        }
                    }
                }
            }
        }).execute();

    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
    }
}