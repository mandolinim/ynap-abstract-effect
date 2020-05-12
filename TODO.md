- item model
    - create: id, name, initial +qty
    - checkin: +qty
    - checkout: -qty
    - rename: not empty new name
    - active/deactive
    
- item repository (algebra/interpreter)
    - load/add/save
    - load-active/save-active
    - search/search-one
    - in memory mutable
    - in memory Ref
    
- item service (algebra/interpreter)
    - define item workflow: name, initial +qty
    - checkin qty item workflow: id, +qty
    - checkout qty item workflow: id, -qty
    - rename item workflow: id, new name
    - de-activate item workflow: id
 
- id generator (algebra/interpreter)

- logging (algebra/interpreter)
 
# use cases
create brand new item with name and init qty
checkin more item qty (only active)
checkout item qty (only active)
rename the item (only active)
de-activate the item (only active)
item list, just a table with id, name, qty, status

# common questions
* Start with the workflows/behaviour/use case
* What data is needed?
  * From input request
  * From the previous event
  * From the current state of the system
  * What is optional vs. required?
* What are the output events?
  * For broadcast to downsteam systems
* What is the change in system state?
  * Is this a state transition?
* What are the side effects?
  * Things that must be done but are internal to the domain
* What can go wrong?
  * Model success or failure with an OR choice
