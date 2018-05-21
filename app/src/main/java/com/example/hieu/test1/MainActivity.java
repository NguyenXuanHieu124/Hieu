package com.example.hieu.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button btn;
    public boolean d;
    private TextView tv2;
    private EditText edt;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Myref = database.getReference("Hello");
        Myref.setValue("Xin Chao Android");*/
        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("TyGia").setValue("22000");


    }
    private void connectView(){
        tv = (TextView)findViewById(R.id.tv1);
        btn = (Button) findViewById(R.id.bt0);
        tv2 = (TextView) findViewById(R.id.tv2);
        edt = (EditText)findViewById(R.id.edt) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeThing();
                d = !d;
            }
        });
    }
    private void doSomeThing(){
        myRef.child("TyGia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tv.setText(dataSnapshot.getValue().toString());
                int vnd,tg;
                String gia ="";
                gia = tv.getText().toString();
                tg = Integer.valueOf(gia);
                vnd = Integer.parseInt(edt.getText().toString())*tg;
                tv2.setText(String.valueOf(vnd)+" VND");



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
