package com.example.adviceapi


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adviceapi.databinding.QuizBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class QuizActivity : AppCompatActivity() {
    companion object{
        val EXTRA_QUIZ = "quiz"
    }

    private lateinit var binding: QuizBinding

    private lateinit var choice1 : Button
    private lateinit var choice2 : Button
    private lateinit var choice3 : Button
    private lateinit var choice4 : Button


    private lateinit var query : TextView
    private lateinit var choicesGroup : Group

    private lateinit var ans1: Button
    private lateinit var ans2: Button
    private lateinit var group2 : Group
    private lateinit var question2 : TextView

    private lateinit var choice5: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.quiz_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var gson = Gson()
        val inputStream = resources.openRawResource(R.raw.quiz)
        val jsonString = inputStream.bufferedReader().use{
            it.readText()
        }

        wireWidgets()

        val type = object : TypeToken<List<QuestionDataClass>>() { }.type
        val questions = gson.fromJson<List<QuestionDataClass>>(jsonString, type)

        var quiz = Quiz(questions)

        group2.visibility = View.GONE
        var choices = quiz.getChoice()

        choice1.text = choices[0]
        choice2.text = choices[1]
        choice3.text = choices[2]
        choice4.text = choices[3]
        choice5.text = choices[4]
        choicesGroup.visibility = View.VISIBLE
        query.text = quiz.getCurrQ()

        fun getNextQuestion(){
            if(quiz.moreQuestions()== false){
                val quizIntent = Intent(this, AdviceSlipActivity::class.java)

                startActivity(quizIntent)
            }
            else{

                choice5.visibility = View.GONE
                var choices = quiz.getChoice()
                if(choices.size == 4){
                    query.text = quiz.getCurrQ()
                    choice1.text = choices[0]
                    choice2.text = choices[1]
                    choice3.text = choices[2]
                    choice4.text = choices[3]
                }
                if(choices.size == 5){
                    choice5.visibility = View.VISIBLE
                    query.text = quiz.getCurrQ()
                    choice1.text = choices[0]
                    choice2.text = choices[1]
                    choice3.text = choices[2]
                    choice4.text = choices[3]
                    choice5.text = choices[4]
                }
                if(choices.size == 2){
                    choicesGroup.visibility = View.GONE
                    group2.visibility = View.VISIBLE
                    question2.text = quiz.getCurrQ()
                    ans1.text = choices[0]
                    ans2.text = choices[1]
                }

            }
        }

        choice1.setOnClickListener {
            quiz.check(choice1.text.toString())
            getNextQuestion()

        }

        choice2.setOnClickListener {
            quiz.check(choice2.text.toString())
            getNextQuestion()

        }

        choice3.setOnClickListener {
            quiz.check(choice3.text.toString())
            getNextQuestion()

        }

        choice4.setOnClickListener {
            quiz.check(choice4.text.toString())
            getNextQuestion()

        }

        choice5.setOnClickListener {
            quiz.check(choice5.text.toString())

            getNextQuestion()

        }

        ans1.setOnClickListener {
            quiz.check(ans1.text.toString())
            group2.visibility = View.GONE
            choicesGroup.visibility = View.VISIBLE
            getNextQuestion()

        }

        ans2.setOnClickListener {
            quiz.check(ans2.text.toString())
            group2.visibility = View.GONE
            choicesGroup.visibility = View.VISIBLE
            getNextQuestion()


        }


    }

    private fun wireWidgets(){
        choice1 = findViewById<Button>(R.id.choice1)
        choice2 = findViewById<Button>(R.id.choice2)
        choice3 = findViewById<Button>(R.id.choice3)
        choice4 = findViewById<Button>(R.id.choice4)
        query = findViewById<TextView>(R.id.question)

        choice5 = findViewById(R.id.choice5)


        choicesGroup = findViewById<Group>(R.id.fourQuestionGroup)
        group2 = findViewById(R.id.twoQuestionGroup)
        ans1 = findViewById(R.id.ans1)
        ans2 = findViewById(R.id.ans2)
        question2 = findViewById(R.id.group2Q)


    }




}