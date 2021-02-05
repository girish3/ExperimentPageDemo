package com.girish.experimentpagedemo.experiment

enum class Experiment(val key: String, val experimentType: ExperimentType<Any>) {

    // To add an experiment, add a name and the value type it takes,
    // FEATURE("<name>", <type><default value>)

    BUY_GOLD("BUY_GOLD", BOOLEAN),
    ADD_CUSTOMER_OVERLAY("ADD_CUSTOMER_OVERLAY", BOOLEAN),
    CASHBOOK("CASHBOOK", BOOLEAN_DEFAULT_ON),
    REVIEW_SHOW_TIME("REVIEW_SHOW_TIME", INTEGER(4));

    fun getType(): Class<out Any> {
        return experimentType.type
    }
}