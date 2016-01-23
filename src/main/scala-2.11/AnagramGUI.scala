import java.awt.event.InputEvent
import javax.swing.KeyStroke
import java.awt.Color

import scala.swing._
import AnagramSolver._

object AnagramGUI extends SimpleSwingApplication {

  override def main(args: Array[String]) = super.main(args)

  def top = new MainFrame {

    
    val english = "ENdict"
    val greek = "GRdict"
    var lang = english
    val (dictEN , dictGR) = getDicts

    val entryPoint = new TextArea(10,20){
      text = "Enter your word here!"
      font = new Font("Monospace", java.awt.Font.ITALIC , 20)
      background = new Color(32, 0, 32)
      foreground = new Color(127, 179, 225)
      caret.color = foreground
      editable = true
      focusable = true
    }

    val outPoint = new TextArea(10,20){
      font = new Font("Monospace", java.awt.Font.BOLD , 20)
      background = new Color(0, 77, 101)
      foreground = Color.white
      caret.color = foreground
      editable = false
      focusable = false
    }

    val button = new Button(new Action("Find!") {
      def apply() {
        outPoint.text = ""
        if (lang == english)
          solveIt(entryPoint.text.trim.toUpperCase , dictEN).foreach(outPoint.text += _.toLowerCase+"\n")
        else if (lang == greek )
          solveIt(entryPoint.text.trim.toUpperCase , dictGR).foreach(outPoint.text += _.toLowerCase+"\n")
      }
    })


    val scrollbar = new ScrollPane() {
      visible = true
    }

    menuBar = new MenuBar {
      resizable = false
      contents += new Menu("File") {
        contents += new MenuItem(new Action("Exit") {
          accelerator = Some(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_DOWN_MASK))
          def apply() {
            sys.exit(0)
          }
        })
      }
      contents += new Menu("Language"){
        contents += new MenuItem(Action("English"){ lang = english })
        contents += new MenuItem(Action("Greek"){ lang = greek })
      }
    }

    contents = new BorderPanel {
      add(new ScrollPane(entryPoint), BorderPanel.Position.West)
      add(new ScrollPane(outPoint), BorderPanel.Position.East)
      layout(button) = BorderPanel.Position.South
    }
  }
}

