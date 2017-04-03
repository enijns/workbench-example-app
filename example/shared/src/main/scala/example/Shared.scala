package example


trait Api{
  def list(path: String): Seq[String]

  def save(example: Example): Example

  def all(): Seq[Example]
}

case class Example(str: String, int: Int, id: Option[Int])