package com.example.myproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends Fragment {
    private View view;
    private EditText txt_name, txt_pass;
    private Button btn_register;
    private Context context;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (View) inflater.inflate(R.layout.fragment_register, container, false);
        setHasOptionsMenu(true);
        context = view.getContext();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // anh xa
        txt_name = view.findViewById(R.id.txt_name);
        txt_pass = view.findViewById(R.id.txt_pass);
        btn_register = view.findViewById(R.id.btn_register);

        btn_register.setOnClickListener(v -> {
            final String name = txt_name.getText().toString();
            final String pass = txt_pass.getText().toString();
            firebaseAuth.createUserWithEmailAndPassword(name, pass).addOnCompleteListener((Activity) context, task -> {
                if (task.isSuccessful()){
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("pass", pass);
                    Navigation.findNavController(view).navigate(R.id.action_register_to_home2, bundle);
                }
                else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
                }
            });
        });
        return view;
    }
}