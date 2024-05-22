import java.util.Scanner

class MenuScreen<T>(
  private val items: List<T>,
  private val title: String,
  private val displayItem: (T) -> String,
  private val onCreateItem: (() -> Unit)? = null,
  private val onSelectItem: ((T) -> Unit)? = null,
  private val exitLabel: String = "Назад"
) {
  private val scanner = Scanner(System.`in`)

  fun display() {
    while (true) {
      println(title)
      println("0. Создать")
      items.forEachIndexed { index, item ->
        println("${index + 1}. ${displayItem(item)}")
      }
      println("${items.size + 1}. $exitLabel")

      when (val choice = readInt()) {
        0 -> onCreateItem?.invoke()
        in 1..items.size -> onSelectItem?.invoke(items[choice - 1])
        items.size + 1 -> return
        else -> println("Неправильный выбор, попробуйте снова.")
      }
    }
  }

  private fun readInt(): Int {
    while (true) {
      val input = scanner.nextLine()
      val value = input.toIntOrNull()
      if (value != null) {
        return value
      } else {
        println("Пожалуйста, введите цифру.")
      }
    }
  }
}

