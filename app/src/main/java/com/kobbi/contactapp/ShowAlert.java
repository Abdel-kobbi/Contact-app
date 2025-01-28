package com.kobbi.contactapp;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class ShowAlert extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(requireActivity());
        alertBuilder.setTitle("Confirmation")
                .setMessage("Etes-vous sûr de vouloir supprimer ce contact ?")
                .setPositiveButton("Oui", (dialog, i) -> {
                    // delete Contact

                })
                .setNegativeButton("Non", (dialog, i) -> dialog.dismiss());

        return alertBuilder.create();
    }
}
