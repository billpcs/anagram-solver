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


}
