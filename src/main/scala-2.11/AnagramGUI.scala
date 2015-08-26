import AnagramSolver._
import scala.swing._
import event.ButtonClicked
import java.awt.Color
import java.io.File


object AnagramGUI extends SimpleSwingApplication {

  override def main(args: Array[String]) = super.main(args)

  def top = new MainFrame {

    val english = "ENdict"
    val greek = "GRdict"
    var lang = english
    val basePath = new File("").getCanonicalPath
    val restPath = "/src/main/resources/"
    val dictEN = io.Source.fromFile(basePath + restPath + "ENdict")("UTF-8").getLines().toArray
    val dictGR = io.Source.fromFile(basePath + restPath + "GRdict")("UTF-8").getLines().toArray

    val entryPoint = new TextArea(10,20){
      text = "Enter your word here!"
      font = new Font("Monospace", java.awt.Font.ITALIC , 20)
      background = new Color(32, 0, 32)
      foreground = new Color(127, 179, 225)
      caret.color = foreground
    }

    val outPoint = new TextArea(10,20){
      font = new Font("Monospace", java.awt.Font.BOLD , 20)
      background = new Color(0, 77, 101)
      foreground = Color.white
      caret.color = foreground
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
        contents += new MenuItem(Action("English"){ lang = english })
        contents += new MenuItem(Action("Greek"){ lang = greek })
      }
    }

    listenTo(button)
    reactions += {
      case ButtonClicked(`button`) => {
        outPoint.text = ""
        if (lang == greek )
          solveIt(entryPoint.text.trim.toUpperCase , dictGR).foreach(outPoint.text += _.toLowerCase+"\n")
        else if (lang == english)
          solveIt(entryPoint.text.trim.toUpperCase , dictEN).foreach(outPoint.text += _.toLowerCase+"\n")
      }
    }

    contents = new BorderPanel {
      add(new ScrollPane(entryPoint), BorderPanel.Position.West)
      add(new ScrollPane(outPoint) , BorderPanel.Position.East)
      layout(button) = BorderPanel.Position.South
    }
  }
}

