package ba.etf.rma21.projekat.data

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromStringToDate(value: String?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromdateToString(date: Date?): String? {
        return date?.time?.toString()
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        var result = ""
        for (i in 0 until list.size) {
            result += list[i]
            if (i != list.size - 1) {
                result += ","
            }
        }
        return result
    }

    @TypeConverter
    fun stringToList(value: String): List<String> {
        return value.split(",").toList()
    }
}
