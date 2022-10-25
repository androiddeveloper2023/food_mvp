package com.example.foodappWithMvp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodappWithMvp.R
import com.example.foodappWithMvp.model.Food
import kotlin.collections.ArrayList

class FoodAdapter(private val data:ArrayList<Food>, val foodEvents: FoodEvents):RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {


    inner class FoodViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val img = itemview.findViewById<ImageView>(R.id.imageView)
        val txt_foodname = itemview.findViewById<TextView>(R.id.txtsubject)
        val txt_foodcity = itemview.findViewById<TextView>(R.id.textView2)
        val txt_foodprice = itemview.findViewById<TextView>(R.id.textView3)
        val txt_fooddistance = itemview.findViewById<TextView>(R.id.textView4)
        val rating_star = itemview.findViewById<RatingBar>(R.id.ratingBar)
        var numofrating = itemview.findViewById<TextView>(R.id.textView5)

        @SuppressLint("SetTextI18n")
        fun bindViews(position: Int){
            txt_foodname.text = data[position].Txt_foodname
            txt_foodcity.text = data[position].Txt_foodcity
            txt_foodprice.text = "$ " + data[position].Txt_foodprice + " vip"
            txt_fooddistance.text = data[position].Txt_fooddistance + " miles from you"
            rating_star.rating = data[position].Rating_star
            numofrating.text = "( " + data[position].Numofrating.toString() + " Ratings )"
Glide.with(itemView)
    .load(data[position].Img)
    .into(img)




     itemView.setOnClickListener {

         foodEvents.onFoodClicked(data[adapterPosition],adapterPosition)



     }


            itemView.setOnLongClickListener {

                foodEvents.onFoodLongClicked(data[adapterPosition],adapterPosition)



                true
            }






        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
val view=LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)

        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bindViews(position)
    }

    override fun getItemCount(): Int {
        return data.size

    }


    fun addNewFood(newFood: Food){

        data.add(0,newFood)


        notifyItemInserted(0)




    }
fun deleteFood(oldFood: Food, position: Int){

    data.remove(oldFood)

    notifyItemRemoved(position)


}


    fun updateNewFood(food: Food, position: Int){


        data.set(position,food)

        notifyItemChanged(position)




    }


    fun searchData(searchFood:ArrayList<Food>){


        data.clear()

        data.addAll(searchFood)


        notifyDataSetChanged()





    }








    interface FoodEvents{

     fun   onFoodClicked(food: Food, position: Int)

     fun onFoodLongClicked(food: Food, position: Int)



    }
}