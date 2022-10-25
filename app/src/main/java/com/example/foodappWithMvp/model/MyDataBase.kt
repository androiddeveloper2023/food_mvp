package com.example.foodappWithMvp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class MyDataBase:RoomDatabase() {

    abstract val foodDao: FoodDao





    companion object{


        private var dataBase: MyDataBase?=null


        fun getDataBase(context:Context): MyDataBase {


            if (dataBase ==null){


                dataBase = Room.databaseBuilder(context.applicationContext, MyDataBase::class.java,"MyDataBase.db")






                    .allowMainThreadQueries()


                    .build()




            }




            return dataBase!!


        }













    }






}