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
    SELECT word, count(*) c FROM test.words GROUP BY word ORDER BY c DESC LIMIT 10;
```

Results:
```
and     11918
the     11072
of      7843
that    6466
to      5972
in      5138
a       4305
he      4089
i       4025
his     3685
```

Scala
-----

[CommonWords.scala](./src/org/commonwords/CommonWords.scala)

    sbt run

Output:

    List((and,11918), (the,11072), (of,7843), (that,6466), (to,5972))


As you can see, English language may change overtime but its most common words tend stay the same.