package com.example.myproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_2 extends Fragment {

    private View view;
    private EditText txt_name, txt_pass;
    private Button btn_register;
    private Context context;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    public Register_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  (View)inflater.inflate(R.layout.fragment_register_2, container, false);
        setHasOptionsMenu(true);
        context = view.getContext();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // anh xa
        txt_name = view.findViewById(R.id.txt_name);
        txt_pass = view.findViewById(R.id.txt_pass);
        btn_register = view.findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = txt_name.getText().toString();
                final String pass = txt_pass.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(name, pass)
                        .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    // create user sucess
                                    Navigation.findNavController(view).navigate(R.id.action_register_2_to_person_2);
                                }
                                else
                                {
                                    // fails
                                    Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        return view;
    }
}