import java.util.Scanner

class NotesApp {
  private val archives = mutableListOf<Archive>()
  private val scanner = Scanner(System.`in`)

  fun start() {
    println("Приложение 'Заметки' приветствует Вас!")
    MenuScreen(
      items = archives,
      title = "Список архивов:",
      displayItem = { it.name },
      onCreateItem = { createArchive() },
      onSelectItem = { archive -> selectArchive(archive) },
      exitLabel = "Выход"
    ).display()
  }

  private fun createArchive() {
    while (true) {
      println("Введите имя архива:")
      val name = scanner.nextLine()
      if (name.isNotBlank()) {
        archives.add(Archive(name))
        println("Архив '$name' создан.")
        return
      } else {
        println("Имя архива не может быть пустым. Пожалуйста, введите имя архива.")
      }
    }
  }

  private fun selectArchive(archive: Archive) {
    MenuScreen(
      items = archive.notes,
      title = "Архив: ${archive.name}",
      displayItem = { it.title },
      onCreateItem = { createNoteInArchive(archive) },
      onSelectItem = { note -> viewNoteInArchive(note) }
    ).display()
  }

  private fun createNoteInArchive(archive: Archive) {
    while (true) {
      println("Введите заголовок заметки:")
      val title = scanner.nextLine()
      if (title.isNotBlank()) {
        while (true) {
          println("Введите содержание заметки:")
          val content = scanner.nextLine()
          if (content.isNotBlank()) {

            archive.notes.add(Note(title, content))
            println("Заметка '$title' создана.")
            return
          } else {
            println("Содержание заметки не может быть пустым. Пожалуйста, введите содержание заметки.")
          }
        }
      } else {
        println("Заголовок заметки не может быть пустым. Пожалуйста, введите заголовок заметки.")
      }
    }
  }

  private fun viewNoteInArchive(note: Note) {
    println("Заметка: ${note.title}")
    println(note.content)
    println("0. Назад")
    while (true) {
      if (scanner.nextLine() == "0") return
      else println("Неправильный выбор, попробуйте снова.")
    }
  }
}

