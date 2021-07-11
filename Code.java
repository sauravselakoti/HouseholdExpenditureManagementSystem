import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;


class Home 
{
    static Scanner sc=new Scanner(System.in);
    private static ArrayList<Home>Society=new ArrayList<>();//A list of all homes in society only manager can add or remove any family from Society

    float electricity_bill;
    float telephone_bill;
    float grocery_bill;
    Member m1,m2,m3;//given that each family have 3 members
    int family_id;//It is unique 

   static class Member//Member is static because any member can exist without family
    {
        int salary;
        int saving_plan;
        String name;
        LocalDate dob;
        double Available_amount;
        Member(int salary,int saving_plan,String name,LocalDate dob)
        {
            this.salary=salary;
            if(saving_plan<=5)this.saving_plan=saving_plan;
            else this.saving_plan=-1;
            this.name=name;
            this.dob=dob;
            if(salary>0&&saving_plan<=5)//In Case any member is less than 18 his salary is by default zero
                                        //and if any member's income is very low and he can't afford any saving plan
            {
                this.Available_amount=salary-simulate.plans[saving_plan];//As Each Member will have to pay his saving plan;
            }
            
            else {this.Available_amount=salary;}
        }
    }


    float getBill()//returns total bill
    {
        float total_bill=electricity_bill+grocery_bill+telephone_bill;
        return total_bill;
    }

    
    public void Bill()//Only Manager has acces to this he will generate bill for any family;
    {
        System.out.print("\tPlease Enter Family Id:");
        int fam_id=Integer.parseInt(sc.nextLine());
        for(int i=0;i<Society.size();i++)
        {
            if(Society.get(i).family_id==fam_id)
            {
                System.out.print("\n\tPlease Enter Grocery Bill(in Rupees):");
                Society.get(i).grocery_bill=Float.parseFloat(sc.nextLine());
                System.out.print("\n\tPlease Enter Electricity Bill(in Rupees):");
                Society.get(i).electricity_bill=Float.parseFloat(sc.nextLine());
                System.out.print("\n\tPlease Enter Telephonr Bill (in Rupess):");
                Society.get(i).telephone_bill=Float.parseFloat(sc.nextLine()); 
            }
        }
    }
    static void add(Home h)//Adding home to society
    {
        Society.add(h);
    }
    
    static Home search(int family_id)//will search family in Society by giving family_id
    {
        boolean isFound=false;
        if(Society.isEmpty())
        {
            System.out.println("\tNo Family in Society");
            return null;
        }
        for(Home i:Society)
        {
            if(i.family_id==family_id)
            {
                isFound=true;
                return i;
                
            }
        }
        
        return null;
    }

    
 void acceptPayment(double expenditure,double amount)//Accepts payment
 {
    double toBePaid=Math.round(((amount/3)*100.0)/100.0);
    //The amount is going to reduced from their salary
    System.out.println("\n\tExpected Amount From Each Member"+toBePaid);
    if(m1.salary<toBePaid)
    {
        System.out.println(m1.name+" Paid Lower Than Expected "+m1.salary*0.25);
    }
    if(m2.salary<toBePaid)
    {
       System.out.println(m2.name+" Paid Lower Than Expected "+m2.salary*0.25);
    }
    if(m3.salary<toBePaid)
    {
        System.out.println(m3.name+" Paid Lower Than Expected "+m3.salary*0.25);
    }
    int actual_payers=0;//because everyone's salary is not >0 and greater than to Be paid that's why it will check who are actual payers
     if(m1.salary>0 && m1.salary>toBePaid)actual_payers++;
     if(m2.salary>0 && m2.salary>toBePaid)actual_payers++;
     if(m3.salary>0 && m3.salary>toBePaid)actual_payers++;
     toBePaid=amount/actual_payers;
     System.out.println("\n\tTotal bill is:"+amount+" which will be paid by "+actual_payers+" and amount:"+toBePaid);
     if(m1.salary>toBePaid)m1.Available_amount-=toBePaid;
     if(m2.salary>toBePaid)m2.Available_amount-=toBePaid;
     if(m3.salary>toBePaid)m3.Available_amount-=toBePaid;
    telephone_bill=0;
    grocery_bill=0;
    electricity_bill=0;
    System.out.println("\n\tPAYMENT DONE SUCCESSFULLY.....CONGRATS.....");
 }
 
 
 void acceptPayment(double expenditure,double amount, Home.Member m)//This function is overloaded In case if any Family is not able to pay their bill then any of the family member will have to pay so adjust funvtion selscts that member         
 {
    //Now Total amount-Expenditure will be distributed in all 3 members and The remaining extra bill will be paid by member m as told by User Himself in adjust function
     double excess_bill=amount-expenditure;
     m.Available_amount-=excess_bill;//extra amount is being paid by member m who was given by that family
     //now remaining amount will be distributed in all
     
     double toBePaid=excess_bill/3;
     int actual_payers=0;//checking actual payers
     if(m1.salary>0 && m1.salary>toBePaid)actual_payers++;
     if(m2.salary>0 && m2.salary>toBePaid)actual_payers++;
     if(m3.salary>0 && m3.salary>toBePaid)actual_payers++;
     toBePaid=expenditure/actual_payers;
     if(m1.salary>toBePaid)m1.Available_amount-=toBePaid;
     if(m2.salary>toBePaid)m2.Available_amount-=toBePaid;
     if(m3.salary>toBePaid)m3.Available_amount-=toBePaid;
     telephone_bill=0;
     grocery_bill=0;
     electricity_bill=0;
     System.out.println("\n\tPAYMENT DONE SUCCESSFULLY....CONGRATS");
 }
 
 
 void unableToBear(double exp,double amount)//prints all those members who are not able to bear their expenses
 {
    double toBePaid=amount/3;
    //The amount is going to reduced from their salary
    System.out.println("\n\tExpected Amount From Each Member"+toBePaid);
    if(m1.salary<toBePaid)
    {
        System.out.println(m1.name+" Paid Lower Than Expected "+m1.salary*0.25);
    }
    if(m2.salary<toBePaid)
    {
       System.out.println(m2.name+" Paid Lower Than Expected "+m2.salary*0.25);
    }
    if(m3.salary<toBePaid)
    {
        System.out.println(m3.name+" Paid Lower Than Expected "+m3.salary*0.25);
    } 
    
    
 }
 
 static void showStatus()//it is accessible by manager only it will show family id of all and paid or unpaid status
 {
     if(Society.isEmpty())
     {
         System.out.println("\t\tNo Family is avaliable in Society");
     }
     else
     {
         System.out.println("Family id\tName\tStatus\tBalance\t\tDOB");
         for(int i=0;i<Society.size();i++)
         {
             Home h=Society.get(i);
             double total_bill=h.electricity_bill+h.grocery_bill+h.electricity_bill;
             if(total_bill!=0)
             {
                 System.out.println(h.family_id+"\t"+h.m1.name+"\tUNPAID\t\t"+total_bill+"\t\t"+h.m1.dob);
             }
             else
             {
                 System.out.println(h.family_id+"\t"+h.m1.name+"\tPAID\t\tNIL\t\t"+h.m1.dob);
             }
         }
     }
 }
 
 Home.Member adjust(double exp,double total)//This function is to adjust In case the total expenditure is less than bill then this will return a member who 
                                            //will have to pay Remaining amount and if no member is able to pay then return null
 {
     System.out.println("\n\tSorry, But Expenditure avilable is not sufficient enough to pay total Bill\n\tYou can pay "+(total-exp)+" from Your available money account");
     System.out.println("\n00.\t"+m1.name+"\t\t"+m1.Available_amount);
     System.out.println("01.\t"+m2.name+"\t\t"+m2.Available_amount);
     System.out.println("02.\t"+m3.name+"\t\t"+m3.Available_amount);
     System.out.println("\nPlease enter the cooresponding key");
     int choice=Integer.parseInt(sc.nextLine());
     if(choice==1)
     {
         if(m1.Available_amount>(total-exp))
         {
             System.out.println("\n\tPress 1 to pay Rs."+(total-exp));
             if(sc.nextInt()==1)
             return m1;
         }
         else
         {
             System.out.print("Insufficient Funds Available");
             
         }
     }
     else if(choice==2)
     {
         if(m2.Available_amount>(total-exp))
         {
             System.out.println("\n\tPress 1 to pay Rs."+(total-exp));
             if(sc.nextInt()==1)
             return m2;
         }
         else
         {
             System.out.print("Insufficient Funds Available");
             
         }
     }
     else if(choice==3)
     {
         if(m3.Available_amount>(total-exp))
         {
             System.out.println("\n\tPress 1 to pay Rs."+(total-exp));
             if(sc.nextInt()==1)
             return m3;
         }
         else
         {
             System.out.print("Insufficient Funds Available");
             
         }
     }
     return null;
 }
 
 
}


class simulate//It is just to simulate
{
   static Scanner sc=new Scanner(System.in);

   static void getMyBill()//calls search function if any home returned checks its total balance if 0 then already paid else shows bill
                            //if nothing is returned by search function then prints no family available
   {
       print("\n\tPlease Enter your Family Id");
       int family_id=Integer.parseInt(sc.nextLine());
      Home h=Home.search(family_id);
      if(h!=null)
      {
          if(h.getBill()>0)
            System.out.println("Your Total Bill is:"+h.getBill());
          else System.out.println("You have already paid your bill..");
      }
      else if(h==null)
      {
          System.out.println("This Id NOT found:"+family_id);
      }
   }
   
   
   
   public static int addFamily()//This function can be aceesesd by manager to add family from Requests Queue list if Requests is null then show no family available
                                //else if total salary is zero then that family can't be added else add the first family(LIke QUEUE) to Society and print family id generated
   {
       if(Requests.isEmpty())
           print("Currently No Requests available...");
       else
       {
           Home temp=new Home();
           temp.electricity_bill=0;
           temp.grocery_bill=0;
           temp.telephone_bill=0;
           temp.m1=Requests.get(0)[0];
           temp.m2=Requests.get(0)[1];
           temp.m3=Requests.get(0)[2];
           temp.family_id=temp.m1.name.charAt(0)+society_counter++;//family_id is secret code which will be the ASCII value of First Character of First member of the family+Society Counter 
           Requests.remove(0);//when many requests arrive for manager, manager accepts first request and then removes the first request from the Requests array
           if((temp.m1.salary+temp.m2.salary+temp.m3.salary)==0)
           {
               print("\n\t This Family can't Be added to society Because of Insufficient Funds");
           }
           else
           {
               Home.add(temp);
               return temp.family_id;
           }
       }
       return -1;
   }
  
   
   public static void print(String s)//This is simple print just to avoid writing System.out.println again and again
   {
       System.out.println(s);
   }
   
   static int plans[]={15000,22000,5500,18500,11000};//These are predefined plans given in question
   
   
   static int society_counter=10000;//it is society counter
   
   static ArrayList<Home.Member[]>Requests=new ArrayList<>();//When any member sends his application the details of all members is kept in this List...later on First
                                                             //family of this list is added to Society by Manager
   
   public static void registerRequest()//A member can send application by this function it asks all details
   {
       print("\nFill Details For All Three members:");
       Home.Member[]members=new Home.Member[3];//As given in the question there are only 3 members in each family
       for(int i=0;i<3;i++)
       {
           LocalDate dob=LocalDate.now();//This has been done just to initilize and to avoid "not initilized "error
           Period p;
           int age=0;
           int salary;
           int sav_plan=-1;
           System.out.print("\n\tName of Member "+i+":");
           String name=sc.nextLine();
           try
           {
           System.out.println("\tEnter DOB (strictly in YYYY-MM-DD format):");
           dob=LocalDate.parse(sc.nextLine());
           p=dob.until(LocalDate.now());
           age=p.getYears();
           }
           catch(Exception e)
           {
               System.out.println(e);
               try
               {
                   System.out.println(e);
                   System.out.println("\tEnter DOB (strictly in YYYY-MM-DD format):");
                   dob=LocalDate.parse(sc.nextLine());
                   p=dob.until(LocalDate.now());
                   age=p.getYears();
               }
               catch(Exception f)
               {
                   print("You again Entered wrong...System will exit now");
                   System.exit(0);
               }
           }
           if(age<18)//if any member is less than 18 years by default his salary is zero
           {
               salary=0;
           }
           else
           {
                print("\n\tEnter Your Salary (in Rupees)");
                salary=Integer.parseInt(sc.nextLine());
                print("\nChoose Your Saving plan");
                boolean isAnyPlan=false;
                for(int j=0;j<plans.length;j++)
                {
                    if(plans[j]<(salary*0.75))//Here as he has to give 25% to family expenditure So he can choose plan accordingly
                    {
                        System.out.println("plan "+j+" :->>"+plans[j]);
                        isAnyPlan=true;
                    }
                }
                if(isAnyPlan)//if there is any plan for user then he can choose from that
                {
                    print("\n\tPlease select the corresponding key(numbers only):");
                    int key=Integer.parseInt(sc.nextLine());
                    if(key>5)
                    {
                        print("Invalid KEY....try again...");
                        key=Integer.parseInt(sc.nextLine());
                    }
                    if(plans[key]<salary*0.75)
                    {
                        sav_plan=key;
                    }
                }
                else//but if his salary is very less and he can't afoord any plan then he is not asked to enter plan number
                {
                    print("\nNo plans available for You");
                    sav_plan=100;//later on this will be handeled while alloting Available amount
                }
           }
       Home.Member ob=new Home.Member(salary,sav_plan,name,dob);
       members[i]=ob;
       }
   Requests.add(members);
   System.out.println("\tYour request has been registered\n\t You can get your family id from manager BY SHOWSTATUS METHOD:) ");
   }
   
   
   static void generateBill()//user can see his bill with family id
   {
       print("\n\tPlease Enter Family id");
       int fam_id=Integer.parseInt(sc.nextLine());
       Home h=Home.search(fam_id);
       if(h!=null)
       h.Bill();
       else
       {
          print("\tcould not generate bill");
       }
   }
   
   static void familyDetails()//It will print all details of family
   {
       System.out.print("\n\t PLease enter your Family id:");
       int fam_id=Integer.parseInt(sc.nextLine());
       Home h=Home.search(fam_id);
       if(h==null)
       {
           System.out.println("NO SUCH FAMILY ID FOUND");
       }
       else
       {
           System.out.println("\n\t\tFamily Id "+h.family_id);
           System.out.println("\n\t\tFamily Details");
           
           System.out.println("\n\tS. no\tNAME\t\tSALARY(in ruppes)\tSaving plan\tDate OF Birth\t\tAmount Available");
           if(h.m1.salary==0)
           {
               System.out.println("\t1:"+h.m1.name+"\t"+"Not Applicable"+"\tNot Applicable\t"+h.m1.dob+"\t\t"+h.m1.Available_amount);
           }
           else
           {
               System.out.println("\t1:"+h.m1.name+"\t"+h.m1.salary+"\t"+h.m1.saving_plan+"\t\t"+h.m1.dob+"\t\t"+h.m1.Available_amount);
           }
           if(h.m2.salary==0)
           {
               System.out.println("\t2:"+h.m2.name+"\t"+"Not Applicable"+"\tNot Applicable\t"+h.m2.dob+"\t\t"+h.m2.Available_amount);
           }
           else
           {
               System.out.println("\t2:"+h.m2.name+"\t\t"+h.m2.salary+"\t\t"+h.m2.saving_plan+"\t\t"+h.m2.dob+"\t\t"+h.m2.Available_amount);
           }
           if(h.m3.salary==0)
           {
               System.out.println("\t3:"+h.m3.name+"\t"+"Not Applicable"+"\tNot Applicable\t"+h.m3.dob+"\t\t"+h.m3.Available_amount);
           }
           else
           {
               System.out.println("\t3:"+h.m3.name+"\t\t"+h.m3.salary+"\t\t"+h.m3.saving_plan+"\t\t"+h.m3.dob+"\t\t"+h.m3.Available_amount);
           }
           System.out.println("\t\tTotal Bill(telephone, grocery And electricity bill):"+(h.electricity_bill+h.grocery_bill+h.telephone_bill));
           double total_exp=(h.m1.Available_amount+h.m2.Available_amount+h.m3.Available_amount)*0.75;
           System.out.println("\t Total Expenditure Available:"+total_exp);
       }
   }
   static void payBill()//This function enables user to pay his bill
   {
       System.out.print("\tEnter Your Family Id:");
       int fam_id=Integer.parseInt(sc.nextLine());
       Home h=Home.search(fam_id);//search function return Home h of given family_id
       if(h==null)
       {
           print("Sorry ! This id not found");
       }
       else
       {
           double mybill=h.getBill();//getbill function returns total bill 
           double total_exp;
           System.out.println("\n\tTotal Bill:"+mybill);
           if(mybill>0)
           { 
            total_exp=(h.m1.salary+h.m2.salary+h.m3.salary)*0.25;//calculating the total expenditur which is 25 % of each member's salary;
            System.out.println("Your Total Expenditure is:"+total_exp);
            if(total_exp>=mybill)//now if expenditure is more than total bill they can pay easily
            {
                print("\tPress 1 To Pay\n\tPress 2 to exit");
                int choice=Integer.parseInt(sc.nextLine());
                if(choice==1)
                {
                    h.acceptPayment(total_exp,mybill);//this function will accept payment
                }
            }
            else if(total_exp<mybill)
            {
                h.unableToBear(total_exp,mybill);//This Function Prints all members who are unable to bear Family Expenditure.
                Home.Member m=h.adjust(total_exp,mybill);//This Function will adjust salary,Savings,Expenditure so that Bill could be paid.It return a member who will 
                                                        //pay extra balance aprt from expenditure
                h.acceptPayment(total_exp,mybill,m);//this is overloaded function which pays bill
            }
            
           }
           else if(mybill==0)//it total bill is zero it means they have paid bill already
           {
               System.out.println("\n\tBill Already Paid");
           }
          
           
       }
   }
   public static void main(String...arg)
    {
        
        //There are two types of users one is member and other is manager
        print("\n\t\tWELCOME TO HOME MANAGEMENT SYSTEM");
        int choice=0;
        while(choice<3)
        {
        print("\n\tCHOOSE OPTION-\n\t1.Home Mangement System Member\n\t2.Home Mangement System Manager");
        choice=Integer.parseInt(sc.nextLine());
        switch(choice)
        {
            case 1:
                print("\n select option");
                print("\n\t1.Register Yourself\n\t2.Get My Bill\n\t3.My Family Details\n\t4.Pay Bill\n\t5.EXIT");
                int ch=Integer.parseInt(sc.nextLine());
                switch(ch)
                {
                    case 1:registerRequest();break;//A family can't add themselves in society they will fill their details and put their application in request queue
                    case 2:getMyBill();break;//this function asks family id and shows them their total bill
                    case 3:familyDetails();break;//it will show family details 
                    case 4:payBill();break;//Family member can pay his BILL
                    default:print("\n\t\tTHANKYOU");break;
                }
                break;
            case 2:
                
               
                print("\n\t WELCOME SAURAV....\nEnter Your password..(password is rt@13)");
                String password=sc.nextLine();
                if(password.equals("rt@13"))
                {
                    print("\nselect option");
                    print("\n\t1.add new Family \n\t2.Generate Bill\n\t3.Show status\n\t4.EXIT");
                    int cho=Integer.parseInt(sc.nextLine());
                    switch(cho)
                    {
                        case 1:{
                                int fam_id=addFamily();
                                if(fam_id!=-1)
                                {
                                    System.out.println("\n\t\tSuccessfully Added...Family Id is:"+fam_id+"\n\tPlease note this id for future reference");
                                }
                                else
                                {
                                    System.out.println("\n\tSome Error Occured");
                                }
                                break;
                                }
                        case 2:generateBill();break;
                        case 3:Home.showStatus();break;
                        case 4:break;
                        
                       
                    }
                    
                }
                else
                {
                    System.out.println("Your password is incorrect");
        
                }
                
            default:
                print("\t\t\tThankyou");
        }
    }
    }
}
