package com.example.soccerquiz

class QuizItem {
    var question: String
    var answerList: List<String>

    constructor(question: String, answerList: List<String>) {
        this.question = question
        this.answerList = answerList
    }
}