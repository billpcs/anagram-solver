import AnagramSolver._
import scala.swing._
import scala.swing.BorderPanel.Position._
import event._
import java.awt.Color
import java.io._
import scala.language.postfixOps

object AnagramGUI extends SimpleSwingApplication {

  override def main(args: Array[String]) = super.main(args)

  def top = new MainFrame {

    var lang = "ENdict"
    val basePath = new File(".").getCanonicalPath
    var restPath = "/src/main/resources/"
    val entryPoint = new TextArea(10,20){
      text = "Enter your word here!"
      font = new Font("Monospace", java.awt.Font.ITALIC , 20)
      background = Color.white
      foreground = Color.black
    }

    val outPoint = new TextArea(10,20){
      font = new Font("Monospace", java.awt.Font.BOLD , 20)
      background = Color.black
      foreground = Color.white
      editable = false
    }

    val button = new Button{
      text = "Find them!"
    }

    val scrollbar = new ScrollPane() {
      visible = true
    }


    menuBar = new MenuBar {
      resizable = false
      contents += new Menu("File") {
        contents += new MenuItem(Action("Quit") {sys.exit(0)} )
      }
      contents += new Menu("Language"){
        contents += new MenuItem(Action("English"){lang = "ENdict"})
        contents += new MenuItem(Action("Greek"){lang = "GRdict"})
      }
    }

    listenTo(button)
    reactions += {
      case ButtonClicked(component) if component == button =>
        outPoint.text = ""
        solveIt(
          basePath + restPath + lang, entryPoint.text.trim.toUpperCase
          ) foreach(p=> outPoint.text += p.toLowerCase+"\n")
    }

    contents = new BorderPanel {
      add(new ScrollPane(entryPoint), BorderPanel.Position.West)
      add(new ScrollPane(outPoint) , BorderPanel.Position.East)
      layout(button) = South
    }


  }


}

