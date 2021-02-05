package com.girish.experimentpagedemo.experiment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.girish.experimentpagedemo.R
import kotlinx.android.synthetic.main.activity_experiment.*
import kotlinx.android.synthetic.main.experiment_boolean_view.view.*
import kotlinx.android.synthetic.main.experiment_boolean_view.view.experiment_name
import kotlinx.android.synthetic.main.experiment_int_view.view.*
import java.util.*

class ExperimentActivity : AppCompatActivity() {

    lateinit var experimentManager: ExperimentManager

    private val BOOLEAN_LISTENER = fun(view: View, newValue: Boolean) {
        val experiment = view.tag as Experiment
        experimentManager.save(experiment, newValue)
    }

    private val INT_LISTENER = fun(view: View, newValue: Int) {
        val experiment = view.tag as Experiment
        experimentManager.save(experiment, newValue)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experiment)
        experimentManager = ExperimentManager(this)

        addExperimentViews()
    }

    private fun addExperimentViews() {
        for (experiment in Experiment.values()) {
            val type = experiment.getType()
            if (type == Boolean::class.java) {
                val view = getBooleanView(experiment, experimentManager.isEnabled(experiment))
                linearLayout.addView(view)
            } else if (type == Int::class.java) {
                val view = getIntegerView(experiment, experimentManager.getInt(experiment))
                linearLayout.addView(view)
            }
        }
    }

    private fun getTextView(text: String): TextView {
        val textView = TextView(this)
        textView.text = text
        return textView
    }

    private fun getBooleanView(experiment: Experiment, initialValue: Boolean): View {
        val view = layoutInflater.inflate(R.layout.experiment_boolean_view, linearLayout, false)

        view.experiment_name.text = experiment.name
        view.toggle_view.tag = experiment
        view.toggle_view.isChecked = initialValue
        // TODO: @girish pass the listener in the method argument. Make it a pure function
        view.toggle_view.setOnCheckedChangeListener(BOOLEAN_LISTENER)

        return view
    }

    private fun getIntegerView(experiment: Experiment, initialValue: Int): View {
        val view = layoutInflater.inflate(R.layout.experiment_int_view, linearLayout, false)

        view.experiment_name.text = experiment.name
        view.enter_integer.tag = experiment
        view.enter_integer.setText(initialValue.toString())
        view.enter_integer.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            private var timer: Timer = Timer()
            private val DELAY: Long = 1000 // Milliseconds

            override fun afterTextChanged(s: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            if (s == null || s.isEmpty()) return
                            INT_LISTENER(view.enter_integer, s.toString().toInt())
                        }
                    },
                    DELAY
                )
            }
        })

        return view
    }
}