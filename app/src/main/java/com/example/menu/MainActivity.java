package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE};
 ListView listView;
 Contact contactCourant=null;
 ArrayList<Contact> tous=new ArrayList<Contact>();
 Contact c1=new Contact("Anta thiam","778904570","Etudiante en Master 1 retel soir");
 Contact c2=new Contact("Oumar sow","776004570","Etudiant en Master 1 retel soir");
 Contact c3=new Contact("kine samb","778900500","Etudiante en Master 1 retel soir");
 Contact c4=new Contact("Ameth fall","778004570","Etudiant Master 1 retel soir");
 //vérification de la permission
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

 @Override
        protected void onCreate (Bundle savedInstanceState){
     if(!hasPermissions(this, PERMISSIONS)){
         ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
     }
     tous.add(c1);
     tous.add(c2);
     tous.add(c3);
     tous.add(c4);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            final String contacts[] = {c1.getNom(), c2.getNom(), c3.getNom(), c4.getNom()};
            listView = (ListView) findViewById(R.id.listView);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
            listView.setAdapter(adapter);

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   // Toast.makeText(MainActivity.this ,"ok:"+listView.getItemAtPosition(position),Toast.LENGTH_LONG).show();
                    for(Contact c : tous)
                    {
                        if(c.getNom().equals(listView.getItemAtPosition(position)))
                            contactCourant=c;
                    }
                    return false;
                }
            });
/*
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   // Toast.makeText(MainActivity.this ,"ok:"+listView.getItemAtPosition(i),Toast.LENGTH_LONG).show();
                 for(Contact c : tous)
                 {
                     if(c.getNom().equals(listView.getItemAtPosition(i)))
                         contactCourant=c;
                 }
                }
            });


 */
// Enregistrer la  ListView  dans le Context menu
     registerForContextMenu(listView);
        }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.setHeaderTitle("Get Action");
    }
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.call){
            Toast.makeText(getApplicationContext(),"Appel vers"+contactCourant.getNumero(),Toast.LENGTH_LONG).show();
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+contactCourant.getNumero()));//change the number
            startActivity(callIntent);

        }
        else if(item.getItemId()==R.id.info){
           // Toast.makeText(getApplicationContext(),"Send Infos",Toast.LENGTH_LONG).show();
           // Toast.makeText(getApplicationContext(),contactCourant.getDescription(),Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
            intent.putExtra("contact",contactCourant);
            startActivity(intent);

        }else{
            return false;
        }
        return true;
    }

    }
