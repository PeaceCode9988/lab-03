package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {
    interface EditCityDialogListener {
        void addCity(City city);
        void editCity(int positionselected, City changetothiscity);
    }
    private EditCityDialogListener listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditCityDialogListener) {
            listener = (EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }
    public static EditCityFragment newInstance(int whichpositiontochange) {
        Bundle args = new Bundle();
        args.putInt("position", whichpositiontochange);

        EditCityFragment fragment = new EditCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);

        EditText ChangetoeditCityName = view.findViewById(R.id.change_edit_text_city_text);
        EditText ChangetoeditProvinceName = view.findViewById(R.id.change_edit_text_province_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        Bundle args = getArguments();
        int whichpositiontochange = args.getInt("position");
        return builder
                .setView(view)
                .setTitle("Edit a city Information")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Change", (dialog, which) -> {




                    String cityName = ChangetoeditCityName.getText().toString();
                    String provinceName = ChangetoeditProvinceName.getText().toString();

                    listener.editCity(whichpositiontochange,new City(cityName, provinceName));
                })
                .create();
    }
}

