import com.typesafe.config.ConfigFactory

import scala.io.Source

/* finds most used words in a plaintext file */
object CommonWords extends App {
  lazy val filename = ConfigFactory.load.getString("input")
  lazy val numOfWords = ConfigFactory.load.getInt("numOfWords")

  /* given a filename, get all words from the file */
  lazy val words = Source.fromFile(filename).mkString.toLowerCase.split("\\P{L}+")

  /* get top n most used words and the count of their usages */
  def topWords = words.groupBy(w => w).mapValues(_.size).toList.sortBy(-_._2).take(numOfWords)

  println (topWords)
}