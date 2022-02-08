package com.bignerdranch.android.expensemanager.fragments.loginfragments.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.expensemanager.MainActivity
import com.bignerdranch.android.expensemanager.R
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {
    private val defaultUser = "user"
    private val defaultPassword = "password"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val username: EditText = view.login_username_edit_text
        val password: EditText = view.login_password_edit_text

        view.login_button.setOnClickListener {
            if (validateUsername(username) &&
                validatePassword(password)) {
                Toast.makeText(requireContext(), R.string.successful_login_toast, Toast.LENGTH_SHORT).show()

                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            } else if (checkEmptiness(username, password)) {
                Toast.makeText(requireContext(), R.string.empty_cells_toast, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), R.string.invalid_user_toast, Toast.LENGTH_SHORT).show()
            }
        }

        view.login_sign_up_button.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        return view
    }

    private fun validateUsername(username: EditText): Boolean {
        return username.text.toString() == defaultUser
    }

    private fun validatePassword(password: EditText): Boolean {
        return password.text.toString() == defaultPassword
    }

    private fun checkEmptiness(username: EditText, password: EditText): Boolean {
        return username.text.isEmpty() || password.text.isEmpty()
    }
}