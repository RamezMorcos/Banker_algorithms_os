import java.util.ArrayList;
import java.util.Scanner;

public class Banker_algo {
    int number_of_process;
    int number_of_resource;
    int [] available;
    int [] copy_of_avail;
    int [][] maximum;
    int [][] allocation;
    int [][] need;
    ArrayList<Integer> ind=new ArrayList<>();
    public void initialize(int num_of_process,int num_of_resource){
        number_of_process=num_of_process;
        number_of_resource=num_of_resource;
        available=new int[num_of_resource];
        copy_of_avail=new int[num_of_resource];
        maximum=new int[num_of_process][num_of_resource];
        allocation=new int[num_of_process][num_of_resource];
        need=new int [num_of_process][num_of_resource];
        maximum=new int[num_of_process][num_of_resource];
        Scanner s=new Scanner(System.in);
        for(int i=0;i<num_of_process;i++){
            for(int j=0;j<num_of_resource;j++){
                System.out.println("Enter the allocation");
                allocation[i][j]=s.nextInt();
                System.out.println("Enter the Maximum");
                maximum[i][j]=s.nextInt();
                need[i][j]=maximum[i][j]-allocation[i][j];

            }
        }

        System.out.println("Enter the available resources");
        for(int i=0;i<num_of_resource;i++){
            available[i]=s.nextInt();
            copy_of_avail[i]=available[i];
        }
    }
    boolean check_if_exist(int i){
        for (int k=0;k<ind.size();k++){
            if(ind.get(k)==i){
                return false;
            }
        }
        return true;
    }
    public boolean banker(){
        int unsafe=0;
        int siz=0;

        while(siz<number_of_process){

            unsafe++;
            for(int i=0;i<number_of_process;i++){
                int yc=0;
                for(int j=0;j<number_of_resource;j++){
                    if(available[j]>=need[i][j]){yc++;}
                }
                boolean check =check_if_exist(i);
                if(yc==number_of_resource &&check==true){
                    for(int j=0;j<number_of_resource;j++){
                        available[j]=available[j]+allocation[i][j];
                    }

                    ind.add(i);
                    siz=ind.size();
                }

            }
            if(unsafe==number_of_process*2){
                System.out.println("It is unsafe ");
                return false;
            }
        }
        return true;

    }
    public boolean reguest(int ind){
        Scanner s=new Scanner(System.in);
        for(int i=0;i<number_of_resource;i++){
            System.out.println("Enter the allocation of the process ");
            allocation[ind][i]=s.nextInt();
            available[i]=copy_of_avail[i];
            need[ind][i]=maximum[ind][i]-allocation[ind][i];

        }
      return banker();
    }

    public void print(){
        for(int i=0;i<ind.size();i++){
            System.out.println("p  "+ ind.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter nnumber of processes  and resources");
        Scanner s=new Scanner(System.in);
        int process=s.nextInt();
      int resourcess=s.nextInt();
        Banker_algo b=new Banker_algo();
        b.initialize(process,resourcess);
        boolean x=b.banker();
        while (x){
            b.print();
            System.out.println("tell me num_of_process you want to change");
            int y=s.nextInt();
           x= b.reguest(y);
           System.out.println("if you want anothe request press 1 else 0");
           int c=s.nextInt();
           if(c==0){
               break;
           }
    }
    }
}

