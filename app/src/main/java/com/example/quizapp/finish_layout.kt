package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class finish_layout : AppCompatActivity() {
    private var username:String?=null
    private var total_correct_answers:Int?=null
    private var total_questions:Int?=null
    private var final_text:TextView?=null
    private var final_image:ImageView?=null
    private var final_score:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_layout)
        var btn_finish:Button=findViewById(R.id.btn_finish)
        username=intent.getStringExtra(constant.username.toString())
        total_correct_answers=intent.getIntExtra(constant.total_correct_answers.toString(),0)
        total_questions=intent.getIntExtra(constant.total_questions.toString(),0)
        final_text=findViewById(R.id.final_text)
        final_image=findViewById(R.id.final_image)
        final_score=findViewById(R.id.final_score)
        if(total_correct_answers==total_questions){
            final_text?.text=" damn ${username} kun is soo UwU,arara,kawaii"
           final_image?.setImageResource(R.drawable.congo)
        }else{
            final_text?.text="lmao what a loser is ${username}"
        }
        final_score?.text="final score is $total_correct_answers / $total_questions"
        btn_finish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }


}