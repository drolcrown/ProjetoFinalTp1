package controllers

import controllers.exceptions.LimiteDeGastosUltrapassado

class PlanController(renda: Double, gastos : ExpenseController) extends Plans {
  var name : String = _
  var income: Double = renda
  var expense : ExpenseController = gastos
  private var percTransport : Double = 0.0
  private var percFeeding : Double = 0.0
  private var percNecessary : Double = 0.0
  private var percLeisure : Double = 0.0
  private var percOthers : Double = 0.0

  def setName(nome : String) : Unit ={
    this.name = nome
  }

  def getName() : String ={
    this.name
  }

  def insertExpense(newExpense : Double, kind : String): Unit = {
    redefinePlan(income)
    alerts(newExpense, kind)
  }

  def deleteExpense(kind : String): Unit ={
    if(expense.validExpense(kind)) {
      expense.listExpense().remove(kind)
    }else{
      println("GASTO INEXISTENTE")
    }
  }

  def redefinePlan(incomeAnt : Double) : PlanController ={
    if(incomeAnt > 0) {
      definePlan(this.name, this.percTransport / income,
        this.percFeeding / income,
        this.percNecessary / income,
        this.percLeisure / income,
        this.percOthers / income)
    }else{
      definePlan(this.name, this.percTransport,
        this.percFeeding,
        this.percNecessary,
        this.percLeisure,
        this.percOthers)
    }
  }

  override def definePlan(name: String, val1 : Double, val2 : Double, val3 : Double,  val4 : Double,  val5 : Double) : PlanController = {
    this.name = name
    name match {
        case "DEFAULT" => if(expense.getOthers() > 0) setPlans(0.20, 0.20, 0.20, 0.20, 0.20) else setPlans(0.25, 0.25, 0.25, 0.25, 0.0)

        case "EASY" => if(expense.getOthers() > 0) setPlans(0.10, 0.20, 0.10, 0.40, 0.20) else setPlans(0.20, 0.20, 0.20, 0.40, 0.0)

        case "MEDIUM" => if(expense.getOthers() > 0) setPlans(0.25, 0.25, 0.25, 0.15, 0.10) else setPlans(0.25, 0.25, 0.30, 0.20, 0.0)

        case "HARD" => if(expense.getOthers() > 0) setPlans(0.20, 0.20, 0.40, 0.10, 0.10) else setPlans(0.25, 0.25, 0.40, 0.10, 0.0)

        case "INSANE" => if(expense.getOthers() > 0) setPlans(0.10, 0.20, 0.50, 0.10, 0.10) else setPlans(0.20, 0.25, 0.50, 0.05, 0.0)

        case _ => setPlans(val1, val2, val3, val4, val5)

    }

    def setPlans(valor1 : Double, valor2 : Double, valor3 : Double,  valor4 : Double,  valor5 : Double) : Unit = {
       if(income > 0){
        percTransport = income * valor1
        percFeeding = income * valor2
        percNecessary = income * valor3
        percLeisure = income * valor4
        percOthers = income * valor5
      }else{
        percTransport = valor1
        percFeeding = valor2
        percNecessary = valor3
        percLeisure = valor4
        percOthers = valor5
      }
    }
    return this
  }

  def verifyKind(kind : String) : Double = {
    kind match {
      case "feeding" => percFeeding - expense.getExpense(kind)
      case "transport" =>  percTransport - expense.getExpense(kind)
      case "necessary" =>  percNecessary - expense.getExpense(kind)
      case "leisure" =>  percLeisure - expense.getExpense(kind)
      case _ => percOthers - expense.getExpense(kind)
    }
  }

  def alerts(newExpense : Double, kind : String): Unit = {
    if(expense.validExpense(kind) && newExpense > 0) {
      var percExpense = verifyKind(kind)
      if (percExpense <= 0) {
        println("VOCE ESTOUROU SEU LIMITE DE GASTOS COM " + kind.toUpperCase)
        println("SALDO EM " +kind.toUpperCase+" = " + percExpense)
        //        throw LimiteDeGastosUltrapassado()
      }
      else {
        var valor = Conversor().converte((expense.getExpense(kind) * 100) / (expense.getExpense(kind)+percExpense), 2)
        println("VOCE USOU " + valor + "% DO SEU LIMITE COM " + kind.toUpperCase)
      }
    }else{
      println("GASTO INVALIDO")
    }
  }
}
