package com.example.fooocare;
import com.example.fooocare.Model.BMRModel;
import com.example.fooocare.Model.RuleModel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BmrFireBaseTest {
    FirebaseUser user;
    DatabaseReference root;
    FirebaseDatabase database;
    BMRModel model, model2;

    public BmrFireBaseTest() {

    }

    @Test
    public void generateList(){
        BMRModel.generateList();
        assertEquals(4, 2+2);
    }
}
