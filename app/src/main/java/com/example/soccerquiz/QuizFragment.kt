package com.example.soccerquiz

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.soccerquiz.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    lateinit var binding: FragmentQuizBinding
    val quizItem: MutableList<QuizItem> = mutableListOf(
        QuizItem("How many players does each team have on the pitch when a soccer match starts?", listOf("11","8","12")),
        QuizItem("What should be the circumference of a Size 5 (adult) football?", listOf("27\" to 28\"","24\" to 25\"","23\" to 24\"")),
        QuizItem("What is given to a player for a very serious personal foul on an opponent?", listOf("Red Card","Green Card","Yellow Card")),
        QuizItem("To most places in the world, soccer is known as what?", listOf("Football","Footgame","Legball")),
        QuizItem("Offside. If a player is offside, what action does the referee take?", listOf("Awards an indirect free kick to the opposing team","Awards a penalty to the opposing team","Awards a yellow card  to the player")),
        QuizItem("How many laws of Association Football are there?", listOf("17","11","23")),
        QuizItem("Excluding the goalkeeper, what part of the body cannot touch the ball?", listOf("Arm","Head","Shoulder")),
        QuizItem("What is it called when a player, without the ball on the offensive team is behind the last defender, or fullback?", listOf("Offside","Outside","Field-side")),
        QuizItem("The Ball. The circumference of the ball should not be greater than what?", listOf("70","80","90")),
        QuizItem("How many minutes are played in a regular game (without injury time or extra time)?", listOf("90","95","100")),
        QuizItem("What statement describes a proper throw-in?", listOf("Both hands must be on the ball behind the head, both feet must be on the ground","Both hands must be on the ball behind the head","Both feet must be on the ground")),
        QuizItem("How big is a regulation official soccer goal?", listOf("2.44m high, 7.32m wide","2.55m high, 7.62m wide","2.33m high, 8.15m wide")),
        )
    lateinit var currentQuizItem: QuizItem
    lateinit var answers: MutableList<String>
    private var quizItemIndex: Int = 0
    private var numberOfQuestions: Int = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Soccer Quiz"
        getRandomQuizItem()
        binding.quizFragment = this
        setPassOnCliked()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    fun setPassOnCliked() {
        binding.button2.setOnClickListener { view: View ->
            val selectedCheckboxId = binding.quizRadioGroup.checkedRadioButtonId
            if (selectedCheckboxId != -1) {
                var answerIndex = 0
                when(selectedCheckboxId) {
                    R.id.firstRadioButton -> answerIndex = 0
                    R.id.secondRadioButton -> answerIndex = 1
                    R.id.thirdRadioButton -> answerIndex = 2
                }
                if (answers[answerIndex] == currentQuizItem.answerList[0]) {
                    quizItemIndex++
                    if (quizItemIndex < numberOfQuestions) {
                        setQuizItem()
                        binding.invalidateAll()
                    } else {
                        view.findNavController().navigate(R.id.action_quizFragment_to_goalFragment)
                    }
                } else {
                    view.findNavController().navigate(R.id.action_quizFragment_to_missFragment)
                }
            }
        }
    }

    private fun getRandomQuizItem() {
        quizItem.shuffle()
        quizItemIndex = 0
        setQuizItem()
    }

    private fun setQuizItem() {
        currentQuizItem = quizItem[quizItemIndex]
        answers = currentQuizItem.answerList.toMutableList()
        answers.shuffle()
    }
}