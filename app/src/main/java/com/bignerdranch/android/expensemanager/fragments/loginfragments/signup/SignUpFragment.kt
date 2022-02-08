package com.bignerdranch.android.expensemanager.fragments.loginfragments.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.expensemanager.R
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class SignUpFragment : Fragment() {
    lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val username: EditText = view.signup_username_edit_text
        val password: EditText = view.signup_password_edit_text
        val confirmPassword: EditText = view.confirm_password_edit_text

        view.sign_up_button.setOnClickListener {
            if ((!checkEmptiness(username, password, confirmPassword)) &&
                checkPasswordSimilarity(password, confirmPassword)) {
                user = User(username.toString(), password.toString())

                Toast.makeText(requireContext(), R.string.successful_signup_toast, Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            } else if (checkEmptiness(username, password, confirmPassword)) {
                Toast.makeText(requireContext(), R.string.empty_cells_toast, Toast.LENGTH_SHORT).show()
            } else if (!checkPasswordSimilarity(password, confirmPassword)) {
                Toast.makeText(requireContext(), R.string.unmatched_passwords_toast, Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun checkEmptiness(username: EditText,
                               password: EditText,
                               confirmPassword: EditText): Boolean {
        return username.text.isEmpty() || password.text.isEmpty() || confirmPassword.text.isEmpty()
    }

    private fun checkPasswordSimilarity(password: EditText, confirmPassword: EditText): Boolean {
        return password.text.toString() == confirmPassword.text.toString()
    }
}