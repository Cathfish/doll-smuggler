# doll-smuggler

doll-smuggler is a program written in Clojure to solve the following problem:

You are a drug trafficker. Every day you meet with a different nice older lady (the mule) and find out how much weight she can carry in her handbag. You then meet with your supplier who has packed various drugs into a myriad of collectible porcelain dolls. Once packed with drugs, each of the precious dolls has a unique combination of weight and street value. Sometimes your supplier has more dolls than the nice lady can carry, though space in her handbag is never an issue. Your job is to choose which dolls the kind soul will carry, maximizing street value, while not going over her weight restriction.

A test suite is included to verify this solution.

## Installation

The source code for this project is here:
<br/>https://github.com/Cathfish/doll-smuggler<br/>
Clone the whole repository or download just the src files as you see fit.

Make sure the src/doll-smuggler directory (or a copy of it) finds it's way into the src/ directory of your project. For example if your project is called "my-project", your mp_project/src/ folder should contain a "my_project" directory and a "doll_smuggler" directory to be able to use the doll-smuggler program.

To run the test suite, you'll need leiningen if you don't already have it. Find it here:
       <br/> https://github.com/technomancy/leiningen

## Usage

The doll-smuggler program should be included in your current project via:
<br/> (:require [doll-smuggler.core :as ALIAS])<br/>
where ALIAS is replaced by an alias of your choice. You can of course choose not to use an alias by leaving off ":as ALIAS".

If doll-smuggling is part of a larger ring of illicit activity, run:
<br/> (pack-dolls MAX-WEIGHT DOLLS) <br/>
where MAX-WEIGHT is a positive integer denoting the maximum weight of the handbag, and DOLLS is a collection of the form:
<br/>#{{:name STRING1 :weight POSITIVE_INT1 :value POSITIVE_INT1}
<br/>  {:name STRING2 :weight POSITIVE_INT2 :value POSITIVE_INT2}
<br/>  {:name STRING3 :weight POSITIVE_INT3 :value POSITIVE_INT3}
<br/>  ...								 }

The collection could alternatively be a vector of maps or list of maps. pack-dolls will return a collection containing the optimal set of drug-filled dolls to pack in the handbag, which you can use for other purposes in your program.

If doll-smuggling is your only forray into law-breaking, you might instead run:
<br/> (pack-dolls-and-print MAX-WEIGHT DOLLS)
which will run the pack-dolls function and will print the results directly to your terminal.

To run the test suite from the command line:
	1. cd to the doll-smuggler directory
	2. lein test

### Future features

In the next few updates look out for the stealthy function jedi-mind-tricks which, when run, will distract nearby law enforcement with a wave of the hand and the following output:
	"These aren't the dolls you're looking for."

### References

This is the first program I've ever written in Clojure. It's a really cool and powerful language, but it can be a tough one to wrap your head around if you've never done functional programming before (and maybe even if you have).Nevertheless, I recommend taking some time to learn the basics. The following resources were incredibly helpful for me during this project.

https://kimh.github.io/clojure-by-example/#about
https://clojuredocs.org/
http://www.braveclojure.com/do-things/

Additionally this was a helpful resource for learning about the knapsack problem.
https://en.wikipedia.org/wiki/Knapsack_problem

## License

Copyright © 2017 Catherine Fisher

Distributed under the Eclipse Public License either version 1.0 or any later version
