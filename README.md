You are a drug trafficker. Every day you meet with a different nice old lady (the mule) and find out how much weight she can carry in her handbag. You then meet with your supplier who has packed various drugs into a myriad of collectible porcelain dolls. Once packed with drugs, each of the precious dolls has a unique combination of weight and street value. Sometimes your supplier has more dolls than grandma can carry, though space in her handbag is never an issue. Your job is to choose which dolls the nice old lady will carry, maximizing street value, while not going over her weight restriction.

Write a Clojure function which chooses the optimal set of drug-packed porcelain dolls given a set of dolls where each has a unique weight and value combination, while staying within a given weight restriction, W, maximizing the total street value of drugs delivered by the grandma.

Include a set of executable high-level tests for your solution.

Hints:
* use leiningen - https://github.com/technomancy/leiningen
* read this - http://en.wikipedia.org/wiki/Knapsack_problem
* find more interesting test data (known inputs & outputs) on the internets
