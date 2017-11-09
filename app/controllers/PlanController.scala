package controllers

class PlanController(nome: String, income: Double, expense : ExpenseController) {
  private var name = nome
  private var percTransport : Double =_
  private var percFeeding : Double =_
  private var percNecessary : Double =_
  private var percLeisure : Double =_

  def setName(nome : String) : Unit ={
    this.name = nome
  }

  def getName() : String ={
    this.name
  }

  name match {
    case "DEFAULT" => percTransport = expense.getTransport()-income * 0.25; percFeeding = expense.getFeeding()- income * 0.25
                      percNecessary = expense.getNecessary()-income * 0.25; percLeisure = expense.getLeisure()-income * 0.25
    case "EASY" => percTransport = expense.getTransport()-income * 0.20; percFeeding = expense.getFeeding()-income * 0.20
                      percNecessary = expense.getNecessary()-income * 0.20; percLeisure = expense.getLeisure()-income * 0.40
    case "MEDIUM" => percTransport = expense.getTransport()-income * 0.20; percFeeding = expense.getFeeding()-income * 0.20
                      percNecessary = expense.getNecessary()-income * 0.40; percLeisure = expense.getLeisure()-income * 0.20
    case "HARD" => percTransport = expense.getTransport()-income * 0.20; percFeeding = expense.getFeeding()-income * 0.20
                      percNecessary = expense.getNecessary()-income * 0.50; percLeisure = expense.getLeisure()-income * 0.10
    case "INSANE" => percTransport = expense.getTransport()-income * 0.10; percFeeding = expense.getFeeding()-income * 0.10
                      percNecessary = expense.getNecessary()-income * 0.70; percLeisure = expense.getLeisure()-income * 0.10
  }

  def alerts(expense : Double, kind : String): Unit = {
    var percExpense = 0.0

    kind match {
      case "FEEDING" => percExpense = percFeeding
      case "TRANSPORT" => percExpense = percTransport
      case "NECESSARY" => percExpense = percNecessary
      case "LEISURE" => percExpense = percLeisure
    }

    if (expense > percExpense){
        print("VOCE ESTOUROU SEU LIMITE DE GASTOS COM " + kind)
    }
    else {
        print("VOCE USOU " + expense*100 / percExpense + "% DOS SEUS LIMITE COM " + kind)
    }
  }

}



/*
    case "DEFAULT" => expense.setTransport(income * 0.25); expense.setFeeding(income * 0.25)
                      expense.setNecessary(income * 0.25); expense.setLeisure(income * 0.25)
    case "EASY" => expense.setTransport(income * 0.20); expense.setFeeding(income * 0.20)
                      expense.setNecessary(income * 0.20); expense.setLeisure(income * 0.40)
    case "MEDIUM" => expense.setTransport(income * 0.20); expense.setFeeding(income * 0.20)
                      expense.setNecessary(income * 0.40); expense.setLeisure(income * 0.20)
    case "HARD" => expense.setTransport(income * 0.20); expense.setFeeding(income * 0.20)
                      expense.setNecessary(income * 0.50); expense.setLeisure(income * 0.10)
    case "INSANE" => expense.setTransport(income * 0.10); expense.setFeeding(income * 0.10)
                      expense.setNecessary(income * 0.70); expense.setLeisure(income * 0.10)
*/