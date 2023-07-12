## Assignment for 360T

This project contains three main classes: `Player`, `Initiator`, and `Main` class.
 - Player.java:
   This class implements runnable interface. It has reply() and receive() methods
 - Initiator.java:
   This class extends Player class. Since it has to initiate the message. It has sendInitMessage() method. The functionality of this method is to send a predefined message
 - Main.java:
   The main class initiates two threads, one for player class and other for initiator class. It also passes the ArrayBlockingQueue to the respective classes as well

 

### Shell Scripts

The shell scripts can be found in the **scripts** folder. Inside the folder, there are two scripts:

- `build.sh`: Use this script to build the project. It will generate the `.jar` file inside the `target` folder. Run the following command to execute the script:
  ```bash
  ./scripts/build.sh
- `start.sh`: Use this script to run the project.
  ```bash
  ./scripts/start.sh

### Prerequisite Installation
- java version "1.8.0_361"
- Apache Maven 3.9.0

#### Assumption
- The predefined number of messages has been set to 10.
- BlockingQueues has been used as they ensures thread safety, and avoids the pitfalls of busy-waiting or manual synchronization.
