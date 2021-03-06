(ns predicates)

(defn sum-f [f g x]
  (+ (f x) (g x)))

(defn less-than [n]
  (fn [k] (< k n)))

(defn equal-to [n]
  (fn [k] (== k n)))

(defn set->predicate [a-set]
  (fn [x] (contains? a-set x)))

(defn pred-and [pred1 pred2]
  (fn [x] (and
               (pred1 x)
               (pred2 x))))

(defn pred-or [pred1 pred2]
  (fn [x] (or
             (pred1 x)
             (pred2 x))))

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn blank? [string]
  (or
   (nil? string)
   (empty? string)
   (every? whitespace? string)))

(defn has-award? [book award]
  ((complement nil?) (award (:awards book))))

(defn HAS-ALL-THE-AWARDS? [book awards]
  (let [awardscount (count awards)
        filteredawards (filter (fn [x] (has-award? book x)) awards)
        filteredawardscount (count filteredawards)]
    (== awardscount filteredawardscount)))

(defn my-some [pred a-seq]
  (first (filter identity (map pred a-seq))))

(defn my-every? [pred a-seq]
  (let [initialcount (count a-seq)
        filteredseq (filter pred a-seq)
        filteredcount (count filteredseq)]
    (== initialcount filteredcount)))

(defn prime? [n]
  (let [pred (fn [x] (== 0 (mod n  x)))]
    (not (some pred (range 2 n)))))

;^^
