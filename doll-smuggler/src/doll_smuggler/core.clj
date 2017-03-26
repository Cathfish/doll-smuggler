(ns doll-smuggler.core
  (:gen-class))


(defn value-then-weight-sort
  "Returns the doll with the higher value. If values are the same, returns doll with lower weight."
  [dollA dollB]
  (if (= (:value dollA) (:value dollB))
    (< (:weight dollA) (:weight dollB))
    (> (:value dollA) (:value dollB))))

(defn pack-dolls
  "Find the optimal set of drug-packed porcelain dolls."
  [max-weight dolls]
    
  (if (<= (reduce + (map :weight (vec dolls))) max-weight)
    (println (vec dolls))
    (do 
      ;Sort the dolls
      (let [sorted-dolls (sort value-then-weight-sort dolls)]          
        (loop [dolls-left sorted-dolls packed []] 
	  (if (empty? dolls-left)
	    (println packed)
	      (do (let [weight-left (- max-weight (reduce + (map :weight packed)))]
		(if (<= (:weight (first dolls-left)) weight-left) 	      
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
  (println "Hello, World!")
  (pack-dolls 400 dolls))