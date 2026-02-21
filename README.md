# Polynomial Reconstruction using Lagrange Interpolation

## Problem
Roots of a polynomial are provided in JSON format. Each root contains:
- x value (JSON key)
- y value encoded in a specific base

Goal:
- Parse JSON input
- Convert base encoded values to decimal
- Select k roots (k = m + 1)
- Reconstruct polynomial
- Compute constant term f(0)

---

## Approach
- Parsed JSON using org.json
- Converted values using BigInteger
- Sorted roots and selected smallest k roots
- Applied Lagrange interpolation
- Evaluated polynomial at x = 0

---

## Tech Stack
Java, BigInteger, BigDecimal, org.json

---

## Project Structure
FindConstantTerm.java  
testcases.json  
json-20231013.jar  
README.md  
output.txt  

---

## Run

Compile:
javac -cp ".;json-20231013.jar" FindConstantTerm.java

Run:
java -cp ".;json-20231013.jar" FindConstantTerm

---

## Output
Testcase1:
3

Testcase2:
-6290016743746469796

---

## Algorithm
Lagrange Interpolation

f(0)=Σ yi Π(0-xj)/(xi-xj)

---

## Author
Santhosh
