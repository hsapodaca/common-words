Common Words
============

A comparison of various solutions to find most frequently-used words in a book.

As an example, I'm using the original copy of [The Canterbury Tales by Geoffrey Chaucer](http://www.gutenberg.org/ebooks/2383):

```bash
wget -O canterbury-tales.txt http://www.gutenberg.org/cache/epub/2383/pg2383.txt 
```

Bash
----

One-liner:

```bash
    cat canterbury-tales.txt | tr [:space:][:punct:][:cntrl:] "\n" | tr [:upper:] [:lower:] | grep -v  -e "^$"  | sort | uniq -c | sort -nr | head -10
```


```bash
  11787 and
  10909 the
   7823 of
   6405 that
   5943 to
   5102 in
   4265 a
   4070 he
   3893 i
   3678 his
```

SQL
---

*Caveat:* No easy ability to strip punctuation unless with MariaDB, handle special cases like hyphens at the end of the line.

```sql
    CREATE TABLE words (word varchar(30) NOT NULL, KEY word (word) USING BTREE);
    LOAD DATA LOCAL INFILE 'canterbury-tales.csv' INTO TABLE words LINES TERMINATED BY ' ';
    SELECT count(*) c, word FROM test.words GROUP BY word ORDER BY c DESC LIMIT 10;
```

Results:
```
11918 and
11072 the
7843 of
6466 that
5972 to
5138 in
4305 a
4089 he
4025 i
3685 his
```

Scala
-----

[CommonWords.scala](common-words/blob/master/src/main/scala/org/commonwords/CommonWords.scala)

    sbt run

Output:

```
11918 and
11072 the
7843 of
6466 that
5972 to
5138 in
4305 a
4089 he
4025 i
3685 his
```

As you can see from the examples, English language may change overtime but its most common words tend stay the same.