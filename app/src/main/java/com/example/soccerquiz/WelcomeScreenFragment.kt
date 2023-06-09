package com.example.soccerquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.soccerquiz.databinding.FragmentWelcomeScreenBinding

class WelcomeScreenFragment : Fragment() {
    lateinit var binding: FragmentWelcomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome_screen, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Soccer Quiz"
        binding.button.setOnClickListener {
            view: View -> Navigation.findNavController(view).navigate(R.id.action_welcomeScreenFragment2_to_quizFragment)
        }
        return binding.root
    }
}