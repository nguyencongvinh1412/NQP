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
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class Login extends Fragment {
    private View view;
    private EditText txt_name, txt_pass;
    // ImageView imgGmail;
    private Button btn_login, btn_register;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (View)inflater.inflate(R.layout.fragment_login, container, false);
        context = view.getContext();
        setHasOptionsMenu(true);
        FirebaseApp.initializeApp(context);

        // ánh xạ các compoment
        txt_name = view.findViewById(R.id.txt_name);
        txt_pass = view.findViewById(R.id.txt_pass);
        btn_login = view.findViewById(R.id.btn_login);
        btn_register = view.findViewById(R.id.btn_register);
        firebaseAuth = FirebaseAuth.getInstance();


        // đăng ký tài khoản mới
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lệnh nhảy sang fragment khác
                Navigation.findNavController(view).navigate(R.id.action_login_to_register);
            }
        });

        // login với tài khoản
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = txt_name.getText().toString();
                final String pass = txt_pass.getText().toString();
                if (name.trim().equals("") || pass.trim().equals("")) {
                    Toast.makeText(context, "Please enter information for userName and Password", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(name, pass)
                            .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // login sucess
                                        Bundle bundle = new Bundle();
                                        bundle.putString("name", name);
                                        bundle.putString("pass", pass);
                                        Navigation.findNavController(view).navigate(R.id.action_login_to_home2, bundle);
                                    } else {
                                        // failed
                                        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        return view;
    }
}