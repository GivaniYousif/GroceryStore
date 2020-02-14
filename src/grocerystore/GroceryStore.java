/*
Course:        CS-182
Project:       Grocery Register
Name:          Givani Yousif
Description:   This application will prompt the user for different types of 
               products and product types.  It will compile a sub-total of
               the items, as well as count the number of items purchased.
*/

package grocerystore;

import java.util.Scanner;
import java.text.NumberFormat;

public class GroceryStore 
{
   enum ProductType { FOOD, ALCOHOL, MERCHANDISE, OTHER }
   
   public static void main(String[] args) 
   {
      Scanner        keyboard = new Scanner( System.in );
      String         productName;
      String         productType;
      char           productChar;
      ProductType    product;
      int            itemCount, foodCount = 0, alcoholCount = 0, merchandiseCount = 0;
      float          unitCost;
      double         itemCost;
      double         netReceipt = 0.0, grossReceipt = 0.0;
      double         itemTaxes = 0.0, grossTaxes = 0.0;
      NumberFormat   formatter = NumberFormat.getCurrencyInstance();
      String         itemString = "", netString = "", taxString = "", grossString = "";
      
      System.out.println( "Grocery Register" );
      System.out.print( "Enter Product Name (Quit to Exit): " );
      productName = keyboard.nextLine();
      
      /* 
         Determine if the ProductName entered was either "Quit" or there was
         nothing entered -> Exit.  If product name was entered, the loop 
         will be entered.
      */
      while (( !productName.equalsIgnoreCase( "QUIT" )) && ( productName.length() != 0 ))
      {
         System.out.print( "Product Type [A]lcohol, [F]ood, [M]erchandise]: " );
         productType = keyboard.nextLine();
         productChar = productType.charAt(0);
         
         // Review Product Type entered, set Enumerated Type value 
         if (( productType.equalsIgnoreCase( "FOOD" )) || ( productChar == 'f' ) || ( productChar == 'F' ))
         {
            product = ProductType.FOOD;
         }
         else if (( productType.equalsIgnoreCase( "ALCOHOL" )) || ( productChar == 'a' ) || ( productChar == 'A' ))
         {
            product = ProductType.ALCOHOL;
         }
         else if (( productType.equalsIgnoreCase( "MERCHANDISE" )) || ( productChar == 'm' ) || ( productChar == 'M' ))
         {
            product = ProductType.MERCHANDISE;
         }
         else
         {
            product = ProductType.OTHER;
         }

         // Prompt for Quantity of Product's purchased
         System.out.print( "[" + productName + "] Quantity   : " );
         itemCount = keyboard.nextInt();
         
         // Prompt for Individual price of Product purchased
         System.out.print( "[" + productName + "] Unit Price : " );
         unitCost = keyboard.nextFloat();
         
         itemCost = itemCount * unitCost;
         
         switch( product )
         {
            case FOOD:
               // Food Products are Tax Exempt 
               foodCount += itemCount;
               itemTaxes = 0;
            break;
               
            case ALCOHOL:
               // Alcohol Products require a 12% Tax 
               alcoholCount += itemCount;
               itemTaxes = itemCost * 0.12;
            break;
               
            case MERCHANDISE:
               // Merchandise Products require a 7% Tax 
               merchandiseCount += itemCount;
               itemTaxes = itemCost * 0.07;
            break;
               
            default:
               System.out.println( "Invalid Product Type");
         }
         
         // Add up Sub-Total, to display 
         netReceipt += itemCost;
         grossTaxes += itemTaxes;
         
         itemString = formatter.format( itemCost );
         netString = formatter.format( netReceipt );
         
         // Output Current Product Information, plus current Sub-Total
         System.out.println( productName + " [Qty:" + itemCount + "]     Price: " + itemString + "      {Sub-Total: " + netString + "}" );

         
         // Prompt for the next Product 
         keyboard.nextLine();                            // Clear Input Buffer 
         System.out.print( "\nEnter Product Name: " );
         productName = keyboard.nextLine();
      }
      
      // Output Number of Items
      taxString = formatter.format( grossTaxes );
      grossString = formatter.format( netReceipt + grossTaxes );
      
      System.out.println( "*** Customer Receipt ***" );
      System.out.println( "Number of Food Items       : " + foodCount );
      System.out.println( "Number of Alcohol Items    : " + alcoholCount );
      System.out.println( "Number of Merchandise Items: " + merchandiseCount );
      System.out.println( "Net Grocery Receipt        : " + netString );
      System.out.println( "Gross Taxes                : " + taxString );
      System.out.println( "Gross Grocery Receipt      : " + grossString );
   }   
}