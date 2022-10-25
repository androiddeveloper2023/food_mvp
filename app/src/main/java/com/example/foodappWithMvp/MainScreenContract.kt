package com.example.foodappWithMvp

import com.example.foodappWithMvp.model.Food

interface MainScreenContract {


    interface view{

         fun showAllFoods(data:ArrayList<Food>)

         fun showAddNewFood(newfood: Food)



         fun showDeleteFood(oldFood: Food, position: Int)


         fun showUpdateFood(editingFood: Food, position:Int)



         fun showRefreshData(data:List<Food>)



    }




    interface presenter{



                fun onAttach(view: view)

                fun onDetach()


                fun onSearchFoodClicked(word:String)


                fun onAddNewFoodClicked(food: Food)




                fun onClearAllFoodClicked()




                fun onUpdateFoodClicked(food: Food, position: Int)


                fun onDeleteFoodClicked(food: Food, position: Int)


                       fun firstRun()


    }

    




}