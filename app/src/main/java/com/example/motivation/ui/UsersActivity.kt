package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.motivation.insfra.MotivationConstants.KEY.USER_NAME
import com.example.motivation.R
import com.example.motivation.insfra.SecurityPreference
import com.example.motivation.databinding.ActivityUsersBinding
import com.example.motivation.insfra.MotivationConstants.KEY.EMPTY

class UsersActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(
        savedInstanceState: Bundle?,
    ) {
        super.onCreate(savedInstanceState)

        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSave.setOnClickListener(this)
        validateName()
    }

    private fun validateName() {
        val name = SecurityPreference(this).getString(USER_NAME)
        if (name != EMPTY) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.bt_save) {
            handleSave()
        }
    }

    private fun handleSave() {

        val name = binding.etName.text.toString()
        if (name != EMPTY) {
            SecurityPreference(this).storeString(USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}