package com.kobbi.contactapp;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class ShowAlert extends DialogFragment {

    private final Contact contact;

    public ShowAlert(Contact contact) {
        this.contact = contact;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(requireActivity());
        alertBuilder.setTitle("Confirmation")
                .setMessage("Etes-vous sûr de vouloir supprimer ce contact ?")
                .setPositiveButton("Oui", (dialog, i) -> {
                    // delete Contact
                    new DbContact(requireContext()).deleteContact(contact);
                    Toast.makeText(requireContext(), "Le contact a été supprimé avec succès.", Toast.LENGTH_SHORT).show();
                    requireActivity().finish();
                })
                .setNegativeButton("Non", (dialog, i) -> dialog.dismiss());

        return alertBuilder.create();
    }
}
