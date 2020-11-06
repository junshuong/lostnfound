package org.wit.lostnfound.console.views

import org.wit.lostnfound.console.models.ItemJSONStore
import org.wit.lostnfound.console.models.ItemModel

class ItemView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("Main Menu")
        println(" 1. Add Item")
        println(" 2. Update Item")
        println(" 3. List All Items")
        println(" 4. Search Item")
        println(" 5. Delete Item")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listAllItems(items: ItemJSONStore) {
        println("List All Items")
        println()
        items.logAll()
        println()
    }

    fun showItem(item : ItemModel) {
        if(item != null)
            println("Item Details [ $item ]")
        else
            println("Item Not Found...")
    }

    fun addItemData(item : ItemModel) : Boolean {
        println()
        print("Enter description of Item : ")
        item.description = readLine()!!

        return item.description.isNotEmpty()
    }

    fun updateItemData(item : ItemModel) : Boolean {
        var tempDescription: String?

        if (item != null) {
            print("Enter a new description for [ ${item.description} ] : ")
            tempDescription = readLine()!!

            if (!tempDescription.isNullOrEmpty()) {
                item.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}