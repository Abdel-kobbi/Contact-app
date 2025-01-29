package com.kobbi.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateContact extends AppCompatActivity {

    private EditText name, phone;
    private Button btnUpdate;

    Contact contact;
    DbContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DbContact(getApplicationContext());

        name = findViewById(R.id.contactName);
        phone = findViewById(R.id.contactPhone);
        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");
        if (contact != null) {
            name.setText(contact.getName());
            phone.setText(contact.getPhone());
        }

        btnUpdate.setOnClickListener(v -> {
            String nameValue = name.getText().toString();
            String phoneValue = phone.getText().toString();
            if (nameValue.isEmpty() || phoneValue.isEmpty()) {
                return;
            }
            if (contact != null) {
                contact.setName(nameValue);
                contact.setPhone(phoneValue);
                db.updateContact(contact);
                finish();
                Toast.makeText(getApplicationContext(), "Le contact a été modifié avec succès.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.deleteContact) {
            new ShowAlert(contact).show(getSupportFragmentManager(), "");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}