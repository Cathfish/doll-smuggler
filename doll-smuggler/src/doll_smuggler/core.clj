(ns doll-smuggler.core
  (:gen-class))

(defn value-then-weight-sort
  "Returns the doll with the higher value. If values are the same, returns doll with lower weight. If weights are also the same, sort in lexicographical name order."
  [dollA dollB]
  (if (= (:value dollA) (:value dollB))
    (if (= (:weight dollA) (:weight dollB)) 
      (< (compare (:name dollA) (:name dollB)) 0)
      (< (:weight dollA) (:weight dollB)))
    (> (:value dollA) (:value dollB))))

(defn get-weights
  "Returns a list of the value of each :weight attribute in a collection of dolls."
  [doll-collection]
  (map :weight (vec doll-collection)))

(defn acc-weights
  "Returns the sum of all weights in a collection of dolls"
  [doll-collection]
  (reduce + (get-weights doll-collection)))

(defn should-pack-all
  "Returns true if all the available dolls will fit in the mule's handbag; false otherwise."
  [max-weight dolls]
  (if (<= (acc-weights dolls) max-weight)
    true
    false))

(defn should-pack-doll
  "Returns true if the doll weighs less than the remaining available weight; false otherwise."
  [remaining-weight doll]
  (if (<= (:weight doll) remaining-weight)
    true
    false))

(defn pack-dolls
  "Find the optimal set of drug-packed porcelain dolls."
  [max-weight dolls]

  ;If the total weight of all dolls is less than the max-weight, pack all the dolls.  
  (if (should-pack-all max-weight dolls)
    (vec dolls)
    (do (let [sorted-dolls (sort value-then-weight-sort dolls)] ;Otherwise, decide which ones to pack.          
      (loop [dolls-left sorted-dolls packed []] 
	(if (empty? dolls-left)
	  packed
	  (do (let [weight-left (- max-weight (acc-weights packed))]
	    ;If the next doll's weight is less than the remaining available weight,
	    ;add it to the bag. Otherwise, skip to the next doll.
	    (if (should-pack-doll weight-left (first dolls-left)) 	      
	      (recur (rest dolls-left) (conj packed (first dolls-left)))
	      (recur (rest dolls-left) packed))))))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]) 
