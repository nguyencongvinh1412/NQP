package com.example.myproject;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class person_2 extends Fragment {

    private View view;
    private Context context;
    private FirebaseAuth mAuth;
    private TextView txtTenAcc;
    private TextView txtLogOut;

    public person_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

        // kiem tra trang thai dang nhap
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser == null)
        {
            // nhay den trang dang nhap
            Navigation.findNavController(view).navigate(R.id.action_person_2_to_login_2);
        }
        else
        {
            // update thong thi len form thong tin
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user != null)
            {
                txtTenAcc.setText(user.getEmail());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (View)inflater.inflate(R.layout.fragment_person_2, container, false);
        context = view.getContext();
        // bat su kien cac button khac
        txtLogOut = view.findViewById(R.id.txt_logOut);
        txtTenAcc = view.findViewById(R.id.txt_Ten_Accout);
        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Navigation.findNavController(view).navigate(R.id.action_person_2_to_login_2);
            }
        });

        return  view;
    }
}