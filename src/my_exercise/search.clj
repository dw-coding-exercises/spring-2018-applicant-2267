(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]))
            ; Had to comment this import due to compile error
            ;[clj-http.client :as client]
            
            
(defn header [_]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0, maximum-scale=1.0"}]
   [:title "My upcoming elections"]
   [:link {:rel "stylesheet" :href "default.css"}]])

; Get the city from form input
(defn city [request]
  (get 
    (get request :params) 
    :city))

; Get the state from form input
(defn state [request]
  (get 
    (get request :params) 
    :state))

; Format input (lowercase, replace spaces with underscores).
(defn fmt [place]
  ; TODO: if more time, use a library for this instead (and check validity).
  (clojure.string/lower-case(clojure.string/replace place " " "_")))

; Returns a list of OCD-IDs, separated by commas.
; TODO: If more time, look up counties or other regions by ZIP and address.
(defn ocdids [request]
  [:div
   "ocd-division/country:us/state:"
   (fmt (state request))   
   ",ocd-division/country:us/state:"
   (fmt (state request))   
   "/place:"
   (fmt (city request))])

; This function would be used to fetch the upcoming elections to display to the user.
; Unable to complete in time.
; TODO: If more time, show all upcoming elections with date.
(defn get_elections [request])
  ;(client/get "https://api.turbovote.org/elections/upcoming?district-divisions=" (ocdids request)))


; Results to display to user.
; Currently just displays the OCD-ID.
; Future ideas: Allow the user to set a reminder, or email self about election(s). Link to info about
; any candidates and propositions for upcoming elections. Allow user to share date/reminders/info with
;friends.
(defn results [request]
  [:div 
   "OCD-ID: "
   (ocdid request)])  


(defn page [request]
  (html5
   (header request)
   (results request)))

