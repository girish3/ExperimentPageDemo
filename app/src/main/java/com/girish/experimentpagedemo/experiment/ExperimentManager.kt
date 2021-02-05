package com.girish.experimentpagedemo.experiment

import android.content.Context
import android.content.SharedPreferences

class ExperimentManager(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences("<filename>", Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    fun save(experiment: Experiment, value: Boolean) {
        if (experiment.getType() != Boolean::class.java) {
            throw TypeCastException("Wrong type passed")
        }

        editor.putBoolean(experiment.key, value)
        editor.apply()
    }

    fun save(experiment: Experiment, value: Int) {
        if (experiment.getType() != Int::class.java) {
            throw TypeCastException("Wrong type passed")
        }

        editor.putInt(experiment.key, value)
        editor.apply()
    }

    fun save(experiment: Experiment, value: String) {
        if (experiment.getType() != String::class.java) {
            throw TypeCastException("Wrong type passed")
        }

        editor.putString(experiment.key, value)
        editor.apply()
    }

    fun isEnabled(experiment: Experiment): Boolean {
        if (experiment.getType() == Boolean::class.java) {
            return prefs.getBoolean(experiment.key, experiment.experimentType.defaultValue as Boolean)
        }
        throw TypeCastException("Wrong type passed")
    }

    fun getInt(feature: Experiment): Int {
        if (feature.getType() != Int::class.java) {
            throw TypeCastException("Wrong type passed")
        }

        return prefs.getInt(feature.key, feature.experimentType.defaultValue as Int)
    }

}