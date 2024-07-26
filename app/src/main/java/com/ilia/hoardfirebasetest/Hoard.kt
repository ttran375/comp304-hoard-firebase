package com.ilia.hoardfirebasetest

class Hoard (hoardName: String?, goldHoarded: Int, hoardAccessible: Boolean ){

    val hoardName = hoardName
    val goldHoarded = goldHoarded
    val hoardAccessible = hoardAccessible

    override fun toString(): String {
        return "($hoardName:$goldHoarded:$hoardAccessible)"
    }



}