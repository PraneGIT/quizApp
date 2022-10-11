package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class quizquestions : AppCompatActivity(),View.OnClickListener {
    private var correct_answers:Int=0
    private var mcurrentposition:Int=1;
    private var musername:String?=null
    private var mquestionlist:ArrayList<question>?=null
    private var mselectedoption:Int=0
    private var progressBar:ProgressBar?=null
    private var tvProgressBar:TextView?=null
    private var tvQuestion:TextView?=null
    private var tvImage:ImageView?=null
    private var optionone:TextView?=null
    private var optiontwo:TextView?=null
    private var optionthree:TextView?=null
    private var optionfour:TextView?=null
    private var btnSubmit:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizquestions)
        progressBar =findViewById(R.id.progressbar)
        tvProgressBar=findViewById(R.id.tv_bar)
        tvQuestion=findViewById(R.id.tv_Question)
        tvImage=findViewById(R.id.iv_question)
        optionone=findViewById(R.id.tv_optionone)
        optiontwo=findViewById(R.id.tv_optiontwo)
        optionthree=findViewById(R.id.tv_optionthree)
        optionfour=findViewById(R.id.tv_optionfour)
        btnSubmit=findViewById(R.id.btnSubmit)
        mquestionlist=constant.getQuestions()
        optionone?.setOnClickListener(this)
        optiontwo?.setOnClickListener(this)
        optionthree?.setOnClickListener(this)
        optionfour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
        setQuestions()
    }

    private fun setQuestions() {
        val question: question = mquestionlist!![mcurrentposition - 1]
        tvImage?.setImageResource(question.image)
        progressBar?.progress = mcurrentposition
        tvProgressBar?.text = "${mcurrentposition}/4"
        tvQuestion?.text = question.question
        optionone?.text = question.option1
        optiontwo?.text = question.option2
        optionthree?.text = question.option3
        optionfour?.text = question.option4
        if(mcurrentposition==mquestionlist!!.size){
            btnSubmit?.text="Finish"
        }else{
            btnSubmit?.text="Submit"
        }
    }
    private fun defaultOptionview(){
        val options=ArrayList<TextView>()
        optionone?.let {
            options.add(0,it)
        }
        optiontwo?.let {
            options.add(1,it)
        }
        optionthree?.let {
            options.add(2,it)
        }
        optionfour?.let {
            options.add(3,it)
        }
        for(option in options){
            option?.setTextColor(Color.parseColor("#7A8089"))
            option?.typeface= Typeface.DEFAULT
            option?.background=ContextCompat.getDrawable(this,R.drawable.default_option_bg)
        }
    }
    private fun selectedoptionview(tv:TextView,selectedoptnum:Int){
        defaultOptionview()
        mselectedoption= selectedoptnum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,R.drawable.selected_opiton_border_bg)

    }
    override fun onClick(view: View?) {
        musername=intent.getStringExtra(constant.username)
        when(view?.id){
            R.id.tv_optionone->{
                optionone?.let {
                    selectedoptionview(it,1)
                }
            }
            R.id.tv_optiontwo->{
                optiontwo?.let {
                    selectedoptionview(it,2)
                }
            }
            R.id.tv_optionthree->{
                optionthree?.let {
                    selectedoptionview(it,3)
                }
            }
            R.id.tv_optionfour->{
                optionfour?.let {
                    selectedoptionview(it,4)
                }
            }
            R.id.btnSubmit->{
                if(mselectedoption==0){
                    mcurrentposition+=1
                    defaultOptionview()
                    when{
                        mcurrentposition<=mquestionlist!!.size->{
                            setQuestions()
                        }
                        else->{
                                val intent=Intent(this,finish_layout::class.java)
                                 intent.putExtra(constant.username,musername)
                                 intent.putExtra(constant.total_correct_answers,correct_answers)
                                 intent.putExtra(constant.total_questions,mquestionlist!!.size)
                            startActivity(intent)
                                  finish()
                        }
                    }
                }else{
                    val question=mquestionlist?.get(mcurrentposition-1)
                    if(question!!.correctAnswer!=mselectedoption){
                        answerView(mselectedoption,R.drawable.wrong_option_border_bg)
                    }else{
                        correct_answers+=1
                    }
                    answerView(question!!.correctAnswer,R.drawable.correct_option_border_bg)
                    if(mcurrentposition==mquestionlist!!.size){
                        btnSubmit?.text="Finish"
                    }else{
                        btnSubmit?.text="Next"
                    }
                    mselectedoption=0
                }
            }
        }
    }
    private fun answerView(answer:Int,drawableView:Int){
        when(answer){
            1->{
                optionone?.background=ContextCompat.getDrawable(this,drawableView)
            }
            2->{
                optiontwo?.background=ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                optionthree?.background=ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                optionfour?.background=ContextCompat.getDrawable(this,drawableView)
            }
        }
    }
}