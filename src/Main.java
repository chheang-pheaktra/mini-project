import Stock.ManageStock;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<ManageStock> product =new ArrayList<>();
        Scanner sr = new Scanner(System.in);
        int CurrentPage=1;
        int RowPage=2;
        product.add(new ManageStock (1,"Angkor Water", 50,1000.00, LocalDate.now()) );
        product.add(new ManageStock(2,"Cambodia beer", 50,2500.00, LocalDate.now()) );
        product.add(new ManageStock(3,"CoCa", 50,2000.00, LocalDate.now()) );
        product.add(new ManageStock(4,"Pepsi", 50,2000.00, LocalDate.now()) );
        product.add(new ManageStock(5,"Orange Juice", 50,1500.00, LocalDate.now()) );
        product.add(new ManageStock(6,"Sting", 50,2000.00, LocalDate.now()) );
        product.add(new ManageStock(7,"Hanuman", 50,2500.00, LocalDate.now()) );
        product.add(new ManageStock(8,"Angkor beer", 50,2000.00, LocalDate.now()) );

        System.out.println("\n" +
                " ██████╗███████╗████████╗ █████╗ ██████╗          ██╗ █████╗ ██╗   ██╗ █████╗     \n" +
                "██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗         ██║██╔══██╗██║   ██║██╔══██╗    \n" +
                "██║     ███████╗   ██║   ███████║██║  ██║         ██║███████║██║   ██║███████║    \n" +
                "██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║    \n" +
                "╚██████╗███████║   ██║   ██║  ██║██████╔╝    ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║    \n" +
                " ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝      ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝    \n" +
                "                                                                                  \n");

        System.out.println("#".repeat(10)+"Stock Management System".toUpperCase()+"#".repeat(10)+"\n");
       do {
           Table table =new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
           table.addCell(" ".repeat(2)+"| *)Display"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| W)rite"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| R)ead"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| U)pdate"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| D)elete"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| F)irst"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| P)revious"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| N)ext"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| L)ast"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| S)earch"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| G)o to"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| Se)t row"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| H)elp"+" ".repeat(2));
           table.addCell(" ".repeat(2)+"| E)xit"+" ".repeat(2));
           System.out.println(table.render());
           System.out.println();
           System.out.print("Command ->\t");
           String options = sr.nextLine();
           List<ManageStock> productList = null;
           switch (options) {
               case "*" -> display(product, CurrentPage,RowPage);
               case "w", "W" -> write(product);
               case "r", "R" -> read(product);
               case "u", "U" -> update(product);
               case "d", "D" -> delete(productList);
               case "f", "F" -> CurrentPage = first(CurrentPage, RowPage, product);
               case "p", "P" -> CurrentPage = previous(CurrentPage, RowPage, product);
               case "n", "N" -> CurrentPage = next(CurrentPage, RowPage, product);
               case "l", "L" -> CurrentPage = last(CurrentPage, RowPage, product);
               case "s", "S" -> search(product, CurrentPage, RowPage);
               case "g", "G" -> CurrentPage = goTo(CurrentPage, RowPage, product);
               case "se", "Se" -> RowPage = setRow(RowPage,product);
               case "h", "H" -> help();
               case "e", "E" -> {
                   System.exit(0);
               }
               default -> System.err.println("Invalid Option");
           }
       }while (true);

    }
    public static void display(List<ManageStock>product,int CurrentPage,int RowPage){
        int FirstIndex = (CurrentPage-1)*RowPage;
        int LastIndex=Math.min(FirstIndex+RowPage,product.size());
        Table DisplayTable =new Table(5,BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        DisplayTable.addCell(" ".repeat(2)+"ProductID"+" ".repeat(2));
        DisplayTable.addCell(" ".repeat(2)+"ProductName"+" ".repeat(2));
        DisplayTable.addCell(" ".repeat(2)+"ProductPrice"+" ".repeat(2));
        DisplayTable.addCell(" ".repeat(2)+"Qty"+" ".repeat(2));
        DisplayTable.addCell(" ".repeat(2)+"Date"+" ".repeat(2));
        for(int i= FirstIndex; i<LastIndex;i++){
            ManageStock products = product.get(i);
            DisplayTable.addCell(" ".repeat(2)+products.getProductID().toString());
            DisplayTable.addCell(" ".repeat(2)+products.getNameProduct());
            DisplayTable.addCell(" ".repeat(2)+products.getProductPrice().toString());
            DisplayTable.addCell(" ".repeat(2)+products.getQty().toString());
            DisplayTable.addCell(" ".repeat(2)+products.getDateOfProduct().toString());

        }
        System.out.println(DisplayTable.render());
        System.out.println("*".repeat(60));
        int PageTotal =(int)Math.ceil((double)product.size()/RowPage);
        System.out.println("Page"+CurrentPage+"Of"+PageTotal+" ".repeat(40)+"Toatal Record"+product.size());
    }

   public static  void write(List<ManageStock>product){
        ManageStock LastProduct =product.get(product.size()-1);
        Integer ProductID = LastProduct.getProductID()+1;

        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("ProductID : "+ProductID);
            System.out.print("ProductName: ");
            String ProductName = sc.nextLine();
            System.out.println("Product Price: ");
            Double ProductPrice =Double.parseDouble(sc.nextLine());
            System.out.println("Qty : ");
            Integer Qty = Integer.parseInt(sc.nextLine());

            ManageStock stock =new ManageStock(ProductID,ProductName,Qty,ProductPrice,LocalDate.now());
            product.add(stock);
            do {
                Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                table.addCell(" ID            : "+ProductID+" ".repeat(10));
                table.addCell(" Name          : "+ProductName+" ".repeat(10));
                table.addCell(" Unit price    : "+ProductPrice+" ".repeat(10));
                table.addCell(" Qty           : "+Qty+" ".repeat(10));
                table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                System.out.println(table.render());
                System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                String options = sc.nextLine();
                switch (options) {
                    case "y","Y" -> {
                        System.out.println("Product added successfully.");
                        return;
                    }
                    case "n","N" -> {
                        product.remove(stock);
                        System.out.println("Product is not added");
                        return;
                    }
                    default -> System.out.println("Invalid options.");
                }
            }while (true);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
   }
   public static void read(List<ManageStock> productList){
        Scanner sc = new Scanner(System.in);
        boolean IsFound = false;
        try {
            System.out.println("Read By ID: ");
            Integer Id= Integer.parseInt(sc.nextLine());
            for (ManageStock product : productList){
                if (product.getProductID().equals(Id)){
                    Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
                    table.addCell("ID              : "+Id);
                    table.addCell("Name            : "+product.getNameProduct());
                    table.addCell("Price           : "+product.getProductPrice());
                    table.addCell("Qty             : "+product.getQty());
                    table.addCell("Date            : "+LocalDate.now());
                    IsFound=true;
                            break;
                }
                System.out.println("Product with ID: " + Id + " Not Found");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
   }
   public static void update(List<ManageStock> productlist) {
       Scanner sc = new Scanner(System.in);
       boolean IsFound = false;
       try {
           System.out.println("Input ID For Update: ");
           Integer Id = Integer.parseInt(sc.nextLine());
           for (ManageStock product : productlist) {
               if (product.getProductID().equals(Id)) {
                   Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
                   table.addCell("ID              : " + Id);
                   table.addCell("Name            : " + product.getNameProduct());
                   table.addCell("Price           : " + product.getProductPrice());
                   table.addCell("Qty             : " + product.getQty());
                   table.addCell("Date            : " + LocalDate.now());
                   IsFound = true;
                   break;
               }
               if (!IsFound) {
                   System.out.println(" Product That Update With Id" + Id + "Is Not Found!");
               }
               ManageStock productUpdate = null;
               for (ManageStock Product : productlist) {
                   if (Product.getProductID().equals(Id)) {
                       productUpdate = Product;
                       IsFound = true;
                       break;
                   }
               }
               if (!IsFound) {
                   System.out.println("Product With ID:" + Id + "is found");
                   return;
               }
               ManageStock Product = productUpdate;
               System.out.println("What do you want to update?");
               Table tableToUpdate = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.SURROUND);
               tableToUpdate.addCell(" ".repeat(2) + "1. All" + " ".repeat(2));
               tableToUpdate.addCell(" ".repeat(2) + "2. Name" + " ".repeat(2));
               tableToUpdate.addCell(" ".repeat(2) + "3. Quantity" + " ".repeat(2));
               tableToUpdate.addCell(" ".repeat(2) + "4. Price" + " ".repeat(2));
               tableToUpdate.addCell(" ".repeat(2) + "5. Back to menu" + " ".repeat(2));
               System.out.println(tableToUpdate.render());
               try {
                   System.out.print("Choose option (1-5) : ");
                   int op = Integer.parseInt(sc.nextLine());
                   switch (op) {
                       case 1 -> {
                           try {
                               System.out.print("Enter new product name: ");
                               String newProductName = sc.nextLine();
                               System.out.print("Enter new quantity: ");
                               Integer newQuantity = Integer.parseInt(sc.nextLine());
                               System.out.print("Enter new price: ");
                               Double newPrice = Double.parseDouble(sc.nextLine());

                               product.setNameProduct(newProductName);
                               product.setQty(newQuantity);
                               product.setProductPrice(newPrice);

                               Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                               updatedTable.addCell(" ID            : " + Id + " ".repeat(10));
                               updatedTable.addCell(" Name          : " + newProductName + " ".repeat(10));
                               updatedTable.addCell(" Unit price    : " + newPrice + " ".repeat(10));
                               updatedTable.addCell(" Qty           : " + newQuantity + " ".repeat(10));
                               updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                               System.out.println(updatedTable.render());

                               System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                               String options = sc.nextLine();
                               switch (options) {
                                   case "y", "Y" -> {
                                       productlist.add(productUpdate);
                                       System.out.println("Product added successfully.");
                                   }
                                   case "n", "N" -> System.out.println("Product is not added");
                                   default -> System.out.println("Invalid options.");
                               }
                           } catch (Exception exception) {
                               System.out.println(exception.getMessage());
                           }
                       }


                       case 2 -> {
                           try {
                               System.out.println("Enter new product name : ");
                               String newProductName = sc.nextLine();
                               product.setNameProduct(newProductName);
                               Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                               updatedTable.addCell(" ID            : " + Id + " ".repeat(10));
                               updatedTable.addCell(" Name          : " + newProductName + " ".repeat(10));
                               updatedTable.addCell(" Unit price    : " + product.getProductPrice()
                                       + " ".repeat(10));
                               updatedTable.addCell(" Qty           : " + product.getQty() + " ".repeat(10));
                               updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                               System.out.println(updatedTable.render());
                               System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                               String options = sc.nextLine();
                               switch (options) {
                                   case "y", "Y" -> {
                                       productlist.add(productUpdate);
                                       System.out.println("Product added successfully.");
                                   }
                                   case "n", "N" -> System.out.println("Product is not added");
                                   default -> System.out.println("Invalid options.");
                               }
                           } catch (Exception exception) {
                               System.out.println(exception.getMessage());
                           }
                       }
                       case 3 -> {
                           try {
                               System.out.println("Enter new product Price : ");
                               Double newProductPrice = Double.parseDouble(sc.nextLine());
                               product.setProductPrice(newProductPrice);
                               Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                               updatedTable.addCell(" ID            : " + Id + " ".repeat(10));
                               updatedTable.addCell(" Name          : " + product.getNameProduct() + " ".repeat(10));
                               updatedTable.addCell(" Unit price    : " + newProductPrice + " ".repeat(10));
                               updatedTable.addCell(" Qty           : " + product.getQty() + " ".repeat(10));
                               updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                               System.out.println(updatedTable.render());
                               System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                               String options = sc.nextLine();
                               switch (options) {
                                   case "y", "Y" -> {
                                       productlist.add(productUpdate);
                                       System.out.println("Product added successfully.");
                                   }
                                   case "n", "N" -> System.out.println("Product is not added");
                                   default -> System.out.println("Invalid options.");
                               }
                           } catch (Exception exception) {
                               System.out.println(exception.getMessage());
                           }
                       }
                       case 4 -> {
                           try {
                               System.out.println("Enter new product Price : ");
                               Integer newProductQty = Integer.parseInt(sc.nextLine());
                               product.setQty(newProductQty);
                               Table updatedTable = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND);
                               updatedTable.addCell(" ID            : " + Id + " ".repeat(10));
                               updatedTable.addCell(" Name          : " + product.getNameProduct() + " ".repeat(10));
                               updatedTable.addCell(" Unit price    : " + product.getProductPrice() + " ".repeat(10));
                               updatedTable.addCell(" Qty           : " + newProductQty + " ".repeat(10));
                               updatedTable.addCell(" Imported Date : " + LocalDate.now() + " ".repeat(10));
                               System.out.println(updatedTable.render());
                               System.out.print("Are you sure to add this record? [Y/y] or [N/n] : ");
                               String options = sc.nextLine();
                               switch (options) {
                                   case "y", "Y" -> {
                                       productlist.add(productUpdate);
                                       System.out.println("Product added successfully.");
                                   }
                                   case "n", "N" -> System.out.println("Product is not added");
                                   default -> System.out.println("Invalid options.");
                               }
                           } catch (Exception e) {
                               System.out.println(e.getMessage());
                           }
                       }
                       case 5 -> System.out.println("Back to menu : ");
                       default -> throw new IllegalStateException("Unexpected value: " + op);
                   }
               } catch (Exception e) {
                   System.out.println(e.getMessage());
               }

           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
   }
   public static void delete(List<ManageStock> productList){
       Scanner scanner = new Scanner(System.in);
       try {
           System.out.print("Enter the ID of the product you want to delete: ");
           Integer productId = Integer.parseInt(scanner.nextLine());
           for (ManageStock product : productList) {
               if (product.getProductID().equals(productId)) {
                   Table table = new Table(1,BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.SURROUND);
                   table.addCell(" ID            : "+product.getProductID()+" ".repeat(10));
                   table.addCell(" Name          : "+product.getNameProduct()+" ".repeat(10));
                   table.addCell(" Unit price    : "+product.getProductPrice()+" ".repeat(10));
                   table.addCell(" Qty           : "+product.getQty()+" ".repeat(10));
                   table.addCell(" Imported Date : "+LocalDate.now()+" ".repeat(10));
                   System.out.println(table.render());
                   System.out.print( "Are you sure to add this record? [Y/y] or [N/n] : ");
                   String options = scanner.nextLine();
                   switch (options) {
                       case "y","Y" -> {
                           productList.remove(product);
                           System.out.println("Product with ID " + productId + " deleted successfully.");
                           return;
                       }
                       case "n","N" -> {
                           System.out.println("Product is not deleted");
                           return;
                       }
                       default -> System.out.println("Invalid options");
                   }
               }
           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
   }
   public static int first(int CurrentPage, int RowPage, List<ManageStock>productlist){
        if (CurrentPage==1){
            System.out.println("You are on first Page");
        }else {
            CurrentPage=1;
            display(productlist,CurrentPage,RowPage);
        }
        return CurrentPage;
   }
   public static int previous(int currentpage, int rowpage,List<ManageStock>productlist){
        if (currentpage>1){
            currentpage--;
        }
        return currentpage;
   }
   public static int next(int currentpage,int rowpage,List<ManageStock>productlist){
        int totalpage=(int)Math.ceil((double) productlist.size()/rowpage);
        if(currentpage<totalpage){
            currentpage++;
            display(productlist,currentpage,rowpage);
        }
        return currentpage;
   }
   public static int last(int CurrentPage,int RowPage,List<ManageStock>productlist){
        int totalpage =(int) Math.ceil((double) productlist.size()/RowPage);
        if(CurrentPage==totalpage){
            System.out.println("You Are On The Last Page");
        }
        else {
            CurrentPage=totalpage;
            display(productlist,CurrentPage,RowPage);
        }
        return CurrentPage;
   }
   public static void search(List<ManageStock>productlist,int CurrentPage,int RowPage){
       Scanner scanner = new Scanner(System.in);
       System.out.print("Search product by keyword: ");
       String searchKeyword = scanner.nextLine().toLowerCase();

       List<ManageStock> MatchingProducts = new ArrayList<>();

       for (ManageStock product : productlist) {
           String productName = product.getNameProduct().toLowerCase();

           if (productName.contains(searchKeyword)) {
               MatchingProducts.add(product);
           }
       }
       int totalPages = (int) Math.ceil((double) MatchingProducts.size() / RowPage);
       if (MatchingProducts.isEmpty()) {
           System.out.println("No products found containing the keyword '" + searchKeyword + "'.");
       } else {
           if (CurrentPage < 1) {
               CurrentPage = 1;
           } else if (CurrentPage > totalPages) {
               CurrentPage = totalPages;
           }

           int startIndex = (CurrentPage - 1) * RowPage;
           int endIndex = Math.min(startIndex + RowPage, MatchingProducts.size());

           Table tableDisplay = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
           tableDisplay.addCell(" ".repeat(2) + "ID" + " ".repeat(2));
           tableDisplay.addCell(" ".repeat(2) + "Name" + " ".repeat(2));
           tableDisplay.addCell(" ".repeat(2) + "Unit Price" + " ".repeat(2));
           tableDisplay.addCell(" ".repeat(2) + "Qty" + " ".repeat(2));
           tableDisplay.addCell(" ".repeat(2) + "Date" + " ".repeat(2));

           for (int i = startIndex; i < endIndex; i++) {
               ManageStock product = MatchingProducts.get(i);
               tableDisplay.addCell(" ".repeat(2) + product.getProductID().toString());
               tableDisplay.addCell(" ".repeat(2) + product.getNameProduct());
               tableDisplay.addCell(" ".repeat(2) + product.getProductPrice().toString());
               tableDisplay.addCell(" ".repeat(2) + product.getQty().toString());
               tableDisplay.addCell(" ".repeat(2) + product.getDateOfProduct().toString());
           }

           System.out.println(tableDisplay.render());
           System.out.println("~ ".repeat(60));

           System.out.println("Page " + CurrentPage + " of " + totalPages + " ".repeat(40) + "Total matching products: " + MatchingProducts.size());
           System.out.println("~ ".repeat(60));
       }
   }
   public static int goTo(int CurrentPage,int RowPage,List<ManageStock>productlist){
       Scanner scanner = new Scanner(System.in);
       System.out.print("Enter the page number you want to go to: ");

       int targetPage = scanner.nextInt();
       int totalPages = (int) Math.ceil((double) productlist.size() / RowPage);

       if (targetPage >= 1 && targetPage <= totalPages) {
           CurrentPage = targetPage;
           display(productlist, CurrentPage, RowPage);
       } else {
           System.out.println("Invalid page number. Please enter a page number between 1 and " + totalPages + ".");
       }
       return CurrentPage;
   }
   public static int setRow(int RowPage,List<ManageStock>productlist){
       Scanner sc = new Scanner(System.in);
       try {
           System.out.print("Enter number of row(s) you want to display : ");
           int numberOfRows = Integer.parseInt(sc.nextLine());
           if (numberOfRows >0 && numberOfRows <= productlist.size()){
               return numberOfRows;
           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       return RowPage;
   }
   public static void help(){
       Table tableShowHelp = new Table(1,BorderStyle.CLASSIC_COMPATIBLE_LIGHT_WIDE,ShownBorders.SURROUND);
       tableShowHelp.addCell("1. \tPress\t * : Display all record of products");
       tableShowHelp.addCell("2. \tPress\t w : Add new product");
       tableShowHelp.addCell("   \tPress\t w ->#proName-unitPrice-qty : shortcut to add new product");
       tableShowHelp.addCell("3. \tPress\t r : Read Content any content");
       tableShowHelp.addCell("   \tPress\t r ->#proID : shortcut to read product by ID");
       tableShowHelp.addCell("4. \tPress\t u : Update data");
       tableShowHelp.addCell("5. \tPress\t d : Delete data");
       tableShowHelp.addCell("   \tPress\t d ->#proID : shortcut to delete product by ID");
       tableShowHelp.addCell("6. \tPress\t f : Display first page");
       tableShowHelp.addCell("7. \tPress\t p : Display previous page");
       tableShowHelp.addCell("8. \tPress\t n : Display next page");
       tableShowHelp.addCell("9. \tPress\t l : Display last page");
       tableShowHelp.addCell("10.\tPress\t s : Search product by name");
       tableShowHelp.addCell("11.\tPress\t h : Help");
       System.out.println(tableShowHelp.render());
   }
}