(ns ld5-vs.core)

;; 1. Rail Fence Cipher Encrypt
;; So, generally saying for the encrypt function my approach was to generate a sequence of
;; indexes(generate-seq function), which basically are the "rails" that define the way
;; characters of the message will be positioned. After that, message is being split into
;; a sequence of characters, which later are mapped by columns with "rails" sequence.
;; When pairs are created, they are getting sorted by "rails" in an ascending order, so
;; that firstly are displayed characters from the first row, then second row and so on.
;; filter-seq function removes all the characters apart from letters and spaces, then
;; replacing spaces with underscore symbol.
(defn generate-seq [text n]
  (if (= n 0) nil
              (take (count text) (flatten (repeat (Math/ceil(/ (count text) n))
                                                  (concat (range 1 (+ n 1))
                                                          (reverse (rest (butlast (range 1 (+ n 1))))))
                                                  )))
              )
  )

(generate-seq "WE ARE DISCOVERED. RUN AT ONCE." 3)

(assert (= '(1 2 3 2 1 2 3 2 1 2 3 2 1 2 3 2 1 2 3 2 1 2 3 2 1 2 3 2 1 2 3)
           (generate-seq "WE ARE DISCOVERED. RUN AT ONCE." 3)))

(generate-seq "WEAREDISCOVERED" 4)

(assert (= '(1 2 3 4 3 2 1 2 3 4 3 2 1 2 3)
           (generate-seq "WEAREDISCOVERED" 4)))

(defn filter-seq [text]
  (clojure.string/replace
    (clojure.string/replace text #"[^a-zA-Z ]" "") #"[^\S]" "_")
  )

(filter-seq "WE ARE DISCOVERED. RUN AT ONCE.")

(assert (= "WE_ARE_DISCOVERED_RUN_AT_ONCE"
           (filter-seq "WE ARE DISCOVERED. RUN AT ONCE.")))

(filter-seq "WEAREDISCOVERED123")

(assert (= "WEAREDISCOVERED"
           (filter-seq "WEAREDISCOVERED123")))

(defn encrypt [text n]
  (apply str
         (map first (sort-by last
                             (apply map vector [(seq(filter-seq text))
                                                (generate-seq text n)])))
         )
  )

(encrypt "WE ARE DISCOVERED. RUN AT ONCE." 3)

(assert (= "WRIVDN_EEAEDSOEE_U_TOC__CRRAN"
           (encrypt "WE ARE DISCOVERED. RUN AT ONCE." 3)))

(encrypt "WEAREDISCOVERED" 4)

(assert (= "WIREDSEEAECVDRO"
           (encrypt "WEAREDISCOVERED" 4)))

;; 2. Rail Fence Cipher Decrypt
;; Basically, I've done my best to make a solution for decrypt function, and actually it is "partly" working.
;; I did manage to correctly find values of characters(their position in actual decrypted message) from
;; first and last rails and moreover I found remaining values, which is enough to decrypt the message with
;; rail count equal to 3, but I couldn't really get the solution further. For the solution I pretended, that
;; the length of the message is multiple of 2 * (n - 1), because in that case lengths of a string from first
;; and last rail are equal. Then, I concatenated those two strings and by comparing it to the sequence
;; of 1..L(length of the message) I found difference from 2 sets, which actually is remaining positions
;; from rails 2..N-1.

(defn generate-seq-dec [text n]
  (if (= n 0) nil
              (take (count text) (flatten (concat (take-nth (* 2 (- n 1)) (range 1 (+ 1 (count text))))
                                                  (sort(clojure.set/difference (set(range 1 (+ 1 (count text))))
                                                                               (set(flatten (concat (take-nth (* 2 (- n 1)) (range 1 (+ 1 (count text))))
                                                                                                    (map (fn [x] (+ x (- n 1)))(take-nth (* 2 (- n 1)) (range 1 (+ 1 (count text))))))))
                                                                               ))
                                                  (map (fn [x] (+ x (- n 1)))(take-nth (* 2 (- n 1)) (range 1 (+ 1 (count text)))))
                                                  )))
              )
  )

(generate-seq-dec "WAEICVRDERDSOEE" 2)

(assert (= '(1 3 5 7 9 11 13 15 2 4 6 8 10 12 14)
           (generate-seq-dec "WAEICVRDERDSOEE" 2)))

(generate-seq-dec "WRIVDN_EEAEDSOEE_U_TOC__CRRAN" 3)

(assert (= '(1 5 9 13 17 21 25 29 2 4 6 8 10 12 14 16 18 20 22 24 26 28 3 7 11 15 19 23 27)
           (generate-seq-dec "WRIVDN_EEAEDSOEE_U_TOC__CRRAN" 3)))

(defn decrypt [text n]
  (apply str
         (map first (sort-by last (apply map vector [(seq(filter-seq text))
                                                     (generate-seq-dec text n)]))))
  )

(decrypt "WAEICVRDERDSOEE" 2)

(assert (= "WEAREDISCOVERED"
           (decrypt "WAEICVRDERDSOEE" 2)))

(decrypt "WECRERDSOEEAIVD" 3)

(assert (= "WEAREDISCOVERED"
           (decrypt "WECRERDSOEEAIVD" 3)))
