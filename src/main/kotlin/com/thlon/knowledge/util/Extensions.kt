package com.thlon.knowledge.util

import java.time.LocalDate

fun String.toLocalDate() = LocalDate.parse(this, Objects.dateFormat)
