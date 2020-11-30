package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView nom,numero,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nom=findViewById(R.id.nom);
        numero=findViewById(R.id.numero);
        description=findViewById(R.id.description);
        Object c=getIntent().getSerializableExtra("contact");
        Contact contact=(Contact)c;
        nom.setText("  "+contact.getNom());
        numero.setText("  "+contact.getNumero());
        description.setText("  "+contact.getDescription());


    }
}