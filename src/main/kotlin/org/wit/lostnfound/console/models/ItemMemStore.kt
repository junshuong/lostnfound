package org.wit.lostnfound.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ItemMemStore : ItemStore {

    val items = ArrayList<ItemModel>()

    override fun findAll(): List<ItemModel> {
        return items
    }

    override fun findOne(id: Long) : ItemModel? {
        var foundItem: ItemModel? = items.find { p -> p.id == id }
        return foundItem
    }

    override fun create(item: ItemModel) {
        item.id = getId()
        items.add(item)
        logAll()
    }

    override fun update(item: ItemModel) {
        var foundItem = findOne(item.id!!)
        if (foundItem != null) {
            foundItem.description = item.description
        }
    }

    override fun delete(item: ItemModel) {
        items.remove(item)
    }

    internal fun logAll() {
        items.forEach { logger.info("$it") }
    }
}