(ns binary-tree-clojure.core
  (:gen-class))

(defn is-greater? [new-node node]
  (> (:value new-node) (:value node)))
 
(defn is-smaller? [new-node node]
  (< (:value new-node) (:value node)))

(declare add-node)

(defn add-node-in [node side new-node]
  (assoc node side (add-node (side node) new-node)))

(defn add-node [node new-node]
  (cond
    (not (some? node))          new-node
    (is-greater? new-node node) (add-node-in node :right new-node)
    (is-smaller? new-node node) (add-node-in node :left new-node)
    :else node))

(defn create-node [value]
  {:value value})

(defn add-node-from-user-input
  ([] (add-node-from-user-input nil))
  ([root]
   (let [node-value (Integer. (read-line))
         new-root   (add-node root (create-node node-value))]
     (println "new root\n" new-root "\n\n")
     (add-node-from-user-input new-root))))

(defn -main
  [& _]
  (println "🌳 binary tree 🌳"
           "\n\n"
           "enter a non numeric value to exit")
  
  (try
    (add-node-from-user-input)
    (catch Exception _ (println "exiting..."))))
