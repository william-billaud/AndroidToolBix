package fr.isen.billaud.androidtoolbox

import java.text.SimpleDateFormat
import java.util.*

class User {
    var name : String?= null
    var surname : String?=null
    var birthDate: Calendar?=null

    constructor() : super(){}

    constructor(name: String,surname: String,birthDate: Calendar) : super(){
        this.name= name
        this.surname=surname
        this.birthDate=birthDate
    }

    override fun toString(): String {
        return "User(name=$name, surname=$surname, birthdate : " + this.birthdateString()+")"
    }

    fun birthdateString():String{
        val formater = SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
        return formater.format(birthDate?.time)
    }

    fun age(): Int {
        val cal = Calendar.getInstance()
        if(birthDate==null){
            return -1
        }
        return if(cal.get(Calendar.DAY_OF_YEAR)>birthDate!!.get(Calendar.DAY_OF_YEAR)){
            cal.get(Calendar.YEAR)-birthDate!!.get(Calendar.YEAR)
        }else{
            cal.get(Calendar.YEAR)-birthDate!!.get(Calendar.YEAR)-1
        }

    }

}