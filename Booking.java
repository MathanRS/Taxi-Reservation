import java.util.*;
public class Booking
{
   public static void bookTaxi (int customerID,char pickupPoint,char dropPoint,int pickupTime,List<Taxi> freeTaxis)
   {
      int min =999;                                        //To find nearest
      int distanceBetweenpickUpandDrop  = 0;               //distance between pickup and drop 
      int earning=0;                                       //This trip earnings 
      int nextfreeTime =0;                                  //when Taxi will be free next 
      char nextSpot='Z';                                   //where Taxi trip over
      Taxi bookedTaxi =null;                               //booked Taxi
      String tripDetail="" ;                               //Alldetails of current trip as string
   
   for(Taxi t : freeTaxis)
   {
      int distanceBetweenCustomerAndTaxi =Math.abs((t.currentSpot-'0')-(pickupPoint - '0'))*15;
      if(distanceBetweenCustomerAndTaxi <min)
      {
         bookedTaxi =t;
         //distance between pickup and drop =(drop -pickup)*15km
         distanceBetweenpickUpandDrop=Math.abs((dropPoint - '0')- (pickupPoint - '0'))*15; 
         //trip earning =100 + (distanceBetweenpickUpandDrop - 5)*10
         earning =(distanceBetweenpickUpandDrop-5)*10 +100;

         // drop time calculation 
         int dropTime =pickupTime + distanceBetweenpickUpandDrop/15;

         //when Taxi will be free next
         nextfreeTime = dropTime ;
         //Taxi will be at drop point after trip
         nextSpot = dropPoint;
         //creating trip detail
         tripDetail =customerID + "             " + customerID + "            "+pickupPoint +"         "+dropPoint + "   "+pickupTime+ "       " + dropTime+"          "+ earning+ "      ";
         min=distanceBetweenCustomerAndTaxi;
      }
   }
   //setting corressponding details to allotted Taxi 
   bookedTaxi.setDetails (true,nextSpot,nextfreeTime,bookedTaxi.totalEarnings +earning,tripDetail);
   // Booked SUCCESSFULLY
   System.out.println("Taxi" + bookedTaxi.id+"booked");
}
   public static List<Taxi> createTaxis(int n)
   {
      List <Taxi> Taxis =new ArrayList<Taxi> ();
      //create Taxis
      for(int i=1; i<=n;i++)
      {
         Taxi t=new Taxi ();
         Taxis.add(t);
      }
      return Taxis ;
   }
   public static List <Taxi>getFreeTaxis (List<Taxi> Taxis,int pickupTime,char pickupPoint)
   {
      List <Taxi> freeTaxis =new ArrayList <Taxi> ();
      for(Taxi t:Taxis)
      {
         //Taxi should be free
         //Taxi should have enough time to reach customer before pickuptime
         if(t.freeTime <= pickupTime && (Math.abs((t.currentSpot-'0')-(pickupPoint -'0'))<=pickupTime - t.freeTime))
         freeTaxis.add(t);
      }
      return freeTaxis;
   }

   public static void main(String args[])
   {
      //create 4 Taxi's
      List <Taxi> Taxis=createTaxis(4);

      Scanner s =new Scanner(System.in);   
      int id=1;     //Starting at customer  id=1 ;
  
   while(true)
   {
      System.out.println("0--> Book Taxi");
      System.out.println("1--> Print Taxi details");
      int choice=s.nextInt();
      switch (choice)
      {
         case 0:
         {
            //get Deatails from Customer
            int customerID =id;
            System.out.println("Enter pickup point");
            char pickupPoint =s.next().charAt(0);
            System.out.print("Enter Drop point ");
            char dropPoint =s.next().charAt(0);
            System.out.println("Enter Pickup time");
            int pickupTime =s.nextInt();

            //check if pickup and drop points are valid 
            if(pickupPoint <'A' || dropPoint <'A' || pickupPoint >'F'|| dropPoint>'F')
            {
               System.out.println("Please choose valid A to B");
               return ;
            }
            List <Taxi> freeTaxis =getFreeTaxis(Taxis,pickupTime,pickupPoint);
            if(freeTaxis.size()==0)
            {
               System.out.println("No Taxi can be allotted.Exitting");
               return;
            }
            //sort Taxis based on sortings 
            Collections.sort(freeTaxis,(a,b)-> a.totalEarnings - b.totalEarnings);

            //get free Taxi nearest to us 
            bookTaxi (id,pickupPoint,dropPoint,pickupTime,freeTaxis);
            id++;
            break;
         }
         case 1: 
         {
            //Two functions to print details
            for(Taxi t: Taxis)
            t.printTaxiDetails();
            for(Taxi t: Taxis)
            t.printDetails();
         }
         default:
         return;

      }
   }
   }


}