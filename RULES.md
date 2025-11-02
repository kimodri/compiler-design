# RULES — Quick Cheat-sheet for [project name]

Keep this page short. Use it during presentations and when implementing the lexer.

1) Character set
- Letters: `A-Z`, `a-z` (Unicode letters supported)
- Digits: `0-9`
- Special symbols: `+ - * / % = < > ! ** ( ) [ ] { } , ; : . _ ~`
- Whitespace: space, `\t`, `\n`

2) Identifiers (simple rule)
- Start with a letter. May contain letters, digits, single underscores between parts.
- Cannot start with digit or underscore. No consecutive underscores. Max length 64.
- Case-insensitive (the lexer/parser should fold to lower-case for keywords).

Example identifier regex (simple/portable):
```
^[A-Za-z][A-Za-z0-9]*(?:_[A-Za-z0-9]+)*$   # then check length <= 64
```

3) Numbers
- Integer: `123` → regex: `^\d+$`
- Decimal: `12.34` → regex: `^\d+\.\d+$`
- Combined (simple): `^\d+(?:\.\d+)?$`

4) Strings and chars
- Strings: double-quoted, allow escape sequences: `"Hello\n"`
  - Regex concept: `^"([^"\\]|\\.)*"$`
- Symbol/char: single-quoted single character: `'A'` or escaped ` '\n' `

5) Comments (important for lexer order)
- Single-line: begin with `~~` and run to end-of-line.
- Multi-line: begin with `~` and end with the next `~`. Unclosed multi-line comment is a syntax error.

6) Operators and delimiters (token list)
- Arithmetic: `+  -  *  /  %  **`
- Relational: `==  !=  >  <  >=  <=`
- Logical: `and  or  not`
- Assignment: `=`
- Terminator: `;`  Separators: `,`
- Brackets: `() [] {}`

7) Tokenization order (machine notes — very important for lexer)
1. Comments (single- and multi-line) — strip/emit COMMENT token first.
2. Strings and char literals — must be recognized before operators or delimiters.
3. Numbers (decimal then integer).
4. Identifiers/Keywords — match identifier regex and then check against keyword list (case-insensitive).
5. Operators (multi-char operators like `**`, `==`, `!=`, `>=`, `<=` must be matched before single-char variants).
6. Delimiters, punctuation (`, ; ( ) { } [ ] :`).
7. Whitespace — skip except in string literals or comments.

8) Keywords (quick list)
- Types: `number`, `decimal`, `symbol`, `truefalse`, `text`, `list`
- Control / flow / misc: `if`, `elif`, `else`, `repeat`, `repeatfor`, `stop`, `display`, `nothing`, `and`, `or`, `not`

9) Simple example (tokenized / human-readable)

Source:
```
~~ Single-line comment
number x = 10;
text name = "Jane";
if x > 5 { display("Hi", name); } else { display("Bye"); }
~multi
line comment~
```

Token stream (conceptual):
```
COMMENT, KEYWORD(number), IDENT(x), OP(=), NUMBER(10), SEP(;),
KEYWORD(text), IDENT(name), OP(=), STRING("Jane"), SEP(;),
KEYWORD(if), IDENT(x), OP(>), NUMBER(5), LBRACE({), KEYWORD(display), LPAREN, STRING("Hi"), SEP(,), IDENT(name), RPAREN, SEP(;), RBRACE, KEYWORD(else), LBRACE, KEYWORD(display), LPAREN, STRING("Bye"), RPAREN, SEP(;), RBRACE,
COMMENT
```

10) Implementation tips (beginner friendly)
- Keep the lexer simple: follow the tokenization order above.
- Normalize identifiers to lower-case before checking keywords.
- Emit clear error messages for: unclosed strings, unclosed multi-line comments, illegal characters, identifier length > 64.
- For class presentations: show source → token stream → parse tree.

If you want, I can convert these rules into ready-to-copy Java regexes and short helper methods to paste into `lexical-analyzer/Lexer.java`.

--- End of cheat-sheet ---
