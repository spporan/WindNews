package com.windnews.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * TypeConverter is responsible for serialize and  deserialize object  and help to store in room
 */
class TypeConverter {

    @TypeConverter
    fun fromSource(source: Source): String {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(jsonString: String) : Source {
        return Gson().fromJson(jsonString, Source::class.java)
    }
}