package com.example.springbootcrud.service;

import com.example.springbootcrud.model.User;
import com.google.firebase.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FirebaseService {

    @Autowired
    private FirebaseDatabase firebaseDatabase;

    private static final String USERS_COLLECTION = "users";

    // Lấy danh sách tất cả users
    public CompletableFuture<List<User>> getAllUsers() {
        CompletableFuture<List<User>> future = new CompletableFuture<>();
        
        DatabaseReference usersRef = firebaseDatabase.getReference(USERS_COLLECTION);
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<User> users = new ArrayList<>();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user != null) {
                        user.setId(userSnapshot.getKey());
                        users.add(user);
                    }
                }
                future.complete(users);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeException(error.toException());
            }
        });
        
        return future;
    }

    // Lấy user theo ID
    public CompletableFuture<User> getUserById(String id) {
        CompletableFuture<User> future = new CompletableFuture<>();
        
        DatabaseReference userRef = firebaseDatabase.getReference(USERS_COLLECTION).child(id);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    user.setId(snapshot.getKey());
                }
                future.complete(user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                future.completeException(error.toException());
            }
        });
        
        return future;
    }

    // Thêm user mới
    public CompletableFuture<String> createUser(User user) {
        CompletableFuture<String> future = new CompletableFuture<>();
        
        DatabaseReference usersRef = firebaseDatabase.getReference(USERS_COLLECTION);
        DatabaseReference newUserRef = usersRef.push();
        
        newUserRef.setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    future.completeException(error.toException());
                } else {
                    future.complete(ref.getKey());
                }
            }
        });
        
        return future;
    }

    // Cập nhật user
    public CompletableFuture<Void> updateUser(String id, User user) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        
        DatabaseReference userRef = firebaseDatabase.getReference(USERS_COLLECTION).child(id);
        user.setUpdatedAt(java.time.LocalDateTime.now());
        
        userRef.setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    future.completeException(error.toException());
                } else {
                    future.complete(null);
                }
            }
        });
        
        return future;
    }

    // Xóa user
    public CompletableFuture<Void> deleteUser(String id) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        
        DatabaseReference userRef = firebaseDatabase.getReference(USERS_COLLECTION).child(id);
        userRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    future.completeException(error.toException());
                } else {
                    future.complete(null);
                }
            }
        });
        
        return future;
    }
}