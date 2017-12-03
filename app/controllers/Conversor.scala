package controllers

case class Conversor() {

  def converte(valor : Double, nCasas : Int) : Double = {
    BigDecimal(valor).setScale(nCasas, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

}
