(ns binary-tree-clojure.core
  (:gen-class))

(defn is-greater? [new-node node]
  (> (:value new-node) (:value node)))
 
(defn is-smaller? [new-node node]
  (< (:value new-node) (:value node)))

(defn is-same? [new-node node]
  (= (:value new-node) (:value node)))

(declare add-node)

(defn add-right-node [node new-node]
  (assoc node :right (add-node (:right node) new-node)))

(defn add-node [node new-node]
  (cond
    (not (some? node))          new-node
    (is-greater? new-node node) (add-right-node node new-node)
    (is-smaller? new-node node) {:smaller 1}
    :else {:else 1}))

(defn create-node [value]
  {:value value})

(defn add-node-from-user-input
  ([] (add-node-from-user-input nil))
  ([root]
   (let [node-value (Integer. (read-line))
         new-root   (add-node root (create-node node-value))]
     (println "new root" new-root)
     (add-node-from-user-input new-root))))

(defn -main
  [& args]
  (println "🌳 binary tree 🌳"
           "\n\n"
           "enter a non numeric value to exit")
  
  (try
    (add-node-from-user-input)
    (catch Exception e (println "exiting..." e))))

(-main)

(Integer. (read-line))
