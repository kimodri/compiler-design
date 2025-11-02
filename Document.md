## Documentation for PL Proposal

### I. Introduction

C remains to be the foundation on which most modern languages stand. It revolutionized the movement of systems development and computer science reaching heights never explored before. While C's power is invaluable for programmers, its lexical and syntactic structure poses multiple difficulties for users, even more so for beginners. Several language elements of C were designed primarily for the sake of compactness and machine-learning efficiency rather than clarity or readability. As a result, new learners may find C challenging to write and understand correctly in early stages of programming.

With this, [project name] proposes the development of a beginner-friendly programming language inspired by C. It aims to bridge the existing gaps within readability, writability, and reliability by using a clearer and more consistent lexical and syntactical design, while retaining its efficiency and procedural nature. Strengthening its weaknesses (symbol ambiguity, inconsistent structure, cryptic syntax) will make program logic easier to read, understand, and maintain, especially for beginners. This provides an avenue where learners can focus more on developing programs without being overwhelmed by the language's syntactic complexity.

---

## II. Syntactic Elements of the Language

### 1. Character Set (Harold)

The character set of a programming language defines all the valid characters that can be used to write programs, including letters, digits, symbols, and whitespace characters. These characters serve as the building blocks for identifiers, literals, operators, delimiters, and other syntactic elements in the language.

1.1 Letters
- Uppercase Letters: A–Z
- Lowercase Letters: a–z
- Unicode Letters: The language also supports Unicode alphabetic characters to allow international identifiers (e.g., ñ, ü, α, β).
Letters are used in identifiers, keywords, and reserved words.

1.2 Digits
- Decimal Digits: 0–9
Digits are used to represent numeric constants and may also appear in identifiers (but not as the first character).

1.3 Special Symbols
The following special symbols are recognized and used in the language:

`+  -  *  /  %  =  <  >  !  **  (  )  [  ]  {  }  ,  ;  :  .  _  ~`

- Arithmetic and relational operators use a subset of these symbols.
- The underscore (`_`) may be used within identifiers.
- The tilde (`~`) is reserved for comments.
- Parentheses, brackets, and braces are used for grouping and structure.
- The semicolon (`;`) marks the end of a statement.

1.4 Whitespace Characters
- Whitespace characters include: Space (` `), Tab (`\t`), Newline (`\n`).
Whitespace separates tokens and improves readability, but generally has no syntactic significance except in comments and string literals.

1.5 Escape Sequences (in string literals)

| Escape | Meaning |
|--------|---------|
| `\n`   | Newline |
| `\t`   | Tab |
| `\\`  | Backslash |
| `\"`  | Double quote |
| `\'`   | Single quote |

1.6 Character Encoding

All programs written in Java/C/Ruby must be encoded in UTF-8, ensuring compatibility with Unicode characters and modern text-processing systems.

---

### 2. Identifiers (Faith)

An identifier is a name used to represent variables, functions, and user-defined elements in the program. Rules for identifiers in this language:

- It starts with a letter (A–Z or a–z, including Unicode letters).
- It may contain letters, digits (0–9), or single underscores (`_`) in between.
- It cannot start with a digit or underscore, nor have consecutive underscores.
- It is case-insensitive.
- It must not match any reserved keyword.
- It must contain at least one character and no more than 64 characters in total.

---

### 3. Operation Symbols (Kim)

[The proposed programming language] uses the following operators:

#### Arithmetic Operators

| Operator | Description |
|----------|-------------|
| `+`      | Addition. Adds two operands together. |
| `-`      | Subtraction. Subtracts the second operand from the first. |
| `*`      | Multiplication. Multiplies two operands. |
| `/`      | Division. Divides the first operand by the second. |
| `%`      | Modulo. Remainder of integer division. |
| `**`     | Exponentiation. First operand raised to the second. |

#### Relational Operators

| Operator | Description |
|----------|-------------|
| `==`     | Equality. True if values equal. |
| `!=`     | Inequality. True if values not equal. |
| `>`      | Greater than. True if left larger than right. |
| `<`      | Less than. True if left smaller than right. |
| `>=`     | Greater than or equal. |
| `<=`     | Less than or equal. |

#### Logical Operators

| Operator | Description |
|----------|-------------|
| `and`    | Logical AND: true if both operands are true. |
| `or`     | Logical OR: true if at least one operand is true. |
| `not`    | Logical NOT: negates a boolean. |

---

### 4. Keywords and Reserved Words (Kim)

4.a Keywords

These are the language keywords and short descriptions of their usage.

- `number` — declares an integer-like (number) variable.
- `decimal` — declares a floating-point-like variable.
- `symbol` — declares a single-character value (char-like).
- `truefalse` — boolean type (true/false values).
- `text` — string type.
- `list` — list/array type.
- `and`, `or`, `not` — logical operators (also listed as keywords for readability).
- `if` — conditional statement.
- `elif` — conditional else-if.
- `else` — conditional fallback.
- `repeat` — while-like loop (repeat while condition).
- `repeatfor` — counter-controlled loop.
- `stop` — break: terminates loop.
- `display` — output to console.

4.b Reserved words

- `nothing` — reserved to represent null / no value.

---

### 5. Noise Words (Jan)

Noise words improve readability but do not affect program execution. Examples of usage:

- `then` — optional visual cue in conditional expressions: `if x > y then display x`.
- `start` / `end` — optional block delimiters in some constructs (visual only).
- `so` — used as a noise word in loops: `repeatfor i = 1 < 5 so display(i)`.

---

### 6. Comments (Neo)

| Comment Type | Syntax | Rule |
|--------------|--------|------|
| Single-line  | `~~<comment>` | Begins with two tildes and terminates at EOL. No closing delimiter required. |
| Multi-line   | `~<comment>~`  | Begins with a single tilde and must end with another tilde. If left unclosed, it is a syntax error. |

Note: The tilde is also a noise/special character in the language and reserved for comment syntax.

---

### 7. Blanks (Spaces) (Jan)

Rules and examples:

- Optional use: Statements may be written with or without spaces: `z = x + y` or `z=x+y`.
- Multiple blanks: Consecutive spaces are treated as one: `Number     y      =      5` equals `Number y = 5`.
- Inside identifiers: Blanks are not allowed within identifiers or keywords. `repeatfor i < 5` is accepted; `repeat for i < 5` is not.
- Indentation and formatting: Tabs and newlines are for readability only and have no syntactic effect.

---

### 8. Delimiters and Brackets (Harold)

Delimiters and brackets group elements, separate items, and define structure.

| Delimiter/Bracket | Symbol | Purpose | Example |
|-------------------|--------|---------|---------|
| Statement Terminator | `;` | Marks the end of a statement. | `number x = 10;` |
| List Separator | `,` | Separates items in lists or function args. | `list my_list = [1, 2, 3]; display(a, b);` |
| Assignment | `=` | Variable assignment/initialization. | `number count = 0;` |
| Grouping/Precedence | `()` | Control order of operations and function parameters. | `result = (a + b) * c;` |
| Array Indexing | `[]` | Array/list literal and indexing. | `list data = [10, 20, 30]; value = data[1];` |
| Block/Scope | `{}` | Define code blocks for conditionals, loops, functions. | `if condition { display("True"); }` |
| Text Literal | `" "` | Double quotes enclose strings. | `text name = "Jane Doe";` |
| Symbol Literal | `' '` | Single quotes enclose single characters. | `symbol first_char = 'A';` |
| Key-Value Separator | `:` | For structured data or future features. | (To be defined for maps/dicts) |

---

### 9. Free-and-Fixed-Field Formats (Anthea)

This language uses free format: statements may span multiple lines as long as they end with a semicolon (`;`). There are no indentation rules enforced by the parser.

---

### 10. Expressions (Gab)

Expressions combine operators and operands to produce values. This section describes arithmetic, relational, and logical expressions as well as evaluation order.

#### Arithmetic Expressions

Rules:

- Order of Operations: follow PEMDAS (Parentheses, Exponents, Multiplication/Division, Addition/Subtraction).
- Parentheses override natural precedence.
- Exponentiation (`**`) evaluated after parentheses and before multiplication/division.
- Multiplication and division evaluated left-to-right.
- Addition and subtraction evaluated left-to-right.
- Unary operators: unary plus (`+x`) and unary minus (`-x`) apply to single operands.

Examples: `++x` or `--x` are not part of the core grammar unless explicitly added; unary plus/minus remain supported.

#### Relational Expressions

Relational operators compare two values and produce a boolean (`truefalse`) result. Operators: `>`, `<`, `==`, `!=`, `>=`, `<=`.

#### Logical Expressions

Logical expressions combine relational expressions using `and`, `or`, `not`.

Rules:

- `and` evaluated before `or` unless parentheses override.
- `not` is unary and binds tightly to the expression it negates.
- Short-circuit evaluation applies: in `A and B`, if `A` is false, `B` may not be evaluated.

Examples:

- Arithmetic: `(5 + 1) * 7`
- Relational: `(5 + 1) > 7`
- Logical: `(5 > 1) and (7 == 4)`

---

### 11. Statements (Simoun)

Statement types with syntax patterns and examples.

#### Declaration and Assignment Statements

| Statement Type | Syntax Pattern | Example | Purpose |
|----------------|----------------|---------|---------|
| Declaration & Initialization | DATA_TYPE IDENTIFIER = EXPRESSION ; | `number age = 25;` | Declare and assign a variable. |
| Declaration only | DATA_TYPE IDENTIFIER ; | `text username;` | Declare a variable without initialization. |
| Assignment | IDENTIFIER = EXPRESSION ; | `age = age + 1;` | Assign a new value to an identifier. |
| List Initialization | list IDENTIFIER = [ EXPRESSION {, EXPRESSION}* ] ; | `list scores = [90, 85, 95];` | Declare and initialize list. |
| Nothing Assignment | IDENTIFIER = nothing ; | `username = nothing;` | Assign null-like value. |

#### Output Statement

| Statement Type | Syntax Pattern | Example | Purpose |
|----------------|----------------|---------|---------|
| Display statement | `display ( ARGUMENT { , ARGUMENT }* ) ;` | `display("Hello", name, 20);` | Output one or more comma-separated values. |

#### Control Flow Statements

Conditional Statements:

| Statement Type | Syntax Pattern | Example |
|----------------|----------------|--------|
| If statement | `if RELATIONAL_EXPRESSION { STATEMENTS }` | `if age > 18 { display("Adult"); }` |
| If-Elif-Else block | `if ... then { ... } elif ... then { ... } else { ... } end` | `if x < 0 then { display("Neg"); } elif x == 0 then { display("Zero"); } else { display("Pos"); } end` |

Notes: noise words like `then` and `end` are optional visual cues supported to improve readability.

Looping Statements:

| Statement Type | Syntax Pattern | Example | Purpose |
|----------------|----------------|---------|---------|
| While-style (repeat) | `repeat RELATIONAL_EXPRESSION so { STATEMENTS } end` | `repeat count < 5 so { count = count + 1; } end` | Repeats block while condition is true. |
| For-style (repeatfor) | `repeatfor IDENTIFIER = EXPRESSION < EXPRESSION so { STATEMENTS } end` | `repeatfor i = 1 < 10 so { display(i); } end` | Counter-controlled loop while initial < limit. |
| Break | `stop ;` | `if done { stop; }` | Terminates innermost loop immediately. |

---

## Appendix — Instructions.md (summary)

Sections to include in the instructor-provided `Instructions.md`:

- Especial characters
- Identifiers
- Operators (Arithmetic, Relational, Logical)
- Keywords
- Reserved words
- Noise words
- Comments

Suggested structure for authors/students:

I. Introduction — Describe the proposed PL and inspiration.

II. Syntactic Elements — Character set, identifiers, operations, keywords/reserved words, noise words, comments, blanks, delimiters, free/fixed format, expressions, statements.

For each section include explicit rules, regular expressions when possible, example programs, and a short “machine” description (i.e., how the lexer/parser will treat the feature).

---

If you want, I can:

- Add a short example program in this language to demonstrate syntax.
- Generate a first-cut lexer specification (regexes) or a sample `lex`/`flex` or Java lexer implementation.
- Add formal regular expressions for identifiers, numbers, strings, and comments for use in your lexical analyzer.

Please tell me which follow-up you'd like and I will update the repository accordingly.
