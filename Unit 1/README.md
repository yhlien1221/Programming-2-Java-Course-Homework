# 《Text Analysis Tool》 - My First Week Java Project

## Introduction
This is my project as part of my journey to learn **Java programming**.  
The goal of this simple text analysis tool is to demonstrate my foundational skills in the language.  
The program takes a text input from the user and performs several key analyses:

- Calculates the total number of characters and words.
- Identifies the most frequent character in the text.
- Allows the user to query the frequency of a specific character or word.
- Determines the total count of unique words.

This project showcases my initial understanding of core Java concepts, including basic syntax, data structures (`Map`, `HashMap`), and user interaction.

---

## Key Features
- **Character and Word Counting**: Provides an accurate count of total characters and words in the input text.  
- **Most Common Character**: Analyzes the text to find and display the character that appears most frequently.  
- **Interactive Frequency Lookup**: The user can enter a specific character or word to see how many times it appears in the text.  
- **Unique Word Count**: Calculates and displays the total number of distinct words.  

---

## Technology Stack
- **Language**: Java  
- **Build Tool**: None (standard Java compilation)  
- **Data Structures**: `HashMap` (used for frequency counting of characters and words)  

---

## Code Highlights
- **User Input Handling**: The `Scanner` class is used to efficiently read multi-line text input from the user.  
- **Word Tokenization**: Utilizes a robust regular expression `[\\s\\p{Punct}]+` to accurately split the text into words, effectively handling multiple spaces and punctuation.  
- **Frequency Counting**: Employs the `getOrDefault` method of `HashMap` for a clean and concise implementation of character and word frequency statistics.  
- **Resource Management**: Includes `scanner.close()` to properly close the `Scanner` object and prevent resource leaks.  

---

## Program Flowchart

flowchart TD
    A[Start Program] --> B[Prompt User for Text Input]
    B --> C[Read Input using Scanner]
    C --> D[Count Characters & Words]
    D --> E[Build Frequency Maps using HashMap]
    E --> F{User Query?}
    F -->|Yes| G[Return Frequency of Character/Word]
    F -->|No| H[Display Results]
    G --> H
    H --> I[Show Unique Word Count]
    I --> J[Close Scanner & End Program]



## Example Run

### Input
```
Hello world! Hello Java.
```

## Output
```
Total Characters: 23
Total Words: 4
Most Frequent Character: 'l' (5 times)
Unique Words: 3
Enter a word/character to query: Hello
Frequency of 'Hello': 2
```

## Personal Reflections & Learnings

- Completing this project was a valuable experience that allowed me to solidify my understanding of fundamental Java skills:

- Basic Syntax: Applied core Java syntax, data types, and variable declarations.

- Control Flow: Utilized if-else statements and for loops to control the program's logic.

- Collections Framework: Hands-on practice with Map and HashMap, two essential data structures for frequency analysis.

- String Manipulation: Gained experience using built-in String methods like length() and split(), as well as regular expressions.

- Command-Line Interaction: Built a basic program that interacts with the user via the command line.

This assignment gave me a solid foundation in Java programming and made me excited to continue my learning journey.

