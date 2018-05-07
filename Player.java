import java.io.BufferedReader;
import java.io.IOException;

/**  Cave Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac CaveGame.java
     To run:     java CaveGame

     The main routine is CaveGame.main
				    
**/


public class Player {

  private Room myLoc;

  private Item[] myThings = new Item[2];

  private int itemCount = 0;

  public void setRoom(Room r){
   myLoc = r;
   }

  public String look() {
   return myLoc.getDesc();
   }

  public void go(int direction){
     myLoc.exit(direction,this);
  }

  public void pickUp(Item i){
   if (itemCount < 2) {
      myThings[itemCount] = i;
      itemCount++;
      myLoc.removeItem(i);
      }
  }

  public boolean haveItem(Item itemToFind){
     for (int n = 0; n < itemCount ; n++)
       if (myThings[n] == itemToFind) return true;
     return false;
  }

  public void drop(int itemNum){
   if (itemNum > 0 & itemNum <= itemCount){
      switch(itemNum){
      case 1: { myLoc.addItem(myThings[0]);
	        myThings[0]=myThings[1];
	        itemCount--; 
	        break;
	      }
      case 2: { myLoc.addItem(myThings[1]);
		itemCount--;
		break;
	      } 
      }
   }
   }

  public void setLoc(Room r){myLoc = r;}

  public Room getLoc(){return myLoc;}

  public String showMyThings(){
   String outString = "";
   for (int n = 0; n < itemCount ; n++)
     outString = outString + Integer.toString(n+1) + ": " 
       + myThings[n].getDesc() + " ";
   return outString;
  }

  public boolean handsFull(){return itemCount==2;}

  public boolean handsEmpty(){return itemCount==0;}

  public int numItemsCarried(){return itemCount;}

/**
 * chooseDropItem  determines the specific item that a player wants to drop 
 */
public int chooseDropItem(BufferedReader keyB) throws IOException {
	String inputString = "prepare";
	int theChoice = -1;
	do {
		System.out.println("You are carrying: " + showMyThings() + '\n');
		System.out.print("Enter the number of the item to drop: ");
		inputString = keyB.readLine();
		try {
			theChoice = Integer.parseInt(inputString);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
			theChoice = -1;
		}
		if (theChoice < 0 || theChoice > numItemsCarried())
			System.out.print("Invalid choice.");
	} while (theChoice < 0 || theChoice > numItemsCarried());
	return theChoice;
}

}
