package org.wit.lostnfound.console.models

interface ItemStore {
    fun findAll(): List<ItemModel>
    fun findOne(id: Long): ItemModel?
    fun create(item: ItemModel)
    fun update(item: ItemModel)
    fun delete(item: ItemModel)
}