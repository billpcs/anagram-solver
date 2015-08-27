object AnagramSolver {

  def haveMatch( normal:String , scrambled:String   ) : Boolean = {
    (normal.length == scrambled.length) && (normal.toList.sorted == scrambled.toList.sorted)
  }

  def solveIt(scrambled:String , dict: Array[String]) : List[String] = {
    var found : List[String] = List.empty
    for (normal<-dict)
      if ( haveMatch(normal , scrambled) && !(found contains normal) )
          found = found ::: List(normal)
    if (found.isEmpty) List("Not found")
    else found
  }

  def getDicts : ( Array[String] , Array[String] ) = {
    val dictEN_pre = io.Source.fromURL(getClass.getResource("/ENdict"))("UTF-8")
    val dictEN = dictEN_pre.mkString.split("\n")
    val dictGR_pre = io.Source.fromURL(getClass.getResource("/GRdict"))("UTF-8")
    val dictGR = dictGR_pre.mkString.split("\n")
    dictEN_pre.close()
    dictGR_pre.close()
    (dictEN , dictGR)
  }


}
