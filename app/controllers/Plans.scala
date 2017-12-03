package controllers

trait Plans {
    def definePlan(name : String, val1: Double, val2: Double, val3: Double, val4: Double, val5: Double) : PlanController
}
