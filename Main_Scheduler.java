/**
 REQUEST:
 "Every Expert was Once A Beginner"-Torvalds
 Since Trying is Good, Do Bear with my Documentation
 The codes too long, so please spare sometime to read my code
 I really expect any comments and suggestions that would improve my coding skills.
 Find out any faults and help me debug my code.
 Feel free to contact me if you do not understand my codes and implementations.
 **/
/**
   Author: 02200159
   Pem Tshewang
   Copyright
    Diclaimer:
    All the code errors are on my own and shouldn't tarnish the reputation of esteemed tutor or relevant people.
 **/

import java.util.*;

public class twoIT_02200159 {

    public static int input() {
        /**
         This function is for menu driven option and it asks for the number available in the option!
         The function is in connection with preceding function called preceding function called recieve_data()
         where it asks the number from the users and return the integer to calling function.
         **/
        System.out.printf("\nPick the available numbering>>");
        Scanner get = new Scanner(System.in);
        return get.nextInt();
    }

    public static boolean confirm(int option) {
        /**
         This function basically serve as a boolean function and confirms the user if the choice they
         did select was really the algorithm they wants to go for.
         The function returns boolean values true and false, if the function returns false it again prompts
         the user to pick the right one which really offers the advantage to the user's interfaces and its
         compatibility.
         **/
        Scanner get = new Scanner(System.in);
        HashMap<Integer, String> opt = new HashMap<>();
        opt.put(1, "First Come First Serve");
        opt.put(2, "Shortest Job First");
        opt.put(3, "Priority");
        opt.put(4, "Round Robin");
        if(option > 4)
        {
            System.out.println("Please Choose the Available choice!");
            ask();
        }
        System.out.println("You have selected " + opt.get(option) + " as Scheduling algorithm for the problem");
        System.out.print("Confirm[Y]es/[N]o>>>");
        String YN = get.next();
        if (YN.equalsIgnoreCase("Y")) { // the user can either tap capital or small letter,
            //But the program ignores the case sensitivity
            return true;
        } else {
            return false;
        }
    }

    public static int ask() {
        /**
         This is the driver function for asking the users for their choice and all other functions
         that is related with the prompting users for input descends from this function.
         It returns the labeling number of the options available and laters call other function associated with
         it.
         **/
        int option = input();
        boolean conf = confirm(option);
        if (!conf) {
            return ask();
        }
        return option;
    }

    public static ArrayList<ArrayList<Integer>> recieve_data() {
        /**This function serve the purpose for the first two algorithms namely FCFS and SFJ.
         Since the two algorithms doesn't involve the enquiry extra data like priority, the function was
         made same for the two algorithms.
         The function asks the users to input the count of process,their arrival time and burst time.
         The function packs the each input and their respective values into an arraylist.
         Then its sends the arraylist to the function that solves the specific algorithm problems.
         ArrayList<ArrayList<Integer>> means = integerarray[][] (two dimensional integer array)
         where each array holds the values of one specific purposes.
         I prefer arraylist due to its dynamic control characteristics and object-oriented methods.
         **/
        Scanner get = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> values = new ArrayList<>();
        System.out.println("Please Fill the necessary values when prompted");
        System.out.println("\nEnter the count of Processes!");
        int proc_count = get.nextInt();
        System.out.println("\nPlease values when prompted!!");
        for (int i = 0; i < proc_count; ++i) {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(i + 1); //adding the value to the arraylist and the value is identification for the process count
            System.out.printf("\nPlease Enter the Arrival time for process[%d]: ", i + 1);
            arr.add(get.nextInt());
            System.out.printf("\nPlease Enter the Burst time for process[%d]: ", i + 1);
            arr.add(get.nextInt());
            values.add(arr);
        }
        return values;
    }

    public static ArrayList<ArrayList<Integer>> recieve_data_with_priority() {
        /**
         This function serves the purpose for priority scheduling algorithm namely: Non-Preemptive
         It prompts the user for all the necessary inputs to work with priority algorithm and prompts for the
         priority for all the data.
         The priority should be given to the big number --- small->big -- priority increases
         It returns the array[][] to the calling function()
         **/
        Scanner get = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> values = new ArrayList<>();
        System.out.println("Please Fill the necessary values when prompted");
        System.out.println("\nEnter the count of Processes!");
        int proc_count = get.nextInt();
        System.out.println("\nPlease values when prompted!!");
        for (int i = 0; i < proc_count; ++i) {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(i + 1);
            System.out.printf("\nPlease Enter the Arrival time for process[%d]: ", i + 1);
            arr.add(get.nextInt());
            System.out.printf("\nPlease Enter the Burst time for process[%d]: ", i + 1);
            arr.add(get.nextInt());
            System.out.printf("\nPlease Enter the Priority for process[%d]: ", i + 1);
            arr.add(get.nextInt());
            values.add(arr);
        }
        return values;
    }

    public static void BubbleSort(ArrayList<ArrayList<Integer>> arr) {
        /**
         This function is explicitly made for all algorithms, since all algorithms
         involves the ordering of the arrival time from the least to greatest.
         The function is necessary to sort out the first arrival time and sorting the function
         works for the first two algorithms fcfs and sfj since they rely upon the arrival time.
         **/
        for (int i = 0; i < arr.size(); ++i) {
            for (int j = 0; j < arr.size() - 1; ++j) {
                if (arr.get(j).get(0) > arr.get(j + 1).get(0)) {
                    ArrayList<Integer> temp = arr.get(j);//assigning to the temporary var
                    arr.set(j, arr.get(j + 1));// it is same as indexing the arr or take the consideration
                    //arr[j]=arr[j+1]
                    arr.set(j + 1, temp);
                }
            }
        }
        //This function doesn't return any of the values but make changes to the local variable in the given scope.
    }

    public static void print_Answer(ArrayList<ArrayList<Integer>> values) {
        /**
         This function is specifically designed for the FCFS and SFJ.
         It solves for turnaround time and bursttime.
         also finds the avg turnaround time and burst time for all process.
         Later it prints the answer in tabular form
         **/
        int start_time = values.get(0).get(1);//if it too much for you, it refers to values[0][1]
        //Calculating Finish time for each process
        int i = 0;
        while (i <= values.size() - 1) {//it proceeds till the size of the given process
            if (values.get(i).get(1) <= start_time) {//it checks if it is the first arrival time along all the process
                //It goes for this condition first and calculates the start time for the specific purpose and initializes
                //them to the given variable
                start_time += values.get(i).get(2);
                values.get(i).add(start_time);
                i++;
            } else {
                start_time++;
            }
        }
        //Calculating Turnaround time for each process
        int avg_turnaround = 0;
        //I do assume calculating the average is known concept for you, so I didn't documented here.
        for (ArrayList<Integer> list : values) {
            avg_turnaround = avg_turnaround + (list.get(3) - list.get(1));
            list.add(list.get(3) - list.get(1));
        }
        //Finding waiting time for each Process: Turn around time - Burst Time
        int avg_waiting = 0;
        for (ArrayList<Integer> list : values) {
            avg_waiting = avg_waiting + (list.get(4) - list.get(2));
            list.add(list.get(4) - list.get(2));
        }
        //sort the arrival time of all the process and proceed for the next step, the sorting
        //here is for the arranging the process in their order and later prints it in the ascending order.
        BubbleSort(values);
        System.out.printf("\n%15s %15s %15s %15s %18s %17s", "Process", "ArrivalTime", "BurstTime", "FinishTime", "Turnaroundtime", "Waiting Time");
        for (ArrayList<Integer> list : values) {
            System.out.printf("\n%13s %13s %15s %15s %15s %17s", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
        }
        System.out.println("\n\nThe average turnaround time for above processes is " + (((float) (avg_turnaround / (float) values.size()))));

        System.out.println("\nThe average waiting time for above processes is " + (((float) (avg_waiting / (float) values.size()))));
        System.out.println("\nThank You for Using the Service!!!:)");
    }

    public static void FCFS() {
        /**
         This function is designed to solve the algorithm of the First Come First Serve.
         Idea is to just sort the arrival time according to their arrival
         Assuming it as queue and using the array list as queue.
         **/
        ArrayList<ArrayList<Integer>> values = recieve_data();

        //Sorting the value of array based on Arrival time using Bubble Sort
        //Bubble Sort for stable sorting so that the process can maintain its order (Queue)
        for (int i = 0; i < values.size(); ++i) {
            for (int j = 0; j < values.size() - 1; ++j) {
                if (values.get(j).get(1) > values.get(j + 1).get(1)) {
                    ArrayList<Integer> temp = values.get(j);
                    values.set(j, values.get(j + 1));
                    values.set(j + 1, temp);
                }
            }
        }
        print_Answer(values);//Finds all the requisites as mentioned in the documentation of this function.
    }

    public static void sort_BurstTime(ArrayList<ArrayList<Integer>> values, int start_time) {
        /**
        Inline with SJF function
         This function solves the constraint of the second algorithm
         whereby it depends on the burst time after getting the first arrival time.
         It sorts the burst time using the sorting algorithm: Bubble sort
        **/
        //Bubble sorting the burst time in order of least to greatest
        for (int i = 0; i < values.size(); ++i) {
            for (int j = 0; j < values.size() - 1; ++j) {
                if (values.get(j).get(1) == start_time)//For the first Arrival time
                {
                    if (Objects.equals(values.get(j).get(1), values.get(j + 1).get(1))) {
                        if (values.get(j).get(2) > values.get(j + 1).get(2)) { //values[j][2]
                            //since the burst time is located at the array index 2, it refers to 2
                            ArrayList<Integer> temp = values.get(j);
                            values.set(j, values.get(j + 1));
                            values.set(j + 1, temp);
                        }
                    }
                } else {
                    //It doesn't imply for the process that doesn't have the first arrival time
                    if (values.get(j).get(2) > values.get(j + 1).get(2)) { //Sorting rest with Burst time
                        ArrayList<Integer> temp = values.get(j);
                        values.set(j, values.get(j + 1));
                        values.set(j + 1, temp);
                    }
                }
            }
        }
    }

    public static void SJF() {
        /**
         This function serves the purpose of Shortest Job First Scheduling Algorithm
         It solves the problem after recieving all the necessary values.
         **/
        ArrayList<ArrayList<Integer>> values = recieve_data();
        //Bubble sort the values based on values and Don't apply the sorting to the first arrival time value.
        for (int i = 0; i < values.size(); ++i) {
            for (int j = 0; j < values.size() - 1; ++j) {
                if (values.get(j).get(1) > values.get(j + 1).get(1)) {
                    ArrayList<Integer> temp = values.get(j);
                    values.set(j, values.get(j + 1));
                    values.set(j + 1, temp);
                }
            }
        }
        sort_BurstTime(values, values.get(0).get(1));
        print_Answer(values);
    }

    public static void print_Answer_for_Priority(ArrayList<ArrayList<Integer>> values) {
        /**
         The priority algorithm required extra function to print the values since it contains the priority for
         each values.
         The function takes the parameter where two dimensional array is accepted from the user.
         **/
        //let's calculate the turn around time first using formula
        //Finish Time-Arrival time or values[i][4]-values[i][1]
        float avg_turnaround = 0f;
        for (ArrayList<Integer> item : values) {
            avg_turnaround += (item.get(4) - item.get(1));
            item.add(item.get(4) - item.get(1));
        }
        avg_turnaround /= (float) values.size();

        //let's find the waiting time using formula wt=turnaroundtime-burst time
        float avg_waiting = 0f;
        for (ArrayList<Integer> item : values) {
            avg_waiting += (item.get(5) - item.get(2));
            item.add(item.get(5) - item.get(2));
        }
        avg_waiting /= (float) values.size();
        System.out.printf("\n%15s %15s %15s %15s %18s %17s %17s", "Process", "ArrivalTime", "BurstTime", "Priority", "FinishTime", "TurnaroundTime", "WaitingTime");
        BubbleSort(values);
        for (ArrayList<Integer> list : values) {
            System.out.printf("\n%13s %13s %15s %15s %15s %17s %17s", list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6));
        }
        System.out.println("\n\nThe average turnaround time for above processes is " + (((float) (avg_turnaround / (float) values.size()))));

        System.out.println("\nThe average waiting time for above processes is " + (((float) (avg_waiting / (float) values.size()))));
        System.out.println("\nThank You for Using the Service!!!:)");
    }

    public static void Non_Preemptive(ArrayList<ArrayList<Integer>> values) {
        /**
         This function serves the purpose of the Non_Preemptive scheduling algorithm.
         I assumed that concepts are understandable for you.
         So implementation is also bit tricky, but it seems easy after few trials.
         **/
        //ArrayList<Integer> list = new ArrayList<>();
        //First lets sort the values that has first arrival time based on its priority
        int first_arrival_time = values.get(0).get(1);
        for (int i = 0; i < values.size() - 1; ++i) {
            if (values.get(i).get(1) == first_arrival_time) {
                if (Objects.equals(values.get(i).get(1), values.get(i + 1).get(1))) {
                    if (values.get(i).get(3) > values.get(i + 1).get(3)) {
                        ArrayList<Integer> temp = values.get(i);
                        values.set(i, values.get(i + 1));
                        values.set(i + 1, temp);
                    }
                }
            }
        }
        values.forEach(System.out::println);
        int CT = 0;
        for (int i = 0; i < values.size(); ++i) {
            int most_prior = values.get(i).get(3);
            if (values.get(i).get(1) != first_arrival_time) {
                for (int j = i; j < values.size(); ++j) {
                    //if arrival time is less
                    if (values.get(j).get(1) < CT) {
                        // if burst time is less, swap
                        if (values.get(j).get(3) < most_prior) {
                            most_prior = values.get(j).get(3);
                            ArrayList<Integer> temp = values.get(j);
                            values.set(j, values.get(i));
                            values.set(i, temp);
                        }
                    }
                }
            }
            CT = CT + values.get(i).get(2);// Calculating the finishing time and adding to the array
            values.get(i).add(CT);
        }
        print_Answer_for_Priority(values);
    }

    public static void Priority() {
        /**
         This function serves the purpose of the Priority algorithm-non preemptive
         It is connected with the previous function and it serves the driver code for the priority.
         **/
        ArrayList<ArrayList<Integer>> values = recieve_data_with_priority();
        //Let's first solve by sorting the arrival time
        for (int i = 0; i < values.size(); ++i) {
            for (int j = 0; j < values.size() - 1; ++j) {
                if (values.get(j).get(1) > values.get(j + 1).get(1)) {
                    ArrayList<Integer> temp = values.get(j);
                    values.set(j, values.get(j + 1));
                    values.set(j + 1, temp);
                }
            }
        }
        Non_Preemptive(values);
    }

    public static void Round_Robin() {
        /**
         This function serves the purpose of the algorithm round-robin
         It doesnot have any sub functions and solves + prints all the necessary answer here in tabular form.
         **/
        ArrayList<ArrayList<Integer>> values = recieve_data();
        //copying item
        ArrayList<ArrayList<Integer>> Ready_queue = new ArrayList<>();
        ArrayList<ArrayList<Integer>> Running_queue = new ArrayList<>();
        //Sort the process based on their arrival time
        for (int i = 0; i < values.size(); ++i) {
            for (int j = 0; j < values.size() - 1; ++j) {
                if (values.get(j).get(1) > values.get(j + 1).get(1)) {
                    ArrayList<Integer> temp = values.get(j);
                    values.set(j, values.get(j + 1));
                    values.set(j + 1, temp);
                }
            }
        }
        ArrayList<Integer> burst_time = new ArrayList<>();
        for (ArrayList<Integer> item : values) {
            burst_time.add(item.get(2));
        }
        //keeping the duplication of the orignal array named replica, in order to print the old values inputted by the users
        ArrayList<ArrayList<Integer>> replica = new ArrayList<>(values);
        //Necessary to provide time quantum for this algorithm
        System.out.print("***!Please Provide Time Quantum!***: ");
        Scanner get = new Scanner(System.in);
        int tq = get.nextInt();
        int count = replica.size();
        int gantt_value = 0;
        int start_value = values.get(0).get(1);
        int index = 0;
        //solve all the process till the burst time hasn't reduced to 0
        //It keeps the track of the process that has reduced its burst time to : variable used=count
        while (count > 0) {
            if (gantt_value < 1) {
                for (int i = 0; i < values.size(); ++i) {
                    if (values.get(i).get(1) == start_value) {
                        if (values.get(i).get(2) <= tq) {
                            gantt_value += values.get(i).get(2);
                            //update the burst time to 0 of the specific process
                            //add to running queue
                            count--;
                        } else {
                            gantt_value += tq;
                            //update the burst time of the process
                            values.get(i).set(2, values.get(i).get(2) - tq);
                        }
                        //Check for newer process that are to be added in Ready Queue
                        if (index < values.size()) {
                            for (int j = index + 1; j < values.size(); ++j) {
                                //if arrival time is lesser than gantt value, add to the ready queue
                                if (values.get(j).get(1) <= gantt_value) {
                                    Ready_queue.add(values.get(j));
                                    index++;
                                }
                            }
                        }
                        //add the process to running queue
                        ArrayList<Integer> record = new ArrayList<>();
                        record.add(values.get(i).get(0));
                        record.add(gantt_value);
                        Running_queue.add(record);
                        //add to ready queue
                        Ready_queue.add(values.get(i));
                    }
                }
            } else {
                //Now take out the process from the Ready queue and proceed the further steps
                if (Ready_queue.get(0).get(2) <= tq) {
                    //Add the tq or burst time to the gantt var.
                    gantt_value += Ready_queue.get(0).get(2);
                    count--;
                } else {
                    //add the gantt value with time quantum
                    gantt_value += tq;
                    //update the burst time by subtracting burst time from tq
                    Ready_queue.get(0).set(2, Ready_queue.get(0).get(2) - tq);
                    //check for newer process
                    if (index < values.size()) {
                        for (int i = index + 1; i < values.size(); ++i) {
                            //if arrival time is lesser than gantt value, add to the ready queue
                            if (values.get(i).get(1) <= gantt_value) {
                                Ready_queue.add(values.get(i));
                                index++;
                            }
                        }
                    }
                    // add the current process to the ready queue since the burst time hasn't reduced to 0
                    Ready_queue.add(Ready_queue.get(0));
                }
                //add the process to running queue
                ArrayList<Integer> record = new ArrayList<>();
                record.add(Ready_queue.get(0).get(0));
                record.add(gantt_value);
                Running_queue.add(record);
                //Now remove from the ready queue
                Ready_queue.remove(0);
            }
        }
        HashMap<Integer, Integer> set = new HashMap<>();
        for (int i = Running_queue.size() - 1; i >= 0; i--) {
            set.putIfAbsent(Running_queue.get(i).get(0), Running_queue.get(i).get(1));
        }
        for (Map.Entry<Integer, Integer> val : set.entrySet()) {
            for (ArrayList<Integer> integers : replica) {
                if (Objects.equals(integers.get(0), val.getKey())) {
                    integers.add(val.getValue());
                }
            }
        }
        System.out.printf("\n%15s %15s %15s %15s %18s %17s", "Process", "ArrivalTime", "BurstTime", "FinishTime", "Turnaroundtime", "Waiting Time");
        //calculating turnaround time=Finish Time-Arrival Time
        float avg_turnaround = 0f;
        float avg_waiting = 0f;
        for (ArrayList<Integer> item : replica) {
            item.add(item.get(3) - item.get(1));
            avg_turnaround += (item.get(3) - item.get(1));
        }
        int i = 0;
        for (ArrayList<Integer> item : replica) {
            item.add(item.get(4) - burst_time.get(i));
            avg_waiting += (item.get(4) - burst_time.get(i));
            i++;
        }
        i=0;
        BubbleSort(replica);
        for (ArrayList<Integer> item : replica) {
            System.out.printf("\n%15s %15s %15s %15s %18s %17s", item.get(0), item.get(1), burst_time.get(i), item.get(3), item.get(4), item.get(5));
            i++;
        }
        System.out.println("\n\nThe average turnaround time for above processes is " + (avg_turnaround / (float) replica.size()));

        System.out.println("\nThe average waiting time for above processes is " + (avg_waiting / (float) replica.size()));
        System.out.println("\nThank You for Using the Service!!!:)");
    }

    public static void main(String[] args) { //The main driver code where execution starts
        while (true) {

            Date now = new Date();
            System.out.println("\t\t\t\t!!!The Scheduling Algorithm Solver!!!     |" + now);

            System.out.println("\nPlease Opt your Scheduling Algorithm from the Menu");
            System.out.println("\n1.->First Come First Serve\n2.->Shortest Job First\n3.->Priority(Non-preemptive)\n4.->Round Robin");
            System.out.println("\n Hit Control + d to exit out if you don't want to use the service!");
            System.out.println("\n Scroll up the output pane if output is not visible!!");
            try {
                switch (ask()) {
                    case 1:
                        System.out.println("\nYou have selected First Come First Serve as the algorithm to solve the problem!");
                        FCFS();
                        break;
                    case 2:
                        System.out.println("\nYou have selected Shortest Job First as the algorithm to solve the problem!");
                        SJF();
                        break;
                    case 3:
                        System.out.println("\nYou have selected Priority(Non Preemptive) as the algorithm to solve the problem!\nThe priority should be given to greatest number! ascending");
                        Priority();
                        break;
                    case 4:
                        System.out.println("\nYou have selected Round Robin as the algorithm to solve the problem");
                        Round_Robin();
                        break;
                    default:
                        System.out.println("Please enter valid OPTION!!:(");
                }
            } catch (NoSuchElementException e) {
                System.out.println("\nSuccessful Exit!");
                System.exit(0);
            }
        }
    }
}
