package com.kobbi.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView contactList;
    private Button btnAdd;

    private DbContact db;
    private List<Contact> contacts;

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

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        db = new DbContact(getApplicationContext());

        btnAdd = findViewById(R.id.btnAdd);
        contactList = findViewById(R.id.contactList);
        View emptyView = findViewById(R.id.emptyView);
        contactList.setEmptyView(emptyView);

        setDataToList();

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddContact.class);
            startActivity(intent);
        });

        // add listener to contact list
        contactList.setOnItemClickListener((parent, view, position, id) -> {
            Contact contact = contacts.get(position);
            Intent intent = new Intent(getApplicationContext(), UpdateContact.class);
            intent.putExtra("contact", contact);
            startActivity(intent);

        });
    }

    private void setDataToList() {
        contacts = db.findAll();

        ContactAdapter contactAdapter = new ContactAdapter(getApplicationContext(), R.layout.item_contact, contacts);

        contactList.setAdapter(contactAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setDataToList();
    }
}