---
layout: post
title: Advanced Java - IV - Java Regex
categories: [Java]
---

API to define a pattern for `searching` or `manipulating` strings.

Widely used for defining constraints on strings such as password and email validation.

Java Regex API provides 1 interface and 3 classes in `java.util.regex` package.
   - MatchResult interface
   - Matcher class
   - Pattern class
   - PatternSyntaxException class

### Matcher class
   - Implements the `MatchResult interface`

   - Regex engine : Used to perform match operations on a character sequence.

   - Following are the important methods:
      - _boolean matches()_
      - _boolean find()_
      - _boolean find(int start)_
      - _String group()_
      - _int start()_
      - _int end()_
      - _int groupCount()_


### Pattern class
  - Compiled version of a regular expression.

  - Used for defining pattern for the regex engine.

  - Following are the important methods:
      - _static Pattern compile(String regex)_
      - _Matcher matcher(CharSequence input)_
      - _static boolean matches(String regex, CharSequence input)_
      - _String[] split(CharSequence input)_
      - _String pattern()_

```java
import java.util.regex.*;

public class RegexExample {  
    public static void main(String args[]){  

        Pattern p = Pattern.compile(".s");  
        Matcher m = p.matcher("as");  
        boolean b1 = m.matches();  

        boolean b2=Pattern.compile(".s").matcher("as").matches();  

        boolean b3 = Pattern.matches(".s", "as");  

        System.out.println(b+" "+b2+" "+b3);               // true true true
    }
}
```

## RegEx Essentials

|RegEx Character/Symbol|Usage & Meaning|
|---|---|
|^regex|match at the beginning of the line|
|regex$|match at the end of the line|
|[abc]|a, b, or c|
|[abc][vz]|can match a or b or c followed by either v or z|
|[^abc]|Any character except a, b, or c (negation)|
|[a-zA-Z]|a through z or A through Z, inclusive (range)|
|X\|Z|Finds X or Z.
|XZ|Finds X directly followed by Z|
|X?|X occurs once or not at all|
|X+|X occurs once or more times|
|X*|X occurs zero or more times|
|X{n}|X occurs n times only|
|.|Any character (may or may not match terminator)|
|\d|Any digits, short of [0-9]|
|\D|Any non-digit, short for [^0-9]|
|\s|Any whitespace character, short for [\t\n\x0B\f\r]|
|\S|Any non-whitespace character, short for [^\s]|
|\S+|Several non-whitespace characters|
|\w|Any word character, short for [a-zA-Z_0-9]|
|\W|Any non-word character, short for [^\w]|
|a(?!b)|(Negative look ahead) match "a" if "a" is not followed by "b".|

> The regex is applied on the text from left to right.
