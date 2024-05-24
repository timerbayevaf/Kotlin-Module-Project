data class Archive(
// Название архива
  val name: String,
//  Список заметок в архиве
  val notes: MutableList<Note> = mutableListOf()
)
