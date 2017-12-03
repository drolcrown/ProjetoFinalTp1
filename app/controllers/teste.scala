package controllers

import java.util.Date

object teste extends App {

  var user = new UserController
  user.createPlan("Rafinha", 0.10, 0.10, 0.10, 0.10, 0.10)
  user.setName("Rafael Oliveira")
  user.setEmail("rafa.jones@hotmail.com")
  user.setIncome(1480.00)
  user.setBirthDate(new Date())
  user.setExpenseUser("feeding", 40.00)
  user.setExpenseUser("necessary", 200.00)
  user.setExpenseUser("transport", 250.00)
  user.setExpenseUser("leisure", 360.00)
//  user.setPlan("HARD")

  println("Nome = " + user.getName())
  println("Email = " + user.getEmail())
  println("Data = " + user.getBirthDate())
  println("Renda = " + user.getIncome())
  println("Gastos = " + user.getTotalExpense())
  println("Gastos com Alimentacao = " + user.getExpenseUser().getExpense("feeding"))
  println("Gastos Necessario = " + user.getExpenseUser().getExpense("necessary"))
  println("Gastos com Transporte = " + user.getExpenseUser().getExpense("transport"))
  println("Gastos com Lazer = " + user.getExpenseUser().getExpense("leisure"))
  println("Plano = " + user.getPlan().getName())
  println("Numeros de Planos = " + user.listPlans().size)
  user.insertExpense(20, "leisure")

}
