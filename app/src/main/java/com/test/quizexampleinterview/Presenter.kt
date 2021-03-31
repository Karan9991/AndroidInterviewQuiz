package com.test.quizexampleinterview

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.test.quizexampleinterview.authentication.SignIn

class Presenter(private val view: Contract.View, private val context: Context) : Contract.Presenter {
    override fun alertDialog(title: String?, message: String?, positiveButton: String?, negativeButton: String?, context: Context?, cls: Class<*>?) {
        val b = AlertDialog.Builder(context)
                .setTitle(title)
                .setPositiveButton(positiveButton) { dialog: DialogInterface?, which: Int -> navigateToAhead(cls) }
                .setNegativeButton(negativeButton) { dialog: DialogInterface, which: Int -> dialog.cancel() }
                .setMessage(message)
        b.create().show()
    }

    override fun navigateToAhead(cls: Class<*>?) {
        context.startActivity(Intent(context, cls))
    }

    override fun logout() {
        val b = AlertDialog.Builder(context)
                .setTitle("Sign Out")
                .setPositiveButton("Sign Out") { dialog: DialogInterface, which: Int ->
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(context, SignIn::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    context.startActivity(intent)
                    (context as Activity).finish()
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int -> dialogInterface.dismiss() }
                .setMessage("Are you sure you would like to sign out?")
        b.create().show()
    }
}