package com.example.foodappWithMvp.presenter

import com.example.foodappWithMvp.MainScreenContract
import com.example.foodappWithMvp.model.Food
import com.example.foodappWithMvp.model.FoodDao

class MainScreenPresenter(private val foodDao: FoodDao) : MainScreenContract.presenter {


    private var mainView: MainScreenContract.view? = null








    override fun firstRun() {
        val   foodList = arrayListOf(
            Food(
                Txt_foodname = "Hamburger",
                Txt_foodprice = "15",
                Txt_fooddistance = "3",
                Txt_foodcity = "Isfahan, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                Numofrating = 20,
                Rating_star = 4.5f
            ),
            Food(
                Txt_foodname = "Grilled fish",
                Txt_foodprice = "20",
                Txt_fooddistance = "2.1",
                Txt_foodcity = "Tehran, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                Numofrating = 10,
                Rating_star = 4f
            ),
            Food(
                Txt_foodname = "Lasania",
                Txt_foodprice = "40",
                Txt_fooddistance = "1.4",
                Txt_foodcity = "Isfahan, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                Numofrating = 30,
                Rating_star = 2f
            ),
            Food(
                Txt_foodname = "pizza",
                Txt_foodprice = "10",
                Txt_fooddistance = "2.5",
                Txt_foodcity = "Zahedan, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                Numofrating = 80,
                Rating_star = 1.5f
            ),
            Food(
                Txt_foodname = "Sushi",
                Txt_foodprice = "20",
                Txt_fooddistance = "3.2",
                Txt_foodcity = "Mashhad, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                Numofrating = 200,
                Rating_star = 3f
            ),
            Food(
                Txt_foodname = "Roasted Fish",
                Txt_foodprice = "40",
                Txt_fooddistance = "3.7",
                Txt_foodcity = "Jolfa, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                Numofrating = 50,
                Rating_star = 3.5f
            ),
            Food(
                Txt_foodname = "Fried chicken",
                Txt_foodprice = "70",
                Txt_fooddistance = "3.5",
                Txt_foodcity = "NewYork, USA",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                Numofrating = 70,
                Rating_star = 2.5f
            ),
            Food(
                Txt_foodname = "Vegetable salad",
                Txt_foodprice = "12",
                Txt_fooddistance = "3.6",
                Txt_foodcity = "Berlin, Germany",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                Numofrating = 40,
                Rating_star = 4.5f
            ),
            Food(
                Txt_foodname = "Grilled chicken",
                Txt_foodprice = "10",
                Txt_fooddistance = "3.7",
                Txt_foodcity = "Beijing, China",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                Numofrating = 15,
                Rating_star = 5f
            ),
            Food(
                Txt_foodname = "Baryooni",
                Txt_foodprice = "16",
                Txt_fooddistance = "10",
                Txt_foodcity = "Ilam, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                Numofrating = 28,
                Rating_star = 4.5f
            ),
            Food(
                Txt_foodname = "Ghorme Sabzi",
                Txt_foodprice = "11.5",
                Txt_fooddistance = "7.5",
                Txt_foodcity = "Karaj, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                Numofrating = 27,
                Rating_star = 5f
            ),
            Food(
                Txt_foodname = "Rice",
                Txt_foodprice = "12.5",
                Txt_fooddistance = "2.4",
                Txt_foodcity = "Shiraz, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                Numofrating = 35,
                Rating_star = 2.5f
            ),


            )
        foodDao.insertAllFood(foodList)
    }











    override fun onAttach(view: MainScreenContract.view) {

        mainView = view


        val allFoods = foodDao.getAllFood()


        mainView!!.showAllFoods(ArrayList(allFoods))


    }

    override fun onDetach() {
        mainView = null


    }

    override fun onSearchFoodClicked(word: String) {

        if (word.isNotEmpty()) {

            // show filtered data
            val dataToShow = foodDao.searchFood(word)
            mainView!!.showRefreshData(dataToShow)

        } else {

            // show all data
            val dataToShow = foodDao.searchFood(word)
            mainView!!.showRefreshData(dataToShow)

        }


    }

    override fun onAddNewFoodClicked(food: Food) {


        foodDao.insertFood(food)


        mainView!!.showAddNewFood(food)


    }

    override fun onClearAllFoodClicked() {
        foodDao.deleteAllData()


        mainView!!.showRefreshData(foodDao.getAllFood())


    }

    override fun onUpdateFoodClicked(food: Food, position: Int) {
        foodDao.updateFood(food)

        mainView!!.showUpdateFood(food, position)


    }

    override fun onDeleteFoodClicked(food: Food, position: Int) {

        foodDao.deleteFood(food)

        mainView!!.showDeleteFood(food, position)


    }




}