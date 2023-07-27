package com.example.testarchit.ui.slideshow

/**
 * Created by caizhiwei on 2023/7/27
 */
data class TableDish(val id: Int, val tableName: String, val dishes: List<DishItem>)

data class DishItem(val name: String, val quantity: Int)
