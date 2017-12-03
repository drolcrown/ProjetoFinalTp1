package controllers

import java.util.Date

import controllers.exceptions.PlanoInexistente

import scala.collection.mutable


class UserController {
  private var name: String = _
  private var email: String = _
  private var income: Double = 0.0
  private var birthDate: Date = _
  private val hashPlans = new mutable.HashMap[String, PlanController]
  private var expense = new ExpenseController()
  private var plans = plansDefault()
  private var actualPlan = plans("DEFAULT")

  def listPlans() : mutable.HashMap[String, PlanController] = { plans}

  def plansDefault() : mutable.HashMap[String, PlanController] = {
    hashPlans += ("EASY" -> new PlanController(income, expense).definePlan("EASY", 0.0, 0.0, 0.0, 0.0, 0.0))
    hashPlans += ("MEDIUM" -> new PlanController(income, expense).definePlan("MEDIUM", 0.0, 0.0, 0.0, 0.0, 0.0))
    hashPlans += ("HARD" -> new PlanController(income, expense).definePlan("HARD", 0.0, 0.0, 0.0, 0.0, 0.0))
    hashPlans += ("INSANE" -> new PlanController(income, expense).definePlan("INSANE", 0.0, 0.0, 0.0, 0.0, 0.0))
    hashPlans += ("DEFAULT" -> new PlanController(income, expense).definePlan("DEFAULT", 0.0, 0.0, 0.0, 0.0, 0.0))

    hashPlans
  }

  def createPlan(namePlan : String, val1 : Double, val2 : Double, val3 : Double, val4: Double, val5 : Double) : Unit ={
    if(!plans.contains(namePlan)) {
      var planCreated = new PlanController(income, expense)
      actualPlan = planCreated.definePlan(namePlan, val1, val2, val3, val4, val5)
      plans += (planCreated.name -> planCreated)
    }
    else{
      println("O PLANO " + namePlan + " JÀ EXISTE")
    }
  }

  def deletePlan(namePlan : String) : Unit={
    if(!namePlan.equals(actualPlan.name)) {
      if(plansDefault().contains(namePlan)){
        println("NAO E POSSIVEL EXCLUIR PLANOS PADROES")
      }else {
        if (!plans.contains(namePlan)) {
          plans.remove(namePlan)
        } else {
          println("PLANO INEXISTENTE")
        }
      }
    }else{
      println("NAO E POSSIVEL EXCLUIR O PLANO ATUAL")
    }
  }

  def getName(): String ={
    this.name
  }

  def setName(name : String): Unit ={
    this.name = name
  }

  def getEmail(): String ={
    this.email
  }

  def setEmail(email : String): Unit ={
    this.email = email
  }

  def getBirthDate(): Date ={
    this.birthDate
  }

  def setBirthDate(birthDate : Date): Unit ={
    this.birthDate = birthDate
  }

  def getIncome(): Double ={
    this.income
  }

  def setIncome(income : Double): Unit ={
    var incomeAnt = actualPlan.income
    actualPlan.income = income
    actualPlan = actualPlan.redefinePlan(incomeAnt)
    this.income = income
  }

  def getPlan(): PlanController ={
    actualPlan
  }

  def setPlan(planName : String): Unit ={
    if (plans.contains(planName)) {
      actualPlan = plans(planName)
      actualPlan.expense = expense
      actualPlan.income = income
      actualPlan.redefinePlan(income)
    }
    else {
      println("PLANO INEXISTENTE")
      throw  PlanoInexistente()
    }
  }

  def getBalance(): Double ={
    income-getTotalExpense()
  }

  def getTotalExpense(): Double ={
    var total = 0.0
    for (pair <- expense.listExpense()) {
      total += pair._2.value
    }
    total
  }

  def getExpenseUser(): ExpenseController ={
    this.expense
  }

  def setExpenseUser(kind : String, valor : Double) : Unit = {
    expense.setExpense(kind, valor)
    actualPlan.insertExpense(valor, kind)
  }

  def insertExpense(newExpense : Double, kind : String) : Unit = {
    if(expense.validExpense(kind)){
      expense.setExpense(kind, expense.getExpense(kind) + newExpense)
    }else{
      expense.insertExpense(kind, newExpense)
    }
    actualPlan.insertExpense(newExpense, kind)
  }

  def deleteExpense(name : String) : Unit ={
    expense.deleteExpense(name)
    actualPlan.deleteExpense(name)
  }

}
