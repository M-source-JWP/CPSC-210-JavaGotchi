# My Personal Project

##  JavaGotchi 
JavaGotchi is a virtual pet game that runs in a slot-based time economy,   
where you spend *game*-time doing a limited number of actions per *game*-day.

JavaGotchi is a pet game designed for anyone to play, without needing any prior  
 experience or game knowledge to enjoy.  
 
I decided to design JavaGotchi as I wanted to make a game that can be enjoyed by   
anyone, regardless of how busy they are or how much time they want to spend  
 per gaming session.  
 TODO:

 
## User Stories
- As a user, I want to be able to interact with my pet in multiple ways
- As a user, I want to be able to assign a favorite food to my pet
- As a user, I want to be able to view my pet's status, including its favorite foods
- As a user, I want to be able to remove favorite foods from my pet's list of foods

-*Phase 2*
- As a user, I want to be able to save my pet's current status to file
- As a user, I want to be able to load my pet's status data from a file

## Phase 4: Task 2
I decided to implement the type hierarchy option in my project, with the superclass
 being Food, and the subclasses being Tiramisu and Cheeseburger. Although these classes 
 were present before phase 4, I had neglected to add an @Override to give them distinct functionality other than
 having different hunger/energy/happiness restoration values. With describeFood, now Tiramisu and Cheeseburger
 have distinct function that is used in the checkStatus function, which is called via Status
 in the GUI or console interface.

## Phase 4: Task 3
With more time to work on my project, I believe I would not spend much time refactoring. Although the current layout of
having an entire classes dedicated only Pet, Cheeseburger, and Tiramisu, objects that never have more than one 
instance present, seems unnecessary and awkward, these were design choices with the idea of having multiple types/instances of
pets with independent stats/favorite foods, and multiple instances of Foods with different stat-restore values, based
on various factors. Therefore I would use the extra time not to refactor my design to make it make sense in the 
current situation, but rather add the features that would make my design a valid choice.
