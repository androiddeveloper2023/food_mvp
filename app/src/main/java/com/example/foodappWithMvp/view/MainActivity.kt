package com.example.foodappWithMvp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodappWithMvp.databinding.ActivityMainBinding
import com.example.foodappWithMvp.databinding.ItemDialogAddBinding
import com.example.foodappWithMvp.databinding.ItemPakkardanFoodBinding
import com.example.foodappWithMvp.databinding.ItemUpdatedialogBinding
import com.example.foodappWithMvp.model.Food
import com.example.foodappWithMvp.model.MyDataBase
import com.example.foodappWithMvp.presenter.MainScreenPresenter
import com.example.foodappWithMvp.view.FoodAdapter

import kotlin.collections.ArrayList

class MainScreenActivity : AppCompatActivity() , FoodAdapter.FoodEvents , MainScreenContract.view{
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: FoodAdapter


    private lateinit var present: MainScreenContract.presenter




    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
present= MainScreenPresenter(MyDataBase.getDataBase(this).foodDao)



        val sharedPrefrence = getSharedPreferences("bestfood", Context.MODE_PRIVATE)


        if (sharedPrefrence.getBoolean("firstRun", true)) {

present.firstRun()


            sharedPrefrence.edit().putBoolean("firstRun", false).apply()


        }

        present.onAttach(this)









        binding.btnRemoveall.setOnClickListener {


present.onClearAllFoodClicked()



        }






















        binding.edtsearch.addTextChangedListener { editTextInput ->
            present.onSearchFoodClicked(editTextInput.toString())
        }


        binding.btnadd.setOnClickListener {






    addFood()



}
    }

    override fun onDestroy() {
        super.onDestroy()


        present.onDetach()
    }







    private fun addFood() {

        val dialog = AlertDialog.Builder(this).create()

        val dialogbinding = ItemDialogAddBinding.inflate(layoutInflater)

        dialog.setView(dialogbinding.root)
        dialog.setCancelable(true)
        dialog.show()



        dialogbinding.dialogBtncancel.setOnClickListener {

            dialog.dismiss()
        }


        dialogbinding.dialogBtndone.setOnClickListener {

            if (dialogbinding.dialogEdtfoodname.length() > 0
                &&
                dialogbinding.dialogEdtfoodprice.length() > 0
                &&
                dialogbinding.dialogEdtfooddistance.length() > 0
                &&
                dialogbinding.dialogEdtfoodcity.length() > 0
                &&
                dialogbinding.dialogEdtfoodUrl.length() > 0
            ) {


                val txtnamefood = dialogbinding.dialogEdtfoodname.text.toString()
                val txtpricefood = dialogbinding.dialogEdtfoodprice.text.toString()
                val txtcityfood = dialogbinding.dialogEdtfoodcity.text.toString()
                val txtdiastancefood = dialogbinding.dialogEdtfooddistance.text.toString()
                val textratingstar: Float = (1..5).random().toFloat()
                val numofrating: Int = (1..2000).random().toInt()

                val url_pic = dialogbinding.dialogEdtfoodUrl.text.toString()


                val newfood = Food(
                    Txt_foodname = txtnamefood,
                    Txt_foodprice = txtpricefood,
                    Txt_fooddistance = txtdiastancefood,
                    Txt_foodcity = txtcityfood,
                    Img = url_pic,
                    Numofrating = numofrating,
                    Rating_star = textratingstar
                )

                present.onAddNewFoodClicked(newfood)

                dialog.dismiss()

            }else {
                Toast.makeText(this, "لطفا همه مقادیر را وارد کن ", Toast.LENGTH_SHORT).show()




        }

        }





    }


    override fun onFoodClicked(food: Food, position: Int) {

        val dialog = AlertDialog.Builder(this).create()

        val updateDialog = ItemUpdatedialogBinding.inflate(layoutInflater)

        dialog.setView(updateDialog.root)

        dialog.setCancelable(true)

        dialog.show()


        updateDialog.updatedialogEdtfoodname.setText(food.Txt_foodname)

        updateDialog.updatedialogEdtfoodcity.setText(food.Txt_foodcity)


        updateDialog.updatedialogEdtfoodprice.setText(food.Txt_foodprice)


        updateDialog.updatedialogEdtfooddistance.setText(food.Txt_fooddistance)

        updateDialog.updatedialogBtncancel.setOnClickListener {


            dialog.dismiss()
        }



        updateDialog.updatedialogBtndone.setOnClickListener {


            if (updateDialog.updatedialogEdtfoodname.length() > 0
                &&
                updateDialog.updatedialogEdtfoodcity.length() > 0
                &&
                updateDialog.updatedialogEdtfoodprice.length() > 0
                &&
                updateDialog.updatedialogEdtfooddistance.length() > 0
            ) {


                val nameUpdate = updateDialog.updatedialogEdtfoodname.text.toString()

                val cityUpdate = updateDialog.updatedialogEdtfoodcity.text.toString()

                val priceUpdate = updateDialog.updatedialogEdtfoodprice.text.toString()

                val distanceUpdate = updateDialog.updatedialogEdtfooddistance.text.toString()


                val newUpdateFood = Food(

                    id=food.id,
                    Txt_foodname = nameUpdate,
                    Txt_foodprice = priceUpdate,
                    Txt_fooddistance = distanceUpdate,
                    Txt_foodcity = cityUpdate,
                    Img = food.Img,
                    Numofrating = food.Numofrating,
                    Rating_star = food.Rating_star
                )





               present.onUpdateFoodClicked(newUpdateFood,position)



                dialog.dismiss()

            }else {
                Toast.makeText(this, "لطفا همه مقادیر را وارد کن ", Toast.LENGTH_SHORT).show()


            }





        }
    }

    override fun onFoodLongClicked(food: Food, position: Int) {
        val dialog = AlertDialog.Builder(this).create()

        val deleteDialog = ItemPakkardanFoodBinding.inflate(layoutInflater)

        dialog.setView(deleteDialog.root)
        dialog.setCancelable(true)
        dialog.show()

        deleteDialog.btncancel.setOnClickListener {


            dialog.dismiss()
        }


        deleteDialog.btnpakkardan.setOnClickListener {




            present.onDeleteFoodClicked(food,position)





            dialog.dismiss()
        }
    }

    override fun showAllFoods(data: ArrayList<Food>) {
        myAdapter= FoodAdapter(data,this)
        binding.recyclerMain.adapter=myAdapter
        binding.recyclerMain.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }

    override fun showAddNewFood(newfood: Food) {

        myAdapter.addNewFood(newfood)


    }

    override fun showDeleteFood(oldFood: Food, position: Int) {
myAdapter.deleteFood(oldFood,position)


    }

    override fun showUpdateFood(editingFood: Food, position: Int) {
myAdapter.updateNewFood(editingFood,position)

    }

    override fun showRefreshData(data: List<Food>) {

myAdapter.searchData(ArrayList(data))




    }
}











