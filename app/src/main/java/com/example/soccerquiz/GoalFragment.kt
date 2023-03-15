package com.example.soccerquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.soccerquiz.databinding.FragmentGoalBinding

class GoalFragment : Fragment() {
    lateinit var binding: FragmentGoalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_goal, container, false)
        binding.goalButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_goalFragment_to_quizFragment)
        }
        (activity as AppCompatActivity).supportActionBar?.title = "Soccer Quiz"
        return binding.root
    }
}