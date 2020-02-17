The objective of the project is to reasonably 2D simulate our solarsystem.
My simulation contains three "Planets": The sun, earth and jupiter.

The solution works as following:

1) Everything begins in the update method. In the update method, updateAll
called. Some information is initially given, such as, the mass of the 
individual objects, their initial velocities and positions. Through the 
use of this information on can calculate the acceleration each planet gains. 
This is accomplished by the utilization of newtons second law and his law of
gravitation. 

2) **Extra:** Grey tracks behind each of the planets are layed by the use of their
past positions. The tracks are drawn by the constant creation of new objects,
which are layed down on the travelled path. Just like a traveller would lay rice
onto the ground to recall his/her travelled path.
An Arraylist is created, filled with other arraylists, in number equivalent to the
amount of planets in listOfPlanets. For each timestep a planet is added to each
inner-list. These are later on drawn by the same drawing method as the regular 
planets in listOfPlanets.

3) The positioning is then updated at the end of the timestep using the formula:
p_i(t + dt) = p_i(t) + v_i(t)*dt + a_i(t)*(dt)^2/2

4) Then the velocity is updated after half of the timestep dt, according to the 
formula: v_i(t+dt/2) = v_i(t) + a_i(t)*dt/2

5) Since there now is a new position and new velocity we will gather a new 
acceleration, thus, it is needed to reset the past generated acceleration
and calculate a new one based on the new information. The new acceleration
will be the one utilized in step 6).

6) Here the acceleration is calculated again, using the same method as in 1), for 
the reason mentioned in 5).

7) Given the new acceleration, one can now calculate the new velocity at the end
of the timestep. Which is what is done.

8) Lastly, since there now is a new velocity, and the accleration in part depends
on it, before we re-run the updateAll method and reuse use the updateAcc method,
it is needed to reset all current accelerations.

9) This information is stored and the window cleared, as it needs to constantly be
updated by dt.

10) The planets are drawn in the method drawPlanets. 

11) Next timeframe dt is generated an the algorithm restarts at step 1).

**Written by: Mahmut Osmanovic: 2020-02-17**