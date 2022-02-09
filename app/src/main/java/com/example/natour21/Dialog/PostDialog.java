package com.example.natour21.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.natour21.R;

public class PostDialog extends AppCompatDialogFragment {

    private EditText editTextDurata;
    private Spinner spinnerDifficoltà;
    private Button confirmButton, cancelButton;
    private PostDialogListener listener;
    TimePickerDialog timePickerDialog;
    int min;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);


        editTextDurata = view.findViewById(R.id.editTextTime);
        spinnerDifficoltà = (Spinner) view.findViewById(R.id.spinner);
        Integer[] items = new Integer[]{0,1,2,3,4,5};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinnerDifficoltà.setAdapter(adapter);

        confirmButton = view.findViewById(R.id.conferma_buttonDialog);

        cancelButton = view.findViewById(R.id.annulla_buttonDialog);



        editTextDurata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(getActivity(), R.style.TimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {

                        editTextDurata.setText(String.format("%02d:%02d",hour,minutes));
                        min=hour*60+minutes;
                    }
                },0,0,true);

                timePickerDialog.show();

            }
        });





        view.setBackgroundResource(android.R.color.transparent);
        builder.setView(view)
                .setTitle(Html.fromHtml("<font color='#BC6C25'>Modifica durata e/o difficoltà</font>"));




        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int difficulty = (Integer) spinnerDifficoltà.getSelectedItem();
                String duration = editTextDurata.getText().toString();
                int minutes = min;
                listener.applyChanges(difficulty,duration,minutes);



            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.close();
            }
        });


        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (PostDialogListener)getTargetFragment();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }

    public interface PostDialogListener{
        void applyChanges(int difficulty, String duration,int minutes);
        void close();
    }
}
