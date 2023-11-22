# CSC 345 Project

## Authors
Maria Fay Garcia, Mikos Bazerkanian, Rythm Sanghvi, Elliot Hagyard

## Presentation
Click this [link](https://youtu.be/SdyzEfa7AIk) to view our presentation.

## Overview of the Knapsack Problem

The [knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem) is an NP-complete problem of combinatorial optimization:

> “Given a set of items, each with a weight and a value, determine
which items to include in the collection so that the total weight
is less than or equal to a given limit and the total value is as
large as possible.”

The 0-1 knapsack problem is the most common solution approach and allows
for at most one copy of each distinct item. This project implements a series
of algorithms that approximate solutions to this problem.

## Testing

### Subset Sum
There are two parts to the implementation. The code that generates pseudo-random
superincreasing arrays (Super.java), and the code that implements the approximation
algorithms (SubsetSum.java). To test the generation of superincreasing arrays, run
the test code in SuperTest.java. To test the approximation algorithms, run the
test code in SubsetSumTest.java. 


## Recursive Algorithm
The Recursive Knapsack Algorithm is designed to solve the 0-1 knapsack problem,
a classic optimization challenge where the goal is to select a subset of items from
a given list, each with a weight and value, in order to maximize the total value
while adhering to a specified capacity constraint. Employing a recursive strategy,
the algorithm dynamically explores two choices for each item—whether to include it
in the knapsack or exclude it—ultimately updating the solution based on the path
that yields the maximum total value. This algorithm provides a foundational
understanding of the knapsack problem but may encounter efficiency challenges
for larger datasets, prompting consideration of more optimized techniques such
as dynamic programming.

    Time Complexity: O(2N) (Best and Worst Case)
    Space Complexity: O(N) (Call Stack)

## Dynamic Programming
Both approaches to dynamic programming for the Knapsack problem use an
array to store the output of including a value or not including the value for
specific input elements 

### Bottom Up: 

The bottom up approach fills in the the N*capacity sized 2-d array in a for loop.

    Time Complexity: O(N*Capacity)
    Space Complexity: O(N*Capacity)

### TopDown/Memoized Recursive:

The bottom up approach fills in every element in the table,even when the
 weights aren’t possible given the combinations of items and capacity.
The memoized recursive algorithm fills in the necessary elements, but has
a less well-defined runtime. Assuming there are no shared subproblems that
memoization actually speeds up, the algorithm would end up just being equivalent
to brute force; however, there should always be subproblems in the recursive tree.
In the worst case, the recursive calls would completely fill out the table of
values and so end up being O(N*d).

    Time Complexity: O(N*d)
    Space Complexity: O(N*d)

## Genetic Algorithm (GA)
A genetic algorithm simulates "survival of the fittest" among a population.
In the case of the knapsack problem, the population is a set of possible solutions.
The solutions will initially be randomly generated for the first generation. A
random population might look like this: [[1,0,1,0],[0,0,0,1], [0,1,1,0]]; where
a 1 represents the item’s inclusion in the solution and a 0 the exclusion.

The population is run through a fitness method whereupon each solution is
given a fitness value. The greater the value the better the solution. If
the solution’s summative weight is greater than the max threshold,
then the fitness value resets to 0.

Next, the solution with the greatest value is stored and automatically saved
to the next generation. This is called elitism. The next step in the implementation
is to place the population in a tournament method that randomly selects two
solutions and the one with the greater value is selected to be a parent for
the next generation.

Then two parents are randomly selected and simulate gene recombination
in gametes where different portions of the parents’ (solutions) genes
(items) are concatenated into a new solution and added to the next generation.

The new solution has a method called mutation that may randomly change if an
item is expressed or not–simulating mutation in real life. This process is
repeated until the next generation is the same size as the previous one.
Then the entirety of the program runs again.

Space Complexity: $O(2^d * d)$ where $d$ is the number of items. This is the same for every case. (Best, Worst, Average)

Time Complexity: $O(Nd^2)$ where $N$ is the number of solutions in a given
generation, where $N$ is the number of solutions in a given generation, and $d$ is the number of items in a solution.
This is the same for every case. (Best, Worst, Average)

## Subset Sum Problem
The [subset sum problem](https://en.wikipedia.org/wiki/Subset_sum_problem#Simple_1/2-approximation)
is a special case of the 0-1 knapsack problem. Given a set of integers $A$ and a
target sum $c$, it asks us to find a subset of $A$ that sums to $c$. This problem
is necessarily NP-hard, but if we let $A$ be superincreasing it becomes solvable
in polynomial time using a greedy algorithm. A set is superincreasing, it means that
$\forall{x} \in A$, $x$ is greater than the sum of all elements less than $x$.
For example, the set of all powers of two is a superincreasing set.

This project implements two approximation algorithms for the subset sum problem.
An approximation algorithm of the subset sum problem aims to find a subset with
a sum of at least $r$ and at most $T$ times the optimal sum.

The time complexity of the $r=\frac{1}{2}$ approximation depends on the time complexity of
sorting the set in descending order. Since this is done with insertion sort, the
best-case runtime of the algorithm is $O(N)$ (when the list is already sorted) and
the worst-case runtime of the algorithm is $O(N^2)$ (when the algorithm is sorted
in reverse order).

The time complexity of the fully polynomial time approximation scheme (FPTAS) depends
on the time complexity of sorting curSums in ascending order. Since this is
done using insertion sort, its best-case runtime is $O(k)$ and its worst-case
runtime is $O(k^2)$, where  $k$ is the size of the ArrayList. Since the time
complexity is $O(N)$, where $N$ is the size of the input set, the best-case
runtime is $O(Nk)$ and the worst-case runtime is $O(Nk^2)$. The space complexity
of the $r=\frac{1}{2}$ approximation is $O(N)$ and the space complexity of the FPTAS is $O(Nk)$,
where $N$ is the size of the input set and $k$ is the size of curSums.

## Bibliography

 - Caprara, Alberto; Kellerer, Hans; Pferschy, Ulrich (2000-02-01).
 "The Multiple Subset Sum Problem". SIAM Journal on Optimization. 11 (2): 308–319.
 doi:10.1137/S1052623498348481. ISSN 1052-6234.






