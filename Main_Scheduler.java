import java.util.*;

public class Main_Scheduler {


    public static int input() {
        System.out.printf("\nPick the available numbering>>");
        Scanner get = new Scanner(System.in);
        return get.nextInt();
    }

    public static boolean confirm(int option) {
        Scanner get = new Scanner(System.in);
        HashMap<Integer, String> opt = new HashMap<>();
        opt.put(1, "First Come First Serve");
        opt.put(2, "Shortest Job First");
        opt.put(3, "Priority");
        opt.put(4, "Round Robin");
        System.out.println("You have selected " + opt.get(option) + " as Scheduling algorithm for the problem");
        System.out.print("Confirm[Y]es/[N]o>>>");
        String YN = get.next();
        if (YN.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public static int ask() {
        int option = input();
        boolean conf = confirm(option);
        if (!conf) {
            return ask();
        }
        return option;
    }

    public static ArrayList<ArrayList<Integer>> recieve_data() {
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
            values.add(arr);
        }
        return values;
    }

    public static ArrayList<ArrayList<Integer>> recieve_data_with_priority() {
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
        for (int i = 0; i < arr.size(); ++i) {
            for (int j = 0; j < arr.size() - 1; ++j) {
                if (arr.get(j).get(0) > arr.get(j + 1).get(0)) {
                    ArrayList<Integer> temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    public static void print_Answer(ArrayList<ArrayList<Integer>> values) {
        int start_time = values.get(0).get(1);
        //Calculating Finish time for each process
        int i = 0;
        while (i <= values.size() - 1) {
            if (values.get(i).get(1) <= start_time) {
                start_time += values.get(i).get(2);
                values.get(i).add(start_time);
                i++;
            } else {
                start_time++;
            }
        }
        //Calculating Turnaround time for each process
        int avg_turnaround = 0;
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
        print_Answer(values);
    }

    public static void sort_BurstTime(ArrayList<ArrayList<Integer>> values, int start_time) {
        //Inline with SJF function
        //Bubble sorting based on burst time except For the first start process
        for (int i = 0; i < values.size(); ++i) {
            for (int j = 0; j < values.size() - 1; ++j) {
                if (values.get(j).get(1) == start_time)//For the first Arrival time
                {
                    if (Objects.equals(values.get(j).get(1), values.get(j + 1).get(1))) {
                        if (values.get(j).get(2) > values.get(j + 1).get(2)) {
                            ArrayList<Integer> temp = values.get(j);
                            values.set(j, values.get(j + 1));
                            values.set(j + 1, temp);
                        }
                    }
                } else {
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
        ArrayList<Integer> list = new ArrayList<>();
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
            CT = CT + values.get(i).get(2);
            values.get(i).add(CT);
        }
        print_Answer_for_Priority(values);
    }

    public static void Priority() {
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
        ArrayList<ArrayList<Integer>> replica = new ArrayList<>(values);
        System.out.print("***!Please Provide Time Quantum!***: ");
        Scanner get = new Scanner(System.in);
        int tq = get.nextInt();
        int count = replica.size();
        int gantt_value = 0;
        int start_value = values.get(0).get(1);
        int index = 0;
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
        for (ArrayList<Integer> item : replica) {
            System.out.printf("\n%15s %15s %15s %15s %18s %17s", item.get(0), item.get(1), burst_time.get(i), item.get(3), item.get(4), item.get(5));
            i++;
        }
        System.out.println("\n\nThe average turnaround time for above processes is " + (avg_turnaround / (float) replica.size()));

        System.out.println("\nThe average waiting time for above processes is " + (avg_waiting / (float) replica.size()));
        System.out.println("\nThank You for Using the Service!!!:)");
    }

    public static void main(String[] args) {

        Scanner get = new Scanner(System.in);
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
                        System.out.println("\nYou have selected Priority(Non Preemptive) as the algorithm to solve the problem!");
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
