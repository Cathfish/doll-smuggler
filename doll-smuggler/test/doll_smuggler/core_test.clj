(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

(def given-test-dolls
  [{:name "luke" :weight 9 :value 150}
  {:name "anthony" :weight 13 :value 35}
  {:name "candice" :weight 153 :value 200}
  {:name "dorothy" :weight 50 :value 160}
  {:name "puppy" :weight 15 :value 60}
  {:name "thomas" :weight 68 :value 45}
  {:name "randal" :weight 27 :value 60}
  {:name "april" :weight 39 :value 40}
  {:name "nancy" :weight 23 :value 30}
  {:name "bonnie" :weight 52 :value 10}
  {:name "marc" :weight 11 :value 70}
  {:name "kate" :weight 32 :value 30}
  {:name "tbone" :weight 24 :value 15}
  {:name "tommy" :weight 48 :value 10}
  {:name "uma" :weight 73 :value 40}
  {:name "grumpkin" :weight 42 :value 70}
  {:name "dusty" :weight 43 :value 75}
  {:name "grumpy" :weight 22 :value 80}
  {:name "eddie" :weight 7 :value 20}
  {:name "tory" :weight 18 :value 12}
  {:name "sally" :weight 4 :value 50}
  {:name "babe" :weight 30 :value 10}])

(def unsorted-dolls 
  [{:name "dusty" :weight 2 :value 40}
  {:name "luke" :weight 2 :value 50}
  {:name "sally" :weight 4 :value 30}
  {:name "grumpy" :weight 1 :value 40}
  {:name "grumpkin" :weight 1 :value 30}
  {:name "candice" :weight 1 :value 50}  
  {:name "puppy" :weight 2 :value 30}
  {:name "marc" :weight 3 :value 40}
  {:name "dorothy" :weight 2 :value 50}
  {:name "randal" :weight 3 :value 30}])

(def sorted-dolls
  [{:name "candice" :weight 1 :value 50}
  {:name "dorothy" :weight 2 :value 50}
  {:name "luke" :weight 2 :value 50}
  {:name "grumpy" :weight 1 :value 40}
  {:name "dusty" :weight 2 :value 40}
  {:name "marc" :weight 3 :value 40}
  {:name "grumpkin" :weight 1 :value 30}
  {:name "puppy" :weight 2 :value 30}
  {:name "randal" :weight 3 :value 30}
  {:name "sally" :weight 4 :value 30}])

(def equal-weight-dolls
  #{{:name "luke" :weight 10 :value 150}
  {:name "anthony" :weight 10 :value 35}
  {:name "candice" :weight 10 :value 200}
  {:name "dorothy" :weight 10 :value 160}
  {:name "puppy" :weight 10 :value 60}})

(def equal-value-dolls
  '({:name "grumpy" :weight 22 :value 100}
  {:name "eddie" :weight 7 :value 100}
  {:name "tory" :weight 18 :value 100}
  {:name "sally" :weight 4 :value 100}
  {:name "babe" :weight 30 :value 100}
  {:name "babe" :weight 21 :value 100}))

(def basic-set-of-maps
  #{{:name "dollA" :weight 1 :value 10}
  {:name "dollB" :weight 2 :value 20}
  {:name "dollC" :weight 3 :value 30}})

(def basic-vector-of-maps
  [{:name "dollA" :weight 1 :value 10}
  {:name "dollB" :weight 2 :value 20}
  {:name "dollC" :weight 3 :value 30}])

(def basic-list-of-maps
  '({:name "dollA" :weight 1 :value 10}
   {:name "dollB" :weight 2 :value 20}
   {:name "dollC" :weight 3 :value 30}))

(deftest given-test
  (testing "Provided test case."
    (let [packed (pack-dolls 400 given-test-dolls)]
      (is (= (count packed) 12))
      (is (= (reduce + (map :weight packed)) 396))
      (is (= (reduce + (map :value packed)) 1030)))))

(deftest all-dolls-fit
  (testing "Total weight of dolls is less than max-weight. All dolls should be packed."
    (let [packed (pack-dolls 400 (take 5 given-test-dolls))]
      (is (should-pack-all 400 (take 5 given-test-dolls)))
      (is (= (count packed) 5))
      (is (= packed (take 5 given-test-dolls))))))

(deftest max-weight-reached
  (testing "Weight of packed dolls is exactly max-weight."
    (let [packed (pack-dolls 40 equal-weight-dolls)]
      (is (= (reduce + (map :weight packed)) 40)))))

(deftest pick-optimal-value 
  (testing "When the dolls all have the same weight, pack the ones of highest value."
    (let [packed (pack-dolls 40 equal-weight-dolls)]
      (is (= (reduce + (map :value packed)) 570)))))

(deftest pick-optimal-weight
  (testing "When the dolls all have the same value, pack the ones of least weight."
    (let [packed (pack-dolls 75 equal-value-dolls)]
      (is (= (reduce + (map :weight packed)) 72)))))

(deftest no-dolls-packed
  (testing "Max-weight is lower than any of the doll weights; no dolls should be packed."
    (let [packed (pack-dolls 3 given-test-dolls)]
      (is (empty? packed)))))

(deftest empty-supplier
  (testing "Supplier has no dolls to pack."
    (let [packed (pack-dolls 400 [])]
      (is (empty? packed)))))

(deftest different-collection-types
  (testing "Function can handle different data-structures (lists, vectors, sets) so long as the inner elements are maps."
    (let [packed (pack-dolls 5 basic-set-of-maps)]
      (is packed))
    (let [packed (pack-dolls 5 basic-vector-of-maps)]
      (is packed)
    (let [packed (pack-dolls 5 basic-list-of-maps)]
      (is packed)))))

(deftest sort-dolls
  (testing "Dolls are sorted by descending value, using ascending weight as a tie-breaker."
    (let [packed (sort value-then-weight-sort unsorted-dolls)]
      (is (= packed sorted-dolls)))))

(deftest test-get-weights
  (testing "get-weights function returns a list of all the weight values for a doll collection."
    (let [weights (get-weights equal-value-dolls)]
      (is (= weights '(22 7 18 4 30 21))))))

(deftest test-acc-weights
  (testing "acc-weights function returns the sum of all the weight values for a doll collection."
    (let [sum (acc-weights equal-value-dolls)]
      (is (= sum 102)))))
