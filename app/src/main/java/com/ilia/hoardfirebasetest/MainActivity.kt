package com.ilia.hoardfirebasetest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    //
    private var database: FirebaseDatabase? = null
    private var listRootRef: DatabaseReference? = null
    var hoard: Hoard? = null
    private var editTextGold: EditText? = null
    private var editTextHoardName: EditText? = null
    private var txtMessage: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        editTextGold = findViewById<View>(R.id.editTextGold) as EditText
        editTextHoardName = findViewById<View>(R.id.editTextTextHoardName) as EditText
        txtMessage = findViewById<View>(R.id.txtMessage) as TextView
        //
        database = FirebaseDatabase.getInstance()
        listRootRef = database!!.getReference("hoards")
    }

    //
    fun sendAmount(view: View?) {
        //
        val hoardName = editTextHoardName!!.text.toString()
        val goldHoarded = editTextGold!!.text.toString().toInt()
        hoard = Hoard(
            hoardName,
            goldHoarded,
            true
        )
        val itemRootRef = listRootRef!!.child(hoard!!.hoardName.toString())
        // Set values for the properties of our hoard.
        itemRootRef.child("hoardName").setValue(hoard!!.hoardName)
        itemRootRef.child("goldHoarded").setValue(hoard!!.goldHoarded)
        itemRootRef.child("hoardAccessible").setValue(hoard!!.hoardAccessible)
        // Read from the database
        listRootRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val key = dataSnapshot.key
                val value = dataSnapshot.value.toString()
                txtMessage!!.text = "Value is: $value"
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        //
    }
}