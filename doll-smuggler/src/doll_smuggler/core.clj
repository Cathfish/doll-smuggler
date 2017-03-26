(ns doll-smuggler.core
  (:gen-class))

(defn value-then-weight-sort
  "Returns the doll with the higher value. If values are the same, returns doll with lower weight."
  [dollA dollB]
  (if (= (:value dollA) (:value dollB))
    (< (:weight dollA) (:weight dollB))
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
    (println (vec dolls))
    (do (let [sorted-dolls (sort value-then-weight-sort dolls)] ;Otherwise, decide which ones to pack.          
      (loop [dolls-left sorted-dolls packed []] 
	(if (empty? dolls-left)
	  (println packed)
	  (do (let [weight-left (- max-weight (acc-weights packed))]
	    ;If the next doll's weight is less than the remaining available weight,
	    ;add it to the bag. Otherwise, skip to the next doll.
	    (if (should-pack-doll weight-left (first dolls-left)) 	      
	      (recur (rest dolls-left) (conj packed (first dolls-left)))
	      (recur (rest dolls-left) packed))))))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def dolls #{{:name "luke" :weight 9 :value 150}
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
		{:name "babe" :weight 30 :value 10}
		{:name "babe" :weight 21 :value 3}})
  (pack-dolls 400 dolls))
