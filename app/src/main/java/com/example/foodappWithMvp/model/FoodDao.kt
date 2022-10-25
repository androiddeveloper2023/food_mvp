package com.example.foodappWithMvp.model

import androidx.room.*
import com.example.foodappWithMvp.model.Food


@Dao
interface FoodDao {

    @Insert
    fun insertFood(newFood: Food)



    @Delete
    fun deleteFood(oldFood: Food)


    @Update
    fun updateFood(updatefood: Food)





    @Query("select * from table_food")
fun getAllFood():List<Food>

    @Query("delete from table_food")
    fun deleteAllData()




    @Query("select * from table_food "+"where Txt_foodname like '%' || :world || '%'")
    fun searchFood(world:String):List<Food>



    @Insert
    fun insertAllFood(data:List<Food>)

}