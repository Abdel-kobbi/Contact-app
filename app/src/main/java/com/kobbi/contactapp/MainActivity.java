package com.kobbi.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView contactList;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        contactList = findViewById(R.id.contactList);
        btnAdd = findViewById(R.id.btnAdd);

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Abdel", "0658545427"));
        contacts.add(new Contact("reda", "0658545427"));
        contacts.add(new Contact("halim", "0658545427"));

        ContactAdapter contactAdapter = new ContactAdapter(getApplicationContext(), R.layout.item_contact, contacts);

        contactList.setAdapter(contactAdapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddContactForm.class);
            startActivity(intent);
        });
    }
}