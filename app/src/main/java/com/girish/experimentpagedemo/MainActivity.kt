package com.girish.experimentpagedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.girish.experimentpagedemo.experiment.Experiment
import com.girish.experimentpagedemo.experiment.ExperimentActivity
import com.girish.experimentpagedemo.experiment.ExperimentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var experimentManager: ExperimentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        abSettings.setOnClickListener {
            val intent = Intent(this, ExperimentActivity::class.java)
            startActivity(intent)
        }

        experimentManager = ExperimentManager(this)
    }

    override fun onStart() {
        super.onStart()

        // Experiment value check
        if (experimentManager.isEnabled(Experiment.BUY_GOLD)) {
            buyGold.visibility = View.VISIBLE
        } else {
            buyGold.visibility = View.GONE
        }
    }
}