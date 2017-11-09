package controllers

class ExpenseController {
  private var transport = 0.0
  private var feeding = 0.0
  private var necessary = 0.0
  private var leisure = 0.0


  def getTransport() : Double = {
    transport
  }

  def setTransport(transport : Double) : Unit = {
    this.transport = transport
  }

  def getFeeding() : Double = {
    feeding
  }

  def setFeeding(feeding: Double) : Unit = {
    this.feeding = feeding
  }

  def getLeisure() : Double = {
    leisure
  }

  def setLeisure(leisure : Double) : Unit = {
    this.leisure = leisure
  }

  def getNecessary() : Double = {
    necessary
  }

  def setNecessary(necessary : Double) : Unit = {
    this.necessary = necessary
  }


}