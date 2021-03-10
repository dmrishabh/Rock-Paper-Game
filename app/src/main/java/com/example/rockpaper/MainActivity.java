package com.example.rockpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.backup.FullBackupDataOutput;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootReference = firebaseDatabase.getReference();
    DatabaseReference gameRef = rootReference.child("GAME");

    TextView textView;
    Button btnRock,btnPaper,btnScissor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        rootReference.child("users").child("Name").setValue("sam");
//        rootReference.child("users").child("number").setValue("10");
        findViewById();

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("ROCK");
            }
        });
        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("Paper");
            }
        });
        btnScissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("Scissor");
            }
        });

    }

    private void findViewById() {
        textView=findViewById(R.id.textView);
        btnPaper=findViewById(R.id.paper);
        btnRock=findViewById(R.id.rock);
        btnScissor=findViewById(R.id.scissor);
    }
    @Override
    protected void onStart() {
        super.onStart();
        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            String string = snapshot.getValue().toString();
            textView.setText(string);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("tag", "Something is missing");
            }
        });
    }

}





























