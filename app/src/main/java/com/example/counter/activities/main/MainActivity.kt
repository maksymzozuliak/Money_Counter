package com.example.counter.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.counter.*
import com.example.counter.activities.addpurchase.AddPurchase
import com.example.counter.activities.changebalance.ChangeBalance
import com.example.counter.activities.history.History
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppPreferences.setup(applicationContext)

        auth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if(account != null){
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            } catch (e: ApiException){
                Log.d("MyLog", "Ошибка")
            }
        }
        sign_in_button.setOnClickListener{
            signInWithGoogle()
        }

        current_balance_show.text = AppPreferences.balance.toString()

        open_changebalance_button.setOnClickListener {
            val intent = Intent(this, ChangeBalance::class.java)
            startActivity(intent)
        }

        open_history_button.setOnClickListener {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }

        open_addpurchase_button.setOnClickListener {
            val intent = Intent(this, AddPurchase::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        current_balance_show.text = AppPreferences.balance.toString()
    }

    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }

    private fun signInWithGoogle(){
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken : String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful) {
                Log.d("MyLog", "Получилось")
            }
            else {
                Log.d("MyLog", "Ошибка")
            }
        }

    }
}