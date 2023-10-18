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

## Time Complexity Analysis

The time complexity of the algorithm `T(n)` describes the number of elementary operations it performs depending on the amount of input data, where `n` is the number of intervals in the `input.txt` file.

The time complexity consists of operations related to reading input data from the file at the beginning of the program, denoted as `T0(n)`, the complexity of the first method `T1(n)`, the complexity of the second method `T2(n)`, and the operations involved in writing the answer to a file `T3(n)`.

1. **Reading Data (T0(n)):** The complexity of reading data from the file is `T0(n) = n + 1`, as the procedure is represented as a loop that reads `n` intervals from the file and the number `l` (the number of intervals) at the first line.

2. **Method 1 (T1(n)):** The complexity of method 1 consists of merge sorting and adding the intervals. `T1(n) = nlogn + (n - 1) = θ(n log n)`, as merge sort has a complexity of nlogn, and after sorting, the algorithm performs n-1 comparisons in a loop.

3. **Method 2 (T2(n)):** The complexity of method 2 consists of filling the number line array and converting the obtained number line into an array of intervals. `T2(n) = (n + 1) * 1000 = θ(n)` – in the worst case, as filling the number line can take a maximum of `1000 * n` elementary operations when each of the `n` intervals must be `[0; 1000]`, and the conversion of the number line into an array of intervals is done in a loop with `n` iterations.

4. **Writing Data (T3(n)):** The complexity of writing output data to the file involves two loops, taking `the number of obtained intervals` – a real number `c` (`c` < `n`). `T3(n) = 2 * c`.

Therefore, the total time complexity of the algorithm is: 

> `T(n) = nlogn + 2n + 1 + 2 * c = θ(n log n)`.


## Repository Contents

This repository includes the solution code for the "Sum of Intervals" problem. The code processes the input intervals and calculates the sum of intervals using the minimum number of intervals.

## How to Use

You can use the provided code to solve the "Sum of Intervals" problem by providing the input in the specified format and running the code that is stored in the `Main.java` file. In order to generate input file you may also use the script from `makeInputFile.py`. The results will be written to an output file, as described in the problem statement.

\
[![author](https://img.shields.io/badge/author-leucist-blue)](https://github.com/Leucist/)
