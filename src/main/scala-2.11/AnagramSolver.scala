object AnagramSolver extends App{

  def haveSameLetters(dest:String , norm:String ) : Boolean = {
    val dlst = dest.toList.sorted
    val nlst = norm.toList.sorted
    val l = dlst.length
    def inner(dlst:List[Char] , nlst:List[Char]  , idx:Int) : Boolean = {
      if (l == idx ) true
      else if (dlst(idx) == nlst(idx))inner(dlst , nlst , idx+1)
      else false
    }
    inner(dlst , nlst , 0)
  }

  def haveMatch( normal:String , scrambled:String   ) : Boolean = {
    (normal.length == scrambled.length) &&
      haveSameLetters(scrambled , normal)
  }

  def solveIt(path:String , scrambled:String ) : List[String] = {
    val dict = scala.io.Source.fromFile(path).getLines().toArray
    var found = List[String]()
    for (normal<-dict)
      if ( haveMatch(normal , scrambled) && !(found contains normal) )
          found = found ::: List(normal)
    if (found.isEmpty) List("Not found")
    else found
  }
}
