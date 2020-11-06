package org.wit.lostnfound.console.main

import mu.KotlinLogging
import org.wit.lostnfound.console.models.ItemModel

private val logger = KotlinLogging.logger {}

var description = ItemModel()

fun main (args: Array<String>) {
    logger.info {"Launching Lost-n-Found App"}
    println("Lost-n-Found Kotlin App")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addItem()
            2 -> updateItem()
            3 -> listAllItems()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Lost-n-Found App" }
}

fun menu() : Int {
    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Item")
    println(" 2. Update Item")
    println(" 3. List All Items")
    println("-1. Exit")
    println()
    print("Enter option : ")
    input = readLine()!!
    option = if (input.isNullOrEmpty() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addItem() {

    println("Add an Item")
    println()
    print("Enter description of Item : ")
    description.item = readLine()!!
    println("Item entered is : [ ${description.item} ]")
}

fun updateItem() {
    println("Update Item description : ")
    println()
    println("Enter a new description for the [ ${description.item} ] : ")
    description.item = readLine()!!
    println("You have updated the description of item to : [ ${description.item} ] ")
}

fun listAllItems() {
    println("You chose List all Items")
}
