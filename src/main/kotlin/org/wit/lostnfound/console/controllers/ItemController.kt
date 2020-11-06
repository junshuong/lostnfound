package org.wit.lostnfound.console.controllers

import mu.KotlinLogging
import org.wit.lostnfound.console.models.ItemJSONStore
import org.wit.lostnfound.console.models.ItemModel
import org.wit.lostnfound.console.views.ItemView

class ItemController {
//    val items = ItemMemStore()
    val items = ItemJSONStore()

    val itemView = ItemView()
    private val logger = KotlinLogging.logger {}

    init {
        logger.info {"Launching Lost-n-Found App"}
        println("Lost-n-Found Kotlin App")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -1 -> println("Exiting App")
                -99 -> dummyData()
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Lost-n-Found App" }
    }


    fun menu() :Int { return itemView.menu() }

    fun add(){
        var item = ItemModel()

        if (itemView.addItemData(item))
            items.create(item)
        else
            logger.info("Item Not Added")
    }

    fun list() {
        itemView.listAllItems(items)
    }

    fun update() {
        itemView.listAllItems(items)
        var searchId = itemView.getId()
        val item = search(searchId)

        if(item != null) {
            if(itemView.updateItemData(item)) {
                items.update(item)
                itemView.showItem(item)
                logger.info("Item Updated : [ $item ]")
            }
            else
                logger.info("Item description Not Updated")
        }
        else
            println("Item description Not Updated...")
    }

    fun search() {
        val item = search(itemView.getId())!!
        itemView.showItem(item)
    }

    fun delete() {
        itemView.listAllItems(items)
        var searchId = itemView.getId()
        val item = search(searchId)

        if(item != null) {
            items.delete(item)
            println("Item Deleted...")
            itemView.listAllItems(items)
        }
        else
            println("Item Not Deleted...")
    }


    fun search(id: Long) : ItemModel? {
        var foundItem = items.findOne(id)
        return foundItem
    }

    fun dummyData() {
        items.create(ItemModel(1, "Shoe"))
        items.create(ItemModel(2, "Bag"))
        items.create(ItemModel(3, "Phone"))
    }
}