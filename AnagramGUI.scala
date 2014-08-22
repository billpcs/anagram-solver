import AnagSolverInScala._
import scala.swing._
import scala.swing.BorderPanel.Position._
import event._
import java.awt.{ Color, Graphics2D }
import java.awt.datatransfer._
import java.io._
import scala.io.Source
import scala.language.postfixOps



object AnagramGUI extends SimpleSwingApplication {

	override def main(args: Array[String]) = super.main(args)

	def top = new MainFrame {

		val entryPoint = new TextArea(4,20){
			text = "Enter your word here!"
			font = new Font("Monospace", java.awt.Font.ITALIC , 20)
			background = Color.white
			foreground = Color.black
		}
	
		val outPoint = new TextArea(4,20){
			font = new Font("Monospace", java.awt.Font.BOLD , 20)
			background = Color.black
			foreground = Color.white
			editable = false
		}

		val button = new Button{
			text = "Solve it for me!"
		}

		val scrollbar = new ScrollPane() {
      		visible = true
    	} 


    	menuBar = new MenuBar {
    		contents += new Menu("File") {
    			contents += new MenuItem(Action("Quit") {sys.exit(0)} )
    		}
    	}

    	listenTo(button)
    	reactions += {
    		case ButtonClicked(component) if(component==button)=>
            outPoint.text = ""
    		outPoint.foreground = Color.white
    		solveIt("americanenglishCAPS" , entryPoint.text.toUpperCase ) foreach(p=> outPoint.text += p.toLowerCase+"\n")
    	}

    	contents = new BorderPanel {       
        	add(new ScrollPane(entryPoint), BorderPanel.Position.West)
        	add(new ScrollPane(outPoint) , BorderPanel.Position.East)
        	layout(button) = South
      	}


	}	

	
}
