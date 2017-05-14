package com.tricktekno.datasync;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.tricktekno.datasync.MainActivity.filesname;
import static com.tricktekno.datasync.MainActivity.filesurl;
import static com.tricktekno.datasync.MainActivity.mDownloadUrl;

public class ShowFileActivity extends AppCompatActivity {


    private ProgressDialog mProgressDialog;
   public static ArrayList<Uri> animalsNameList;

    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_show_file);

        // Get the reference of ListViewAnimals
        ListView animalList=(ListView)findViewById(R.id.listViewAnimals);

       // animalsNameList = new ArrayList<>();
       // getAnimalNames();
        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, filesname);
        // Set The Adapter
        animalList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        animalList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
            {

                Uri selectedAnimal=filesurl.get(position);
                String selectedfile=filesname.get(position);
                Toast.makeText(getApplicationContext(), "File Downloading...... : "+selectedfile,   Toast.LENGTH_LONG).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedAnimal.toString()));
                startActivity(browserIntent);
            }
        });
    }

    void getAnimalNames() {

        if (mDownloadUrl != null) {
            animalsNameList.add(mDownloadUrl);
        } else {
            animalsNameList.add(null);
            Toast.makeText(getApplicationContext(), "No file urls",   Toast.LENGTH_LONG).show();
        }
    }
}