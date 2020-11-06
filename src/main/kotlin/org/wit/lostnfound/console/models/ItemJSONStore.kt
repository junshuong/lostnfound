package org.wit.lostnfound.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.lostnfound.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "placemarks.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<ItemModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ItemJSONStore : ItemStore {

    var items = mutableListOf<ItemModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ItemModel> {
        return items
    }

    override fun findOne(id: Long) : ItemModel? {
        var foundItem: ItemModel? = items.find { p -> p.id == id }
        return foundItem
    }

    override fun create(item: ItemModel) {
        item.id = generateRandomId()
        items.add(item)
        serialize()
    }

    override fun update(item: ItemModel) {
        var foundItem = findOne(item.id!!)
        if (foundItem != null) {
            foundItem.description = item.description
        }
        serialize()
    }

    override fun delete(item: ItemModel) {
        items.remove(item)
        serialize()
    }

    internal fun logAll() {
        items.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(items, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        items = Gson().fromJson(jsonString, listType)
    }
}