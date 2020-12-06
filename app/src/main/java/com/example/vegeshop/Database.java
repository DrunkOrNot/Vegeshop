package com.example.vegeshop;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Database {

    private static DatabaseReference mDatabase;
    private static List<IUserDataChangeListener> listeners = new ArrayList<IUserDataChangeListener>();

    public static void PostData(Product product) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(product.ID).setValue(product);
    }

    public static void GetData(String productID) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Object query = mDatabase.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Product product = dataSnapshot.getValue(Product.class);
                for (IUserDataChangeListener listener : listeners) {
                    listener.onUserDataReceivedFromDatabase(product);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                for (IUserDataChangeListener listener : listeners) {
                    listener.onUserDataReceivedFromDatabase(null);
                }
            }
        });
    }

    public static void AddListener(IUserDataChangeListener listener) { listeners.add(listener); }
}
