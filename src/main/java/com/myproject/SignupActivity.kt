package com.myproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.myproject.Models.User
import com.myproject.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user=User()
        binding.signUpbtn.setOnClickListener {
            if ((binding.filledTextField.editText?.text.toString() == "") or
                (binding.textInputLayout.editText?.text.toString() == "") or
                (binding.Password.editText?.text.toString() == "")
            ){
                Toast.makeText(this@SignupActivity,"please fill the all Information",Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.textInputLayout.editText?.text.toString(),
                    binding.Password.editText?.text.toString()
                    ).addOnCompleteListener {
                        result->
                        if (result.isSuccessful) {
                            Toast.makeText(
                                this@SignupActivity,
                                "Login Successfully",Toast.LENGTH_SHORT).show()
                            user.Name=binding.filledTextField.editText?.text.toString()
                            user.password=binding.Password.editText?.text.toString()
                            user.email=binding.textInputLayout.editText?.text.toString()
                            Firebase.firestore.collection("User").document(Firebase.auth.currentUser!!.uid).set(user).
                            addOnSuccessListener {
                                startActivity(Intent(this@SignupActivity,HomeActivity::class.java))
                            }

                        }else{
                            Toast.makeText(this@SignupActivity, result.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                }


            }
        }
        binding.login.setOnClickListener{
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))

            }
        }
    }