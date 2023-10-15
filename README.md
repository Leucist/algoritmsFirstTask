# Project Description

This repository contains a solution to the "Sum of Intervals" problem, which is a part of the "Algorithms and Data Structures" coursework.

## Problem Statement

**Variation E - "Sum of Intervals"**

**Problem Statement:** Given a sequence of `n` closed intervals `<a_i, b_i>`, where `a_i` and `b_i` are integers, and `1 <= a_i, b_i <= 1 000`, `1 <= n <= 1 000 000`, find the sum of intervals from the sequence using the minimum possible number of intervals.

## Input and Output

- **Input:** The input is provided in a text file named `inputFile.txt`. The first line contains the length `n` of the sequence. Subsequent `n` lines contain the intervals in the form of pairs of integers separated by a single space. The first number in each pair represents the beginning of the interval, and the second number represents the end of the interval.

- **Output:** The output is written to a text file named `outputFile.txt`. At least two sets of solutions to the problem are provided in the output. Each set consists of:
  - A line containing the length `l` of the resulting sequence (formed by summing intervals).
  - Subsequent lines in the set contain pairs of numbers separated by spaces, representing the start and end of the resulting intervals.

## Repository Contents

This repository includes the solution code for the "Sum of Intervals" problem. The code processes the input intervals and calculates the sum of intervals using the minimum number of intervals.

## How to Use

You can use the provided code to solve the "Sum of Intervals" problem by providing the input in the specified format and running the code that is stored in the `Main.java` file. In order to generate input file you may also use the script from `makeInputFile.py`. The results will be written to an output file, as described in the problem statement.

\
[![author](https://img.shields.io/badge/author-leucist-blue)](https://github.com/Leucist/)
