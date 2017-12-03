package controllers

import controllers.exceptions.GastoInexistente

import scala.collection.mutable

class ExpenseController {
  private val expensesOthers = new mutable.HashMap[String, Expense]
  private val hashExpenses = new mutable.HashMap[String, Expense]
  private var transport = new Expense("transport", 0.0)
  private var feeding = new Expense("feeding", 0.0)
  private var necessary = new Expense("necessary", 0.0)
  private var leisure = new Expense("leisure", 0.0)
  private var expensesList = expensesDefault()

  def expensesDefault() : mutable.HashMap[String, Expense] = {
    hashExpenses += (transport.name -> transport)
    hashExpenses += (leisure.name -> leisure)
    hashExpenses += (necessary.name -> necessary)
    hashExpenses += (feeding.name -> feeding)

    hashExpenses
  }

  def listExpense() : mutable.HashMap[String, Expense] = expensesList

  def getOthers() : Double = {
    var total = 0.0
    for (pair <- expensesOthers) {
      total += pair._2.value
    }
    total
  }


  def deleteExpense(name : String) : Unit ={
    if(validExpense(name)) {
      listExpense().remove(name)
      if(expensesList.contains(name)){
        expensesList.remove(name)
      }
    }else{
      println("GASTO INEXISTENTE")
    }
  }

  def insertExpense(name : String, value : Double) : mutable.HashMap[String, Expense] ={
    var newExpense = new Expense(name, value)
    expensesOthers += (newExpense.name -> newExpense)
    return expensesList += (newExpense.name -> newExpense)

  }

  def getExpense(name : String) : Double = {
    if(expensesList.contains(name)) {
      var expense = expensesList(name)
      return expense.value
    }else {
      println("GASTO INEXISTENTE")
      return 0.0
    }
  }

  def setExpense(name : String, value : Double) : Unit = {
    if(expensesList.contains(name)) {
      var expense = expensesList(name)
      expense.value = value
    }
    else{
      println("GASTO INEXISTENTE")
    }
  }

  def validExpense(kind : String) : Boolean = {
    var valB = false
    if(listExpense().contains(kind)){
      valB = true
    }
    valB
  }
}