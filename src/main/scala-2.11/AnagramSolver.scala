import scala.io.Source

object AnagramSolver extends App{

  def haveSameLetters(dest:String , norm:String ) : Boolean = {

    val dlst = dest.toList.sorted
    val nlst = norm.toList.sorted
    val l = dlst.length
    def inner(dlst:List[Char] , nlst:List[Char] , l : Int , idx:Int) : Boolean = {
      if (l == idx ) true
      else if (dlst(idx) == nlst(idx))inner(dlst , nlst , l , idx+1)
      else false
    }
    inner(dlst , nlst , l , 0)
  }


  def solveIt(path:String , scrambled:String ) : Array[String] = {
    val dict = Source.fromFile(path).getLines().toArray
    var found = Array[String]()
    for (normal<-dict)
      if (normal.length == scrambled.length)
        if (haveSameLetters(scrambled , normal) ){
          if (!(found contains normal))
            found = found :+ normal
        }
    if (found.isEmpty) Array("Not found")
    else found
  }
}
