package com.example.adviceapi

class Quiz(val questions: List<QuestionDataClass>) {
    var points = 0
    var currQ = 0


    fun moreQuestions():Boolean{
        if(currQ < questions.size-1){
            return true
        }
        return false
    }

    fun getCurrQ():String{
        return questions[currQ].question
    }

    fun getChoice(): List<String>{
        return questions[currQ].choices.keys.toList()
    }

    fun check(choice : String){

        points += questions[currQ].choices[choice] ?: 0
        currQ++


    }

}