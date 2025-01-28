package com.kobbi.contactapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddContact extends AppCompatActivity {

    Toolbar toolbar;

    EditText name, phone;
    Button btnAdd;

    DbContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DbContact(getApplicationContext());

        name = findViewById(R.id.contactName);
        phone = findViewById(R.id.contactPhone);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(v -> {
            String nameValue = name.getText().toString();
            String phoneValue = phone.getText().toString();
            if (nameValue.isEmpty() || phoneValue.isEmpty()) {
                return;
            }

            db.addContact(new Contact(nameValue, phoneValue));
            // clear editView
            name.setText("");
            phone.setText("");
            // show message
            Toast.makeText(getApplicationContext(), "Le contact a été ajouté avec succès.", Toast.LENGTH_SHORT).show();
        });


    }
}