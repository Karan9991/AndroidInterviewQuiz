package com.test.quizexampleinterview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.test.quizexampleinterview.authentication.SignIn;

public class Presenter implements Contract.Presenter{

    private final Contract.View view;
    private Context context;

    public Presenter(Contract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void alertDialog(String title, String message, String positiveButton, String negativeButton, Context context, Class<?> cls) {
        AlertDialog.Builder b = new AlertDialog.Builder(context)
                .setTitle(title)
                .setPositiveButton(positiveButton, (dialog, which) -> navigateToAhead(cls))
                .setNegativeButton(negativeButton, (dialog, which) -> dialog.cancel())
                .setMessage(message);
        b.create().show();
    }

    @Override
    public void navigateToAhead(Class<?> cls) {
        context.startActivity(new Intent(context, cls));
    }

    @Override
    public void logout() {
        AlertDialog.Builder b = new AlertDialog.Builder(context)
                .setTitle("Sign Out")
                .setPositiveButton("Sign Out", (dialog, which) -> {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(context, SignIn.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    dialog.dismiss();
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
                .setMessage("Are you sure you would like to sign out?");
        b.create().show();
    }
}
