import java.util.Scanner

class NotesApp {
  private val archives = mutableListOf<Archive>()
  private val scanner = Scanner(System.`in`)

  fun start() {
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
    println("Введите имя архива:")
    val name = scanner.nextLine()
    archives.add(Archive(name))
    println("Архив '$name' создан.")
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
    println("Введите заголовок заметки:")
    val title = scanner.nextLine()
    println("Введите содержание заметки:")
    val content = scanner.nextLine()
    archive.notes.add(Note(title, content))
    println("Заметка '$title' создана.")
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

