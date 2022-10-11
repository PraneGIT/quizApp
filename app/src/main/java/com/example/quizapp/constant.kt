package com.example.quizapp

object constant {
    const val username:String="username"
    const val total_questions:String="total questions"
    const val total_correct_answers:String="correct"
    fun getQuestions():ArrayList<question>{
        var questionslist= arrayListOf<question>()
        val question1=question(1,"who is the author of this famous manga ?",R.drawable.berr,
            "Junji Ito","Sui Ishida","kentaro Miura","Eichiro Oda",3)
        questionslist.add(question1)
        val question2=question(2,"who is the best girl in JJBA ?",R.drawable.jjba,
            "Elina Joestar","Joylene Joestar","Lisa Lisa","Speed Wagon",4)
        questionslist.add(question2)
        val question3=question(3,"what among the following Saitama DIDN'T do daily to get his powers",
        R.drawable.sai,"100 situps","10 KM run","100 pushups","1 masturbation",4)
        questionslist.add(question3)
        val question4=question(4,"what is the difference btw AOT and GOT",R.drawable.levi,"ladwa hai kya sale ","anime dekhi hai kya kabhi","jkb se puch","dono hi porn hai",3)
        questionslist.add(question4)
        return questionslist
    }
}