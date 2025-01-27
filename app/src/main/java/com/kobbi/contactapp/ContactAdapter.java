package com.kobbi.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resource;

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView contactName = (TextView) convertView.findViewById(R.id.contactName);
        TextView contactPhone = (TextView) convertView.findViewById(R.id.contactPhone);
        Contact currentContact = getItem(position);
        contactName.setText(currentContact.getName());
        contactPhone.setText(currentContact.getPhone());
        return convertView;
    }
}
