package mahmoud.naji.lab3activitycomputing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var cunter:Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference()

        btnSave.setOnClickListener {
            var name = edtName.text.toString()
            var id = edtId.text.toString()
            var age = edtAge.text.toString()

            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )
            myRef.child("person").child("$cunter").setValue(person)
            cunter++
            Toast.makeText(applicationContext,"Success", Toast.LENGTH_LONG).show()


        }
        btnget.setOnClickListener {
            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val value = snapshot.getValue()
                    tvdata.text = value.toString()
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Failler", Toast.LENGTH_LONG).show()

                }
            })



}}}