import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLLabelElement
import org.w3c.dom.Node
import org.w3c.dom.events.Event

fun main() {
    window.onload = { document.body?.gradeTable() }
}

class Student(
    val name: String,
    val surname: String,
    val grade: Int
)

val students = listOf(
    Student("Sheldon", "Cooper", 5),
    Student("Leonard", "Hofstadter", 4),
    Student("Howard", "Wolowitz",4)
)

fun Node.gradeTable() {
    append {
        table {
            tr {
                th { +"Name"; style = "border: 1px solid black;" }
                th { +"Grade"; style = "border: 1px solid black;" }
                th { +"Buttons"; style = "border: 1px solid black;" }
            }
            style = "border: 1px solid black"
            for (i in students.indices)
                tr {
                    td { +students[i].name; style = "border: 1px solid black;"
                        +" "
                        +students[i].surname; style = "border: 1px solid black;" }
                    td {
                        style = "Text-align: center; border: 1px solid black"
                        label {
                            +"${students[i].grade}"; id = "$i"
                        }
                    }
                    td {
                        style = "border: 1px solid black; Text-align: center"
                        button {
                            style = "width:45%"
                            +"-"
                            onClickFunction = onClickFun("-", i)
                        }
                        button {
                            style = "width:45%"
                            +"+"
                            onClickFunction = onClickFun("+", i)
                        }
                    }
                }
            tr {
                td {
                    label {
                        +"Average grade = "
                    }
                }
                td {
                    label {
                        id = (students.lastIndex + 1).toString()
                    }
                }
            }
        }
    }
}

fun onClickFun(command: String, index: Int): (Event) -> Unit = {
    val element = (document.getElementById(index.toString()) as HTMLLabelElement)
    when (command) {
        "+" -> if (element.textContent!!.toInt() + 1 != 6)
            element.textContent = (element.textContent!!.toInt() + 1).toString()
        "-" -> if (element.textContent!!.toInt() - 1 != -1)
            element.textContent = (element.textContent!!.toInt() - 1).toString()
    }
    val result = document.getElementById((students.lastIndex + 1).toString()) as HTMLLabelElement
    var sumGrade = 0.0
    for (i in students.indices)
        sumGrade += (document.getElementById(i.toString()) as HTMLLabelElement).textContent!!.toFloat()
    result.textContent = " ${(sumGrade / students.size)}"
}