(ns ld3-vs.core)

;; 1. Find the last element of a list.
(defn my-last [xs]
  (if (empty? (rest xs))
    (first xs)
    (my-last (rest xs))
    )
  )

(assert (= :d
         (my-last '(:a :b :c :d))))

;; 2. Find the N-th element of a list.
(defn get-nth [xs n]
  (if (= n 0)
    (first xs)
    (get-nth (rest xs) (- n 1))
    )
  )

(assert (= :c
         (get-nth '(:a :b :c :d) 2)))

;; 3. Find the length of a list
(defn my-length [xs]
  (if (empty? xs)
    0
    (+ 1 (my-length (rest xs)))
    )
  )

(assert (= 4
         (my-length '(:a :b :c :d))))

;; 4. Reverse a list.
(defn my-reverse [xs]
  (if (empty? xs) nil
                  (cons (my-last xs)
                        (my-reverse (butlast xs)))
                  )
  )

(assert (= '(:d :c :b :a)
         (my-reverse '(:a :b :c :d))))

;; 5. Find out whether a list is a palindrome.

;; (defn is-palindrome? [xs]
;; (cond (empty? (rest xs)) true
;;    (= (get-nth xs 0) (get-nth xs (- (my-length xs) 1))) (is-palindrome? (rest (butlast xs)))
;;    :else false
;;    )
;; )

(defn is-palindrome? [xs]
  (if (= (seq xs) (my-reverse xs))
    true
    false
    )
  )

(assert (= true
         (is-palindrome? '(:a :b :c :b :a))))

;; 6. Duplicate the elements of a list.
(defn duplicate [xs]
  (if (empty? xs) nil
                  (cons (first xs)
                        (cons (first xs)
                              (duplicate (rest xs)))
                        )
                  )
  )

(assert (= '(:a :a :b :b :c :c)
         (duplicate '(:a :b :c))))

;; 7. Eliminate consecutive duplicates of a list.
(defn compress [xs]
  (if (empty? xs) nil
                  (cons (first xs)
                        (compress (rest (rest xs))))
                  )
  )

(assert (= '(:a :b :c)
         (compress '(:a :a :b :b :c :c))))

;; 8. Remove the N-th element of a list
(defn remove-at [xs n]
  (if (= n 0) (rest xs)
              (cons (first xs)
                    (remove-at (rest xs)
                               (- n 1)))
              )
  )


(assert (= '(:a :b :d :e)
         (remove-at '(:a :b :c :d :e) 2)))

;; 9. Insert a new element at the N-th position of a list.
(defn insert-at [x xs n]
  (if (= n 0) (cons x (seq xs))
              (cons (first xs)
                    (insert-at x (rest xs)
                               (- n 1)))
              )
  )

(assert (= '(:a :b :x :c :d)
         (insert-at :x '(:a :b :c :d) 2)))

;; 10. Create a list containing all integers within a given range.
(defn my-range [a b]
  (if (<= a b)
    (cons a (my-range (inc a) b))
    nil
    )
  )

(assert (= '(3 4 5 6 7)
         (my-range 3 7)))

;; 11. Concatenate two lists
(defn my-concat [xs ys]
  (cond (empty? xs) (seq ys)
        (empty? ys) (seq xs)
        :else (cons (first xs) (my-concat (rest xs) ys))
        )
  )

(assert (= '(:a :b :c :d :e :f)
         (my-concat '(:a :b :c) '(:d :e :f))))

;; 12. Split a list into two parts; the length of the first part is given.
(defn my-drop [xs n]
  (if (= n 0) (seq xs)
                  (cons (get-nth xs (my-length xs))
                        (my-drop (rest xs) (- n 1)))
                        )
                  )

(assert (= '(:d :e)
         (my-drop '(:a :b :c :d :e) 3)))

(my-drop '(:a :b :c :d :e) 3)

;; 13. Split a list into two parts; the length of the first part is given.
(defn my-take [xs n]
  (if (= n 0) nil
              (cons (first xs)
                    (my-take (rest xs) (- n 1)))
              )
  )

(assert (= '(:a :b :c)
         (my-take '(:a :b :c :d :e) 3)))

;; 14. Implement the filtering function
(defn my-filter [p xs]
  (cond (empty? xs) nil
        (p (first xs)) (cons (first xs)
                             (my-filter p (rest xs)))
        :else (my-filter p (rest xs))
        )
  )

(assert (= '(1 3 5)
         (my-filter odd? '(1 2 3 4 5))))

;; 15. Implement the mapping function
(defn my-map [f xs]
  (if (empty? xs) nil
                  (cons (f (first xs))
                        (my-map f (rest xs)))
                  )
  )

(assert (= '(2 3 4 5 6)
         (my-map inc '(1 2 3 4 5))))

;; 16. Implement the reducing function
(defn my-reduce [f acc xs]
  (if (empty? xs)
    acc
    (f (first xs) (my-reduce f acc (rest xs))))
  )

(assert (= 15
         (my-reduce + 0 '(1 2 3 4 5))))

;; 17. Implement the flattening function
;;Deadline was approaching, so I didn't manage to finish this task.
(defn my-flatten [xs]
  (cond (empty? xs) nil
        (my-filter () (first xs)) (cons (first xs)
                                (my-flatten (rest xs)))
    :else (my-flatten (rest xs)))
    )

(assert (= '(:a :b :c :d :e)
           (my-flatten '(:a (:b (:c :d) :e)))))
