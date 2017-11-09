package controllers

import java.util.Date


class UserController {
  private var name : String = _
  private var email : String = _
  private var income : Double = _
  private var birthDate : Date = _
  private var expense = new ExpenseController()
  private var plans = new PlanController("DEFAULT", income, expense)


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
    this.income = income
  }

  def getPlan(): PlanController ={
    this.plans
  }

  def setPlan(planName : String): Unit ={
    this.plans.setName(planName)
  }

  def getBalance(): Double ={
    income-getTotalExpense()
  }

  def getTotalExpense(): Double ={
    expense.getLeisure() + expense.getFeeding() + expense.getNecessary() + expense.getTransport()
  }

  def getExpense(): ExpenseController ={
    this.expense
  }

  def setExpense(tranport : Double, feeding : Double, essential : Double, leisure : Double) : Unit = {
    expense.setFeeding(feeding)
    expense.setLeisure(leisure)
    expense.setNecessary(essential)
    expense.setTransport(tranport)
  }

  def insertExpense(expense : Double, kind : String) : Unit = {
    plans.alerts(expense, kind)

    kind match {
      case "FEEDING" => this.expense.setFeeding(this.expense.getFeeding()+expense)
      case "TRANSPORT" => this.expense.setTransport(this.expense.getTransport()+expense)
      case "NECESSARY" => this.expense.setNecessary(this.expense.getNecessary()+expense)
      case "LEISURE" => this.expense.setLeisure(this.expense.getLeisure()+expense)
    }
  }

}
