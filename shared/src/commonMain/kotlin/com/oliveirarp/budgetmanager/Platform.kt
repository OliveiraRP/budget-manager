package com.oliveirarp.budgetmanager

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform