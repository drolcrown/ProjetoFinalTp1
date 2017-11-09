package controllers

import java.util.Date

object teste extends App {

  var user = new UserController
  user.setName("Rafael Oliveira")
  user.setEmail("rafa.jones@hotmail.com")
  user.setIncome(980.00)
  user.setBirthDate(new Date())
  user.setExpense(95.00, 180.00, 200.00, 300.00)
  user.setPlan("EASY")

  println("Nome = " + user.getName())
  println("Email = " + user.getEmail())
  println("Data = " + user.getBirthDate())
  println("Renda = " + user.getIncome())
  println("Gastos = " + user.getTotalExpense())
  println("Gastos com Alimentacao = " + user.getExpense().getFeeding())
  println("Gastos Necessario = " + user.getExpense().getNecessary())
  println("Gastos com Transporte = " + user.getExpense().getTransport())
  println("Gastos com Lazer = " + user.getExpense().getLeisure())
  println("Plano = " + user.getPlan().getName())

  user.insertExpense(3, "TRANSPORT")
}
