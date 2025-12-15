package com.ahmadmaaz1.newsy.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.ahmadmaaz1.newsy.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.name},${source.id}"
    }

    @TypeConverter
    fun stringToSource(string: String): Source {
        return string.split(",").let {
            Source(name = it[0], id = it[1])
        }
    }
}