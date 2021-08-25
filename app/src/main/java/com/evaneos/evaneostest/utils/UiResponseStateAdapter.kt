package com.evaneos.evaneostest.ui

inline fun <reified T> isInstanceOf(instance: Any?): Boolean {
    return instance is T
}