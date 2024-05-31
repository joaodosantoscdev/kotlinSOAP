package com.example.exercicioksoap

import org.ksoap2.serialization.KvmSerializable
import org.ksoap2.serialization.PropertyInfo
import java.util.Hashtable

data class Position (
    val xAxys: String,
    val yAxys: String,
    val zAxys: String,
    val timestamp: String
) : KvmSerializable {

    override fun getProperty(index: Int): Any {
        if(index == 0)
            return xAxys
        else if(index == 1)
            return yAxys
        else if(index == 2)
            return zAxys
        else if(index == 3)
            return timestamp
        return "null"
    }

    override fun getPropertyCount(): Int {
        return 4
    }

    override fun setProperty(index: Int, value: Any?) {
        when (index) {
            0 -> value.toString()
            1 -> value.toString()
            2 -> value.toString()
            3 -> value
            else -> "null"
        }
    }

    override fun getPropertyInfo(index: Int, properties: Hashtable<*, *>?, info: PropertyInfo?) {
        if (info == null)
            return

        when (index) {
            0 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "xAxys"
            }
            1 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "yAxys"
            }
            2 -> {
                info.type = PropertyInfo.STRING_CLASS
                info.name = "zAxys"
            }
            3 -> {
                info.type = PropertyInfo.INTEGER_CLASS
                info.name = "timestamp"
            }
            else -> {}
        }
    }
}