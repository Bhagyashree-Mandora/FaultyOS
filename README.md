# FaultyOS
The aim is to survive and prevent loss of execution effort in case of program crashes due to faults.  

Our program counts from 1 to 100 in 100 sec, incrementing the state achieved by 1 per sec. 
This denotes the execution effort done by a program. The faulty OS program crashes the counting program at the set rate. 
Our aim is to survive this crash and reach 100 in 100 sec.  

This is done by saving the state achieved in a file. n processes are running the same program and are waiting on a filelock on this file to continue execution. 
Reaching 100 is the terminating condition for all processes.  

This program can successfully survive crashes at least every 500 ms.  
