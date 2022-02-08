package com.bignerdranch.android.expensemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.bignerdranch.android.expensemanager.fragments.loginfragments.login.LoginFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupActionBarWithNavController(findNavController(R.id.login_nav_container))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.login_nav_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}


