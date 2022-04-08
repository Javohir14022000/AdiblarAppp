package com.example.adiblarappp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.adiblarappp.database.entity.Adib
import io.reactivex.rxjava3.core.Flowable

@Dao
interface AdibDao {

    @Insert
    fun addAdib(adib: Adib)

    @Query("select * from adib")
    fun getAllAdib(): List<Adib>

    @Query("select * from adib")
    fun getAllAdibFlowable(): Flowable<List<Adib>>

    @Query("delete from adib where id=:id")
    fun deleteAdib(id: Int)

    @Query("update adib set isSaved=:isSaved where id=:id")
    fun updateLiterature(isSaved: Boolean, id: Int)

    @Query("select * from adib where name like :name")
    fun getLiteratureByName(name: String): Adib
}