package com.girish.experimentpagedemo.experiment

abstract class ExperimentType<out T> (val defaultValue: T, val type: Class<out T>)

object BOOLEAN: ExperimentType<Boolean>(false, Boolean::class.java)
object BOOLEAN_DEFAULT_ON: ExperimentType<Boolean>(true, Boolean::class.java)
class INTEGER(defaultValue: Int): ExperimentType<Int>(defaultValue, Int::class.java)